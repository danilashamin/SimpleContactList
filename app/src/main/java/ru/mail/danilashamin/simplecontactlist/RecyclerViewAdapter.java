package ru.mail.danilashamin.simplecontactlist;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.siyamed.shapeimageview.HeartImageView;
import com.github.siyamed.shapeimageview.StarImageView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static ru.mail.danilashamin.simplecontactlist.C.MALE_GENDER;

/**
 * Created by Danil on 06.03.2018 on 1:27.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContactViewHolder> {

    @Expose
    @SerializedName("results")
    private List<Contact> results;
    private Context context;
    private List<Contact> contactList;

    RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_element, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact currentContact = contactList.get(position);

        if (currentContact.getGender().equals(MALE_GENDER)) {
            holder.ivHeart.setMaxWidth(0);
        } else {
            holder.ivStar.setMaxWidth(0);
        }


    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public List<Contact> getResults() {
        return results;
    }

    public void setResults(List<Contact> results) {
        this.results = results;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        AppCompatTextView tvName;
        @BindView(R.id.ivStar)
        StarImageView ivStar;
        @BindView(R.id.ivHeart)
        HeartImageView ivHeart;

        ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
