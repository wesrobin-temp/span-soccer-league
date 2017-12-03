package io.wesley.span.test.business;

import io.wesley.span.test.TestConstants;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;

public class TestDefaultLeagueTableFormatter {
   private DefaultLeagueTableFormatter testedClass;

   @BeforeMethod
   public void beforeMethod() {
      testedClass = spy(new DefaultLeagueTableFormatter());
   }

   @AfterMethod
   public void afterMethod() {
      reset(testedClass);
   }

   @Test
   public void format_givenTable_producesExpectedString() {
      // Do the thing
      String actual = testedClass.format(TestConstants.leagueTable);

      // Verify
      Assert.assertEquals(actual, TestConstants.expectedFormattedLeagueTable);
   }
}
