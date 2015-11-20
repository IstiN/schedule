package com.github.istin.schedule.gson;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by uladzimir_klyshevich on 11/20/15.
 */
public class Lesson implements Serializable {
    @SerializedName("i")
    private String id;

    @SerializedName("s")
    private String timeStart;

    @SerializedName("e")
    private String timeEnd;

    @SerializedName("d")
    private String date;

    @SerializedName("t")
    private String title;

    @SerializedName("a")
    private String address;

    @SerializedName("r")
    private String room;

    public String getId() {
        return id;
    }

    public void setId(String pId) {
        id = pId;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String pTimeStart) {
        timeStart = pTimeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String pTimeEnd) {
        timeEnd = pTimeEnd;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String pDate) {
        date = pDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String pTitle) {
        title = pTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String pAddress) {
        address = pAddress;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String pRoom) {
        room = pRoom;
    }
}
