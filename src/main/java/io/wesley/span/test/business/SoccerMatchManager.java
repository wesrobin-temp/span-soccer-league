package io.wesley.span.test.business;

import io.wesley.span.test.data.SoccerMatch;

public class SoccerMatchManager {
   public int getResult(SoccerMatch soccerMatch) {
      if (soccerMatch.getAwayTeamScore() == -1 || soccerMatch.getHomeTeamScore() == -1) {
         throw new IllegalStateException("No result set for the given Match.");
      }

      return soccerMatch.getHomeTeamScore().compareTo(soccerMatch.getAwayTeamScore());
   }
}
