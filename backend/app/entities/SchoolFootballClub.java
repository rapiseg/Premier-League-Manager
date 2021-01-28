package entities;

public class SchoolFootballClub extends FootballClub{

    public SchoolFootballClub(String name, String location) {
        this(name,location,0,0,0,0,0,0,0);
    }

    public SchoolFootballClub(String name, String location, int win, int draw, int defeat, int noOfGoalsScored, int noOfGoalsReceived, int points, int noOfMatchesPlayed) {
        super(name, location, win, draw, defeat, noOfGoalsScored, noOfGoalsReceived,points);
    }

    @Override
    public String toString() {
        return "School" + super.toString();
    }
}

