package ru.mail.danilashamin.simplecontactlist.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.siyamed.shapeimageview.HeartImageView;
import com.github.siyamed.shapeimageview.StarImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mail.danilashamin.simplecontactlist.R;
import ru.mail.danilashamin.simplecontactlist.contact.Contact;
import ru.mail.danilashamin.simplecontactlist.contact.Name;

import static ru.mail.danilashamin.simplecontactlist.C.MALE_GENDER;

/**
 * Created by Danil on 06.03.2018 on 1:27.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContactViewHolder> {

    private List<Contact> contactList;

    public RecyclerViewAdapter(List<Contact> contactList) {
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

        setImageViewInvisible(currentContact, holder);
        Name name = currentContact.getName();
        holder.tvName.setText(String.format("%s %s %s", name.getTitle(), name.getFirstName(), name.getLastName()));

        Glide
                .with(holder.itemView)
                .load(currentContact.getPicture().getLarge())
                .apply(new RequestOptions().
                        placeholder(R.drawable.progress_animation).
                        error(R.drawable.error)
                        .centerCrop())
                .into(getImageView(currentContact, holder));
    }

    private void setImageViewInvisible(Contact contact, ContactViewHolder holder) {
        if (contact.getGender().equals(MALE_GENDER)) {
            ((ViewGroup) holder.itemView).removeView(holder.ivHeart);
        } else {
            ((ViewGroup) holder.itemView).removeView(holder.ivStar);
        }
    }

    private ImageView getImageView(Contact contact, ContactViewHolder holder) {
        if (contact.getGender().equals(MALE_GENDER)) {
            return holder.ivStar;
        } else {
            return holder.ivHeart;
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
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
