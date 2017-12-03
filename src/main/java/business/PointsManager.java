package business;

import application.SoccerLeagueTable;
import data.SoccerTeam;

public class PointsManager {
   private static final Integer WIN_POINTS = 3;
   private static final Integer DRAW_POINTS = 1;
   private static final Integer LOSS_POINTS = 0;

   protected final SoccerLeagueTable table;

   public PointsManager(SoccerLeagueTable table) {
      this.table = table;
   }

   public void registerWin(SoccerTeam team) {
      int oldTotal = table.getScore(team);

      table.updateScore(team, oldTotal + WIN_POINTS);
   }

   public void registerDraw(SoccerTeam team) {
      int oldTotal = table.getScore(team);

      table.updateScore(team, oldTotal + DRAW_POINTS);
   }

   public void registerLoss(SoccerTeam team) {
      int oldTotal = table.getScore(team);

      table.updateScore(team, oldTotal + LOSS_POINTS);
   }
}
