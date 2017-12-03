package io.wesley.span.test;

import io.wesley.span.test.application.IScoreSheetProvider;
import io.wesley.span.test.application.ScoreSheetFromFileProvider;
import io.wesley.span.test.application.SoccerLeagueTable;
import io.wesley.span.test.business.DefaultLeagueTableFormatter;
import io.wesley.span.test.business.PointsManager;
import io.wesley.span.test.business.SoccerMatchManager;
import io.wesley.span.test.data.ScoreSheet;
import io.wesley.span.test.data.SoccerMatch;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ITSoccerLeague {
   @DataProvider(name = "SoccerLeague")
   public Object[][] results() {
      return new Object[][]{{"happyday-soccer-results.txt", "happyday-expected.txt"}, {"large-values-soccer-results" +
            ".txt", "large-values-expected.txt"}, {"crazy-but-legal-names-soccer-results" +
            ".txt", "crazy-but-legal-names-expected.txt"}};
   }


   @Test(dataProvider = "SoccerLeague")
   public void testHappyDayResults(String inputFileName, String expectedFileName) throws Exception {
      File inputFile = new File("src/test/resources/" + inputFileName);
      IScoreSheetProvider scoreSheetProvider = new ScoreSheetFromFileProvider(inputFile.getAbsolutePath());

      ScoreSheet scoreSheet = scoreSheetProvider.produce();

      // Initialise some workers..
      SoccerMatchManager matchManager = new SoccerMatchManager();
      SoccerLeagueTable leagueTable = new SoccerLeagueTable(new DefaultLeagueTableFormatter());
      PointsManager pointsManager = new PointsManager(leagueTable);

      // Tally up the scores for complete matches
      for (SoccerMatch match : scoreSheet.getSoccerMatches()) {
         pointsManager.registerGame(match, matchManager.getResult(match));
      }

      String output = leagueTable.exportData();
      String expected = "";
      File expectedFile = new File("src/test/resources/" + expectedFileName);

      try (Stream<String> stream = Files.lines(Paths.get(expectedFile.getAbsolutePath()))) {
         expected = stream
               .filter(line -> !line.startsWith("line3"))
               .collect(Collectors.joining(System.lineSeparator()));
      } catch (IOException e) {
         e.printStackTrace();
      }

      Assert.assertEquals(output, expected);
   }


}
