package org.rdutta.hostel.service;


import jakarta.transaction.Transactional;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.rdutta.hostel.dto.HostelDTO;
import org.rdutta.hostel.dto.RoomDTO;
import org.rdutta.hostel.http.HostelRequest;
import org.rdutta.hostel.entity.Hostel;
import org.rdutta.hostel.entity.Room;
import org.rdutta.hostel.http.RoomRequest;
import org.rdutta.hostel.mapper.HostelMapper;
import org.rdutta.hostel.repository.HostelRepository;
import org.rdutta.hostel.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HostelService implements I_HostelService {
    private final Logger log = LoggerFactory.getLogger(HostelService.class);

    private final HostelRepository hostelRepository;
    private final RoomRepository roomRepository;
    private final HostelMapper hostelMapper;


    @Autowired
    public HostelService(HostelRepository hostelRepository, RoomRepository roomRepository, HostelMapper hostelMapper) {
        this.hostelRepository = hostelRepository;
        this.roomRepository = roomRepository;
        this.hostelMapper = hostelMapper;
    }

    @Transactional
    @Override
    public UUID saveHostel(HostelRequest hostelRequest) {
        Hostel hostel = hostelMapper.convertToHostel(hostelRequest);

        // Set the hostel reference in each room
        for (Room room : hostel.getRooms()) {
            room.setHostel(hostel);
            room.statusChange();
        }

        // Save the hostel entity
        hostelRepository.save(hostel);

        return hostel.getHostelId();
    }

    @Caching(
            evict = {@CacheEvict(value = "hostel", allEntries = true),
            @CacheEvict(value = "hostel", key = "#hostelId")}
    )
    @Transactional
    @Override
    public void deleteHostel(UUID hostelId) {
        if (hostelRepository.existsById(hostelId)){
            hostelRepository.deleteById(hostelId);
        }
        System.out.println("Hostel deleted: " + hostelId);
    }


    @Transactional
    @Override
    public String updateHostel(UUID hostelId, HostelRequest hostelRequest) {
      try{
          if(hostelRepository.existsById(hostelId)){
              Hostel hostel = hostelMapper.convertToHostel(hostelRequest);
              hostel.setHostelName(hostelRequest.hostelName());
              hostelRepository.save(hostel);
              log.info("Hostel {} updated", hostelId);
          }
      }catch (ResourceNotFoundException e){
          log.error(e.getMessage());
      }
        return "Hostel successfully updated";
    }

    @Transactional
    @Override
    public String updateRoom(UUID roomId, RoomRequest roomRequest) {
       try{
           if(roomRepository.existsById(roomId)){
               Room existingRoom = roomRepository.findById(roomId).get();
               existingRoom.setIsEmpty("FULLMEMBER");
               roomRepository.save(existingRoom);
               log.info("Room {} updated", roomId);
           }
       }catch (ResourceNotFoundException resourceNotFoundException){
           log.error(resourceNotFoundException.getMessage());
       }
        return "Room successfully updated";
    }

    @Cacheable(value = "hostel", key = "#hostelId")
    @Override
    public HostelDTO getHostel(UUID hostelId) {
        Hostel hostel = hostelRepository.findById(hostelId).orElseThrow(()->new RuntimeException("Hostel not found"));
        List<RoomDTO> rooms = hostelMapper.convertRoomsToDTOs(hostel.getRooms());
        return new HostelDTO(
                hostel.getHostelId(),
                hostel.getHostelName(),
                rooms
        );
    }

    @Cacheable(value = "hostel")
    @Override
    public List<HostelDTO> getHostels() {
        List<Hostel> hostels = hostelRepository.findAll();
        return hostels.stream()
                .map(hostelMapper::convertToDTO)
                .collect(Collectors.toList());
    }

}