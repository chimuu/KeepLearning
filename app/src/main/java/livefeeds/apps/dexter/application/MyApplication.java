package livefeeds.apps.dexter.application;

import android.app.Application;

import net.gotev.uploadservice.UploadService;

import livefeeds.apps.dexter.activity.BuildConfig;

/**
 * Created by shopsense on 12-02-2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // setup the broadcast action namespace string which will
        // be used to notify upload status.
        // Gradle automatically generates proper variable as below.
        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;
    }

}
