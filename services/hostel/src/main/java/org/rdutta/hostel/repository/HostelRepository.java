package org.rdutta.hostel.repository;

import org.rdutta.hostel.entity.Hostel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, UUID> {
    @Override
    @EntityGraph(attributePaths = "rooms")
    List<Hostel> findAll();
}
