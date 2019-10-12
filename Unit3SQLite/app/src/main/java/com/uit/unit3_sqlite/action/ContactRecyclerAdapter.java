package com.uit.unit3_sqlite.action;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.uit.unit3_sqlite.R;

import java.util.ArrayList;

interface ContactItemClickedListener{
    void onItemClicked(int id, int position);
}

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ContactViewHolder>{
    ArrayList<Contact> contacts;
    ContactItemClickedListener onListener;


    public ContactRecyclerAdapter(ArrayList<Contact> contacts, ContactItemClickedListener onListener) {
        this.contacts = contacts;
        this.onListener = onListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
        holder.bindData(contacts.get(position), onListener);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void addData(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    void addItemData(Contact contact){
        this.contacts.add(contact);
        notifyItemInserted(contacts.size());
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView id, name, phoneNumber;

        ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id_contact);
            name = itemView.findViewById(R.id.tv_name);
            phoneNumber = itemView.findViewById(R.id.tv_phone_number);
        }

        void bindData(Contact item, final ContactItemClickedListener onListener){
            id.setText(item.getId() + "");
            name.setText(item.getName());
            phoneNumber.setText(item.getPhoneNumber());

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onListener.onItemClicked(contacts.get(getAdapterPosition()).getId(), getAdapterPosition());
                    contacts.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
