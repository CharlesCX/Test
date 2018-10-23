package com.charles.testlist.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.charles.testlist.R;
import com.charles.testlist.models.Restaurant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    List<Restaurant> restaurantList;
    Context context;

        public ListViewAdapter(List<Restaurant> restaurantList, Context context) {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewAdapter.ViewHolder holder, int position) {
        final Restaurant restaurant = restaurantList.get(position);

        holder.name.setText(restaurantList.get(position).getName());
        Glide
                .with(context)
                .load(restaurant.getCoverImgUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.item_image)
        ImageView image;

        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }
    }
}
