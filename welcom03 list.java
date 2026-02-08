
import core.data.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Welcome03_List {
   @SuppressWarnings("unused")
   public static void main(String[] args) {  


      DataSource ds = DataSource.connect("http://weather.gov/xml/current_obs/index.xml").load();
      ArrayList<WeatherStation> allstns = ds.fetchList("WeatherStation", "station/station_name",
                                                      "station/station_id", "station/state", "station/latitude", "station/longitude");

      System.out.println("Total stations: " + allstns.size());
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter a state abbreviation: ");
      String state = sc.next();
      
      System.out.println("Stations in " + state);

      String southName = "";
      double southLat = 90.0;
      for (WeatherStation ws : allstns) {
         
         if (ws.isLocatedInState(state)) {
            System.out.println("  " + ws.getId() + ": " + ws.getName());
         }

         if (ws.getLat() < southLat) {
            southLat = ws.getLat();
            southName = ws.getName();
         }

      }
      System.out.println(southName + ": " + southLat);
      sc.close();
   }
}