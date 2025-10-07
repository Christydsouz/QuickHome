package com.example.cabbooking.model;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;
import java.util.List;

/**
 * Data model for booking
 */
public class Booking {
    @DocumentId
    private String docId;
    private String pickupPlaceAddress;
    private String dropOffPlaceAddress;
    private Double pickUpPlaceLatitude;
    private Double pickUpPlaceLongitude;
    private Double dropOffPlaceLatitude;
    private Double dropOffPlaceLongitude;
    private User driver;
    private String distanceInKm;
    private String priceInVND;
    private String transportationType;
    private Boolean available;
    private Boolean arrived;
    private Boolean finished;
    private List<String> declinedDrivers; // List of driver IDs who declined this booking
    private String rideOTP; // OTP for ride verification
    private Boolean otpVerified; // Whether OTP has been verified

    public Booking() {
    }

    public Booking(String docId, String pickupPlaceAddress, String dropOffPlaceAddress, Double pickUpPlaceLatitude, Double pickUpPlaceLongitude, Double dropOffPlaceLatitude, Double dropOffPlaceLongitude, User driver, String distanceInKm, String priceInVND, String transportationType, Boolean available, Boolean arrived, Boolean finished, List<String> declinedDrivers, String rideOTP, Boolean otpVerified) {
        this.docId = docId;
        this.pickupPlaceAddress = pickupPlaceAddress;
        this.dropOffPlaceAddress = dropOffPlaceAddress;
        this.pickUpPlaceLatitude = pickUpPlaceLatitude;
        this.pickUpPlaceLongitude = pickUpPlaceLongitude;
        this.dropOffPlaceLatitude = dropOffPlaceLatitude;
        this.dropOffPlaceLongitude = dropOffPlaceLongitude;
        this.driver = driver;
        this.distanceInKm = distanceInKm;
        this.priceInVND = priceInVND;
        this.transportationType = transportationType;
        this.available = available;
        this.arrived = arrived;
        this.finished = finished;
        this.declinedDrivers = declinedDrivers != null ? declinedDrivers : new ArrayList<>();
        this.rideOTP = rideOTP;
        this.otpVerified = otpVerified != null ? otpVerified : false;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPickupPlaceAddress() {
        return pickupPlaceAddress;
    }

    public void setPickupPlaceAddress(String pickupPlaceAddress) {
        this.pickupPlaceAddress = pickupPlaceAddress;
    }

    public String getDropOffPlaceAddress() {
        return dropOffPlaceAddress;
    }

    public void setDropOffPlaceAddress(String dropOffPlaceAddress) {
        this.dropOffPlaceAddress = dropOffPlaceAddress;
    }

    public Double getPickUpPlaceLatitude() {
        return pickUpPlaceLatitude;
    }

    public void setPickUpPlaceLatitude(Double pickUpPlaceLatitude) {
        this.pickUpPlaceLatitude = pickUpPlaceLatitude;
    }

    public Double getPickUpPlaceLongitude() {
        return pickUpPlaceLongitude;
    }

    public void setPickUpPlaceLongitude(Double pickUpPlaceLongitude) {
        this.pickUpPlaceLongitude = pickUpPlaceLongitude;
    }

    public Double getDropOffPlaceLatitude() {
        return dropOffPlaceLatitude;
    }

    public void setDropOffPlaceLatitude(Double dropOffPlaceLatitude) {
        this.dropOffPlaceLatitude = dropOffPlaceLatitude;
    }

    public Double getDropOffPlaceLongitude() {
        return dropOffPlaceLongitude;
    }

    public void setDropOffPlaceLongitude(Double dropOffPlaceLongitude) {
        this.dropOffPlaceLongitude = dropOffPlaceLongitude;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public String getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(String distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public String getPriceInVND() {
        return priceInVND;
    }

    public void setPriceInVND(String priceInVND) {
        this.priceInVND = priceInVND;
    }

    public String getTransportationType() {
        return transportationType;
    }

    public void setTransportationType(String transportationType) {
        this.transportationType = transportationType;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getArrived() {
        return arrived;
    }

    public void setArrived(Boolean arrived) {
        this.arrived = arrived;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public List<String> getDeclinedDrivers() {
        return declinedDrivers != null ? declinedDrivers : new ArrayList<>();
    }

    public void setDeclinedDrivers(List<String> declinedDrivers) {
        this.declinedDrivers = declinedDrivers != null ? declinedDrivers : new ArrayList<>();
    }

    public String getRideOTP() {
        return rideOTP;
    }

    public void setRideOTP(String rideOTP) {
        this.rideOTP = rideOTP;
    }

    public Boolean getOtpVerified() {
        return otpVerified != null ? otpVerified : false;
    }

    public void setOtpVerified(Boolean otpVerified) {
        this.otpVerified = otpVerified != null ? otpVerified : false;
    }
}
