package com.example.cardiacmeasurementmanager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model Class
 */
public class ModelClass implements Parcelable, Comparable<ModelClass> {

    private String date="";
    private String time="";
    private int systolic=0;
    private int diastolic=0;
    private int heartRate=0;
    private String comment="";

    /**
     * data
     * @param date
     * @param time
     * @param systolic
     * @param diastolic
     * @param heart_rate
     * @param comment
     */
    public ModelClass(String date, String time, int systolic, int diastolic, int heart_rate, String comment) {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heart_rate;
        this.comment = comment;
    }

    /**
     * data
     * @param date
     * @param time
     * @param systolic
     * @param diastolic
     * @param heart_rate
     */
    public ModelClass(String date, String time, int systolic, int diastolic, int heart_rate) {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heart_rate;
        this.comment = "";
    }

    /**
     * model class
     */
    public ModelClass(){}

    /**
     * parcel in method
     * @param in reads string, integer values
     */
    protected ModelClass(Parcel in) {
        date = in.readString();
        time = in.readString();
        systolic = in.readInt();
        diastolic = in.readInt();
        heartRate = in.readInt();
        comment = in.readString();
    }

    /**
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeInt(systolic);
        dest.writeInt(diastolic);
        dest.writeInt(heartRate);
        dest.writeString(comment);
    }

    /**
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelClass> CREATOR = new Creator<ModelClass>() {
        /**
         *
         * @param in
         * @return
         */
        @Override
        public ModelClass createFromParcel(Parcel in) {
            return new ModelClass(in);
        }

        /**
         *
         * @param size
         * @return
         */
        @Override
        public ModelClass[] newArray(int size) {
            return new ModelClass[size];
        }
    };

    /**
     * method that gets date
     * @return the date user inserted
     */
    public String getDate() {
        return date;
    }

    /**
     * method that gets time
     * @return the time user inserted
     */
    public String getTime() {
        return time;
    }

    /**
     * method that gets systolic value
     * @return the systolic user inserted
     */
    public int getSystolic() {
        return systolic;
    }

    /**
     * method that gets diastolic value
     * @return the diastolic user inserted
     */
    public int getDiastolic() {
        return diastolic;
    }

    /**
     * method that gets heart rate
     * @return the heartrate user inserted
     */
    public int getHeartRate() {
        return heartRate;
    }

    /**
     * method that gets comment
     * @return the comment user inserted
     */
    public String getComment() {
        return comment;
    }

    /**
     * method that Sets date
     * @param date the date user inserted
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * method that Sets time
     * @param time the time user inserted
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * method that Sets systolic value
     * @param systolic the systolic user inserted
     */
    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    /**
     * method that Sets diastolic value
     * @param diastolic the diastolic user inserted
     */
    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    /**
     * method that Sets heart rate
     * @param heart_rate the heartrate user inserted
     */
    public void setHeart_rate(int heart_rate) {
        this.heartRate = heart_rate;
    }

    /**
     * method that Sets comment
     * @param comment the comment user inserted
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 
     * @param record
     * @return
     */
    @Override
    public int compareTo(ModelClass record) {
        return this.date.compareTo(record.getDate());
    }

}
