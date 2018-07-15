package com.tang.testdemo.moudel.viewModel;

import android.arch.lifecycle.ViewModel;

import com.tang.testdemo.repositorys.MainRespositiory;

public class MainViewModel extends ViewModel {

    private MainRespositiory mainRespositiory;

    private MainViewModel(MainRespositiory mainRespositiory){
        this.mainRespositiory = mainRespositiory;
    }


}
