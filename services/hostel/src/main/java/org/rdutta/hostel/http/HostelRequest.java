package org.rdutta.hostel.http;

import java.util.List;

public record HostelRequest(
        String hostelName,
        List<RoomRequest> rooms
) {
}
