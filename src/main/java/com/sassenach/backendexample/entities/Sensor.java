package com.sassenach.backendexample.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

// A Sensor is an entity that represents a record of a given physical sensing device.


@Entity
public class Sensor {
    
    // We should have some means of uniquely identifiying 
    // a given physical sensor in the system
    // both for ease of use in the database
    // as well as troubleshooting physical problems in the system
    // I always prefer using UUIDs, but note to self:
    // they aren't a silver bullet, and if you're dealing with a really large dataset
    // i've heard things can get very slow when it comes to indexing
    // you could use the sensor's MAC address, if you were sure they would never be changed

    @GeneratedValue(strategy = GenerationType.UUID)
    private String sensorUUID;
    // A human-friendly name; like "East Lighthouse Ambient Temperature Sensor 2" or something
    @Id
    private String deviceName;

    public Sensor() {
    }
    public Sensor(String sensorUUID, String deviceName) {
        super();
        this.sensorUUID = sensorUUID;
        this.deviceName = deviceName;
    }


    public String getSensorUUID() {
        return sensorUUID;
    }
    public void setSensorUUID(String sensorUUID) {
        this.sensorUUID = sensorUUID;
    }
    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

        @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sensorUUID == null) ? 0 : sensorUUID.hashCode());
        result = prime * result + ((deviceName == null) ? 0 : deviceName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sensor other = (Sensor) obj;
        if (sensorUUID == null) {
            if (other.sensorUUID != null)
                return false;
        } else if (!sensorUUID.equals(other.sensorUUID))
            return false;
        if (deviceName == null) {
            if (other.deviceName != null)
                return false;
        } else if (!deviceName.equals(other.deviceName))
            return false;
        return true;
    }
    

    
}
