package com.svprofi.trackit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "post_office")
@Getter
@Setter
public class PostOffice {

    @Id
    @Column(unique = true)
    private String postalCode;
    @NotBlank
    @Column(name = "name")
    @Size(max = 100)
    private String name;
    @NotBlank
    @Column(name = "addres")
    @Size(max = 255)
    private String address;

    @Override
    public String toString() {
        return "PostOffice{" +
                "postalCode='" + postalCode + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}