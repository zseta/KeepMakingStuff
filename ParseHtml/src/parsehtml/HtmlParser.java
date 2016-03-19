package parsehtml;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HtmlParser {
    
    private static final String URL = "http://www.stat-football.com/en/t/eng10.php";
    private static final String SELECTOR_TABLE_ROW = "#tb01 .f11 td:lt(10)";
    private static final int NUMBER_OF_COLUMNS = 10;
    private Elements tableRows;
    private List<TeamInfo> teamInfos;
    private TeamInfo team;
    
    public void parse(){
        try {
            Document doc = Jsoup.connect(URL).get();
            tableRows = doc.select(SELECTOR_TABLE_ROW);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    
    
    
    private void init(){
        teamInfos = new ArrayList<>();
        int size = tableRows.size()/NUMBER_OF_COLUMNS; //10 because there are 10 different data in a single row 
        for (int i=0;i<size;i++){
            team = new TeamInfo();
            team.rank = getRank(i).text();
            team.teamName = getTeam(i).text();
            team.played = getPlayed(i).text();
            team.win = getWin(i).text();
            team.draw = getDraw(i).text();
            team.lose = getLose(i).text();
            team.forGoals = getForGoals(i).text();
            team.againstGoals = getAgainstGoals(i).text();
            team.points = getPoints(i).text();
            teamInfos.add(team);
        }
    }
    
    public void print(){
        init();
        Gson gson = new Gson();;
        FileWriter fw;
        try {
            fw = new FileWriter("data.json");
            String json;
            for (int i=0;i<teamInfos.size();i++){
                json = gson.toJson(teamInfos.get(i));
                fw.write(json);
                System.out.println(json);
            }
            fw.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    class TeamInfo {
        String rank, teamName, played, win, draw, lose, forGoals, againstGoals, points;
    }
    
    
    // we have 10 different data (0-9) 9 of them useful
    // in one tableRows element we have the (overall) data of one row
    //inside a row get(0) means Rank, get(1) means team and so on..
    public Element getRank(int i){
        return tableRows.get(0+i*10);
    }
    
    public Element getTeam(int i){
        return tableRows.get(1+i*10);
    }
    
    public Element getPlayed(int i){
        return tableRows.get(2+i*10);
    }
    
    public Element getWin(int i){
        return tableRows.get(3+i*10);
    }
    
    public Element getDraw(int i){
        return tableRows.get(4+i*10);
    }
    
    public Element getLose(int i){
        return tableRows.get(5+i*10);
    }
    
    public Element getForGoals(int i){
        return tableRows.get(6+i*10);
    }
    
    public Element getAgainstGoals(int i){
        return tableRows.get(8+i*10);    //tableRow.get(7) is a "-" character so we skip it
    }
    
    public Element getPoints(int i){
        return tableRows.get(9+i*10);
    }
}