package entities;

import java.util.Objects;
/*
This class acts as super class for all the football club classes
and is a subclass of SportsClub super class,
This implements Comparable interface in order make it possible to override the compareTo method
*/
public class FootballClub extends SportsClub implements Comparable<FootballClub>{
    private int win;
    private int draw;
    private int defeat;
    private int noOfGoalsScored;
    private int noOfGoalsReceived;
    private int points;
    private int noOfMatchesPlayed;
    private int goalDifference;

    public FootballClub(String name, String location) {
        //call the other constructor using this key word
        this(name,location,0,0,0,0,0,0);
    }

    public FootballClub(String name, String location, int win, int draw, int defeat, int noOfGoalsScored, int noOfGoalsReceived,int points) {
        super(name, location);
        this.win = win;
        this.draw = draw;
        this.defeat = defeat;
        this.noOfGoalsScored = noOfGoalsScored;
        this.noOfGoalsReceived = noOfGoalsReceived;
        /*
        Each team will get 3 points for a win and 1 point for a draw.
        Number of played matches can be calculated by counting all wins,draws and defeats.
        */
        this.points = points;
        this.noOfMatchesPlayed = this.win + this.draw + this.defeat;
        this.goalDifference = noOfGoalsScored - noOfGoalsReceived;
    }


    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        if (win < 0){
            throw new IllegalArgumentException("Number of wins can not be negative"); // throw an exception when negative value is entered
        }
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        if (draw < 0){
            throw new IllegalArgumentException("Number of draws can not be negative");
        }
        this.draw = draw;
    }

    public int getDefeat() {
        return defeat;
    }

    public void setDefeat(int defeat) {
        if (defeat < 0){
            throw new IllegalArgumentException("Number of defeat can not be negative");
        }
        this.defeat = defeat;
    }

    public int getNoOfGoalsScored() {
        return this.noOfGoalsScored;
    }

    public void setNoOfGoalsScored(int noOfGoalsScored) {
        if (noOfGoalsScored < 0){
            throw new IllegalArgumentException("Number of goals scored can not be negative");
        }
        this.noOfGoalsScored = noOfGoalsScored;
    }

    public int getNoOfGoalsReceived() {
        return noOfGoalsReceived;
    }

    public void setNoOfGoalsReceived(int noOfGoalsReceived) {
        if (noOfGoalsReceived< 0){
            throw new IllegalArgumentException("Number of goals received can not be negative");
        }
        this.noOfGoalsReceived = noOfGoalsReceived;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        if (points < 0){
            throw new IllegalArgumentException("Number of points  can not be negative");
        }
        this.points = points;
    }

    public int getNoOfMatchesPlayed() {
        return this.getWin()+this.getDraw()+this.getDefeat();
    }

    public void setNoOfMatchesPlayed(int noOfMatchesPlayed) {
        if (noOfMatchesPlayed < 0){
            throw new IllegalArgumentException("Number of played matches  can not be negative");
        }
        this.noOfMatchesPlayed = noOfMatchesPlayed;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    /*
        calculate the goal difference
         */
    public int getGoalDifference(){
        this.goalDifference =  noOfGoalsScored-noOfGoalsReceived;
        return goalDifference;
    }


    @Override
    public String toString() {
        return "FootballClub " + super.getName() +
                " is located at " + super.getLocation() + "\n" +
                "Wins this season = " + win + "\n" +
                "Draws this season = " + draw +"\n" +
                "Defeats this season = " + defeat +"\n" +
                "Number of goals scored this season = " + noOfGoalsScored +"\n" +
                "Number of goals received this season = " + noOfGoalsReceived +"\n" +
                "Points = " + getPoints()+"\n" +
                "Goal difference this season = " + getGoalDifference() +"\n" +
                "Number of matches played this season = " + noOfMatchesPlayed ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FootballClub)) return false;
        FootballClub that = (FootballClub) o;
        return getName().equals(that.getName()) && getLocation().equals(that.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLocation());
    }


    @Override
    public int compareTo(FootballClub o) {
        if (getPoints() > o.getPoints()) return 1;
        if (getPoints() < o.getPoints()) return -1;
        // use goal difference to compare football clubs when they have equal points
        return ((getGoalDifference())-(o.getGoalDifference()));
    }
}
