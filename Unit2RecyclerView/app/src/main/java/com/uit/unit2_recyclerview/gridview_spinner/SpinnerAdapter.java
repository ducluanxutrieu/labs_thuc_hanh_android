package com.uit.unit2_recyclerview.gridview_spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.uit.unit2_recyclerview.R;


public class SpinnerAdapter extends ArrayAdapter<Thumbnail> {
    SpinnerAdapter(@NonNull Context context, int resource, Thumbnail[] list) {
        super(context, resource, list);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return rowView(convertView,position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return rowView(convertView,position);
    }

    private View rowView(View convertView , int position){
        ViewHolder holder ;
        View rowView = convertView;
        if (rowView==null) {

            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            rowView = inflater.inflate(R.layout.item_spinner_dish, null, false);

            holder.tile = rowView.findViewById(R.id.item_spinner_name);
            holder.image = rowView.findViewById(R.id.item_spinner_thumbnail);
            rowView.setTag(holder);
        }else{
            holder = (ViewHolder) rowView.getTag();
        }
//        holder.image.setImageResource(getItem(position).getImg());
        Glide.with(getContext()).load(getItem(position).getImg()).apply(RequestOptions.centerCropTransform()).into(holder.image);
        holder.tile.setText(getItem(position).getName());

        return rowView;
    }

    private class ViewHolder {
        TextView tile;
        ImageView image;
    }
}
