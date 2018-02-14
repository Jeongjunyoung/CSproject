package cs.apps.obg.service;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatDialog;
import android.widget.ImageView;

import cs.apps.obg.R;
import cs.apps.obg.activity.CSActivity;

/**
 * Created by d1jun on 2018-02-12.
 */

public class ProgressApplication extends Application {
    private static ProgressApplication progressApplication;
    AppCompatDialog progressDialog;

    public static ProgressApplication getInstance() {
        return progressApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        progressApplication = this;
    }
    /*public void progressOn(Activity activity) {
        progressDialog = new AppCompatDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setContentView(R.layout.login_dialog);
        progressDialog.show();
        final ImageView loadingImage = (ImageView) progressDialog.findViewById(R.id.frame_loading);
        final AnimationDrawable frameAnimation = (AnimationDrawable) loadingImage.getBackground();
        loadingImage.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });
    }
    public void progressOff() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }*/
}
