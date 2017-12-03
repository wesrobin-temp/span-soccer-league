package io.wesley.span.test.business;

import io.wesley.span.test.application.SoccerLeagueTable;
import io.wesley.span.test.data.SoccerMatch;
import io.wesley.span.test.data.SoccerTeam;

public class PointsManager {
   protected static final Long WIN_POINTS = 3L;
   protected static final Long DRAW_POINTS = 1L;
   protected static final Long LOSS_POINTS = 0L;

   protected final SoccerLeagueTable table;

   public PointsManager(SoccerLeagueTable table) {
      this.table = table;
   }

   public void registerGame(SoccerMatch match, int result) {
      switch (result) {
         case 1:
            registerWin(match.getHomeTeam());
            registerLoss(match.getAwayTeam());
            break;
         case -1:
            registerWin(match.getAwayTeam());
            registerLoss(match.getHomeTeam());
            break;
         case 0:
            registerDraw(match.getAwayTeam());
            registerDraw(match.getHomeTeam());
            break;
      }
   }

   protected void registerWin(SoccerTeam team) {
      if (!table.hasTeam(team)) {
         table.addNewTeam(team);
      }

      table.addPoints(team, WIN_POINTS);
   }

   protected void registerDraw(SoccerTeam team) {
      if (!table.hasTeam(team)) {
         table.addNewTeam(team);
      }

      table.addPoints(team, DRAW_POINTS);
   }

   protected void registerLoss(SoccerTeam team) {
      if (!table.hasTeam(team)) {
         table.addNewTeam(team);
      }

      table.addPoints(team, LOSS_POINTS);
   }
}
