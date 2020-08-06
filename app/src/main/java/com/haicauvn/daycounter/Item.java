package com.haicauvn.daycounter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Random;

public class Item  implements Parcelable {
    private String id;
    private String name;
    private Date date;
    private String Description;

    public Item(String name, Date date, String description) {
        Random rd = new Random();
        this.id = name.toLowerCase().substring(0,5) + Integer.toString(rd.nextInt());
        this.name = name;
        this.date = date;
        Description = description;
    }

    protected Item(Parcel in) {
        name = in.readString();
        Description = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(Description);
    }
}
