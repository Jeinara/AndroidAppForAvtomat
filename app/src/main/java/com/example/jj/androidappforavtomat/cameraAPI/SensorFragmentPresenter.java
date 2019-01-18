package com.example.jj.androidappforavtomat.cameraAPI;

import android.content.Context;


public class SensorFragmentPresenter implements ISensorFragmentPresenter {
    private Context applicationContext;
    private ISensorFragmentActivity iSensorFragmentActivity;

    public Context getApplicationContext() {
        return applicationContext;
    }

    public ISensorFragmentActivity getiSensorFragmentActivity() {
        return iSensorFragmentActivity;
    }

    public SensorFragmentPresenter(SensorFragment sensorFragment) {
        iSensorFragmentActivity = sensorFragment;
        applicationContext = sensorFragment.getApplicationContext();
    }




}
