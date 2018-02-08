package cs.apps.obg.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs.apps.obg.R;
import cs.apps.obg.adapter.RankAdapter;
import cs.apps.obg.database.DBHelper;
import cs.apps.obg.domain.FlagVO;
import cs.apps.obg.domain.RankingScoreVO;
import cs.apps.obg.inter.LoadUserCallback;
import cs.apps.obg.service.UserApplication;

public class FlagActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private ChildEventListener mChildEventListener;
    private static final String TAG = "Flag";
    private String mUserUID;
    ImageView flagImage;
    LinearLayout rankingLayout, readyLayout;
    Button contents1, contents2, contents3, contents4, flagStart, flagBack, flagRestart, flagOut;
    TextView goodAnswer, badAnswer, flagScore, flagTimer, flagCapital, highestScoreText, newHighestText;
    Spinner mSpinner;
    RecyclerView recyclerView;
    private int[] orderArr;
    private HashMap<Integer, String> flagMap = new HashMap<>();
    private HashMap<Integer, String> countryMap = new HashMap<>();
    List<Integer> imageIds = new ArrayList<>();
    int mPosition = 0;
    int goodNum = 0;
    int badNum = 0;
    private int fTotalNum;
    private String flagAnswer, db_rankingKinds;
    private boolean isTimeOut = false;
    private boolean isFlagQuiz;
    private int timeNum;
    private int continentNum;
    private ArrayList<FlagVO> list = new ArrayList<>();
    TimerRunnable handler;
    TimerUpdate thread;
    private RankAdapter rankAdapter;
    DBHelper dbHelper;
    private Map<String, Integer> scoreMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mChildEventListener != null) {
            myRef.removeEventListener(mChildEventListener);
        }
    }

    private void initView() {
        Intent intent = getIntent();
        isFlagQuiz = intent.getBooleanExtra("flag_quiz", false);
        if (isFlagQuiz) {
            db_rankingKinds = "saving-data/ranking_flag";
            scoreMap = UserApplication.getInstance().getServiceInterface().getFlagMap();
        } else {
            db_rankingKinds = "saving-data/ranking_capital";
            scoreMap = UserApplication.getInstance().getServiceInterface().getCapitalMap();
        }
        System.out.println("MAP >>> Europe Score : " + scoreMap.get("flag_europe_store"));
        recyclerView = (RecyclerView) findViewById(R.id.ranking_list);
        rankAdapter = new RankAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rankAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("saving-data/users");
        mAuth = FirebaseAuth.getInstance();
        //mUser = mAuth.getCurrentUser();
        //println("E-MAIL : "+mUser.getEmail()+", UID : "+mUser.getUid());
        UserApplication.getInstance().getServiceInterface().setUser();
        //UserApplication.getInstance().getServiceInterface().getUserScore();
        dbHelper = new DBHelper(this);
        dbHelper.open();
        flagImage = (ImageView) findViewById(R.id.flag_image);
        contents1 = (Button) findViewById(R.id.flag_contents1);
        contents2 = (Button) findViewById(R.id.flag_contents2);
        contents3 = (Button) findViewById(R.id.flag_contents3);
        contents4 = (Button) findViewById(R.id.flag_contents4);
        flagStart = (Button) findViewById(R.id.flag_quiz_start);
        flagBack = (Button) findViewById(R.id.flag_quiz_back);
        flagRestart = (Button) findViewById(R.id.flag_restart);
        flagOut = (Button) findViewById(R.id.flag_out);
        goodAnswer = (TextView) findViewById(R.id.good_answer);
        badAnswer = (TextView) findViewById(R.id.bad_answer);
        flagTimer = (TextView) findViewById(R.id.flag_timer);
        flagScore = (TextView) findViewById(R.id.flag_score);
        flagCapital = (TextView) findViewById(R.id.flag_capital);
        highestScoreText = (TextView) findViewById(R.id.flag_highest_score);
        //newHighestText = (TextView) findViewById(R.id.new_highest_score);
        rankingLayout = (LinearLayout) findViewById(R.id.flag_ranking_layout);
        readyLayout = (LinearLayout) findViewById(R.id.flag_ready_layout);
        if (isFlagQuiz) {
            flagCapital.setVisibility(View.GONE);
        } else {
            flagCapital.setVisibility(View.VISIBLE);
        }
        mSpinner = (Spinner) findViewById(R.id.flag_continent_spinner);
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.continent,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(spinnerAdapter);
        contents1.setOnClickListener(this);
        contents2.setOnClickListener(this);
        contents3.setOnClickListener(this);
        contents4.setOnClickListener(this);
        flagStart.setOnClickListener(this);
        flagBack.setOnClickListener(this);
        flagRestart.setOnClickListener(this);
        flagOut.setOnClickListener(this);
        handler = new TimerRunnable();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flag_contents1:
                checkAnswer(contents1.getText().toString());
                break;
            case R.id.flag_contents2:
                checkAnswer(contents2.getText().toString());
                break;
            case R.id.flag_contents3:
                checkAnswer(contents3.getText().toString());
                break;
            case R.id.flag_contents4:
                checkAnswer(contents4.getText().toString());
                break;
            case R.id.flag_quiz_start:
                flagMap.clear();
                imageIds.clear();
                readyLayout.setVisibility(View.GONE);
                UserApplication.getInstance().getServiceInterface().setScoreMap();
                String str = mSpinner.getSelectedItem().toString();
                continentNum = mSpinner.getSelectedItemPosition();
                list = dbHelper.selectData(continentNum);
                fTotalNum = list.size();
                System.out.println("MAP >>> Europe Score : " + scoreMap.get(getContinentString(continentNum)));
                orderArr = new int[fTotalNum];
                for(int i=0;i<list.size();i++) {
                    int index = i+1;
                    System.out.println("index : "+ index +"   country : "+ list.get(i).getCountry());
                    if (isFlagQuiz) {
                        flagMap.put(i, list.get(i).getCountry());
                    } else {
                        flagMap.put(i, list.get(i).getCapital());
                    }
                    countryMap.put(i, list.get(i).getCountry());
                    imageIds.add(list.get(i).getResId());
                }
                quizShuffle();
                break;
            case R.id.flag_quiz_back:
                break;
            case R.id.flag_restart:
                rankingLayout.setVisibility(View.GONE);
                readyLayout.setVisibility(View.VISIBLE);
                newHighestText.setVisibility(View.GONE);
                goodAnswer.setText(String.valueOf(0));
                badAnswer.setText(String.valueOf(0));
                isTimeOut = false;
                break;
            case R.id.flag_out:
                finish();
                break;
        }
    }

    private void checkAnswer(String answer) {
        if (answer.equals(flagAnswer)) {
            goodNum += 1;
            goodAnswer.setText(String.valueOf(goodNum));
        } else if (flagAnswer.equals("END")) {

        } else {
            badNum += 1;
            badAnswer.setText(String.valueOf(badNum));
        }

        if (mPosition < orderArr.length) {
            setNextQuiz();
        } else {
            //flagImage.setImageResource(R.drawable.flag1_);
            flagAnswer = "END";
        }
    }

    @SuppressLint("ResourceType")
    private void setNextQuiz() {
        int[] contentsArr = shakeNum(orderArr[mPosition]);
        flagAnswer = flagMap.get(orderArr[mPosition]);
        flagCapital.setText(countryMap.get(orderArr[mPosition]));
        contents1.setText(flagMap.get(contentsArr[0]));
        contents2.setText(flagMap.get(contentsArr[1]));
        contents3.setText(flagMap.get(contentsArr[2]));
        contents4.setText(flagMap.get(contentsArr[3]));
        flagImage.setImageResource(imageIds.get(orderArr[mPosition]));
        mPosition += 1;
    }
    private int[] shakeNum(int position) {
        int[] contentsArr = new int[4];
        boolean isOverlap;
        contentsArr[0] = position;
        int random = 0;
        for(int i=1; i<contentsArr.length;i++) {
            random = (int) (Math.random() * fTotalNum) % fTotalNum;
            isOverlap = true;
            for(int j=0;j<i;j++) {
                if (contentsArr[j] == random) {
                    i--;
                    isOverlap = false;
                }
            }
            if (isOverlap) {
                contentsArr[i] = random;
            }
        }

        int temp,seed;
        for(int i=0;i<contentsArr.length;i++) {
            seed = (int)(Math.random() * 3) + 1;
            temp = contentsArr[i];
            contentsArr[i] = contentsArr[seed];
            contentsArr[seed] = temp;
        }
        return contentsArr;
    }

    private void quizShuffle() {
        int random = 0;
        if (continentNum == 0) {
            timeNum = 35;
        } else {
            timeNum = 20;
        }
        goodNum = 0;
        badNum = 0;
        boolean isOverlap;
        for(int i=0; i<orderArr.length;i++) {
            random = (int) (Math.random() * fTotalNum) % fTotalNum;
            isOverlap = true;
            for(int j=0;j<i;j++) {
                if (orderArr[j] == random) {
                    i--;
                    isOverlap = false;
                }
            }
            if (isOverlap) {
                orderArr[i] = random;
            }
        }
        for(int i=0;i<orderArr.length;i++) {
            System.out.println("orderArr["+i+"] : " + String.valueOf(orderArr[i]));
        }
        thread = new TimerUpdate();
        thread.start();
        setNextQuiz();

    }
    public class TimerRunnable extends Handler {

        @Override
        public void handleMessage(Message msg) {
            updateTimer();
        }
    }
    class TimerUpdate extends Thread{
        @Override
        public void run() {
            while (!isTimeOut) {
                try {
                    if (!isTimeOut) {
                        /*timeNum--;
                        flagTimer.setText(String.valueOf(timeNum));
                        println(String.valueOf(timeNum));*/
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    //Log.d("TimerUpdate",e.getMessage());
                }

            }
        }
    }

    private void updateTimer() {
        timeNum--;
        flagTimer.setText(String.valueOf(timeNum));
        if (timeNum == 0) {
            finishTimer();
        } else if (timeNum < 6) {
            flagTimer.setTextColor(Color.parseColor("#FFFF0900"));
        }
    }
    private void finishTimer() {
        resultScore(goodNum, badNum);
        rankingLayout.setVisibility(View.VISIBLE);
        isTimeOut = true;
        thread.interrupt();
    }

    @Override
    public void onBackPressed() {
        if (rankingLayout.getVisibility() == View.VISIBLE) {
            finish();
        } else {
            thread.interrupt();
            finish();
            super.onBackPressed();
        }
    }

    private long getMaxScore() {
        if (isFlagQuiz) {
            scoreMap = UserApplication.getInstance().getServiceInterface().getFlagMap();
        } else {
            scoreMap = UserApplication.getInstance().getServiceInterface().getCapitalMap();
        }
        if (scoreMap.get(getContinentString(continentNum)) == null) {
            return 0;
        }
        return (long) scoreMap.get(getContinentString(continentNum));
    }

    private long checkMaxScore(long score, long highestScore) {
        long updateScore = 0;
        if (score > highestScore) {
            updateScore = score;
            //newHighestText.setVisibility(View.VISIBLE);
        } else if (score <= highestScore) {
            updateScore = highestScore;
        }
        return updateScore;
    }
    private void resultScore(int goodScore, int badScore) {
        long score = (goodScore*1573) - (badScore*372);
        if (score < 0) {
            score = 0;
        }
        long highestScore = getMaxScore();
        long updateScore = checkMaxScore(score, highestScore);
        highestScoreText.setText(String.valueOf(highestScore));
        flagScore.setText(String.valueOf(score));
        DatabaseReference ref = database.getReference(db_rankingKinds);
        if (isFlagQuiz) {
            myRef.child(UserApplication.getInstance().getServiceInterface().getUID())
                    .child("score").child("flag_store").child(getContinentString(continentNum)).setValue(updateScore);
        } else {
            myRef.child(UserApplication.getInstance().getServiceInterface().getUID())
                    .child("score").child("capital_store").child(getContinentString(continentNum)).setValue(updateScore);
        }
        ref.child(getContinentString(continentNum)).child("values").
                child(UserApplication.getInstance().getServiceInterface().getNickname()).setValue(updateScore);
        getRankingList(new LoadUserCallback() {
            @Override
            public void onUserLoaded(String str) {

            }
            @Override
            public void onRankLoaded(List<RankingScoreVO> list) {
                rankAdapter.setItems(list);
                System.out.println(list.size());
                System.out.println("Size : "+rankAdapter.getItemCount()+" 1 item add Success");
                rankAdapter.notifyDataSetChanged();
            }
        });
    }

    private String getContinentString(int position) {
        String str = "";
        if (position == 1) {
            str = "flag_europe_store";
        } else if (position == 2) {
            str = "flag_asia_oceania_store";
        } else if (position == 3) {
            str = "flag_america_store";
        } else if (position == 4) {
            str = "flag_africa_middleeast_store";
        } else if (position == 0) {
            str = "flag_all_store";
        }
        return str;
    }
    private void getRankingList(final LoadUserCallback callback){
        database.getReference(db_rankingKinds+"/"+getContinentString(continentNum)).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                List<RankingScoreVO> list = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    RankingScoreVO vo = new RankingScoreVO();
                    vo.setNickName(snapshot.getKey());
                    vo.setScore(snapshot.getValue().toString());
                    list.add(vo);
                    System.out.println(snapshot.getKey() + " : " + snapshot.getValue());
                }
                Collections.sort(list, new RankingComparator());
                callback.onRankLoaded(list);
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
    static class RankingComparator implements Comparator<RankingScoreVO> {

        @Override
        public int compare(RankingScoreVO vo1, RankingScoreVO vo2) {
            int score1 = Integer.parseInt(vo1.getScore());
            int score2 = Integer.parseInt(vo2.getScore());
            return score1 > score2 ? -1 : score1 < score2 ? 1 : 0;
        }
    }
}
