package com.bnawan.myrecyclerview;

import android.app.DownloadManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bnawan.myrecyclerview.model.Hero;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridHeroAdapter extends RecyclerView.Adapter<GridHeroAdapter.GridViewHolder> {
    private ArrayList<Hero> listHero;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }
    public GridHeroAdapter(ArrayList<Hero> list) {
        this.listHero = list;
    }
    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_hero, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(listHero.get(position).getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Hero data);
    }
}
