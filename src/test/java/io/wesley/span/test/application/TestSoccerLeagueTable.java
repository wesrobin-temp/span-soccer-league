package io.wesley.span.test.application;

import io.wesley.span.test.business.ILeagueTableFormatter;
import io.wesley.span.test.data.SoccerTeam;
import io.wesley.span.test.data.table.LeagueTableNode;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

public class TestSoccerLeagueTable {
   private SoccerLeagueTable testedClass;

   // Some convenient variables to mock
   private SoccerTeam team;

   @BeforeMethod
   public void beforeMethod() {
      team = mock(SoccerTeam.class);
      UUID teamUUID = UUID.randomUUID();
      when(team.getUuid()).thenReturn(teamUUID);
      ILeagueTableFormatter formatter = mock(ILeagueTableFormatter.class);

      testedClass = spy(new SoccerLeagueTable(formatter));
   }

   @AfterMethod
   public void afterMethod() {
      reset(testedClass, team);
   }

   @Test
   public void addNewTeam_givenTeamNotInTable_addsTeamToTable() {

      // Pre-assertions
      Assert.assertNull(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 0);

      // Do the thing
      testedClass.addNewTeam(team);

      // Verify
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);
   }

   @Test
   public void addNewTeam_givenTeamInTable_doesNothing() {
      // Setup
      Long points = 10L;
      // Give the team some points, so that we make sure the .put(team, 0) method call isn't run
      testedClass.LEAGUE_TABLE.put(team.getUuid().toString(), new LeagueTableNode(team));
      testedClass.LEAGUE_TABLE.get(team.getUuid().toString()).addPoints(points);

      // Pre-assertions
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);

      // Do the thing
      testedClass.addNewTeam(team);

      // Verify
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);
      Assert.assertEquals(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()).getPoints(), points);
   }

   @Test
   public void addNewTeam_givenNull_doesNothing() {
      // Setup
      Long points = 10L;
      // Give the team some points, so that we make sure the .put(team, 0) method call isn't run
      testedClass.LEAGUE_TABLE.put(team.getUuid().toString(), new LeagueTableNode(team));
      testedClass.LEAGUE_TABLE.get(team.getUuid().toString()).addPoints(points);

      // Pre-assertions
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);

      // Do the thing
      testedClass.addNewTeam(null);

      // Verify
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);
      Assert.assertEquals(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()).getPoints(), points);
   }

   @Test
   public void updateScore_givenTeamInTable_replacesScore() {
      // Setup
      testedClass.LEAGUE_TABLE.put(team.getUuid().toString(), new LeagueTableNode(team));

      // Do the thing
      testedClass.addPoints(team, 15L);

      // Verify
      Assert.assertEquals(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()).getPoints(), new Long(15));
   }

   @Test
   public void updateScore_givenTeamNotInTable_doesNothing() {
      // Pre-assertions
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 0);
      Assert.assertNull(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()));

      // Do the thing
      testedClass.addPoints(team, 15L);

      // Verify
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 0);
      Assert.assertNull(testedClass.LEAGUE_TABLE.get(team.getUuid().toString()));
   }
}
