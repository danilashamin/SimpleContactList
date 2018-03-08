package ru.mail.danilashamin.simplecontactlist.adapters;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mail.danilashamin.simplecontactlist.R;
import ru.mail.danilashamin.simplecontactlist.http.contact.Contact;
import ru.mail.danilashamin.simplecontactlist.view.BitmapMaskForm;
import ru.mail.danilashamin.simplecontactlist.view.MaskedDrawableBitmap;

/**
 * Created by Danil on 06.03.2018 on 1:27.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ContactViewHolder> {

    private List<Contact> contactList;

    public RecyclerViewAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_element, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactViewHolder holder, int position) {
        final Contact currentContact = contactList.get(position);

        holder.tvName.setText(String.format("%s %s %s", currentContact.getName().getTitle(),
                currentContact.getName().getFirstName(),
                currentContact.getName().getLastName()));

        Glide
                .with(holder.itemView)
                .load(currentContact.getPicture().getLargePictureUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        MaskedDrawableBitmap maskedBitmap = new MaskedDrawableBitmap();
                        maskedBitmap.setPictureBitmap(((BitmapDrawable) resource.getCurrent()).getBitmap());
                        maskedBitmap.setMaskBitmap(BitmapFactory.decodeResource(holder.itemView.getContext().getResources(),
                                BitmapMaskForm.getMaskForm(currentContact.getGender()) == BitmapMaskForm.STAR ? R.drawable.star_mask : R.drawable.heart_mask));
                        holder.ivPhoto.setImageDrawable(maskedBitmap);
                        return false;
                    }
                }).submit();
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }


    static class ContactViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        AppCompatTextView tvName;
        @BindView(R.id.ivPhoto)
        AppCompatImageView ivPhoto;

        ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
