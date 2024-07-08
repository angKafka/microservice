package org.rdutta.student.dto;

public class RoomBedRequest{
    private int leftBed;
    private int rightBed;

    public RoomBedRequest() {}

    public RoomBedRequest(int leftBed, int rightBed) {
        this.leftBed = leftBed;
        this.rightBed = rightBed;
    }


    public int getLeftBed() {
        return leftBed;
    }

    public void setLeftBed(int leftBed) {
        this.leftBed = leftBed;
    }

    public int getRightBed() {
        return rightBed;
    }

    public void setRightBed(int rightBed) {
        this.rightBed = rightBed;
    }
}

