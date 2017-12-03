package io.wesley.span.test.data;

import java.util.Objects;
import java.util.UUID;

/**
 * Data class representing a soccer team. Stores the name of the team.
 */
public class SoccerTeam {
   private final UUID uuid = UUID.randomUUID();
   private final String teamName;

   public SoccerTeam(String teamName) {
      this.teamName = teamName;
   }

   public UUID getUuid() {
      return uuid;
   }

   public String getTeamName() {
      return teamName;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      SoccerTeam that = (SoccerTeam) o;
      return Objects.equals(uuid, that.uuid) &&
            Objects.equals(teamName, that.teamName);
   }

   @Override
   public int hashCode() {

      return Objects.hash(uuid, teamName);
   }

   @Override
   public String toString() {
      return "SoccerTeam{" +
            "uuid=" + uuid +
            ", teamName='" + teamName + '\'' +
            '}';
   }
}
