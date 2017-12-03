package io.wesley.span.test.business;

import io.wesley.span.test.data.SoccerMatch;
import io.wesley.span.test.data.SoccerTeam;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;

public class TestMatchManager {
   private SoccerMatchManager matchManager;

   @BeforeMethod
   public void beforeMethod() {
      matchManager = spy(new SoccerMatchManager());
   }

   @AfterMethod
   public void afterMethod() {
      reset(matchManager);
   }

   @Test
   public void getResult_givenHomeTeamWin_returnsOne() {
      // Setup
      SoccerTeam homeTeam = new SoccerTeam("homeTeam");
      SoccerTeam awayTeam = new SoccerTeam("awayTeam");
      SoccerMatch match = new SoccerMatch(homeTeam, awayTeam);
      match.setHomeTeamScore(2);
      match.setAwayTeamScore(0);

      // Do the thing
      int result = matchManager.getResult(match);

      // Verify
      Assert.assertEquals(result, 1);
   }

   @Test
   public void getResult_givenAwayTeamWin_returnsMinusOne() {
      // Setup
      SoccerTeam homeTeam = new SoccerTeam("homeTeam");
      SoccerTeam awayTeam = new SoccerTeam("awayTeam");
      SoccerMatch match = new SoccerMatch(homeTeam, awayTeam);
      match.setHomeTeamScore(0);
      match.setAwayTeamScore(2);

      // Do the thing
      int result = matchManager.getResult(match);

      // Verify
      Assert.assertEquals(result, -1);
   }

   @Test
   public void getResult_givenDraw_returnsZero() {
      // Setup
      SoccerTeam homeTeam = new SoccerTeam("homeTeam");
      SoccerTeam awayTeam = new SoccerTeam("awayTeam");
      SoccerMatch match = new SoccerMatch(homeTeam, awayTeam);
      match.setHomeTeamScore(2);
      match.setAwayTeamScore(2);

      // Do the thing
      int result = matchManager.getResult(match);

      // Verify
      Assert.assertEquals(result, 0);
   }
}
