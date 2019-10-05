package com.uit.unit2_recyclerview;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {
//    private List<String> listName = Arrays.asList("Teo", "Ty", "Bin", "Bo");
//    ArrayList<String> mListName = new ArrayList<>(listName);
    ArrayList<Employee> mListNV = new ArrayList<>();
    MainItemClicked itemClicked;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_main, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        holder.bindItemData(mListNV.get(position));
    }

    @Override
    public int getItemCount() {
        return mListNV.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView name, maNV, salary, typeJob;
        Boolean isItemClicked = false;
        ItemViewHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            maNV = itemView.findViewById(R.id.tv_manv);
            salary = itemView.findViewById(R.id.tv_salary);
            typeJob = itemView.findViewById(R.id.tv_type);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isItemClicked){
                        isItemClicked = true;
                        itemView.setBackgroundColor(itemView.getContext().getColor(R.color.green));
                    }else {
                        isItemClicked = false;
                        itemView.setBackgroundColor(itemView.getContext().getColor(R.color.colorWhile));
                    }
                    itemClicked.onItemClicked(getAdapterPosition(), name.getText().toString());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListNV.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });
        }

        void bindItemData(Employee item){
            String _salary = item.tinhLuong() + "";
            name.setText(item.name);
            maNV.setText(item.id);
            salary.setText(_salary);
            typeJob.setText(item.type());
        }
    }
}
