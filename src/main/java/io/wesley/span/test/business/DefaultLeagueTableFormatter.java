package io.wesley.span.test.business;

import io.wesley.span.test.data.table.LeagueTableNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultLeagueTableFormatter implements ILeagueTableFormatter {
   @Override
   public String format(Map<String, LeagueTableNode> leagueTable) {

      List<LeagueTableNode> tableNodes = new ArrayList<>();

      for (Map.Entry<String, LeagueTableNode> entry : leagueTable.entrySet()) {
         tableNodes.add(entry.getValue());
      }

      tableNodes.sort(Collections.reverseOrder());

      int position = 1;
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i<tableNodes.size(); i++) {
         if (i > 0 && tableNodes.get(i-1).getPoints().compareTo(tableNodes.get(i).getPoints()) != 0) {
            position = i+1;
         }

         sb.append(buildOutputLine(position, tableNodes.get(i)));
         sb.append(System.lineSeparator());
      }

      return sb.toString();
   }

   private String buildOutputLine(int position, LeagueTableNode leagueTableNode) {
      StringBuilder sb = new StringBuilder();

      sb.append(position);
      sb.append(". ");
      sb.append(leagueTableNode.getSoccerTeam().getTeamName());
      sb.append(", ");
      sb.append(leagueTableNode.getPoints());
      if (leagueTableNode.getPoints() == 1) {
         sb.append(" pt");
      } else {
         sb.append(" pts");
      }

      return  sb.toString();
   }
}
