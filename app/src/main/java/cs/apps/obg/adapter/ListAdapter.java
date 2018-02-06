package cs.apps.obg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cs.apps.obg.R;
import cs.apps.obg.domain.FlagVO;

/**
 * Created by d1jun on 2018-01-30.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {
    private ArrayList<FlagVO> mItems;
    private Context mContext;
    private ItemClick itemClick;
    public interface ItemClick {
        public void onClick(View view, int position);
    }
    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public ListAdapter(ArrayList<FlagVO> items, Context context) {
        mItems = items;
        mContext = context;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final int Position = position;
        ((ItemViewHolder) holder).setCountryItem(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {
                    itemClick.onClick(v, Position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(ArrayList<FlagVO> items) {
        mItems.clear();
        mItems = items;
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView flagImage;
        TextView country, capital, continent;
        LinearLayout continentLayout;
        public ItemViewHolder(View view) {
            super(view);
            this.view = view;
            flagImage = (ImageView) view.findViewById(R.id.flag_image);
            country = (TextView) view.findViewById(R.id.country_text);
            capital = (TextView) view.findViewById(R.id.capital_text);
            continent = (TextView) view.findViewById(R.id.continent_text);
            continentLayout = (LinearLayout) view.findViewById(R.id.continent_layout);
        }

        public void setCountryItem(int position) {
            Glide.with(mContext).load(mItems.get(position).getResId()).into(flagImage);
            country.setText(mItems.get(position).getCountry());
            capital.setText(mItems.get(position).getCapital());
        }
    }
}
