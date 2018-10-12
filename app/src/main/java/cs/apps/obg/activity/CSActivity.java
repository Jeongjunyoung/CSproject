package cs.apps.obg.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import cs.apps.obg.R;
import cs.apps.obg.domain.UserVO;
import cs.apps.obg.service.UserApplication;

public class CSActivity extends ProgressActivity{
    private static final int RC_SIGN_IN = 10;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Map<String, UserVO> user;
    private LinearLayout loginLayout, setNicknameLayout, guestLoginLayout;
    private String uID;
    private EditText nicknameText, guestEmail, guestPassword;
    private Button nicknameConfirm, guestSignInBtn;
    private TextView loginText;
//    private ProgressDialog progressDialog;
    //private CallbackManager mCallbackManager;
    //private boolean isNewUser  = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs);
        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_ad_unit_id));
        AdView mAdView = (AdView) findViewById(R.id.cs_adview);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        setNicknameLayout = (LinearLayout) findViewById(R.id.set_nickname_layout);
        guestLoginLayout = (LinearLayout) findViewById(R.id.guest_login_layout);
        nicknameText = (EditText) findViewById(R.id.nickname_text);
        guestEmail = (EditText) findViewById(R.id.guest_email);
        guestPassword = (EditText) findViewById(R.id.guest_pw);
        nicknameConfirm = (Button) findViewById(R.id.nickname_confirm);
        guestSignInBtn = (Button) findViewById(R.id.guest_sign_in);
        loginText = (TextView) findViewById(R.id.login_text);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("saving-data");
        userRef = myRef.child("users");
        mUser = mAuth.getCurrentUser();
        if (mUser != null) {
            uID = mUser.getUid();
        } else if (mUser == null) {
            loginLayout.setVisibility(View.VISIBLE);
        }
        user = new HashMap<>();
        database.getReference("saving-data/users/"+uID+"/user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserVO vo = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //loginLayout.setVisibility(View.GONE);
                    vo = snapshot.getValue(UserVO.class);
                    //isNewUser = false;
                }
                if (vo == null) {
                    //setNicknameLayout.setVisibility(View.VISIBLE);
                   // user.put("information", new UserVO(mUser.getEmail(), ""));
                    //userRef.child(mUser.getUid()).child("user").setValue(user);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        Button button = (Button) findViewById(R.id.country_flag);
        Button button2 = (Button) findViewById(R.id.all_list_btn);
        Button button3 = (Button) findViewById(R.id.country_capital);
        Button signout = (Button) findViewById(R.id.google_sign_out);
        Button guestSignIn = (Button) findViewById(R.id.guest_login_btn);
        SignInButton googleSignInBtn = (SignInButton) findViewById(R.id.google_sign_in);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CSActivity.this, FlagActivity.class);
                intent.putExtra("flag_quiz",true);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CSActivity.this, AllListActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CSActivity.this, FlagActivity.class);
                intent.putExtra("flag_quiz", false);
                startActivity(intent);
            }
        });
        nicknameConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickName = nicknameText.getText().toString();
                if (nickName.equals("")) {
                    Toast.makeText(CSActivity.this, "닉네임을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    user.put("information", new UserVO(mUser.getEmail(), nickName));
                    userRef.child(mUser.getUid()).child("user").setValue(user);
                    setNicknameLayout.setVisibility(View.GONE);
                }
            }
        });
        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressOn();
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        guestSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGuestLogin(false);
            }
        });
        findViewById(R.id.guest_login_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGuestLogin(true);

            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
        guestSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guestEmail.getText().equals("") && guestPassword.getText().equals("")) {
                    Toast.makeText(CSActivity.this, "E-MAIL과 PASSWORD를 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    progressOn();
                    createUser(guestEmail.getText().toString(), guestPassword.getText().toString());
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressOff();
                            loginLayout.setVisibility(View.GONE);
                            mUser = mAuth.getCurrentUser();
                            getUser(mUser.getUid());
                        } else {
                            progressOff();
                        }
                    }
                });
    }
    private void getUser(@NonNull final String userUid){ // 유저 정보 가져오기
        database.getReference("saving-data/users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userUid)) { // 기존 유저
                    Log.d("CSA", "getUser 호출");
                    UserVO vo = dataSnapshot.child(userUid).child("user").child("information").getValue(UserVO.class);
                    UserApplication.getInstance().getServiceInterface().setUser();
                    UserApplication.getInstance().getServiceInterface().setScoreMap();
                } else { // 신규 유저
                    setNicknameLayout.setVisibility(View.VISIBLE); // 닉네임 설정창 띄우기
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void createUser(final String email, final String password) { // 유저 생성
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //System.out.println("GUEST LOGIN 성공");
                            guestLogin(email,password);
                        } else {
                            guestLogin(email,password);
                        }
                    }
                });

    }
    private void guestLogin(String email, String password) { // 게스트 로그인
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressOff();
                            loginLayout.setVisibility(View.GONE);
                            mUser = mAuth.getCurrentUser();
                            getUser(mUser.getUid());
                            setGuestLogin(true);
                        } else {
                            progressOff();
                            Toast.makeText(CSActivity.this, "E-MAIL과 PASSWORD를 확인하세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void setGuestLogin(boolean isGone) {
        if (isGone) {
            guestSignInBtn.setVisibility(View.VISIBLE);
            loginText.setVisibility(View.VISIBLE);
            guestLoginLayout.setVisibility(View.GONE);
            guestEmail.setText("");
            guestPassword.setText("");
        } else {
            guestSignInBtn.setVisibility(View.GONE);
            loginText.setVisibility(View.GONE);
            guestLoginLayout.setVisibility(View.VISIBLE);
        }
    }
    private void signOut() { // 로그아웃
        mAuth.signOut();
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                loginLayout.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Logout Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
