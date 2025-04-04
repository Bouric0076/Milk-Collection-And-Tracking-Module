package com.dairy.milk_tracking.models;  

import jakarta.persistence.*;  
import java.util.List;  

@Entity  
public class Farmer {  

    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  

    @OneToOne  
    private User user;  

    private String firstName;  
    private String lastName;  
    private String nationalId;  
    private String phoneNumber;  
    private String email;  
    private String geolocation;  
    private String pictureUrl;  
    private Integer numberOfCows;  

    @ManyToOne  
    private CollectionPoint preferredCollectionPoint;  

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    @OneToMany(mappedBy = "farmer")  
    private List<CollectionRequest> collectionRequests;  

    // Getters and Setters  
    public Long getId() {  
        return id;  
    }  

    public void setId(Long id) {  
        this.id = id;  
    }  

    public User getUser() {  
        return user;  
    }  

    public void setUser(User user) {  
        this.user = user;  
    }  

    public String getFirstName() {  
        return firstName;  
    }  

    public void setFirstName(String firstName) {  
        this.firstName = firstName;  
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

    public String getGeolocation() {  
        return geolocation;  
    }  

    public void setGeolocation(String geolocation) {  
        this.geolocation = geolocation;  
    }  

    public String getPictureUrl() {  
        return pictureUrl;  
    }  

    public void setPictureUrl(String pictureUrl) {  
        this.pictureUrl = pictureUrl;  
    }  

    public Integer getNumberOfCows() {  
        return numberOfCows;  
    }  

    public void setNumberOfCows(Integer numberOfCows) {  
        this.numberOfCows = numberOfCows;  
    }  

    public CollectionPoint getPreferredCollectionPoint() {  
        return preferredCollectionPoint;  
    }  

    public void setPreferredCollectionPoint(CollectionPoint preferredCollectionPoint) {  
        this.preferredCollectionPoint = preferredCollectionPoint;  
    }  

    public List<CollectionRequest> getCollectionRequests() {  
        return collectionRequests;  
    }  

    public void setCollectionRequests(List<CollectionRequest> collectionRequests) {  
        this.collectionRequests = collectionRequests;  
    }  
}  
