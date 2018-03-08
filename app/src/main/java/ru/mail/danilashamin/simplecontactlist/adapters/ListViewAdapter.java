package ru.mail.danilashamin.simplecontactlist.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import ru.mail.danilashamin.simplecontactlist.R;
import ru.mail.danilashamin.simplecontactlist.contact.Contact;
import ru.mail.danilashamin.simplecontactlist.photodrawable.BitmapMaskForm;
import ru.mail.danilashamin.simplecontactlist.photodrawable.MaskedDrawableBitmap;

/**
 * Created by Danil on 08.03.2018 on 13:09.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<Contact> contactsList;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<Contact> contactsList) {
        super();
        this.contactsList = contactsList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.layout_contact_list_element, parent, false);
        }

        Contact currentContact = contactsList.get(position);

        ((AppCompatTextView) view.findViewById(R.id.tvName)).setText(String.format("%s %s %s", currentContact.getName().getTitle(),
                currentContact.getName().getFirstName(),
                currentContact.getName().getLastName()));

        final AppCompatImageView ivPhoto = view.findViewById(R.id.ivPhoto);
        final String gender = currentContact.getGender();
        Glide
                .with(ivPhoto)
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
                        maskedBitmap.setMaskBitmap(BitmapFactory.decodeResource(ivPhoto.getContext().getResources(),
                                BitmapMaskForm.getMaskForm(gender) == BitmapMaskForm.STAR ? R.drawable.star_mask : R.drawable.heart_mask));
                        ivPhoto.setImageDrawable(maskedBitmap);
                        return false;
                    }
                }).submit();
        return view;
    }

}
