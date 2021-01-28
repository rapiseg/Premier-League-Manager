package tests;

import entities.FootballClub;
import services.PremierLeagueManager;

public class PremierLeagueManagerTest {
    public static void main(String[] args) {
        PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
        FootballClub england = new FootballClub("England","England",3,0,1,7,4,4);
        premierLeagueManager.addTeam(england);
        premierLeagueManager.addTeam(england);
        FootballClub india = new FootballClub("india","india",2,1,0,4,7,4);
        premierLeagueManager.addTeam(india);
        premierLeagueManager.displayLeagueTable();
        premierLeagueManager.relegateTeam("England");
        premierLeagueManager.displayLeagueTable();
        premierLeagueManager.relegateTeam("sri lanka");

    }
}
