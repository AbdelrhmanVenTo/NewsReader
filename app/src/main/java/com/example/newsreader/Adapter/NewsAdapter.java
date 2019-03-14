package com.example.newsreader.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsreader.Model.NewsResponse.ArticlesItem;
import com.example.newsreader.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    List<ArticlesItem> newsList ;
    onItemClickListener onNewsClickListener;

    public void setOnNewsClickListener(onItemClickListener onNewsClickListener) {
        this.onNewsClickListener = onNewsClickListener;
    }

    public NewsAdapter(List<ArticlesItem> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item_news,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {
        final ArticlesItem news = newsList.get(pos);

        viewHolder.title.setText(news.getTitle());
        viewHolder.date.setText(news.getPublishedAt());
        if(news.getSource()==null)
            viewHolder.source.setText(news.getSourceName());
        else
            viewHolder.source.setText(news.getSource().getName());

        Glide.with(viewHolder.itemView)
                .load(news.getUrlToImage())
                .into(viewHolder.image);

        if (onNewsClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNewsClickListener.onItemClick(pos, news);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        if(newsList==null) return 0;
        return newsList.size();
    }


    public void changeData(List<ArticlesItem> items ){
        this.newsList=items;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,source,date;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            source = itemView.findViewById(R.id.source_name);
            date = itemView.findViewById(R.id.date);
            image = itemView.findViewById(R.id.imageView);
        }
    }

    public interface onItemClickListener{
        void onItemClick(int position, ArticlesItem articlesItem);
    }
}
