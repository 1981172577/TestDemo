package com.tang.testdemo.moudel.cache;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

import static android.os.Environment.isExternalStorageRemovable;

/**
 * 简介：
 *
 * @author 王强（249346528@qq.com） 2017/9/18.
 */

public class SuperCacheImpl implements SuperCache {
    private static final String DEFAULT_CACHE_NAME = "super_cache";
    /**
     * for all 50 mb
     */
    private static final int MAX_DISK_CACHE_SIZE = 1024 * 1020 * 50;
    /**
     * for object 200个
     */
    private static final int MAX_OBJECT_COUNT = 200;
    /**
     * 内存缓存
     */
    private LruCache<String, Object> memLruCache;
    /**
     * SD卡缓存
     */
    private DiskLruCache mDiskLruCache;
    /**
     * 缓存路径
     */
    private String cacheDirPath;
    /**
     * 任务池
     */
    private ExecutorService writeExecutor =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public SuperCacheImpl(Context ctx) throws Exception {
        memLruCache = new LruCache<String, Object>(MAX_OBJECT_COUNT) {
            @Override
            protected int sizeOf(String key, Object value) {
                return super.sizeOf(key, value);
            }
        };
        cacheDirPath = getDiskCacheDirPath(ctx, DEFAULT_CACHE_NAME);
    }


    private String getDiskCacheDirPath(Context context, String uniqueName) {
        final String cachePath =
                Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) &&
                        !isExternalStorageRemovable() ? getExternalCacheDir(context).getPath() :
                        context.getCacheDir().getPath();

