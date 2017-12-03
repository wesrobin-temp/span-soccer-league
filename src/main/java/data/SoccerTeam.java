package data;

import java.util.Objects;

/**
 * Data class representing a soccer team. Stores the name of the team.
 */
public class SoccerTeam {
   private final String teamName;

   public SoccerTeam(String teamName) {
      this.teamName = teamName;
   }

   public String getTeamName() {
      return teamName;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      SoccerTeam that = (SoccerTeam) o;
      return Objects.equals(teamName, that.teamName);
   }

   @Override
   public int hashCode() {
      return Objects.hash(teamName);
   }
}
