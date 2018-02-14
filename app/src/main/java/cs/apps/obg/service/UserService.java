package cs.apps.obg.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialog;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import cs.apps.obg.R;
import cs.apps.obg.domain.UserScoreVO;
import cs.apps.obg.inter.LoadUserCallback;

/**
 * Created by d1jun on 2018-02-04.
 */

public class UserService extends Service{
    private final IBinder mBinder = new UserServiceBinder();
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference flagRef;
    private DatabaseReference capitalRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String mUID;
    private String mNickname;
    //private static UserScoreVO userScoreVO = new UserScoreVO();
    private Map<String, Integer> scoreFlagMap = new HashMap<>();
    private Map<String, Integer> scoreCapitalMap = new HashMap<>();
    AppCompatDialog progressDialog;
    public class UserServiceBinder extends Binder {
        public UserService getService(){
            return UserService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            mUID = mUser.getUid();
        }
        myRef = database.getReference("saving-data/users/"+mUID+"/user/information");
        flagRef = database.getReference("saving-data/users/"+mUID+"/score/flag_store");
        capitalRef = database.getReference("saving-data/users/"+mUID+"/score/capital_store");
        setFlagScore();
        setCapitalScore();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void getUserNickname() {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals("nick_name")) {
                    System.out.println("getUserNickName() : "+dataSnapshot.getKey() + " : " + dataSnapshot.getValue());
                    //callback.onUserLoaded(dataSnapshot.getValue().toString());
                    mNickname = dataSnapshot.getValue().toString();
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void setUser() {
        mUser = mAuth.getCurrentUser();
        mUID = mUser.getUid();
        myRef = database.getReference("saving-data/users/"+mUID+"/user/information");
        getUserNickname();
    }
    public String getNickname() {
        return mNickname;
    }

    public String getUID() {
        return mUID;
    }

    public void setFlagScore() {
        System.out.println("getUserScore()");
        flagRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println(dataSnapshot.getKey() + " : " + dataSnapshot.getValue());
                scoreFlagMap.put(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void setCapitalScore() {
        capitalRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                System.out.println(dataSnapshot.getKey() + " : " + dataSnapshot.getValue());
                scoreCapitalMap.put(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.getValue().toString()));
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Map<String, Integer> getFlagMap() {
        return scoreFlagMap;
    }
    public Map<String, Integer> getCapitalMap() {
        return scoreCapitalMap;
    }

    public void setScoreMap() {
        setFlagScore();
        setCapitalScore();
    }
    public void progressOn(Activity activity) {
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
    }
    public void getTest() {
        System.out.println("Service TEST");
    }
}
