package io.wesley.span.test.data;

import java.util.Objects;
import java.util.UUID;

/**
 * Data class representing a single match between two teams (home and away). Stores the score of the game, and keeps
 * a UUID to uniquely identify this particular match.
 * <p/>
 * Scores default to -1 and must be set if a result is to be calculated.
 */
public class SoccerMatch {
   private final UUID uuid = UUID.randomUUID();
   private final SoccerTeam homeTeam;
   private final SoccerTeam awayTeam;

   private Long homeTeamScore = -1L;
   private Long awayTeamScore = -1L;

   public SoccerMatch(SoccerTeam homeTeam, SoccerTeam awayTeam) {
      if (homeTeam.equals(awayTeam)) {
         throw new IllegalArgumentException("Same team passed to SoccerMatch as both home and away team.");
      }

      this.homeTeam = homeTeam;
      this.awayTeam = awayTeam;
   }

   public SoccerMatch(SoccerTeam homeTeam, SoccerTeam awayTeam, Long homeTeamScore, Long awayTeamScore) {
      this.homeTeam = homeTeam;
      this.awayTeam = awayTeam;
      this.homeTeamScore = homeTeamScore;
      this.awayTeamScore = awayTeamScore;
   }

   public UUID getUuid() {
      return uuid;
   }

   public SoccerTeam getHomeTeam() {
      return homeTeam;
   }

   public SoccerTeam getAwayTeam() {
      return awayTeam;
   }

   public Long getHomeTeamScore() {
      return homeTeamScore;
   }

   public void setHomeTeamScore(Long homeTeamScore) {
      this.homeTeamScore = homeTeamScore;
   }

   public Long getAwayTeamScore() {
      return awayTeamScore;
   }

   public void setAwayTeamScore(Long awayTeamScore) {
      this.awayTeamScore = awayTeamScore;
   }

   @Override
   public String toString() {
      return "SoccerMatch{" +
            "uuid=" + uuid +
            ", homeTeam=" + homeTeam +
            ", awayTeam=" + awayTeam +
            ", homeTeamScore=" + homeTeamScore +
            ", awayTeamScore=" + awayTeamScore +
            '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      SoccerMatch match = (SoccerMatch) o;
      return Objects.equals(uuid, match.uuid) &&
            Objects.equals(homeTeam, match.homeTeam) &&
            Objects.equals(awayTeam, match.awayTeam) &&
            Objects.equals(homeTeamScore, match.homeTeamScore) &&
            Objects.equals(awayTeamScore, match.awayTeamScore);
   }

   @Override
   public int hashCode() {

      return Objects.hash(uuid, homeTeam, awayTeam, homeTeamScore, awayTeamScore);
   }
}
