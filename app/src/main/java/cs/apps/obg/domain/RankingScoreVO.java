package cs.apps.obg.domain;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by d1jun on 2018-02-03.
 */

public class RankingScoreVO {
    private String nickName;
    private String score;
    public RankingScoreVO(){}

    public RankingScoreVO(String nickName, String score) {
        this.nickName = nickName;
        this.score = score;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
