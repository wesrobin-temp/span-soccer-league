package application;

import data.SoccerTeam;

import java.util.HashMap;
import java.util.Map;

public class SoccerLeagueTable {
   protected final Map<SoccerTeam, Integer> LEAGUE_TABLE = new HashMap<>();

   /**
    * Add a new {@link SoccerTeam} to the table, assigning it a 0 score by default.
    *
    * @param soccerTeam The team to add.
    */
   public void addNewTeam(SoccerTeam soccerTeam) {
      if (!LEAGUE_TABLE.containsKey(soccerTeam) && soccerTeam != null) {
         LEAGUE_TABLE.put(soccerTeam, 0);
      }
   }

   /**
    * Method to check that a given team exists in the league table.
    *
    * @param soccerTeam The team to query the table with.
    * @return {@code true} if it exists, {@code false} if not.
    */
   public boolean hasTeam(SoccerTeam soccerTeam) {
      return LEAGUE_TABLE.containsKey(soccerTeam);
   }

   /**
    * Updates the score for a {@link SoccerTeam} that exists in the table. Does nothing if no team currently exists in the
    * table.
    *
    * @param soccerTeam The team for whom the score must be updated.
    * @param newScore   The score to assign to the given team.
    */
   public void updateScore(SoccerTeam soccerTeam, int newScore) {
      if (LEAGUE_TABLE.containsKey(soccerTeam)) {
         LEAGUE_TABLE.put(soccerTeam, newScore);
      }
   }

   /**
    * Gets the stored score for the given {@link SoccerTeam}.
    *
    * @param soccerTeam The team to retrieve the score for.
    * @return The team's score, or -1 if the team does not exist in the table.
    */
   public int getScore(SoccerTeam soccerTeam) {
      return LEAGUE_TABLE.getOrDefault(soccerTeam, -1);
   }
}
