package io.wesley.span.test.data.table;

import io.wesley.span.test.data.SoccerTeam;

import java.util.Objects;

public class LeagueTableNode implements Comparable<LeagueTableNode> {
   private SoccerTeam soccerTeam;
   private Integer points;

   public LeagueTableNode(SoccerTeam soccerTeam) {
      this.soccerTeam = soccerTeam;
      this.points = 0;
   }

   public Integer addPoints(Integer points) {
      this.points = this.points + points;

      return this.points;
   }

   public SoccerTeam getSoccerTeam() {
      return soccerTeam;
   }

   public Integer getPoints() {
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

   @Override
   public int compareTo(LeagueTableNode o) {
      return this.points.compareTo(o.getPoints());
   }
}
