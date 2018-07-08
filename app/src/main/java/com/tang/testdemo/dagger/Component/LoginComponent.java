package com.tang.testdemo.dagger.Component;

import com.tang.testdemo.annotaion.PerActivity;
import com.tang.testdemo.dagger.Module.LoginMoudle;
import com.tang.testdemo.ui.activity.LoginActivity;

import dagger.BindsInstance;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {LoginMoudle.class})
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

    @Component.Builder
    interface Builder {
        LoginComponent.Builder posComponent(AppComponent posComponent);

        @BindsInstance
        LoginComponent.Builder bindActivity(LoginActivity activity);

        LoginComponent build();
    }
}
