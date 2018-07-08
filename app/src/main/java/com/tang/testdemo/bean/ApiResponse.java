package com.tang.testdemo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 简介：网络请求返回结果的包装类
 *
 * @author 王强（249346528@qq.com） 2017/8/3.
 */

public class ApiResponse<T> {
    @SerializedName(value = "status", alternate = {"code", "error_code", "returnCode"})
    private int code;
    @SerializedName(value = "message")
    private String message;
    @SerializedName(value = "addMessage")
    private String addMessage;
    @SerializedName(value = "resultBean", alternate = {"resultList"})
    private T data;
    @SerializedName(value = "datasize")
    int dataSize;

    public ApiResponse() {
    }

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAddMessage(String addMessage) {
        this.addMessage = addMessage;
    }

    public String getAddMessage() {
        return addMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", addMessage='" + addMessage + '\'' +
                ", data=" + data +
                ", dataSize=" + dataSize +
                '}';
    }

    public static class Status {
        /**
         * 失败
         */
        public static int FAIL = 0;
        /**
         * 成功
         */
        public static int OK = 1;
        /**
         * 错误，如网络超时
         */
        public static int ERROR = 2;
    }
}
