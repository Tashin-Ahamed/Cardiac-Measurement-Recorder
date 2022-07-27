package com.example.cardiacmeasurementmanager;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelClass implements Parcelable, Comparable<ModelClass> {

    private String date="";
    private String time="";
    private int systolic=0;
    private int diastolic=0;
    private int heartRate=0;
    private String comment="";


    public ModelClass(String date, String time, int systolic, int diastolic, int heart_rate, String comment) {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heart_rate;
        this.comment = comment;
    }

    public ModelClass(String date, String time, int systolic, int diastolic, int heart_rate) {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heart_rate;
        this.comment = "";
    }
    public ModelClass(){}

    protected ModelClass(Parcel in) {
        date = in.readString();
        time = in.readString();
        systolic = in.readInt();
        diastolic = in.readInt();
        heartRate = in.readInt();
        comment = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeInt(systolic);
        dest.writeInt(diastolic);
        dest.writeInt(heartRate);
        dest.writeString(comment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelClass> CREATOR = new Creator<ModelClass>() {
        @Override
        public ModelClass createFromParcel(Parcel in) {
            return new ModelClass(in);
        }

        @Override
        public ModelClass[] newArray(int size) {
            return new ModelClass[size];
        }
    };

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getSystolic() {
        return systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public String getComment() {
        return comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public void setHeart_rate(int heart_rate) {
        this.heartRate = heart_rate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(ModelClass record) {
        return this.date.compareTo(record.getDate());
    }

}
