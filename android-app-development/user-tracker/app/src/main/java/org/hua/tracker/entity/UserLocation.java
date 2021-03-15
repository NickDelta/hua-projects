package org.hua.tracker.entity;

import java.util.Objects;

public class UserLocation {

    private Integer id;
    private String userId;
    private Float longitude;
    private Float latitude;
    private String timestamp;

    public UserLocation(Integer id, String userId, Float longitude, Float latitude, String timestamp) {
        this.id = id;
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public UserLocation(String userId, Float longitude, Float latitude) {
        this.userId = userId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLocation that = (UserLocation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
