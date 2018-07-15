package com.tang.testdemo.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tang.testdemo.app.MyApplication;
import com.tang.testdemo.dagger.Component.AppComponent;

import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected Unbinder unbinder;
    protected CompositeDisposable composite = new CompositeDisposable();

    protected View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(),container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (composite != null) {
            composite.dispose();
            composite.clear();
        }
    }

    protected AppComponent getAppComponent() {
        return ((MyApplication) getActivity().getApplication()).getAppComponent();
    }
    /**
     * 显示提示信息
     *
     * @param msg 提示信息
     */
    protected void showMsg(String msg) {
        Context ctx = getContext();
        if (ctx != null && isAdded()) {
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        }
    }

    protected void showMsg(@StringRes int res){
        showMsg(getString(res));
    }

    protected boolean isFinishing() {
        return getActivity() == null || getActivity().isFinishing();
    }

    protected void toastLong(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    protected void toastLong(int msg) {
        toastLong(getString(msg));
    }

    protected void toastShort(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void toastShort(int msg) {
        toastShort(getString(msg));
    }
}
