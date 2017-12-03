package business;

import application.SoccerLeagueTable;
import data.SoccerTeam;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class TestPointsManager {
   private PointsManager testedClass;

   private SoccerLeagueTable soccerLeagueTable;
   private SoccerTeam team;

   @BeforeMethod
   public void beforeMethod() {
      soccerLeagueTable = spy(new SoccerLeagueTable());
      team = mock(SoccerTeam.class);

      testedClass = new PointsManager(soccerLeagueTable);
   }

   @AfterMethod
   public void afterMethod() {
      reset(testedClass, soccerLeagueTable);
   }

   @Test
   public void registerWin_givenWinningTeam_addsThreePointsToTotal() {
      // Setup
      soccerLeagueTable.addNewTeam(team);

      // Pre-assertions
      Assert.assertEquals(soccerLeagueTable.getScore(team), 0);

      // Do the thing
      testedClass.registerWin(team);

      // Verify
      verify(soccerLeagueTable, times(1)).updateScore(eq(team), anyInt());
   }
}
