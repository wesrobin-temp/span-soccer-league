package application;

import data.ScoreSheet;
import data.SoccerMatch;
import data.SoccerTeam;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ScoreSheetFromFileProvider implements IScoreSheetProvider {
   private String file;

   private static final String MATCH_PATTERN = "\\w+ [0-9]+, ?\\w+ [0-9]+$";

   public ScoreSheetFromFileProvider(String fileName) {
      this.file = fileName;
   }

   @Override
   public ScoreSheet produce() throws Exception {
      if (file == null) {
         throw new IllegalStateException("Attempted to produce a scoresheet with a null file.");
      }

      ScoreSheet scoreSheet = new ScoreSheet();

      // TODO: Remove
      // try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      //    String line;
      //    while ((line = br.readLine()) != null) {
      //       SoccerMatch match = generateMatchFromInput(line);
      //       if (match != null) {
      //          scoreSheet.addMatch(match);
      //       }
      //    }
      // }

      try (Stream<String> stream = Files.lines(Paths.get(file))) {
         stream.forEach((s) -> scoreSheet.addMatch(generateMatchFromInput(s)));
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
      Integer homeScore, awayScore;

      String[] scores = matchStr.split(",");

      homeScore = Integer.parseInt(scores[0].substring(scores[0].length()));
      homeTeamName = scores[0].substring(0, scores[0].length() - 1);

      awayScore = Integer.parseInt(scores[1].substring(scores[1].length()));
      awayTeamName = scores[1].substring(0, scores[1].length() - 1);

      SoccerTeam homeTeam = new SoccerTeamFactory().getTeam(homeTeamName);
      SoccerTeam awayTeam = new SoccerTeamFactory().getTeam(awayTeamName);

      SoccerMatch newMatch = new SoccerMatch(homeTeam, awayTeam);

      newMatch.setHomeTeamScore(homeScore);
      newMatch.setAwayTeamScore(awayScore);

      return newMatch;
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
      // TODO: Remove
//      Pattern pattern = Pattern.compile(MATCH_PATTERN);
//
//      return pattern.matcher(matchStr).matches();
      return matchStr.matches(MATCH_PATTERN);
   }
}
