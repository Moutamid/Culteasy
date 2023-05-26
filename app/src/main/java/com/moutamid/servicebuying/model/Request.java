package com.moutamid.servicebuying.model;

public class Request {

    private String id;
    private String serviceName;
    private String subServiceName;
    private String phone;
    private String userId;
    private String location;
    private String date;
    private String time;
    private String status;
    private String description;

    public Request(){

    }

    public Request(String id, String serviceName,String subServiceName, String phone, String userId, String location, String date, String time, String status, String description) {
        this.id = id;
        this.serviceName = serviceName;
        this.subServiceName = subServiceName;
        this.phone = phone;
        this.userId = userId;
        this.location = location;
        this.date = date;
        this.time = time;
        this.status = status;
        this.description = description;
    }

    public String getSubServiceName() {
        return subServiceName;
    }

    public void setSubServiceName(String subServiceName) {
        this.subServiceName = subServiceName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
