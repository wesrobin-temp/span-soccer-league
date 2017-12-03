package application;

import data.SoccerTeam;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class TestSoccerLeagueTable {
   private SoccerLeagueTable testedClass;

   // Some convenient variables to mock
   private SoccerTeam team;

   @BeforeMethod
   public void beforeMethod() {
      testedClass = spy(new SoccerLeagueTable());

      team = mock(SoccerTeam.class);
   }

   @AfterMethod
   public void afterMethod() {
      reset(testedClass, team);
   }

   @Test
   public void addNewTeam_givenTeamNotInTable_addsTeamToTable() {

      // Pre-assertions
      Assert.assertNull(testedClass.LEAGUE_TABLE.get(team));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 0);

      // Do the thing
      testedClass.addNewTeam(team);

      // Verify
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);
   }

   @Test
   public void addNewTeam_givenTeamInTable_doesNothing() {
      // Setup
      Integer points = 10;
      // Give the team some points, so that we make sure the .put(team, 0) method call isn't run
      testedClass.LEAGUE_TABLE.put(team, points);

      // Pre-assertions
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);

      // Do the thing
      testedClass.addNewTeam(team);

      // Verify
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);
      Assert.assertEquals(testedClass.LEAGUE_TABLE.get(team), points);
   }

   @Test
   public void addNewTeam_givenNull_doesNothing() {
      // Setup
      Integer points = 10;
      // Give the team some points, so that we make sure the .put(team, 0) method call isn't run
      testedClass.LEAGUE_TABLE.put(team, points);

      // Pre-assertions
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);

      // Do the thing
      testedClass.addNewTeam(null);

      // Verify
      Assert.assertNotNull(testedClass.LEAGUE_TABLE.get(team));
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 1);
      Assert.assertEquals(testedClass.LEAGUE_TABLE.get(team), points);
   }

   @Test
   public void updateScore_givenTeamInTable_replacesScore() {
      // Setup
      testedClass.LEAGUE_TABLE.put(team, 10);

      // Do the thing
      testedClass.updateScore(team, 15);

      // Verify
      Assert.assertEquals(testedClass.LEAGUE_TABLE.get(team), new Integer(15));
   }

   @Test
   public void updateScore_givenTeamNotInTable_doesNothing() {
      // Pre-assertions
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 0);
      Assert.assertNull(testedClass.LEAGUE_TABLE.get(team));

      // Do the thing
      testedClass.updateScore(team, 15);

      // Verify
      Assert.assertEquals(testedClass.LEAGUE_TABLE.size(), 0);
      Assert.assertNull(testedClass.LEAGUE_TABLE.get(team));
   }
}
