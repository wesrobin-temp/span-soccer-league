package io.wesley.span.test.business;

import io.wesley.span.test.data.table.LeagueTableNode;

import java.util.Map;

public interface ILeagueTableFormatter {
   /**
    * Returns a String representation of the League Table, showing winners and losers.
    *
    * @param leagueTable The table to format into a String.
    * @return The String representing the table.
    */
   String format(Map<String, LeagueTableNode> leagueTable);
}
