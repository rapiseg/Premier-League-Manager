package utils;


import entities.FootballClub;
import play.mvc.Controller;
import services.PremierLeagueManager;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleApplication extends Controller {
    private static PremierLeagueManager englishPremierLeagueManager = new PremierLeagueManager();

    public static String getTeamName() {
        String name;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Team name: ");
            name = sc.nextLine().trim();//gets rid of the blank spaces
            if (name.equals("")) {
                System.out.println("Please type a name. Input can't be empty!!!");
            }else {
                name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                break;
            }
        }
        return name;
    }

    // Get user input for the menu
    public static String menu() throws IOException {
        System.out.println("Press 'A' to add a Team");
        System.out.println("Press 'R' to relegate a Team");
        System.out.println("Press 'D' to Display statistics of a club");
        System.out.println("Press 'T' to Display the Premier League Table");
        System.out.println("Press 'M' to add a played Match");
        System.out.println("Press 'G' to open the league table in GUI");
        System.out.println("Press 'S' to Save");
        System.out.println("Press 'Q' to Quit.");

        Scanner sc = new Scanner(System.in);
        return sc.nextLine().toUpperCase();
    }

    /*
    launches te gui application
     */
    public static void launchProject() throws IOException {
        Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"sbt run\"");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean sbt =false;
        menuLoop:
        while (true){
            String userChoice = menu();
            try {
                /*
                auto loading has to be dne tin order to ensure that there are no data loses while
                running gui and console simultaneously.
                 */
                englishPremierLeagueManager.load("savedClubs.txt", "savedMatches.txt");
            }catch (IOException | ClassNotFoundException e){
                englishPremierLeagueManager.save("savedClubs.txt", "savedMatches.txt");
            }
            switch (userChoice) {
                case "A":
                    englishPremierLeagueManager.addTeam(new FootballClub(getTeamName(), "United kingdom"));
                    englishPremierLeagueManager.save("savedClubs.txt", "savedMatches.txt");
                    break;
                case "R":
                    englishPremierLeagueManager.relegateTeam(getTeamName());
                    englishPremierLeagueManager.save("savedClubs.txt", "savedMatches.txt");
                    break;
                case "D":
                    englishPremierLeagueManager.displayStatistics(getTeamName());
                    break;
                case "T":
                    englishPremierLeagueManager.displayLeagueTable();
                    break;
                case "M":
                    englishPremierLeagueManager.addPlayedMatch(englishPremierLeagueManager.createMatch());
                    englishPremierLeagueManager.save("savedClubs.txt", "savedMatches.txt");
                    break;
                case "G":
                    /*
                    ensures that the gui application can't be launched
                    more than once without restarting the console
                     */
                    if (!sbt) {
                        sbt = true;
                        launchProject();
                    }else {
                        System.out.println("GUI application is already running.");
                    }
                    break;
                case "Q":
                    break menuLoop;
                case "S":
                    englishPremierLeagueManager.load("savedClubs.txt", "savedMatches.txt");
                    System.out.println("File successfully saved");
                    break;
                default:
                    System.out.println("Invalid choice!!!");
                    break;
            }
        }
    }
}

