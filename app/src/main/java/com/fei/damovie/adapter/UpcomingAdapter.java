package com.fei.damovie.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fei.damovie.R;
import com.fei.damovie.helper.Const;
import com.fei.damovie.model.NowPlaying;
import com.fei.damovie.model.Upcoming;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {

    private Context context;
    private List<Upcoming.Results> upcomingList;

    public UpcomingAdapter(Context context) {
        this.context = context;
    }

    public List<Upcoming.Results> getUpcomingList() {
        return upcomingList;
    }

    public void setUpcomingList(List<Upcoming.Results> upcomingList) {
        this.upcomingList = upcomingList;
    }

    @NonNull
    @Override
    public UpcomingAdapter.UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);
        return new UpcomingAdapter.UpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.UpcomingViewHolder holder, int position) {
        final Upcoming.Results results = getUpcomingList().get(position);

        holder.movieCard_title_textView.setText(results.getTitle());
        holder.movieCard_overview_textView.setText(results.getOverview());

        Glide.with(context)
                .load(Const.IMG_URL+results.getPoster_path())
                .into(holder.movieCard_poster_imageView);

        holder.movieCard_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("movie_id",String.valueOf(results.getId()));
                Navigation.findNavController(view).navigate(R.id.action_upcomingFragment_to_movieDetailsFragment,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getUpcomingList().size();
    }

    public class UpcomingViewHolder extends RecyclerView.ViewHolder {

        CardView movieCard_cardView;
        TextView movieCard_title_textView,movieCard_overview_textView;
        ImageView movieCard_poster_imageView;

        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);

            movieCard_cardView = itemView.findViewById(R.id.movieCard_cardView);
            movieCard_title_textView = itemView.findViewById(R.id.movieCard_title_textView);
            movieCard_overview_textView = itemView.findViewById(R.id.movieCard_overview_textView);
            movieCard_poster_imageView = itemView.findViewById(R.id.movieCard_poster_imageView);

        }
    }
}
