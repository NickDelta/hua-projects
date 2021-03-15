package org.hua.tracker.entity;

import java.util.Objects;

public class UserLocation {

    private Integer id;
    private String timestamp;
    private Float latitude;
    private Float longitude;

    public UserLocation(Integer id, String timestamp, Float latitude, Float longitude) {
        this.id = id;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public UserLocation(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLocation location = (UserLocation) o;
        return id.equals(location.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
