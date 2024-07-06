package org.rdutta.hostel.http;

public record RoomRequest(
        int roomNumber,
        int floor,
        String isEmpty,
        int leftBed,
        int rightBed
) {
}
