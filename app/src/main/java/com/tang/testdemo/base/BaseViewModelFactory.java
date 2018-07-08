package com.tang.testdemo.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

/**
 * 简介：
 *
 * @author 王强（249346528@qq.com） 2017/8/29.
 */
public class BaseViewModelFactory<R> implements ViewModelProvider.Factory {
    private R repository;
    private Class<R> repositoryClass;
    public BaseViewModelFactory(R repository, Class<R> repositoryClass) {
        this.repository = repository;
        this.repositoryClass = repositoryClass;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        try {
            return modelClass.getConstructor(repositoryClass).newInstance(repository);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
