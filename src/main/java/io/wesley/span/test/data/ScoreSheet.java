package io.wesley.span.test.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScoreSheet {
   protected List<SoccerMatch> soccerMatches = new ArrayList<>();

   public void addMatch(SoccerMatch soccerMatch) {
      soccerMatches.add(soccerMatch);
   }

   public List<SoccerMatch> getSoccerMatches() {
      return soccerMatches;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ScoreSheet that = (ScoreSheet) o;
      return Objects.equals(soccerMatches, that.soccerMatches);
   }

   @Override
   public int hashCode() {

      return Objects.hash(soccerMatches);
   }

   @Override
   public String toString() {
      return "ScoreSheet{" +
            "soccerMatches=" + soccerMatches +
            '}';
   }
}
