package com.cinnamontoast.scavengerhunt.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Contact;
import com.cinnamontoast.scavengerhunt.R;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    ArrayList<Contact> contactsForRecycler;
    ContactListFormatter contactListFormatter;

    public ContactAdapter(ArrayList<Contact> contactsForRecycler, ContactListFormatter contactListFormatter) {
        this.contactsForRecycler = contactsForRecycler;
        this.contactListFormatter = contactListFormatter;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact, parent, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        view.setOnClickListener(v -> {
            contactListFormatter.contactFormatter(contactViewHolder.contact);
            contactListFormatter.contactHighlighter(view, contactViewHolder.contact);
        });
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.contact = contactsForRecycler.get(position);
        ((TextView) holder.itemView.findViewById(R.id.contactFragmentName)).setText(holder.contact.getName());
        ((TextView) holder.itemView.findViewById(R.id.contactFragmentPhoneNumber)).setText(holder.contact.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return contactsForRecycler.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{
        public Contact contact;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static interface ContactListFormatter{
        public void contactFormatter(Contact contact);
        public void contactHighlighter(View contactView, Contact contact);
    }
}
