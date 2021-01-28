package entities;
import java.util.Objects;

public class UniversityFootballClub extends FootballClub{
    public UniversityFootballClub(String name, String location) {
        this(name,location,0,0,0,0,0,0,0);
    }

    public UniversityFootballClub(String name, String location, int win, int draw, int defeat, int noOfGoalsScored, int noOfGoalsReceived, int points, int noOfMatchesPlayed) {
        super(name, location, win, draw, defeat, noOfGoalsScored, noOfGoalsReceived,points);
    }

    @Override
    public String toString() {
        return "University " + super.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UniversityFootballClub)) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return getName().equals(that.getName()) && getLocation().equals(that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLocation());
    }
}