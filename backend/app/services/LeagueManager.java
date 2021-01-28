package services;

import entities.Match;
import entities.SportsClub;

import java.io.IOException;

/*
this interface can later be implemented by other classes
 */
public interface LeagueManager {
    void addTeam(SportsClub sportsClub);
    void relegateTeam(String name);
    void displayStatistics(String name);
    void displayLeagueTable();
    void addPlayedMatch(Match match);
    void updateStatistics(Match match);
    String simulateMatch();
    boolean addRandomMatch(Match match);
    void save(String savedClubs, String savedMatches) throws IOException;
    void load(String savedClubs, String savedMatches) throws IOException, ClassNotFoundException;
}
