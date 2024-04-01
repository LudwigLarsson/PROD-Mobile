package com.example.finalprodproject.feature_shop.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalprodproject.R;
import com.example.finalprodproject.databinding.CategoryItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopCategoryAdapter extends RecyclerView.Adapter<ShopCategoryAdapter.ViewHolder> {
    private final List<String> items;
    private OnItemClickListener listener;
    private int selectedItem = RecyclerView.NO_POSITION;

    public interface OnItemClickListener {
        void onItemClick(String category);
    }

    public ShopCategoryAdapter(List<String> items) {
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
        holder.binding.categoryItem.setText(items.get(position));


        int adapterPosition = holder.getAbsoluteAdapterPosition();
        if (adapterPosition == selectedItem) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.color_card_mark_text));
        }
        else holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.white));

        holder.itemView.setOnClickListener(v -> {
            selectedItem = holder.getAbsoluteAdapterPosition();
            notifyDataSetChanged();
            if (listener != null) listener.onItemClick(items.get(position));
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

