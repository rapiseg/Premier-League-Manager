package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Match implements Serializable{
    private FootballClub home;
    private FootballClub guest;
    private int[] score = new int[2];
    private LocalDate date;

    public Match(FootballClub home, FootballClub guest) {
        this.home = home;
        this.guest = guest;
    }

    public Match(FootballClub home, FootballClub guest, int homeScore, int guestScore, LocalDate date) {
        this.home = home;
        this.guest = guest;
        this.score = new int[]{homeScore, guestScore};
        this.date = date;
    }

    public FootballClub getHome() {
        return home;
    }

    public FootballClub getGuest() {
        return guest;
    }

    public LocalDate getDate() {
        return date;
    }

    public int[] getScore() {
        return score;
    }

    public String getScoreInFormat(){
        return score[0] + " - " + score[1];
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setScore(int homeScore, int guestSore) {
        if (homeScore < 0|| guestSore <0)throw new IllegalArgumentException("Score can not be negative");
        this.score = new int[]{homeScore,guestSore};
    }


    public FootballClub winner(){
        if (this.score[0] > this.score[1]) return this.home;
        return this.guest;
    }

    public FootballClub loser(){
        if (this.score[0] < this.score[1]) return this.home;
        return this.guest;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return home.equals(match.home) &&
                guest.equals(match.guest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(home, guest);
    }

    @Override
    public String toString() {
        return "   Match Details:  \n" +
                "Home team = " + home.getName() + "\n"+
                ", Guest team = " + guest.getName() + "\n" +
                ", Score = " + getScoreInFormat() + "\n" +
                ", Date = " + date ;
    }
}
