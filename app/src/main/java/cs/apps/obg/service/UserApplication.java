package cs.apps.obg.service;

import android.app.Application;

/**
 * Created by d1jun on 2018-02-04.
 */

public class UserApplication extends Application{
    private static UserApplication mInstance;
    private UserServiceInterface mInterface;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("UserApplication onCreate() 호출");
        mInstance = this;
        mInterface = new UserServiceInterface(getApplicationContext());
    }
    public static UserApplication getInstance() {
        return mInstance;
    }
    public UserServiceInterface getServiceInterface() {
        return mInterface;
    }
}
