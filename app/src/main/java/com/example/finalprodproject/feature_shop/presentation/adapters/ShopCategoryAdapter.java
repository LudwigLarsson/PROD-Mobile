package com.example.finalprodproject.feature_shop.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.CategoryItemBinding;
import com.example.finalprodproject.databinding.CircleItemBinding;
import com.example.finalprodproject.feature_main.data.RoadmapItem;
import com.example.finalprodproject.feature_roadmap.presentation.adapters.GraphAdapter;
import com.example.finalprodproject.feature_shop.data.models.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopCategoryAdapter extends RecyclerView.Adapter<ShopCategoryAdapter.ViewHolder> {
    private final List<Category> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int id);
    }

    public ShopCategoryAdapter(List<Category> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShopCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ShopCategoryAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCategoryAdapter.ViewHolder holder, int position) {
        holder.binding.categoryItem.setText(items.get(position).getTitle());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(items.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CategoryItemBinding binding;

        public ViewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

