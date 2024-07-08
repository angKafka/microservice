package org.rdutta.hostel.controller;

import org.rdutta.hostel.dto.HostelDTO;
import org.rdutta.hostel.http.HostelRequest;
import org.rdutta.hostel.entity.Hostel;
import org.rdutta.hostel.http.RoomBedRequest;
import org.rdutta.hostel.http.RoomRequest;
import org.rdutta.hostel.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hostels")
public class HostelController {

    private final HostelService hostelService;

    @Autowired
    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @PostMapping("/add")
    public ResponseEntity<UUID> createHostel(@RequestBody HostelRequest hostelRequest) {
        UUID hostel_id = hostelService.saveHostel(hostelRequest);

        return ResponseEntity.ok(hostel_id);
    }

    @PutMapping("/edit/{hostel_id}")
    public ResponseEntity<String> updateHostel(@PathVariable("hostel_id") UUID hostel_id, @RequestBody HostelRequest hostelRequest) {
        String checkEmpty = hostelService.updateHostel(hostel_id, hostelRequest);
        return ResponseEntity.ok(checkEmpty);
    }

    @PutMapping("/{room_id}")
    public ResponseEntity<String> chooseBed(@PathVariable("room_id") UUID room_id, @RequestBody RoomBedRequest request) {
        String checkEmpty = hostelService.chooseBed(room_id, request);
        return ResponseEntity.ok(checkEmpty);
    }

    @DeleteMapping("/{hostel_id}")
    public ResponseEntity<Void> deleteHostel(@PathVariable("hostel_id") UUID hostel_id) {
        hostelService.deleteHostel(hostel_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{hostel_id}")
    public ResponseEntity<HostelDTO> getHostelById(@PathVariable("hostel_id") UUID hostel_id) {
        return ResponseEntity.ok(hostelService.getHostel(hostel_id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<HostelDTO>> getHostels() {
        List<HostelDTO> hostels = hostelService.getHostels();
        return ResponseEntity.ok(hostels);
    }

}
