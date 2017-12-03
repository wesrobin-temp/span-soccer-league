package io.wesley.span.test.ui;

import io.wesley.span.test.application.IScoreSheetProvider;
import io.wesley.span.test.application.SoccerLeagueTable;
import io.wesley.span.test.business.DefaultLeagueTableFormatter;
import io.wesley.span.test.business.PointsManager;
import io.wesley.span.test.business.SoccerMatchManager;
import io.wesley.span.test.data.ScoreSheet;
import io.wesley.span.test.data.SoccerMatch;

public class SoccerLeague {
   public static void main(String... args) throws Exception {
      IScoreSheetProvider scoreSheetProvider = new Cli(args).parse();

      if (scoreSheetProvider != null) {
         ScoreSheet scoreSheet = scoreSheetProvider.produce();

         // Initialise some workers..
         SoccerMatchManager matchManager = new SoccerMatchManager();
         SoccerLeagueTable leagueTable = new SoccerLeagueTable(new DefaultLeagueTableFormatter());
         PointsManager pointsManager = new PointsManager(leagueTable);

         // Tally up the scores for complete matches
         for (SoccerMatch match : scoreSheet.getSoccerMatches()) {
            pointsManager.registerGame(match, matchManager.getResult(match));
         }

         System.out.println(leagueTable.exportData());
      }
   }
}
