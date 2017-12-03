package io.wesley.span.test.util;

import io.wesley.span.test.data.ScoreSheet;
import io.wesley.span.test.data.SoccerMatch;
import io.wesley.span.test.data.SoccerTeam;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ScoreSheetFromFileProvider implements IScoreSheetProvider {
   private String filePath;

   private static final String MATCH_PATTERN = "[a-zA-z0-9 ]+ [0-9]+, ?[a-zA-z0-9 ]+ [0-9]+$";

   public ScoreSheetFromFileProvider(String filePath) {
      this.filePath = filePath;
   }

   @Override
   public ScoreSheet produce() throws Exception {
      if (filePath == null) {
         throw new IllegalStateException("Attempted to produce a scoresheet with a null filePath.");
      }

      ScoreSheet scoreSheet = new ScoreSheet();

      if (new File(filePath).exists()) {
         try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach((s) -> scoreSheet.addMatch(generateMatchFromInput(s)));
         }
      }

      return scoreSheet;
   }

   /**
    * Generates a {@link SoccerMatch} based on the given String, which is expected to be a line representing a single
    * game.
    *
    * @param matchStr The String representation of a game.
    * @return The {@link SoccerMatch} representing this game.
    */
   private SoccerMatch generateMatchFromInput(String matchStr) {
      if (!verifyMatchInputString(matchStr)) {
         return null;
      }

      String homeTeamName, awayTeamName;
      Long homeScore, awayScore;

      String[] scores = matchStr.split(",");

      homeScore = Long.parseLong(scores[0].substring(scores[0].lastIndexOf(' ') + 1));
      homeTeamName = scores[0].substring(0, scores[0].lastIndexOf(' ')).trim();

      awayScore = Long.parseLong(scores[1].substring(scores[1].lastIndexOf(' ') + 1));
      awayTeamName = scores[1].substring(0, scores[1].lastIndexOf(' ')).trim();

      SoccerTeam homeTeam = new SoccerTeamFactory().getTeam(homeTeamName);
      SoccerTeam awayTeam = new SoccerTeamFactory().getTeam(awayTeamName);

      return new SoccerMatch(homeTeam, awayTeam, homeScore, awayScore);
   }

   /**
    * Verifies that the given string matches the {@link #MATCH_PATTERN regex} defined at the top of this class.
    * <p/>
    * Explanation of the regex:
    * <ul>
    * <li>"\w+"    : matches one or more word characters (a-z, A-Z, 0-9)</li>
    * <li>" "      : matches a space (" ") character</li>
    * <li>"[0-9]+" : matches one or more numeric characters (0-9)</li>
    * <li>","      : matches a "," character</li>
    * <li>" "      : matches a space (" ") character</li>
    * <li>"\w+"    : matches one or more word characters (a-z, A-Z, 0-9)</li>
    * <li>" "      : matches a space (" ") character</li>
    * <li>"[0-9]+" : matches one or more numeric characters (0-9)</li>
    * <li>"$"      : expects the end of the line</li>
    * </ul>
    *
    * @param matchStr
    * @return
    */
   private boolean verifyMatchInputString(String matchStr) {
      return matchStr.matches(MATCH_PATTERN);
   }
}
