package cs.apps.obg.activity;

import android.support.v7.app.AppCompatActivity;

import cs.apps.obg.service.ProgressApplication;
import cs.apps.obg.service.UserApplication;

/**
 * Created by d1jun on 2018-02-12.
 */

public class ProgressActivity extends AppCompatActivity{

    public void progressOn() {
        UserApplication.getInstance().getServiceInterface().progressOn(this);
    }

    public void progressOff() {
        UserApplication.getInstance().getServiceInterface().progressOff();
    }
}
