package services;


import entities.FootballClub;
import entities.Match;
import entities.SportsClub;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PremierLeagueManager implements LeagueManager {
    private List<FootballClub> footballClubs = new ArrayList<>();
    private List<Match> matchList = new ArrayList<>();
    public static final int MAX_CLUBS = 20;
    /*
    override the addTeam method
     */
    @Override
    public void addTeam(SportsClub sportsClub) {
        //Checking if the football club has been already added to the league
        if (!(sportsClub instanceof FootballClub)){
            System.out.println("Insert a Football club");
            return;
        }
        FootballClub footballClub = (FootballClub) sportsClub;
        for (FootballClub footballClub1: footballClubs){
            if (footballClub.equals(footballClub1)){
                System.out.printf("This football club %s has been already added.\n", footballClub.getName());
                return;
            }
        }
        //Checking if the league has free slots
        if (footballClubs.size() < MAX_CLUBS){
            footballClubs.add(footballClub);
            System.out.printf("Football club %s has been successfully added to the league.\n", footballClub.getName() );
        } else {
            System.out.println("League already has the maximum number of teams");
        }
    }

    /*
    A method to find the if the club is already in the league
     */
    public int findTeam(String name){
        for (FootballClub footballClub: footballClubs){
            if (footballClub.getName().equals(name)) return footballClubs.indexOf(footballClub);
        }
        System.out.printf("Football club %s is not in the league.\n",name);
        return -1;
    }

    // removing a team from the league
    @Override
    public void relegateTeam(String name) {
        int club = findTeam(name);
        if (club >= 0){
            footballClubs.remove(club);
            System.out.println(name+" was removed successfully.");
        }
    }

    // displaying the given club's statistics
    @Override
    public void displayStatistics(String name) {
        int club = findTeam(name);
        if (club >= 0){
            System.out.println(footballClubs.get(club));
        }
    }

    // displaying the league table
    @Override
    public void displayLeagueTable() {
        //sorting the football teams using compareTo
        Collections.sort(footballClubs);
        //reverse the sorted arraylist so it will be in the descending order
        Collections.reverse(footballClubs);
        //format for the table
        String format = "|%-9s|%-25s|%7s|%7s|%7s|%7s|%7s|%7s|%7s|%7s|%n";
        //table decoration
        String line = new String(new char[101]).replace('\0', '-');
        System.out.println(line);
        // table headings
        System.out.printf(format,"Position","Club","Played","Won","Drawn","Lost","GS","GR","GD","Points");
        System.out.println(line);
        /*
        iterate through the arraylist and display all the teams and
        their details including the positions
         */
        for (FootballClub footballClub: footballClubs){
            System.out.printf(format,
                    (footballClubs.indexOf(footballClub)+1),
                    footballClub.getName(),
                    footballClub.getNoOfMatchesPlayed(),
                    footballClub.getWin(),
                    footballClub.getDraw(),
                    footballClub.getDefeat(),
                    footballClub.getNoOfGoalsScored(),
                    footballClub.getNoOfGoalsReceived(),
                    footballClub.getGoalDifference(),
                    footballClub.getPoints()
            );
        }
        System.out.println(line);
    }

    // adding a match
    @Override
    public void addPlayedMatch(Match match) {
        if (match == null) {
            System.out.println();
            return;
        }

        for (Match match1: matchList){
            //checking if the match already took place
            if (match.equals(match1)){
                System.out.println("This match has already took place");
                return ;
            }
            // checking if any teams are having more than 1 match per day
            if (match.getDate().isEqual(match1.getDate())){
                // asserting that no teams can have more than 1 match in 1 day
                if (match.getHome().equals(match1.getHome())
                        || match.getHome().equals(match1.getGuest())
                        || match.getGuest().equals(match1.getHome())
                        || match.getGuest().equals(match1.getGuest())){
                    System.out.println("1 team can not play more than 1 match per day");
                    return;
                }
            }
        }
        // add the match to the match list
        matchList.add(match);
        System.out.println("Match successfully added.");
        //update the statistics of both teams
        updateStatistics(match);
        System.out.println("Statistics successfully updated.");
    }
    /*
     update the statistics
     such as number of goals scored and received
     wins, draws and defeats for both teams
    */
    @Override
    public void updateStatistics(Match match) {
        FootballClub home = match.getHome();
        FootballClub guest = match.getGuest();
        int[] score = match.getScore();
        home.setNoOfMatchesPlayed(home.getNoOfMatchesPlayed() +1);
        home.setNoOfGoalsScored(home.getNoOfGoalsScored() + score[0]);
        home.setNoOfGoalsReceived(home.getNoOfGoalsReceived() + score[1]);
        home.setGoalDifference(home.getGoalDifference() + (score[0] - score[1]));
        guest.setNoOfMatchesPlayed(guest.getNoOfMatchesPlayed()+1);
        guest.setNoOfGoalsScored(guest.getNoOfGoalsScored() + score[1]);
        guest.setNoOfGoalsReceived(guest.getNoOfGoalsReceived() + score[0]);
        guest.setGoalDifference(guest.getGoalDifference() + (score[1] - score[0]));
        if (score[0]==score[1]){
            home.setDraw(home.getDraw() + 1);
            home.setPoints(home.getPoints()+1);
            guest.setDraw(guest.getDraw() + 1);
            guest.setPoints(guest.getPoints()+1);
        } else {
            match.winner().setWin(match.winner().getWin()+1);
            match.winner().setPoints(match.winner().getPoints()+3);
            match.loser().setDefeat(match.loser().getDefeat()+1);
        }

    }

    @Override
    public boolean addRandomMatch(Match match){
        for (Match match1: matchList){
            //checking if the match already took place
            if (match.equals(match1)){
                return false;
            }
            // checking if any teams are having more than 1 match per day
            if (match.getDate().isEqual(match1.getDate())){
                // asserting that no teams can have more than 1 match in 1 day
                if (match.getHome().equals(match1.getHome())
                        || match.getHome().equals(match1.getGuest())
                        || match.getGuest().equals(match1.getHome())
                        || match.getGuest().equals(match1.getGuest())){
                    return false;
                }
            }
        }
        // add the match to the match list
        matchList.add(match);
        //update the statistics of both teams
        updateStatistics(match);
        return true;
    }

    /*
    simulates a new match and sends the proper message
    depending on the availability of match slots
     */
    @Override
    public String simulateMatch(){
        boolean check = false;
        int size = footballClubs.size();
        int maxMatches = MAX_CLUBS * (MAX_CLUBS-1);
        int count = 0;
        Match match = null;

        while (!check){
            if (maxMatches == matchList.size()|| count == maxMatches){
                return ("All the matches for this season are done!!!");
            }
            Random rand = new Random();
            if (size==0){
                return ("There are no teams in the league");

            }
            if (size==1){
                return ("Not enough teams in the league!!!");
            }
            FootballClub home = footballClubs.get(rand.nextInt(size));
            FootballClub guest= footballClubs.get(rand.nextInt(size));
            if (home.equals(guest))continue;
            int homeScore = rand.nextInt(7);
            int guestScore = rand.nextInt(7);
            long minDay = LocalDate.of(2020,8,1).toEpochDay();
            long maxDay = LocalDate.of(2021,5,31).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay,maxDay);
            LocalDate date = LocalDate.ofEpochDay(randomDay);
            match = new  Match (home,guest,homeScore,guestScore,date);
            check = addRandomMatch(match);
            count++;
        }
        return match.toString();
    }

    // create a new match
    public Match createMatch(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Home Team name: ");
        String home = sc.nextLine().trim();
        System.out.println("Guest Team name: ");
        String guest = sc.nextLine().trim();
        if (home.equals("")||guest.equals("")){
            System.out.println("Club names can't be empty");
            return null;
        }
        home = home.substring(0,1).toUpperCase() + home.substring(1).toLowerCase();
        guest = guest.substring(0,1).toUpperCase() + guest.substring(1).toLowerCase();

        int homeScore;
        int guestScore;
        int day;
        int month;
        int year;

        // making sure that both teams have been already add to the league table
        if (findTeam(home) == -1 || findTeam(guest) == -1) return null;
        //checking if the home and guest teams are the same
        if (guest.equals(home)){
            System.out.println("Both teams can not be the same");
            return null;
        }

        Match match = new Match(footballClubs.get(findTeam(home)),footballClubs.get(findTeam(guest)));
        //validate the user inputs for score and date
        try {
            //getting the score from user
            System.out.println("Home Score: ");
            homeScore= sc.nextInt();
            System.out.println("Guest Score: ");
            guestScore = sc.nextInt();
            match.setScore(homeScore,guestScore);
            //getting the date from the user
            System.out.print("Day: ");
            day = sc.nextInt();
            System.out.println();
            System.out.print("Month: ");
            month = sc.nextInt();
            /*Year will automatically be et to the current year
            to avoid unnecessary complications
             */
            year = Calendar.getInstance().get(Calendar.YEAR);
            match.setDate(LocalDate.of(year,month,day));
        } catch (DateTimeException e){
            System.out.println("Invalid date");
            return null;
        } catch (InputMismatchException e){
            System.out.println("Please enter an integer");
            return null;
        } catch (IllegalArgumentException e){
            System.out.println("Score can not be negative");
            return null;
        } catch (Exception e){
            System.out.println("Please check your inputs!!!");
            return null;
        }
        return match;
    }

    @Override
    public void save(String savedClubs, String savedMatches) throws IOException {
        /*
        serializing the football  clubs and saving them
         */
        FileOutputStream fileStream = new FileOutputStream(savedClubs);
        ObjectOutputStream os = new ObjectOutputStream(fileStream);
        for (FootballClub footballClub: footballClubs){
            os.writeObject(footballClub);
        }
        fileStream.close();
        os.close();

        //saving the match list
        FileOutputStream fileOutputStream = new FileOutputStream(savedMatches);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (Match match: matchList){
            objectOutputStream.writeObject(match);
        }
        fileOutputStream.close();
        objectOutputStream.close();
    }

    @Override
    public void load(String savedClubs, String savedMatches) throws IOException, ClassNotFoundException {
        footballClubs.clear();
        matchList.clear();
        // deserializing the football clubs and reviving them
        FileInputStream fileStream = new FileInputStream(savedClubs);
        ObjectInputStream os =new ObjectInputStream(fileStream);
        for (;;) {
            try {
                footballClubs.add((FootballClub) os.readObject());
            } catch (EOFException e) {
                break;
            }
        }
        fileStream.close();
        os.close();

        // deserializing and reviving the match list
        FileInputStream fileInputStream = new FileInputStream(savedMatches);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        for (;;){
            try {
                matchList.add((Match) objectInputStream.readObject());
            } catch (EOFException e){
                break;
            }
        }
        fileInputStream.close();
        objectInputStream.close();
    }

    public List<FootballClub> getFootballClubs() {
        Collections.sort(footballClubs);
        Collections.reverse(footballClubs);
        return footballClubs;
    }

    public List<Match> getMatchList() {
        Collections.sort(matchList, new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return matchList;
    }
}