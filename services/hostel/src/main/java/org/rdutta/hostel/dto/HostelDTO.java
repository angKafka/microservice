package org.rdutta.hostel.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class HostelDTO {
    private UUID hostel_id;
    private String hostel_name;
    private List<RoomDTO> roomDTOS;
public HostelDTO() {}

    @JsonCreator
    public HostelDTO(@JsonProperty("hostel_id") UUID hostel_id,
                     @JsonProperty("hostel_name") String hostel_name,
                     @JsonProperty("rooms") List<RoomDTO> roomDTOS) {
        this.hostel_id = hostel_id;
        this.hostel_name = hostel_name;
        this.roomDTOS = roomDTOS;
    }

    public UUID getHostel_id() {
        return hostel_id;
    }

    public void setHostel_id(UUID hostel_id) {
        this.hostel_id = hostel_id;
    }

    public String getHostel_name() {
        return hostel_name;
    }

    public void setHostel_name(String hostel_name) {
        this.hostel_name = hostel_name;
    }

    public List<RoomDTO> getRoomDTOS() {
        return roomDTOS;
    }

    public void setRoomDTOS(List<RoomDTO> roomDTOS) {
        this.roomDTOS = roomDTOS;
    }
}
