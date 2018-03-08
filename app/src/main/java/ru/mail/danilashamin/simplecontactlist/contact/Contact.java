package ru.mail.danilashamin.simplecontactlist.contact;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact implements Parcelable {
    @Expose
    @SerializedName("picture")
    private Picture picture;
    @Expose
    @SerializedName("name")
    private Name name;
    @Expose
    @SerializedName("gender")
    private String gender;



    public Picture getPicture() {
        return picture;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(picture);
        dest.writeSerializable(name);
        dest.writeString(gender);
    }

    private Contact(Parcel in) {
        picture = (Picture) in.readSerializable();
        name = (Name) in.readSerializable();
        gender = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
