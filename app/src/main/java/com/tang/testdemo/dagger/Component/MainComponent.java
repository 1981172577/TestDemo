package com.tang.testdemo.dagger.Component;

import com.tang.testdemo.annotaion.PerActivity;
import com.tang.testdemo.dagger.Module.MainMoudle;
import com.tang.testdemo.ui.activity.MainActivity;

import dagger.BindsInstance;
import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {MainMoudle.class})
public interface MainComponent {

    void inject(MainActivity loginActivity);

    @Component.Builder
    interface Builder {
        MainComponent.Builder appComponent(AppComponent posComponent);

        @BindsInstance
        MainComponent.Builder bindActivity(MainActivity activity);

        MainComponent build();
    }
}
