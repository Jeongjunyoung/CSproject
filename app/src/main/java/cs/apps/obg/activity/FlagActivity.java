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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs.apps.obg.R;
import cs.apps.obg.adapter.RankingAdapter;
import cs.apps.obg.database.DBHelper;
import cs.apps.obg.domain.FlagVO;
import cs.apps.obg.domain.RankingScoreVO;
import cs.apps.obg.inter.LoadUserCallback;
import cs.apps.obg.service.UserApplication;

public class FlagActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private InterstitialAd mInterstitialAd;
    ImageView flagImage;
    LinearLayout rankingLayout, readyLayout;
    Button contents1, contents2, contents3, contents4, flagStart, flagBack, flagRestart, flagOut;
    TextView goodAnswer, badAnswer, flagScore, flagTimer, flagCapital,
            highestScoreText, viewHighestScore, playingHighest, playingScore, rankingNumText;
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
    private long viewScore;
    private String flagAnswer, db_rankingKinds;
    private boolean isTimeOut = false;
    private boolean isFlagQuiz;
    private int timeNum;
    private int continentNum, frontAdCount;
    private ArrayList<FlagVO> list = new ArrayList<>();
    TimerRunnable handler;
    TimerUpdate thread;
    private RankingAdapter rankAdapter;
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
    }

    private void initView() {
        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_ad_unit_id));
        AdView mAdView = (AdView) findViewById(R.id.flag_adview);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
        setFrontAd();
        Intent intent = getIntent();
        isFlagQuiz = intent.getBooleanExtra("flag_quiz", false);
        setScore();
        //System.out.println("MAP >>> Europe Score : " + scoreMap.get("flag_europe_store"));
        recyclerView = (RecyclerView) findViewById(R.id.ranking_list);
        rankAdapter = new RankingAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rankAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("saving-data/users");
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
        viewHighestScore = (TextView) findViewById(R.id.view_highest_score);
        playingHighest = (TextView) findViewById(R.id.playing_quiz_highest);
        playingScore = (TextView) findViewById(R.id.playing_quiz_score);
        rankingNumText = (TextView) findViewById(R.id.flag_ranking_text);
        rankingLayout = (LinearLayout) findViewById(R.id.flag_ranking_layout);
        readyLayout = (LinearLayout) findViewById(R.id.flag_ready_layout);
        if (isFlagQuiz) {
            flagCapital.setVisibility(View.GONE);
        } else {
            flagCapital.setVisibility(View.VISIBLE);
        }
        mSpinner = (Spinner) findViewById(R.id.flag_continent_spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                String str = "";
                if (scoreMap.get(getContinentString(mSpinner.getSelectedItemPosition())) != null) {
                    str = scoreMap.get(getContinentString(mSpinner.getSelectedItemPosition())).toString();
                } else {
                    str = "0";
                }
                viewHighestScore.setText(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
    @SuppressLint("SetTextI18n")
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
                if (scoreMap.get(getContinentString(continentNum)) == null) {
                    playingHighest.setText("0");
                } else {
                    playingHighest.setText(scoreMap.get(getContinentString(continentNum)).toString());
                }
                playingScore.setText("0");
                orderArr = new int[fTotalNum];
                for(int i=0;i<list.size();i++) {
                    int index = i+1;
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
                finish();
                break;
            case R.id.flag_restart:
                setScore();
                viewHighestScore.setText(scoreMap.get(getContinentString(mSpinner.getSelectedItemPosition())).toString());
                rankingLayout.setVisibility(View.GONE);
                readyLayout.setVisibility(View.VISIBLE);
                goodAnswer.setText(String.valueOf(0));
                badAnswer.setText(String.valueOf(0));
                isTimeOut = false;
                break;
            case R.id.flag_out:
                finish();
                break;
        }
    }

    private void checkAnswer(String answer) {//정답 확인
        if (answer.equals(flagAnswer)) {
            goodNum += 1;
            viewScore += 1973;
            goodAnswer.setText(String.valueOf(goodNum));
        } else if (flagAnswer.equals("END")) {

        } else {
            badNum += 1;
            viewScore -= 572;
            badAnswer.setText(String.valueOf(badNum));
        }
        playingScore.setText(String.valueOf(viewScore));
        if (mPosition < orderArr.length) {
            setNextQuiz();
        } else {
            //flagImage.setImageResource(R.drawable.flag1_);
            flagAnswer = "END";
        }
    }

    @SuppressLint("ResourceType")
    private void setNextQuiz() { //다음 퀴즈
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
    private int[] shakeNum(int position) { //문항 섞기
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
        viewScore = 0;
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
            if (thread != null) {
                thread.interrupt();
            }
            finish();
            super.onBackPressed();
        }
    }

    private long getMaxScore() {
        setScore();
        if (scoreMap.get(getContinentString(continentNum)) == null) {
            return 0;
        }
        return (long) scoreMap.get(getContinentString(continentNum));
    }

    private long checkMaxScore(long score, long highestScore) {
        long updateScore = 0;
        if (score > highestScore) {
            updateScore = score;
        } else if (score <= highestScore) {
            updateScore = highestScore;
        }
        return updateScore;
    }
    private void resultScore(int goodScore, int badScore) {
        long score = (goodScore*1973) - (badScore*572);
        if (score < 0) {
            score = 0;
        }
        long highestScore = getMaxScore();
        long updateScore = checkMaxScore(score, highestScore);
        if (score > highestScore) {
            highestScoreText.setText("NEW RECORD");
        } else {
            highestScoreText.setText(String.valueOf(highestScore));
        }
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
                int index = 0;
                for(int i=0;i<list.size();i++) {
                    if (list.get(i).getNickName().equals(UserApplication.getInstance().getServiceInterface().getNickname())) {
                        rankingNumText.setText(String.valueOf(i+1));
                    }
                }
                List<RankingScoreVO> mList = new ArrayList<>();
                if (mList.size() > 15) {
                    index = 15;
                } else {
                    index = list.size();
                }
                for(int i=0;i<index;i++) {
                    mList.add(list.get(i));
                }
                rankAdapter.setItems(mList);
                rankAdapter.notifyDataSetChanged();
            }
        });
        UserApplication.getInstance().getServiceInterface().setScoreMap();
        showFrontAd();
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
    private void setScore() {
        if (isFlagQuiz) {
            db_rankingKinds = "saving-data/ranking_flag";
            scoreMap = UserApplication.getInstance().getServiceInterface().getFlagMap();
        } else {
            db_rankingKinds = "saving-data/ranking_capital";
            scoreMap = UserApplication.getInstance().getServiceInterface().getCapitalMap();
        }
    }

    private void setFrontAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.frond_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB")
                .build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                AdRequest adRequest1 = new AdRequest.Builder()
                        //.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB")
                        .build();
                mInterstitialAd.loadAd(adRequest1);
            }
        });
    }

    public void showFrontAd() {
        frontAdCount++;
        if (frontAdCount > 2 && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            frontAdCount = 0;
        }
    }
}
