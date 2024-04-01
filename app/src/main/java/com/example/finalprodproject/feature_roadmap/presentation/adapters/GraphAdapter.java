package com.example.finalprodproject.feature_roadmap.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.CircleItemBinding;
import com.example.finalprodproject.feature_main.data.RoadmapItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.ViewHolder> {
    private final List<RoadmapItem> items;
    private final List<Integer> drawablesList = new ArrayList<>();

    public GraphAdapter(List<RoadmapItem> items) {
        this.items = items;

        drawablesList.add(R.drawable.color_border_1);
        drawablesList.add(R.drawable.color_border_2);
        drawablesList.add(R.drawable.color_border_3);
        drawablesList.add(R.drawable.color_border_4);
    }

    @NonNull
    @Override
    public GraphAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CircleItemBinding binding = CircleItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GraphAdapter.ViewHolder holder, int position) {
//        holder.setIsRecyclable(false);
        holder.hideElements();
        int indexRandom = new Random().nextInt(drawablesList.size());
        if (position % 2 == 0) {
                holder.binding.circleTitleFirst.setVisibility(View.VISIBLE);
                holder.binding.circleImageFirst.setVisibility(View.VISIBLE);
                if (position + 1 != items.size()) holder.binding.circleRightFirst.setVisibility(View.VISIBLE);
                holder.binding.circleRightPagerFirst.setVisibility(View.VISIBLE);

                holder.binding.circleTitleFirst.setText(items.get(position).getText());
                holder.binding.circleRightPagerFirst.setText(Integer.toString(items.get(position).getScores()));

                holder.binding.circleImageFirst.setBackgroundResource(drawablesList.get(indexRandom));
                Glide.with(holder.binding.getRoot()).load(items.get(position).getImage()).into(holder.binding.circleImageFirst);
        } else {
                holder.binding.circleTitleSecond.setVisibility(View.VISIBLE);
                holder.binding.circleImageSecond.setVisibility(View.VISIBLE);
                if (position + 1 != items.size()) holder.binding.circleLeftSecond.setVisibility(View.VISIBLE);
                holder.binding.circleRightPagerSecond.setVisibility(View.VISIBLE);

                holder.binding.circleTitleSecond.setText(items.get(position).getText());
                holder.binding.circleRightPagerSecond.setText(Integer.toString(items.get(position).getScores()));

                holder.binding.circleImageSecond.setBackgroundResource(drawablesList.get(indexRandom));
                Glide.with(holder.binding.getRoot()).load(items.get(position).getImage()).into(holder.binding.circleImageSecond);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleItemBinding binding;

        public ViewHolder(CircleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void hideElements() {
            binding.circleTitleFirst.setVisibility(View.GONE);
            binding.circleImageFirst.setVisibility(View.GONE);
            binding.circleRightFirst.setVisibility(View.GONE);
            binding.circleRightPagerFirst.setVisibility(View.GONE);
            binding.circleTitleSecond.setVisibility(View.GONE);
            binding.circleImageSecond.setVisibility(View.GONE);
            binding.circleLeftSecond.setVisibility(View.GONE);
            binding.circleRightPagerSecond.setVisibility(View.GONE);

        }
    }
}

