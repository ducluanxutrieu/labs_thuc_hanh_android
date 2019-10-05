package com.uit.unit2_recyclerview.gridview_spinner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uit.unit2_recyclerview.R;

import java.util.ArrayList;

class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.GridViewHolder> {
    private ArrayList<DishModel> listDish = new ArrayList<>();

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.bindLayout(listDish.get(position));
    }

    @Override
    public int getItemCount() {
        return listDish.size();
    }

    void addDishItem(DishModel model) {
        listDish.add(0, model);
        notifyItemInserted(0);
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView mImageDish;
        AppCompatTextView mNameDish;
        AppCompatImageView mPromotion;

        GridViewHolder(@NonNull View itemView) {
            super(itemView);
            setupItemView();
        }

        private void setupItemView() {
            mImageDish = itemView.findViewById(R.id.iv_dish);
            mNameDish = itemView.findViewById(R.id.tv_dish_name);
            mPromotion = itemView.findViewById(R.id.iv_promotion);
        }

        void bindLayout(DishModel model) {
            //mImageDish.setImageResource(model.getImage());
            Glide.with(itemView.getContext()).load(model.getImage()).apply(RequestOptions.centerCropTransform()).into(mImageDish);
            mPromotion.setVisibility(model.isPromotion() ? View.VISIBLE : View.INVISIBLE);
            mNameDish.setText(model.getName());
        }
    }
}
