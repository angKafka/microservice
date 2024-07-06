package org.rdutta.hostel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;

@Entity
@Table(name = "ROOM")
@DynamicInsert
@DynamicUpdate
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ROOM_ID")
    private UUID roomId;

    @Column(name = "ROOM_NUMBER")
    private int roomNumber;

    @Column(name = "FLOOR")
    private int floor;

    @Column(name = "IS_EMPTY")
    private String isEmpty;

    @Column(name = "LEFT_BED")
    private int leftBed;
    @Column(name = "RIGHT_BED")
    private int rightBed;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSTEL_ID")
    private Hostel hostel;

    public Room() {}

    public Room(int roomNumber, int floor, String isEmpty, int leftBed, int rightBed) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.isEmpty = isEmpty;
        this.leftBed = leftBed;
        this.rightBed = rightBed;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(String isEmpty) {
        this.isEmpty = isEmpty;
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
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

    public void statusChange() {
        if(getLeftBed() == 1 && getRightBed() == 1) {
            setIsEmpty("FULL");
        }else if(getLeftBed() == 1 && getRightBed() == 0) {
            setIsEmpty("HALF-FULL");
        }else if(getLeftBed() == 0 && getRightBed() == 1) {
            setIsEmpty("HALF-FULL");
        }else{
            setIsEmpty("ZERO-MEMBER");
        }
    }
}