        return cachePath + File.separator + uniqueName;
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    private File getExternalCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            return context.getExternalCacheDir();
        }

        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    private DiskLruCache getDiskLruCache() {
        if (mDiskLruCache == null || mDiskLruCache.isClosed()) {
            setUpDiskLruCache();
        }
        return mDiskLruCache;
    }

    private void setUpDiskLruCache() {
        File cacheDir = new File(cacheDirPath);
        if (!cacheDir.exists() && !cacheDir.mkdirs()) {
            throw new RuntimeException("can't make dirs in "
                    + cacheDir.getAbsolutePath());
        }
        if (cacheDir.getUsableSpace() > MAX_DISK_CACHE_SIZE) {
            try {
                mDiskLruCache = DiskLruCache.open(cacheDir, 1, 1, MAX_DISK_CACHE_SIZE);
            } catch (IOException e) {
                throw new RuntimeException("Create LruCacheFail!");
            }
        } else {
            throw new RuntimeException("Do not have enugh disk space!");
        }
    }

    private String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    /**
     * @param bytes
     * @return
     */
    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 读取缓存文件内容到byte数组
     *
     * @param key
     * @return
     */
    private byte[] readContentByKey(String key) {
        final String cacheKey = hashKeyForDisk(key);
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            final DiskLruCache.Snapshot snapshot = getDiskLruCache().get(cacheKey);
            if (snapshot != null) {
                inputStream = snapshot.getInputStream(0);
                byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024 * 8];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }
                return byteArrayOutputStream.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // =======================================
    // ============ String数据 读写 ==============
    // =======================================

    /**
     * 保存字符串
     *
     * @param key   保存的Key
     * @param value 保存的String
     */
    public Editor put(String key, String value) {
        return new StringEditor(key, value);
    }

    @Override
    public synchronized String getAsString(String key) {
        String value = ((String) memLruCache.get(key));
        byte[] contentBytes = value != null ? value.getBytes() : readContentByKey(key);
        if (contentBytes == null) {
            return null;
        }
        return new String(contentBytes);
    }

    // =======================================
    // ============= JSONObject 数据 读写 ==============
    // =======================================

    /**
     * 保存 JSONObject数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的JSON数据
     */
    public void put(String key, JSONObject value) {
        put(key, value.toString());
    }


    /**
     * 读取JSONObject数据
     *
     * @param key
     * @return JSONObject数据
     */
    public JSONObject getAsJSONObject(String key) {
        String JSONString = getAsString(key);
        try {
            JSONObject obj = new JSONObject(JSONString);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // =======================================
    // ============ JSONArray 数据 读写 =============
    // =======================================

    /**
     * 保存 JSONArray数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的JSONArray数据
     */
    public void put(String key, JSONArray value) {
        put(key, value.toString());
    }

    /**
     * 读取JSONArray数据
     *
     * @param key
     * @return JSONArray数据
     */
    public JSONArray getAsJSONArray(String key) {
        String JSONString = getAsString(key);
        try {
            JSONArray obj = new JSONArray(JSONString);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // =======================================
    // ============== byte 数据 读写 =============
    // =======================================

    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的数据
     */
    public Editor put(String key, byte[] value) {
        return new ByteEditor(key, value);
    }

    /**
     * 获取 byte 数据
     *
     * @param key
     * @return byte 数据
     */
    public byte[] getAsBinary(String key) {
        Object object = memLruCache.get(key);
        byte[] value = null;
        try {
            if (object != null) {
//                S.i(key + ":lru中读取");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                oos.writeObject(object);
                oos.flush();
                oos.close();
                value = outputStream.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
            value = null;
        }
        return value != null ? value : readContentByKey(key);
    }

    // =======================================
    // ============= 序列化 数据 读写 ===============
    // =======================================

    /**
     * 保存 Serializable数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的value
     */
    @Override
    public ObjectEditor put(String key, Serializable value) {
        return new ObjectEditor(key, value);
    }

    /**
     * 读取 Serializable数据
     *
     * @param key
     * @return Serializable 数据
     */
    @Override
    public Serializable getAsObject(String key) {
        byte[] data = getAsBinary(key);
        if (data != null) {
            ByteArrayInputStream bais = null;
            ObjectInputStream ois = null;
            try {
                bais = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bais);
                Object reObject = ois.readObject();
                return ((Serializable) reObject);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    // =======================================
    // ============== bitmap 数据 读写 =============
    // =======================================

    /**
     * 保存 bitmap 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的 bitmap 数据
     */
    @Override
    public Editor put(String key, Bitmap value) {
        return put(key, CacheUtils.Bitmap2Bytes(value));
    }

    /**
     * 读取 bitmap 数据
     *
     * @param key
     * @return bitmap 数据
     */
    @Nullable
    @Override
    public Bitmap getAsBitmap(String key) {
        if (getAsBinary(key) == null) {
            return null;
        }
        return CacheUtils.Bytes2Bimap(getAsBinary(key));
    }

    // =======================================
    // ============= drawable 数据 读写 =============
    // =======================================

    /**
     * 保存 drawable 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的drawable数据
     */
    public void put(String key, Drawable value) {
        put(key, CacheUtils.drawable2Bitmap(value));
    }


    /**
     * 读取 Drawable 数据
     *
     * @param key
     * @return Drawable 数据
     */
    public Drawable getAsDrawable(String key) {
        if (getAsBinary(key) == null) {
            return null;
        }
        return CacheUtils.bitmap2Drawable(CacheUtils.Bytes2Bimap(getAsBinary(key)));
    }

    /**
     * 获取缓存文件
     *
     * @param key
     * @return value 缓存的文件
     */
    @Override
    public File file(String key) {
        File f = new File(hashKeyForDisk(key));
        if (f.exists()) {
            return f;
        }
        return null;
    }

    /**
     * 移除某个key
     *
     * @param key
     * @return 是否移除成功
     */
    @Override
    public boolean remove(String key) {
        try {
            memLruCache.remove(key);
            String cacheKey = hashKeyForDisk(key);
            getDiskLruCache().remove(cacheKey);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 清除所有数据
     */
    @Override
    public void clear() {
        try {
            memLruCache.evictAll();
            getDiskLruCache().delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> Observable<T> subscribe(String key) {
        return null;
    }

    private abstract class BaseEditor<T extends Object> implements Editor<T>, Runnable {
        protected Map<String, T> tMap = new ConcurrentHashMap<>();

        public BaseEditor(String key, T value) {
            tMap.put(key, value);
        }

        @Override
        public Editor put(String key, T value) {
            tMap.put(key, value);
            return this;
        }

        protected abstract void writeValue(OutputStream out, T value) throws Exception;

        @Override
        public synchronized boolean commit() {
            if (tMap.size() == 0) {
                return false;
            }
            synchronized (getDiskLruCache()) {
                Iterator<String> it = tMap.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    T value = tMap.get(key);
                    memLruCache.put(key, value);
                    final String cacheKey = hashKeyForDisk(key);
                    OutputStream out;
                    try {
                        DiskLruCache.Snapshot snapshot = getDiskLruCache().get(cacheKey);
                        if (snapshot == null) {
                            final DiskLruCache.Editor
                                    editor = getDiskLruCache().edit(cacheKey);
                            if (editor != null) {
                                out = editor.newOutputStream(0);
                                writeValue(out, value);
                                out.flush();
                                out.close();
                                editor.commit();
                            }
                        } else {
                            snapshot.getInputStream(0).close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } //end while
                try {
                    getDiskLruCache().flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//end synchronized
            return true;
        }

        @Override
        public Editor expire(TimeUnit unit, int time) {
            return this;
        }

        @Override
        public void apply() {
            writeExecutor.execute(this);
        }

        @Override
        public Observable<Boolean> suscribe() {
            return Observable.just(commit());
        }

        @Override
        public void run() {
            commit();
        }
    }

    private class ByteEditor extends BaseEditor<byte[]> {
        ByteEditor(String key, byte[] value) {
            super(key, value);
        }

        @Override
        protected void writeValue(OutputStream out, byte[] value) throws Exception {
            out.write(value);
        }
    }

    private class ObjectEditor extends BaseEditor<Serializable> {
        ObjectEditor(String key, Serializable value) {
            super(key, value);
        }

        @Override
        protected void writeValue(OutputStream out, Serializable value) throws Exception {
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(value);
            oos.flush();
            oos.close();
        }
    }

    private class StringEditor extends BaseEditor<String> {
        StringEditor(String key, String value) {
            super(key, value);
        }

        @Override
        protected void writeValue(OutputStream out, String value) throws Exception {
            out.write(value.getBytes());
        }
    }
}