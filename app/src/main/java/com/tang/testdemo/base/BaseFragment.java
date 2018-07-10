package com.tang.testdemo.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.tang.testdemo.app.MyApplication;
import com.tang.testdemo.dagger.Component.AppComponent;

import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class BaseFragment extends Fragment {
    protected Unbinder unbinder;
    protected CompositeDisposable composite = new CompositeDisposable();

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
