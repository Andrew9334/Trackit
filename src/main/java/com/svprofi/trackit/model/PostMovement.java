package com.svprofi.trackit.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "post_movement")
@Getter
@Setter
public class PostMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "post_item_id", nullable = false)
    private PostItem postItem;
    @ManyToOne
    @JoinColumn(name = "post_office_postal_code", nullable = false)
    private PostOffice postOffice;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType movementType;
    private ZonedDateTime timestamp;
    @Version
    private Long version;

    @PrePersist
    public void prePersist() {
        if (timestamp == null) {
            timestamp = ZonedDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "PostMovement{" +
                "id=" + id +
                ", postItem=" + postItem +
                ", postOffice=" + postOffice +
                ", movementType=" + movementType +
                ", timestamp=" + timestamp +
                '}';
    }
}
