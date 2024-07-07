package org.rdutta.hostel.mapper;

import org.rdutta.hostel.dto.HostelDTO;
import org.rdutta.hostel.dto.RoomDTO;
import org.rdutta.hostel.http.HostelRequest;

import org.rdutta.hostel.entity.Hostel;
import org.rdutta.hostel.entity.Room;
import org.rdutta.hostel.http.RoomRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HostelMapper {
    public Hostel convertToHostel(HostelRequest hostelRequest) {
        List<Room> rooms = hostelRequest.rooms().stream()
                .map(this::convertToRoom)
                .collect(Collectors.toList());

        return new Hostel(hostelRequest.hostelName(), rooms);
    }

    private Room convertToRoom(RoomRequest roomRequest) {
        return new Room(roomRequest.roomNumber(), roomRequest.floor(), roomRequest.isEmpty(), roomRequest.leftBed(), roomRequest.rightBed());
    }

    public List<RoomDTO> convertRoomsToDTOs(List<Room> rooms) {
        List<RoomDTO> roomDTOs = new ArrayList<>();
        for (Room room : rooms) {
            RoomDTO roomDTO = new RoomDTO(room.getRoomId(), room.getRoomNumber(), room.getFloor(), room.getIsEmpty(), room.getLeftBed(), room.getRightBed()); // Assuming these are room properties
            roomDTOs.add(roomDTO);
        }
        return roomDTOs;
    }

    public HostelDTO convertToDTO(Hostel hostel) {
        List<RoomDTO> roomDTOs = hostel.getRooms().stream()
                .map(room -> new RoomDTO(room.getRoomId(), room.getRoomNumber(), room.getFloor(), room.getIsEmpty(), room.getLeftBed(), room.getRightBed()))
                .collect(Collectors.toList());
        return new HostelDTO(hostel.getHostelId(), hostel.getHostelName(), roomDTOs);
    }
}
