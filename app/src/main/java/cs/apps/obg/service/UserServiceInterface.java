package cs.apps.obg.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.List;

import cs.apps.obg.domain.RankingScoreVO;
import cs.apps.obg.inter.LoadUserCallback;

/**
 * Created by d1jun on 2018-02-04.
 */

public class UserServiceInterface {
    private ServiceConnection mServiceConnection;
    private UserService mService;
    public UserServiceInterface(Context context) {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                mService = ((UserService.UserServiceBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mServiceConnection = null;
                mService = null;
            }
        };
        context.bindService(new Intent(context, UserService.class)
                .setPackage(context.getPackageName()), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public String getNickname() {
        if (mService != null) {
            return mService.getNickname();
        }
        return null;
    }
    public void getTest() {
        if (mService != null) {
            mService.getTest();
        }
    }
    public void setUser() {
        if (mService != null) {
            mService.setUser();
        }
    }

    public String getUID() {
        if (mService != null) {
            return mService.getUID();
        }
        return null;
    }
    public void getUserScore() {
        if (mService != null) {
            mService.getUserScore();
        }
    }
}
