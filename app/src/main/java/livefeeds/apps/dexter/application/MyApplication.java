package livefeeds.apps.dexter.application;

import android.app.Application;
import android.content.Context;

import net.gotev.uploadservice.UploadService;


import livefeeds.apps.dexter.activity.BuildConfig;
import livefeeds.apps.dexter.retrofit.RestAPIManager;
import livefeeds.apps.dexter.retrofit.RestClient;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by shopsense on 12-02-2016.
 */
public class MyApplication extends Application {

    private static Context context;
    private static MyApplication appInstance;
    private static RestClient restClient;
    private static RestAPIManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        // setup the broadcast action namespace string which will
        // be used to notify upload status.
        // Gradle automatically generates proper variable as below.
        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;
        getHash();
        appInstance = this;
      //  restClient = new RestClient();
      //  apiManager = new RestAPIManager();
    }

    public static Context getAppContext() {
        return context;
    }

    private void getHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "livefeeds.apps.dexter.activity",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash: ", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
