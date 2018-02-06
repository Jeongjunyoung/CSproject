package cs.apps.obg.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
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
import java.util.List;
import java.util.Map;

import cs.apps.obg.R;
import cs.apps.obg.domain.RankingScoreVO;
import cs.apps.obg.domain.UserVO;
import cs.apps.obg.inter.LoadUserCallback;

public class CSActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int RC_SIGN_IN = 10;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Map<String, UserVO> user;
    private LinearLayout loginLayout;
    private LinearLayout setNicknameLayout;
    private String uID;
    private EditText nicknameText;
    private Button nicknameConfirm;
    //private boolean isNewUser  = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs);
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        setNicknameLayout = (LinearLayout) findViewById(R.id.set_nickname_layout);
        nicknameText = (EditText) findViewById(R.id.nickname_text);
        nicknameConfirm = (Button) findViewById(R.id.nickname_confirm);
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
                    Log.d("CSA", "NULL");
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
                user.put("information", new UserVO(mUser.getEmail(), nickName));
                userRef.child(mUser.getUid()).child("user").setValue(user);
                setNicknameLayout.setVisibility(View.GONE);
            }
        });
        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
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
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
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
                            Log.d("CSA", "Login Success");
                            loginLayout.setVisibility(View.GONE);
                            mUser = mAuth.getCurrentUser();
                            getUser(mUser.getUid());
                        } else {
                        }
                    }
                });
    }
    private void getUser(@NonNull final String userUid){
        database.getReference("saving-data/users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userUid)) {
                    Log.d("CSA", "getUser 호출");
                    UserVO vo = dataSnapshot.child(userUid).child("user").child("information").getValue(UserVO.class);
                } else {
                    //callback.onUserLoaded("none");
                    setNicknameLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void signOut() {
        mAuth.signOut();
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                loginLayout.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Logout Success", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        /*switch (view.getId()) {
            case R.id.country_flag:
                break;
            case R.id.all_list_btn:
                break;
                case R.id.
        }*/
    }

   /* public interface LoadUserCallback {
        public void onUserLoaded();
    }*/
}
