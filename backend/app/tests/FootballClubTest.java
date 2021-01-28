package tests;

import entities.FootballClub;

public class FootballClubTest {
    public static void main(String[] args) {
        FootballClub footballClub1 = new FootballClub("liverpool","liverpool");
        footballClub1.setNoOfMatchesPlayed(4);
        footballClub1.setDraw(2);
        footballClub1.setDefeat(1);
        footballClub1.setWin(1);
        footballClub1.setPoints(5);

        System.out.println(footballClub1.getPoints());

        FootballClub footballClub2 = new FootballClub("arsenal","arsenal");
        try {
            footballClub2.setNoOfGoalsReceived(-5);
        } catch (IllegalArgumentException e){
            System.out.println("Invalid input!!");
        }

        System.out.println();
        if (!footballClub1.equals(footballClub2)) System.out.println("Both clubs aren't equal");
        System.out.println();
        System.out.println(footballClub1);
        System.out.println(footballClub2);
    }
}
