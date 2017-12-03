package io.wesley.span.test;

import io.wesley.span.test.data.ScoreSheet;
import io.wesley.span.test.data.SoccerMatch;
import io.wesley.span.test.data.SoccerTeam;
import io.wesley.span.test.data.table.LeagueTableNode;

import java.util.HashMap;
import java.util.Map;

public class TestConstants {
   public static final ScoreSheet happyDayScoreSheet;
   public static final ScoreSheet emptyScoreSheet;
   public static final Map<String, LeagueTableNode> leagueTable;
   public static final String expectedFormattedLeagueTable;

   static {
      // Expected Score Sheet based on happyday-soccer-results.txt
      happyDayScoreSheet = new ScoreSheet();

      SoccerTeam tarantulas = new SoccerTeam("Tarantulas");
      SoccerTeam lions = new SoccerTeam("Lions");
      SoccerTeam fcAwesome = new SoccerTeam("FC Awesome");
      SoccerTeam snakes = new SoccerTeam("Snakes");
      SoccerTeam grouches = new SoccerTeam("Grouches");

      happyDayScoreSheet.addMatch(new SoccerMatch(lions, snakes, 3L, 3L));
      happyDayScoreSheet.addMatch(new SoccerMatch(tarantulas, fcAwesome, 1L, 0L));
      happyDayScoreSheet.addMatch(new SoccerMatch(lions, fcAwesome, 1L, 1L));
      happyDayScoreSheet.addMatch(new SoccerMatch(tarantulas, snakes, 3L, 1L));
      happyDayScoreSheet.addMatch(new SoccerMatch(lions, grouches, 4L, 0L));

      // Expected Score Sheet based on empty or badly written file
      emptyScoreSheet = new ScoreSheet();

      // Dummy League Table
      leagueTable = new HashMap<>();

      LeagueTableNode tarantualasLeagueTableNode = new LeagueTableNode(tarantulas);
      tarantualasLeagueTableNode.addPoints(20L);
      LeagueTableNode lionsLeagueTableNode = new LeagueTableNode(lions);
      lionsLeagueTableNode.addPoints(15L);
      LeagueTableNode fcAwesomeLeagueTableNode = new LeagueTableNode(fcAwesome);
      fcAwesomeLeagueTableNode.addPoints(10L);
      LeagueTableNode snakesLeagueTableNode = new LeagueTableNode(snakes);
      snakesLeagueTableNode.addPoints(1L);

      leagueTable.put("tarantulas", tarantualasLeagueTableNode);
      leagueTable.put("lions",lionsLeagueTableNode);
      leagueTable.put("fcAwesome", fcAwesomeLeagueTableNode);
      leagueTable.put("snakes", snakesLeagueTableNode);

      // Expected String after parsing Dummy league table
      StringBuilder builder = new StringBuilder();
      builder.append("1. Tarantulas, 20 pts");
      builder.append(System.lineSeparator());
      builder.append("2. Lions, 15 pts");
      builder.append(System.lineSeparator());
      builder.append("3. FC Awesome, 10 pts");
      builder.append(System.lineSeparator());
      builder.append("4. Snakes, 1 pt");

      expectedFormattedLeagueTable = builder.toString();
   }
}
