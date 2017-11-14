package se.codeboss.ceo.converter;

import org.apache.commons.io.FileUtils;
import se.codeboss.ceo.model.Empire;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdxEmpireFileParser {

	private static final int TRAITS_MAX_COUNT = 5;
	private static final int ETHICS_MAX_COUNT = 3;

	static final String EQUALS_ON_NEW_LINE_EXPRESSION = "(.+)\\R=";
	static final String FTL_EXPRESSION = "(ftl\\s*)=(\\w+)";
	static final String KEY_AND_EQUALS_EXPRESSION = "([^\\s]+\\s*)=";
	static final String NO_EXPRESSION = "(.+\\s*=\\s*)no";
	static final String YES_EXPRESSION = "(.+\\s*=\\s*)yes";
	static final String DELIMITER_REQUIRED_EXPRESSION = "([^{](?=\\s+)(?!\\R\\s*}))$";

	static final String EMPTY_LINE_EXPRESSION = "^\\s*$";

	static final String TWO_CIVICS_EXPRESSION = "civics=\\{\\R\\s*(\"[a-z_]+\")\\R\\s*(\"[a-z_]+\")\\R\\s*}";
	static final String SINGLE_CIVICS_EXPRESSION = "civics=\\{\\R\\s*(\"[a-z_]+\")\\R\\s*}";



	public static List<Empire> parseFile(final File empireFile) throws IOException {
		final List<Empire> result = new ArrayList<>();

		final String empireJson = transformEmpireFileToJson(FileUtils.readFileToString(empireFile));



		return result;
	}

	static String transformEmpireFileToJson(final String empireString) {
		// Make sure no equal signs start on a new line so we can do other replacements later properly
		String result = empireString.replaceAll(EQUALS_ON_NEW_LINE_EXPRESSION, "$1=");

		// Add a delimiter to lines not followed by a single line with }, or starting with {..
		result = result.replaceAll(DELIMITER_REQUIRED_EXPRESSION, "$1,");

		// remove empty lines
		result = result.replaceAll(EMPTY_LINE_EXPRESSION, "");

		// Replace yes/no with boolean values
		result = result.replaceAll(NO_EXPRESSION, "$1false");
		result = result.replaceAll(YES_EXPRESSION, "$1true");

		// Add a surrounding quote on value on FTL-line
		result = result.replaceAll(FTL_EXPRESSION, "$1=\"$2\"");

		// replace lines of ethics with an array
		for (int i = ETHICS_MAX_COUNT; i > 0; i--) {
			result = result.replaceAll(generateDuplicateLinesExpression("ethic", i), generateArrayReplacement("ethics", i));
		}

		// replace lines of traits with an array
		// Maximum amount of traits is five, start with replacing that and work downwards
		for (int i = TRAITS_MAX_COUNT; i > 0; i--) {
			result = result.replaceAll(generateDuplicateLinesExpression("trait", i), generateArrayReplacement("traits", i));
		}

		// Replace civics with an array
		result = result.replaceAll(TWO_CIVICS_EXPRESSION, "civics=[$1, $2]");
		result = result.replaceAll(SINGLE_CIVICS_EXPRESSION, "civics=[$1]");

		// Replace key = with "key":
		result = result.replaceAll(KEY_AND_EQUALS_EXPRESSION, "\"$1\":");


		return result;
	}

	static String generateDuplicateLinesExpression(final String startOfLine, final int times) {
		final StringBuilder builder = new StringBuilder();

		builder.append(startOfLine);
		builder.append("=(\"[a-z_]*\")$");

		for (int i = 1; i < times; i++) {
			builder.append("\\R\\s*")
				   .append(startOfLine)
				   .append("=(\"[a-z_]*\")$");
		}

		return builder.toString();
	}

	static String generateArrayReplacement(final String key, final int times) {
		final StringBuilder builder = new StringBuilder();

		builder.append(key)
			   .append("=[$1");
		for (int i = 2; i <= times; i++) {
			builder.append(",$").append(i);
		}

		builder.append("]");


		return builder.toString();
	}
}
