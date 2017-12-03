package data;

import java.util.ArrayList;
import java.util.List;

public class ScoreSheet {
   protected List<SoccerMatch> soccerMatches = new ArrayList<>();

   public void addMatch(SoccerMatch soccerMatch) {
      soccerMatches.add(soccerMatch);
   }
}
