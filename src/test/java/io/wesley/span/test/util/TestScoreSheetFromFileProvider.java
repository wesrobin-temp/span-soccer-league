package io.wesley.span.test.util;

import io.wesley.span.test.TestConstants;
import io.wesley.span.test.data.ScoreSheet;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;

public class TestScoreSheetFromFileProvider {
   private ScoreSheetFromFileProvider testedClass;

   private File happyDayFile;

   @BeforeMethod
   public void beforeMethod() {
      happyDayFile = new File("src\\test\\resources\\happyday-soccer-results.txt");

      testedClass = spy(new ScoreSheetFromFileProvider(happyDayFile.getAbsolutePath()));
   }

   @AfterMethod
   public void afterMethod() {
      reset(testedClass);
   }

   @Test
   public void produce_filePathPointsToRealFile_producesDesiredScoreSheet() throws Exception {
      // Do the thing
      ScoreSheet actual = testedClass.produce();

      // Verify (can't just Assert.assert equals on the score cards because each match gets a random UUID
      for (int i = 0 ; i < actual.getSoccerMatches().size() ; i++) {
         Assert.assertEquals(actual.getSoccerMatches().get(i).getHomeTeam().getTeamName(), TestConstants.happyDayScoreSheet
               .getSoccerMatches().get(i).getHomeTeam().getTeamName());
         Assert.assertEquals(actual.getSoccerMatches().get(i).getAwayTeam().getTeamName(), TestConstants.happyDayScoreSheet
               .getSoccerMatches().get(i).getAwayTeam().getTeamName());
         Assert.assertEquals(actual.getSoccerMatches().get(i).getHomeTeamScore(), TestConstants.happyDayScoreSheet
               .getSoccerMatches().get(i).getHomeTeamScore());
         Assert.assertEquals(actual.getSoccerMatches().get(i).getAwayTeamScore(), TestConstants.happyDayScoreSheet
               .getSoccerMatches().get(i).getAwayTeamScore());
      }
   }

   @Test
   public void produce_filePathPointsToNothing_producesEmptyScoreSheet() throws Exception {
      ScoreSheetFromFileProvider tempClass = new ScoreSheetFromFileProvider("some file that doesn't exist");

      // Do the thing
      ScoreSheet actual = tempClass.produce();

      // Verify
      Assert.assertEquals(actual, TestConstants.emptyScoreSheet);
   }

   @Test(expectedExceptions = IllegalStateException.class)
   public void produce_filePathIsNull_throwsIllegalStateException() throws Exception {
      // Setup
      ScoreSheetFromFileProvider tempClass = new ScoreSheetFromFileProvider(null);

      // Do the thing
      tempClass.produce();
   }
}
