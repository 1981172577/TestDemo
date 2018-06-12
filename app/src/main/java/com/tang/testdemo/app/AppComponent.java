package com.tang.testdemo.app;


import android.app.Application;

import dagger.Component;

@Component
public interface AppComponent {
    void inject(MyApplication application);
}
