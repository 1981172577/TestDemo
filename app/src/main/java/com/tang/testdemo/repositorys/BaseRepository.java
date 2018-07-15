package com.tang.testdemo.repositorys;

import io.reactivex.disposables.CompositeDisposable;

public interface BaseRepository {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    void release();

}