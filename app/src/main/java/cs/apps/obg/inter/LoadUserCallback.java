package cs.apps.obg.inter;

import java.util.List;

import cs.apps.obg.domain.RankingScoreVO;

/**
 * Created by d1jun on 2018-02-02.
 */

public interface LoadUserCallback {
    public void onUserLoaded(String nickName);
    public void onRankLoaded(List<RankingScoreVO> list);
}
