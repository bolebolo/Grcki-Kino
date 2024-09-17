package com.app.grckikino.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RoundsModel implements Parcelable {

    private long gameId, drawId, drawTime, visualDraw;
    private int drawBreak; // pretpostavljam da je drawBreak  broj na kome se kolo zaustavlja tj na kome je dobitnik izvucen i ne bi trebao da prevazilazi kapacitet int-a
    private String status;

    private WinningNumbers winningNumbers;


    public RoundsModel(long gameId, long drawId, long drawTime, int visualDraw, int drawBreak, String status) {
        this.gameId = gameId;
        this.drawId = drawId;
        this.drawTime = drawTime;
        this.visualDraw = visualDraw;
        this.drawBreak = drawBreak;
        this.status = status;
    }

    @SuppressWarnings("unused")
    public RoundsModel() {
    }
    public RoundsModel(long drawId, long drawTime) {
        this.drawId=drawId;
        this.drawTime=drawTime;
    }

    protected RoundsModel(Parcel in) {
        gameId = in.readLong();
        drawId = in.readLong();
        drawTime = in.readLong();
        visualDraw = in.readLong();
        drawBreak = in.readInt();
        status = in.readString();
        winningNumbers = in.readParcelable(WinningNumbers.class.getClassLoader());
    }

    public static final Creator<RoundsModel> CREATOR = new Creator<RoundsModel>() {
        @Override
        public RoundsModel createFromParcel(Parcel in) {
            return new RoundsModel(in);
        }

        @Override
        public RoundsModel[] newArray(int size) {
            return new RoundsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(gameId);
        dest.writeLong(drawId);
        dest.writeLong(drawTime);
        dest.writeLong(visualDraw);
        dest.writeInt(drawBreak);
        dest.writeString(status);
        dest.writeParcelable(winningNumbers,0);
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getDrawId() {
        return drawId;
    }

    public void setDrawId(long drawId) {
        this.drawId = drawId;
    }

    public long getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(long drawTime) {
        this.drawTime = drawTime;
    }

    public long getVisualDraw() {
        return visualDraw;
    }

    public void setVisualDraw(long visualDraw) {
        this.visualDraw = visualDraw;
    }

    public int getDrawBreak() {
        return drawBreak;
    }

    public void setDrawBreak(int drawBreak) {
        this.drawBreak = drawBreak;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WinningNumbers getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }
}