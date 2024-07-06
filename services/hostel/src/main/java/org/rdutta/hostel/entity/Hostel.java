package org.rdutta.hostel.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "HOSTEL")
@DynamicInsert
@DynamicUpdate
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "HOSTEL_ID")
    private UUID hostelId;

    @Column(name = "HOSTEL_NAME")
    private String hostelName;

    @OneToMany(mappedBy = "hostel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Room> rooms;

    public Hostel() {}

    public Hostel(String hostelName, List<Room> rooms) {
        this.hostelName = hostelName;
        this.rooms = rooms;
    }

    public UUID getHostelId() {
        return hostelId;
    }

    public void setHostelId(UUID hostelId) {
        this.hostelId = hostelId;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
        for (Room room : rooms) {
            room.setHostel(this);
        }
    }
}
