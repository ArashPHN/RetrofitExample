package com.example.arash.baninab;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import com.example.arash.baninab.data.contactsData.Contact;

/**
 * Created by Arash on 8/13/2017.
 */
 class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Contact> mContacts;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;
        public Button btnCall ;
        PostItemListener mItemListener;

        public ViewHolder(final View itemView, PostItemListener postItemListener) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_name);
            btnCall = (Button) itemView.findViewById(R.id.btn_phone);
            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getContact(getAdapterPosition()).getPhone().getMobile()));
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                        return;
                    mContext.startActivity(callIntent);

//                    Toast.makeText(mContext, getContact(getAdapterPosition()).getName() , Toast.LENGTH_SHORT).show();
                }
            });
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Contact contact = getContact(getAdapterPosition());
            this.mItemListener.onPostClick(contact);
            notifyDataSetChanged();
        }
    }

    public RecyclerViewAdapter(Context context, List<Contact> posts, PostItemListener itemListener) {
        mContacts = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.contact, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        Contact contact = mContacts.get(position);
        TextView textView = holder.name;
        textView.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public void updateAnswers(List<Contact> contacts) {
        mContacts = contacts;
        notifyDataSetChanged();
    }

    private Contact getContact(int adapterPosition) {
        return mContacts.get(adapterPosition);
    }

    public interface PostItemListener {

        void onPostClick(Contact contact);
    }

    public Contact getNumberOfContact(String id ){
        for(Contact con : mContacts)
            if (con.isContact(id))
                return con ;

        return null ;
    }
}