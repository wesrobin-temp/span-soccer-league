package business;

import data.SoccerMatch;

public class DefaultSoccerMatchManager {
   public int getResult(SoccerMatch soccerMatch) {
      if (soccerMatch.getAwayTeamScore() == -1 || soccerMatch.getHomeTeamScore() == -1) {
         throw new IllegalStateException("No result set for the given Match.");
      }

      return soccerMatch.getHomeTeamScore().compareTo(soccerMatch.getAwayTeamScore());
   }
}
