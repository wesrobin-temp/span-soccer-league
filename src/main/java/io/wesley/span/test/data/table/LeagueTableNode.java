package io.wesley.span.test.data.table;

import io.wesley.span.test.data.SoccerTeam;

import java.util.Objects;

public class LeagueTableNode implements Comparable<LeagueTableNode> {
   private SoccerTeam soccerTeam;
   private Long points;

   public LeagueTableNode(SoccerTeam soccerTeam) {
      this.soccerTeam = soccerTeam;
      this.points = 0L;
   }

   public Long addPoints(Long points) {
      this.points = this.points + points;

      return this.points;
   }

   public SoccerTeam getSoccerTeam() {
      return soccerTeam;
   }

   public Long getPoints() {
      return points;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      LeagueTableNode node = (LeagueTableNode) o;
      return Objects.equals(soccerTeam, node.soccerTeam);
   }

   @Override
   public int hashCode() {

      return Objects.hash(soccerTeam);
   }

   @Override
   public String toString() {
      return "LeagueTableNode{" +
            "soccerTeam=" + soccerTeam +
            ", points=" + points +
            '}';
   }

   /**
    * This CompareTo will sort first on the number of points (highest to lowest) and in the case of a tie, sort
    * Alphabetically.
    *
    * @param o The {@link LeagueTableNode} to compare to.
    * @return the value {@code 0} if {@code this == o};
    *         the value {@code -1} if {@code this < o}; and
    *         the value {@code 1} if {@code this > o}
    */
   @Override
   public int compareTo(LeagueTableNode o) {
      return this.points.compareTo(o.getPoints()) == 0 ? -1 * this.getSoccerTeam().getTeamName().compareToIgnoreCase(o
            .soccerTeam
            .getTeamName()) : this.points.compareTo(o.getPoints());
   }
}
