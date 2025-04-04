package com.dairy.milk_tracking.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Collector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalId;
    private String phoneNumber;
    private String email;
    private String picture; // Profile picture URL/path
    private String geolocation; // Geolocation of the collector
    private String vehicleType;
    private String vehicleNumberPlate;

    @ManyToMany
    @JoinTable(
      name = "collector_collection_point", 
      joinColumns = @JoinColumn(name = "collector_id"), 
      inverseJoinColumns = @JoinColumn(name = "collection_point_id"))
    private List<CollectionPoint> collectionPoints; // Define the collection points a collector covers

    @OneToMany(mappedBy = "collector", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MilkCollection> milkCollections; // Milk collections the collector is responsible for

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumberPlate() {
        return vehicleNumberPlate;
    }

    public void setVehicleNumberPlate(String vehicleNumberPlate) {
        this.vehicleNumberPlate = vehicleNumberPlate;
    }

    public List<CollectionPoint> getCollectionPoints() {
        return collectionPoints;
    }

    public void setCollectionPoints(List<CollectionPoint> collectionPoints) {
        this.collectionPoints = collectionPoints;
    }

    public List<MilkCollection> getMilkCollections() {
        return milkCollections;
    }

    public void setMilkCollections(List<MilkCollection> milkCollections) {
        this.milkCollections = milkCollections;
    }
}
