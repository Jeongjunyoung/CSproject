package cs.apps.obg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs.apps.obg.R;
import cs.apps.obg.domain.RankingScoreVO;

/**
 * Created by d1jun on 2018-02-03.
 */

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankItemViewHolder>{
    private List<RankingScoreVO> mItems = new ArrayList<>();
    private Context mConext;

    public RankAdapter(Context context) {
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
        public RankItemViewHolder(View view) {
            super(view);
            nickName = (TextView) view.findViewById(R.id.rank_nickname);
            score = (TextView) view.findViewById(R.id.rank_score);
            ranking = (TextView) view.findViewById(R.id.rank_ranking);
        }

        public void setRankItem(int i) {
            nickName.setText(mItems.get(i).getNickName());
            score.setText(mItems.get(i).getScore());
            ranking.setText(String.valueOf(i+1));
        }
    }
}
