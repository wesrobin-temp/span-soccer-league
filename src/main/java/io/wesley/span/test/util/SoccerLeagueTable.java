package io.wesley.span.test.util;

import io.wesley.span.test.business.ILeagueTableFormatter;
import io.wesley.span.test.data.SoccerTeam;
import io.wesley.span.test.data.table.LeagueTableNode;

import java.util.HashMap;
import java.util.Map;

public class SoccerLeagueTable {
   protected final Map<String, LeagueTableNode> LEAGUE_TABLE;
   protected final ILeagueTableFormatter formatter;

   public SoccerLeagueTable(ILeagueTableFormatter formatter) {
      LEAGUE_TABLE = new HashMap<>();
      this.formatter = formatter;
   }

   /**
    * Add a new {@link SoccerTeam} to the table, assigning it a 0 score by default.
    *
    * @param soccerTeam The team to add.
    */
   public void addNewTeam(SoccerTeam soccerTeam) {
      if (soccerTeam != null && !LEAGUE_TABLE.containsKey(soccerTeam.getUuid().toString())) {
         LEAGUE_TABLE.put(soccerTeam.getUuid().toString(), new LeagueTableNode(soccerTeam));
      }
   }

   /**
    * Method to check that a given team exists in the league table.
    *
    * @param soccerTeam The team to query the table with.
    * @return {@code true} if it exists, {@code false} if not.
    */
   public boolean hasTeam(SoccerTeam soccerTeam) {
      return LEAGUE_TABLE.containsKey(soccerTeam.getUuid().toString());
   }

   /**
    *
    * @param soccerTeam
    * @param points
    */
   public void addPoints(SoccerTeam soccerTeam, Long points) {
      if (LEAGUE_TABLE.containsKey(soccerTeam.getUuid().toString())) {
         LEAGUE_TABLE.get(soccerTeam.getUuid().toString()).addPoints(points);
      }
   }

   /**
    * Gets the stored score for the given {@link SoccerTeam}.
    *
    * @param soccerTeam The team to retrieve the score for.
    * @return The team's score, or -1 if the team does not exist in the table.
    */
   public Long getScore(SoccerTeam soccerTeam) {
      if (LEAGUE_TABLE.get(soccerTeam.getUuid().toString()) == null) {
         return null;
      }

      return LEAGUE_TABLE.get(soccerTeam.getUuid().toString()).getPoints();
   }

   public String exportData() {
      return formatter.format(LEAGUE_TABLE);
   }
}
