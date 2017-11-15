package se.codeboss.ceo.converter;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("unused")
public class PdxEmpireFileParserTests {

	final static String NEW_LINE = System.getProperty("line.separator");

	public static class UnitTests {

		@Test
		public void generateArrayReplacement_Single() {
			assertEquals("ethics=[$1]", PdxEmpireFileParser.generateArrayReplacement("ethics", 1));
		}

		@Test
		public void generateArrayReplacement_Multiple() {
			assertEquals("ethics=[$1,$2]", PdxEmpireFileParser.generateArrayReplacement("ethics", 2));
			assertEquals("ethics=[$1,$2,$3,$4,$5]", PdxEmpireFileParser.generateArrayReplacement("ethics", 5));
		}

		@Test
		public void generateDuplicateLinesExpression_Single() {
			assertEquals("trait=(\"[a-z_]*\")$", PdxEmpireFileParser.generateDuplicateLinesExpression("trait", 1));
		}

		@Test
		public void generateDuplicateLinesExpression_Multiple() {
			assertEquals("trait=(\"[a-z_]*\")$[\\r\\n]+\\s*trait=(\"[a-z_]*\")$", PdxEmpireFileParser.generateDuplicateLinesExpression("trait", 2));
			assertEquals("trait=(\"[a-z_]*\")$[\\r\\n]+\\s*trait=(\"[a-z_]*\")$[\\r\\n]+\\s*trait=(\"[a-z_]*\")$[\\r\\n]+\\s*trait=(\"[a-z_]*\")$", PdxEmpireFileParser.generateDuplicateLinesExpression("trait", 4));
		}

		@Test
		public void emptyLine_ShouldMatch() {
			final String toReplace = "    ";
			assertEquals("", toReplace.replaceAll(PdxEmpireFileParser.EMPTY_LINE_EXPRESSION, ""));
		}

		@Test
		public void emptyLine_DoNothing() {
			final String toReplace = "  hej  ";
			assertEquals("  hej  ", toReplace.replaceAll(PdxEmpireFileParser.EMPTY_LINE_EXPRESSION, ""));
		}

		@Test
		public void equalsOnNewLine_MoveToPreviousLine() {
			final StringBuilder toReplace = new StringBuilder("My empire");
			toReplace.append(NEW_LINE)
					 .append("={");

			assertEquals("My empire={", toReplace.toString().replaceAll(PdxEmpireFileParser.EQUALS_ON_NEW_LINE_EXPRESSION, "$1="));
		}

	}


	@RunWith(SpringJUnit4ClassRunner.class)
	public static class IntegrationTests {

		@Autowired
		private ResourceLoader resourceLoader;

		@Test
		public void transformToJson() throws IOException {
			final String sample = FileUtils.readFileToString(resourceLoader.getResource("classpath:/KallesJunta.txt").getFile());

			final String json = PdxEmpireFileParser.transformEmpireFileToJson(sample);

			assertEquals("", json);
		}
	}

}