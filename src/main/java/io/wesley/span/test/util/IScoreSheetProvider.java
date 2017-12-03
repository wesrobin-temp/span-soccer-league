package io.wesley.span.test.util;

import io.wesley.span.test.data.ScoreSheet;

public interface IScoreSheetProvider {
   /**
    * Method to produce a {@link ScoreSheet}. Implementers may decide how best to do this.
    *
    * @return The {@link ScoreSheet} representing a list of games.
    * @throws Exception
    */
   ScoreSheet produce() throws Exception;
}
