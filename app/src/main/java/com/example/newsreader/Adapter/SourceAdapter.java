package com.example.newsreader.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import com.example.newsreader.API.ApiManagerIcon;
import com.example.newsreader.Model.IconSource.IconResponse;
import com.example.newsreader.Model.SourcesResponse.SourcesItem;
import com.example.newsreader.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {
    List<SourcesItem> sourcesItems;
    onItemClickListener onTextClickListener;

    public void setOnTextClickListener(onItemClickListener onTextClickListener) {
        this.onTextClickListener = onTextClickListener;
    }

    public SourceAdapter(List<SourcesItem> sourcesItems) {
        this.sourcesItems = sourcesItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.source_layout , viewGroup , false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int pos) {
        final SourcesItem sourcesItem = sourcesItems.get(pos);
        ApiManagerIcon.getIconAPIs().getIcon(sourcesItems.get(pos).getUrl())
                .enqueue(new Callback<IconResponse>() {
                    @Override
                    public void onResponse(Call<IconResponse> call, Response<IconResponse> response) {
                        if(response.isSuccessful()){
                            Log.e("error",response.toString());

                            if(response.body().getIcons().size()>0){
                                Log.e("url",response.body().getIcons().get(0).getUrl());
                            Glide.with(viewHolder.itemView)
                                    .load(response.body().getIcons().get(0).getUrl())
                                    .into(viewHolder.sourceImage);
                            }
                        }

                        }

                    @Override
                    public void onFailure(Call<IconResponse> call, Throwable t) {
                        Log.e("error",t.getLocalizedMessage());

                    }
                });

        viewHolder.sourceName.setText(sourcesItem.getName());
        if (onTextClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTextClickListener.onItemClick(pos, sourcesItem);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (sourcesItems==null)return 0;
        return sourcesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView sourceImage;
        TextView sourceName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sourceImage = itemView.findViewById(R.id.source_image);
            sourceName = itemView.findViewById(R.id.source_title);
        }
    }

    public interface onItemClickListener{
        void onItemClick(int position, SourcesItem sourcesItem);
    }
}
