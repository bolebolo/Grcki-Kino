package com.app.grckikino.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TicketNumberModel implements Parcelable {


    private int number, isSelected;



    public TicketNumberModel(int number, int isSelected) {
        this.number = number;
        this.isSelected = isSelected;
    }

    @SuppressWarnings("unused")
    public TicketNumberModel() {
    }

    protected TicketNumberModel(Parcel in) {
        number = in.readInt();
        isSelected = in.readInt();

    }

    public static final Creator<TicketNumberModel> CREATOR = new Creator<TicketNumberModel>() {
        @Override
        public TicketNumberModel createFromParcel(Parcel in) {
            return new TicketNumberModel(in);
        }

        @Override
        public TicketNumberModel[] newArray(int size) {
            return new TicketNumberModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeInt(isSelected);

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

}
