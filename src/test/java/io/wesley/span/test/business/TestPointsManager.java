package io.wesley.span.test.business;

import io.wesley.span.test.util.SoccerLeagueTable;
import io.wesley.span.test.data.SoccerMatch;
import io.wesley.span.test.data.SoccerTeam;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class TestPointsManager {
   private PointsManager testedClass;

   private SoccerLeagueTable soccerLeagueTable;
   private SoccerTeam team;

   @BeforeMethod
   public void beforeMethod() {
      team = mock(SoccerTeam.class);
      UUID teamUUID = UUID.randomUUID();
      when(team.getUuid()).thenReturn(teamUUID);
      ILeagueTableFormatter formatter = mock(ILeagueTableFormatter.class);
      soccerLeagueTable = spy(new SoccerLeagueTable(formatter));

      testedClass = spy(new PointsManager(soccerLeagueTable));
   }

   @AfterMethod
   public void afterMethod() {
      reset(testedClass, soccerLeagueTable);
   }

   @Test
   public void registerGame_givenHomeTeamWin_callsRegisterWinOnHomeAndRegisterLossOnAway(){
      // Setup
      SoccerTeam homeTeam = mock(SoccerTeam.class);
      SoccerTeam awayTeam = mock(SoccerTeam.class);
      SoccerMatch soccerMatch = new SoccerMatch(homeTeam, awayTeam);
      doNothing().when(testedClass).registerWin(any());
      doNothing().when(testedClass).registerLoss(any());

      // Do the thing
      testedClass.registerGame(soccerMatch, 1);

      // Verify
      verify(testedClass, times(1)).registerWin(homeTeam);
      verify(testedClass, times(1)).registerLoss(awayTeam);
   }

   @Test
   public void registerGame_givenAwayTeamWin_callsRegisterWinOnHomeAndRegisterLossOnAway(){
      // Setup
      SoccerTeam homeTeam = mock(SoccerTeam.class);
      SoccerTeam awayTeam = mock(SoccerTeam.class);
      SoccerMatch soccerMatch = new SoccerMatch(homeTeam, awayTeam);
      doNothing().when(testedClass).registerWin(any());
      doNothing().when(testedClass).registerLoss(any());

      // Do the thing
      testedClass.registerGame(soccerMatch, -1);

      // Verify
      verify(testedClass, times(1)).registerWin(awayTeam);
      verify(testedClass, times(1)).registerLoss(homeTeam);
   }


   @Test
   public void registerGame_givenDraw_callsRegisterWinOnHomeAndRegisterLossOnAway(){
      // Setup
      SoccerTeam homeTeam = mock(SoccerTeam.class);
      SoccerTeam awayTeam = mock(SoccerTeam.class);
      SoccerMatch soccerMatch = new SoccerMatch(homeTeam, awayTeam);
      doNothing().when(testedClass).registerDraw(any());
      doNothing().when(testedClass).registerDraw(any());

      // Do the thing
      testedClass.registerGame(soccerMatch, 0);

      // Verify
      verify(testedClass, times(1)).registerDraw(homeTeam);
      verify(testedClass, times(1)).registerDraw(awayTeam);
   }


   @Test
   public void registerWin_givenWinningTeam_addsWinPointsToTotal() {
      // Setup
      soccerLeagueTable.addNewTeam(team);

      // Pre-assertions
      Assert.assertEquals(soccerLeagueTable.getScore(team), new Long(0));

      // Do the thing
      testedClass.registerWin(team);

      // Verify
      verify(soccerLeagueTable, times(1)).addPoints(eq(team), anyLong());
      Assert.assertEquals(soccerLeagueTable.getScore(team), PointsManager.WIN_POINTS);
   }

   @Test
   public void registerLoss_givenLosingTeam_addsLossPointsToTotal() {
      // Setup
      soccerLeagueTable.addNewTeam(team);

      // Pre-assertions
      Assert.assertEquals(soccerLeagueTable.getScore(team), new Long(0));

      // Do the thing
      testedClass.registerLoss(team);

      // Verify
      verify(soccerLeagueTable, times(1)).addPoints(eq(team), anyLong());
      Assert.assertEquals(soccerLeagueTable.getScore(team), PointsManager.LOSS_POINTS);

   }

   @Test
   public void registerDraw_givenDrawingTeam_addsDrawPointsToTotal() {
      // Setup
      soccerLeagueTable.addNewTeam(team);

      // Pre-assertions
      Assert.assertEquals(soccerLeagueTable.getScore(team), new Long(0));

      // Do the thing
      testedClass.registerDraw(team);

      // Verify
      verify(soccerLeagueTable, times(1)).addPoints(eq(team), anyLong());
      Assert.assertEquals(soccerLeagueTable.getScore(team), PointsManager.DRAW_POINTS);
   }
}
