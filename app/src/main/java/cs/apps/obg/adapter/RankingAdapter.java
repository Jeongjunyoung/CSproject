package cs.apps.obg.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs.apps.obg.R;
import cs.apps.obg.domain.RankingScoreVO;
import cs.apps.obg.service.UserApplication;

/**
 * Created by d1jun on 2018-02-03.
 */

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankItemViewHolder>{
    private List<RankingScoreVO> mItems = new ArrayList<>();
    private Context mConext;

    public RankingAdapter(Context context) {
        mConext = context;
    }
    @Override
    public RankItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);
        return new RankItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RankItemViewHolder holder, int position) {
        ((RankItemViewHolder) holder).setRankItem(position);
    }

    public void setItems(List<RankingScoreVO> items) {
        mItems.clear();
        mItems = items;
        System.out.println(mItems.size());
    }

    public void clearList() {
        mItems.clear();
    }
    public void addItem(RankingScoreVO vo) {
        mItems.add(vo);
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class RankItemViewHolder extends RecyclerView.ViewHolder{
        TextView nickName, score, ranking;
        LinearLayout itemLayout;
        public RankItemViewHolder(View view) {
            super(view);
            nickName = (TextView) view.findViewById(R.id.rank_nickname);
            score = (TextView) view.findViewById(R.id.rank_score);
            ranking = (TextView) view.findViewById(R.id.rank_ranking);
            itemLayout = (LinearLayout) view.findViewById(R.id.ranking_item_layout);
        }

        public void setRankItem(int i) {
            if (i % 2 != 0) {
                itemLayout.setBackgroundColor(Color.parseColor("#dedede"));
                nickName.setTextColor(Color.parseColor("#282828"));
                score.setTextColor(Color.parseColor("#ec6262"));
            } else {
                itemLayout.setBackgroundColor(Color.parseColor("#4b4a4a"));
                nickName.setTextColor(Color.parseColor("#ffffff"));
                score.setTextColor(Color.parseColor("#ec6262"));
            }
            if (mItems.get(i).getNickName().equals(UserApplication.getInstance().getServiceInterface().getNickname())) {
                itemLayout.setBackgroundColor(Color.parseColor("#cf5e61"));
                score.setTextColor(Color.parseColor("#ffffff"));
            }
            nickName.setText(mItems.get(i).getNickName());
            score.setText(mItems.get(i).getScore());
            ranking.setText(String.valueOf(i+1));
        }
    }
}
