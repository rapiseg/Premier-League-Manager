package entities;

import java.io.Serializable;
import java.util.Objects;
/*
This class acts as super class for all the sports club classes
*/
public class SportsClub implements Serializable {
    private String name;
    private String location;

    public SportsClub(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SportsClub " + name
                +" is located at " + location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportsClub)) return false;
        SportsClub that = (SportsClub) o;
        return name.equals(that.name) && location.equals(that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}

