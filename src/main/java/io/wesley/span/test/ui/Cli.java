package io.wesley.span.test.ui;

import io.wesley.span.test.util.IScoreSheetProvider;
import io.wesley.span.test.util.ScoreSheetFromFileProvider;
import org.apache.commons.cli.*;

import java.io.File;

public class Cli {
   private String[] args;
   private Options options = new Options();

   public Cli(String... args) {
      this.args = args;

      options.addOption("f", "file", true, "Set input file.");
      options.addOption("h", "help", false, "Show help.");
   }

   public IScoreSheetProvider parse() {
      CommandLineParser parser = new BasicParser();

      CommandLine cmd;

      try {
         cmd = parser.parse(options, args);

         if (cmd.getOptions().length > 1) {
            printHelp();
         }

         if (cmd.hasOption("f")) {
            File checkFile = new File(cmd.getOptionValue("f"));
            if (!checkFile.exists()) {
               System.out.println("Unable to find the given file. Please use an absolute path to the file.");
               System.exit(0);
            }
            return new ScoreSheetFromFileProvider(cmd.getOptionValue("f"));
         } else if (cmd.hasOption("h")) {
            printHelp();
         }
      } catch (ParseException e) {
         e.printStackTrace();
      }

      printHelp();
      return null;
   }

   private void printHelp() {
      HelpFormatter formater = new HelpFormatter();

      formater.printHelp("Main", options);

      System.exit(0);
   }
}
