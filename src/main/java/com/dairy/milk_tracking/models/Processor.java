package com.dairy.milk_tracking.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Processor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String businessNumber;
    private String contactPersonName;
    private String contactPersonId;
    private String phoneNumber;
    private String email;
    private String geolocation; // Geolocation of the processor
    private String picture; // Processor profile picture

    @ManyToMany
    @JoinTable(
      name = "processor_group", 
      joinColumns = @JoinColumn(name = "processor_id"), 
      inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups; // Groups with which the processor has contracts

    @OneToMany(mappedBy = "processor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MilkCollection> milkCollections; // Milk collections received by the processor

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(String contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<MilkCollection> getMilkCollections() {
        return milkCollections;
    }

    public void setMilkCollections(List<MilkCollection> milkCollections) {
        this.milkCollections = milkCollections;
    }
}
