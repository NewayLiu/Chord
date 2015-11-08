package com.thechord.chord.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.thechord.chord.util.JsonFieldAnnotation;

/**
 * Created by Neway on 2015/11/8.
 */
public class Actor implements Parcelable {

    private String id;
    private String name;
    private String alt;

    @JsonFieldAnnotation(className = "com.thechord.chord.entity.Image")
    private Image avatars;

    public Actor (){

    }

    protected Actor(Parcel in) {
        id = in.readString();
        name = in.readString();
        alt = in.readString();
        avatars = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Creator<Actor> CREATOR = new Creator<Actor>() {
        @Override
        public Actor createFromParcel(Parcel in) {
            return new Actor(in);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Image getAvatars() {
        return avatars;
    }

    public void setAvatars(Image avatars) {
        this.avatars = avatars;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(alt);
        dest.writeParcelable(avatars, flags);
    }
}
