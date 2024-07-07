package org.rdutta.hostel.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class RoomDTO {
    private UUID room_id;
    private int room_number;
    private int floor;
    private String isEmpty;

    public RoomDTO() {}

    @JsonCreator
    public RoomDTO(
            @JsonProperty("room_id")UUID room_id,
            @JsonProperty("room_number")int room_number,
            @JsonProperty("floor") int floor,
            @JsonProperty("isEmpty") String isEmpty) {
        this.room_id = room_id;
        this.room_number = room_number;
        this.floor = floor;
        this.isEmpty = isEmpty;
    }

    public UUID getRoom_id() {
        return room_id;
    }

    public void setRoom_id(UUID room_id) {
        this.room_id = room_id;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
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
}
