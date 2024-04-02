package com.example.finalprodproject.feature_shop.presentation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalprodproject.databinding.ShopCoursesItemBinding;
import com.example.finalprodproject.feature_shop.data.models.CourseShopModel;

import java.util.List;

public class ShopCoursesAdapter extends RecyclerView.Adapter<ShopCoursesAdapter.ViewHolder> {
    private final List<CourseShopModel> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CourseShopModel courseShopModel);
    }

    public ShopCoursesAdapter(List<CourseShopModel> items) {
        this.items = items;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ShopCoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShopCoursesItemBinding binding = ShopCoursesItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ShopCoursesAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCoursesAdapter.ViewHolder holder, int position) {
        holder.binding.shopCourseTitle.setText(items.get(position).getTitle());
        holder.binding.shopCourseBtn.setText("-" + Integer.toString(items.get(position).getPrice()));
        holder.binding.shopCourseDescription.setText(items.get(position).getDescription());
        Glide.with(holder.itemView).load(items.get(position).getImage()).into(holder.binding.shopCourseImage);

        holder.binding.shopCourseBtn.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(items.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ShopCoursesItemBinding binding;

        public ViewHolder(ShopCoursesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


