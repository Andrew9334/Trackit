package com.svprofi.trackit.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "post_item")
@Getter
@Setter
public class PostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String recipientName;
    @NotBlank
    private String recipientAddress;
    @NotBlank
    private String recipientPostalCode;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
    @Enumerated(EnumType.STRING)
    private PostStatus status;
    @OneToMany(mappedBy = "postItem", cascade = CascadeType.ALL)
    private List<PostMovement> movements;
    @Version
    private Long version;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = PostStatus.IN_TRANSIT;
        }
    }
}
