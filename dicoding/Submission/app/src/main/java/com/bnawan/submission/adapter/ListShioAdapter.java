package com.bnawan.submission.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bnawan.submission.DetailActivity;
import com.bnawan.submission.R;
import com.bnawan.submission.model.Shio;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListShioAdapter extends RecyclerView.Adapter<ListShioAdapter.CvHolder> {
    private ArrayList<Shio> listShio;
    private OnItemClickCallback onItemClickCallback;

    public ListShioAdapter(ArrayList<Shio> listShio) {
        this.listShio = listShio;
    }

    @NonNull
    @Override

    public ListShioAdapter.CvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shio_item, viewGroup, false);
        return new CvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListShioAdapter.CvHolder holder, int position) {
        Shio shio = listShio.get(position);

        Glide.with(holder.itemView.getContext())
                .load(shio.getImage())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imageShio);

        holder.nameShio.setText(shio.getName());
        holder.detailShio.setText(shio.getDetail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listShio.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return listShio.size();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public class CvHolder extends RecyclerView.ViewHolder {
        ImageView imageShio;
        TextView nameShio, detailShio;

        public CvHolder(@NonNull View itemView) {
            super(itemView);
            imageShio = itemView.findViewById(R.id.imageShio);
            nameShio = itemView.findViewById(R.id.nameShio);
            detailShio = itemView.findViewById(R.id.detailShio);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Shio data);
    }

}
