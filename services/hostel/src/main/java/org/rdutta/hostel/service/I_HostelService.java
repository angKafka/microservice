package org.rdutta.hostel.service;

import org.rdutta.hostel.dto.HostelDTO;
import org.rdutta.hostel.http.HostelRequest;
import org.rdutta.hostel.http.RoomRequest;

import java.util.List;
import java.util.UUID;

public interface I_HostelService {
    UUID saveHostel(HostelRequest hostelRequest);
    void deleteHostel(UUID hostelId);
    String updateHostel(UUID hostelId, HostelRequest hostelRequest);
    String updateRoom(UUID roomId, RoomRequest roomRequest);
    HostelDTO getHostel(UUID hostelId);
    List<HostelDTO> getHostels();
}
