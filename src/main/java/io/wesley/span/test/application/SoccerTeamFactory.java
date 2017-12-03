package io.wesley.span.test.application;

import io.wesley.span.test.data.SoccerTeam;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to manage the creation of Soccer Teams. A static map is created and kept to ensure that only a single
 * instance of each team is created, keyed on the team's name.
 * <p/>
 * Two teams may not have duplicate names, or they will be treated as the same team.
 */
public class SoccerTeamFactory {
   private static Map<String, SoccerTeam> teamMap = new HashMap<>();

   public SoccerTeam getTeam(String teamName) {
      if (teamMap.containsKey(teamName)) {
         return teamMap.get(teamName);
      } else {
         SoccerTeam newTeam = new SoccerTeam(teamName);
         teamMap.put(teamName, newTeam);

         return newTeam;
      }
   }
}
