package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import play.mvc.Controller;
import play.mvc.Result;
import services.PremierLeagueManager;

import java.io.IOException;

/*
This controller class is responsible for sending data as json
to the front end
 */
public class GUIController extends Controller {
    private static PremierLeagueManager englishPremierLeagueManager = new PremierLeagueManager();

    /*
    loads data from the saved file, converts it to json.
    */
    public Result clubList() throws IOException, ClassNotFoundException {
        try {
            englishPremierLeagueManager.load("savedClubs.txt", "savedMatches.txt");
        }catch (IOException | ClassNotFoundException e){
            englishPremierLeagueManager.save("savedClubs.txt", "savedMatches.txt");
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData  = mapper.convertValue(englishPremierLeagueManager.getFootballClubs(), JsonNode.class);
        return ok(jsonData).as("application/json");
    }

    /*
    loads data from the saved file, converts it to json.
    */
    public Result matchList() throws IOException, ClassNotFoundException {
        try {
            englishPremierLeagueManager.load("savedClubs.txt", "savedMatches.txt");
        }catch (IOException | ClassNotFoundException e){
            englishPremierLeagueManager.save("savedClubs.txt", "savedMatches.txt");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        JsonNode jsonData  = mapper.convertValue(englishPremierLeagueManager.getMatchList(), JsonNode.class);
        return ok(jsonData).as("application/json");
    }

    /*
    loads data from the saved file, converts it to json.
     */
    public Result simulate() throws IOException, ClassNotFoundException {
        try {
            englishPremierLeagueManager.load("savedClubs.txt", "savedMatches.txt");
        }catch (IOException | ClassNotFoundException e){
            englishPremierLeagueManager.save("savedClubs.txt", "savedMatches.txt");
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(englishPremierLeagueManager.simulateMatch(),JsonNode.class);
        englishPremierLeagueManager.save("savedClubs.txt", "savedMatches.txt");
        return ok(jsonNode).as("application/json");
    }
}
/*
References
https://github.com/dilum1995/IIT-PlayFramework-Session/tree/main/week_three
 */
