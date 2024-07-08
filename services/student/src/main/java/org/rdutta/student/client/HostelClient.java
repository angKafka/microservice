package org.rdutta.student.client;

import org.rdutta.student.dto.RoomBedRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(
        name = "student",
        url = "${student.base-url}"
)
public interface HostelClient {

    @PutMapping("/{room_id}")
    String chooseBed(@PathVariable("room_id") UUID room_id, @RequestBody RoomBedRequest request);
}

