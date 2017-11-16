package se.codeboss.ceo.converter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import se.codeboss.ceo.converter.adapters.*;
import se.codeboss.stellaris.data.Empire;
import se.codeboss.stellaris.data.enums.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class PdxEmpireFileReader {

	private static final int TRAITS_MAX_COUNT = 5;
	private static final int ETHICS_MAX_COUNT = 3;

	static final String EQUALS_ON_NEW_LINE_EXPRESSION = "(.+)[\\n\\r]+=";
	static final String VALUES_MISSING_QUOTES_EXPRESSION = "((ftl|gender)\\s*)=(\\w+)";
	static final String KEY_AND_EQUALS_EXPRESSION = "([^\\s\"]+\\s*)=";
	static final String NO_EXPRESSION = "(.+\\s*=\\s*)no";
	static final String YES_EXPRESSION = "(.+\\s*=\\s*)yes";
	static final String DELIMITER_REQUIRED_EXPRESSION = "([^\\s{\\[]+)([\\r\\n]{1,2}(?!\\s*[}\\]])(?!\\s*$))";

	static final String COLORS_TO_ARRAY_EXPRESSION = "colors=\\{([^}]+)}";

	static final String EMPTY_LINE_EXPRESSION = "^\\s*$";

	static final String TWO_CIVICS_EXPRESSION = "civics=\\{\\s*(\"[a-z_]+\")\\s*(\"[a-z_]+\")\\s*}";
	static final String SINGLE_CIVICS_EXPRESSION = "civics=\\{\\s*(\"[a-z_]+\")\\s*}";

	static List<Empire> read(final String empireData) {
		final String empireJson = transformEmpireFileToJson(empireData);

		final Gson gson = createGson();

		final Empire[] empires = gson.fromJson(empireJson, Empire[].class);

		return Arrays.asList(empires);
	}

	private static String transformEmpireFileToJson(final String empireString) {
		String result = empireString.replaceAll(EQUALS_ON_NEW_LINE_EXPRESSION, "");

		// remove empty lines
		result = result.replaceAll(EMPTY_LINE_EXPRESSION, "");

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

		result = result.replaceAll(COLORS_TO_ARRAY_EXPRESSION, "colors=[$1]");

		// Add a delimiter to lines not followed by a single line with }, or starting with {..
		result = result.replaceAll(DELIMITER_REQUIRED_EXPRESSION, "$1,$2");

		// Replace yes/no with boolean values
		result = result.replaceAll(NO_EXPRESSION, "$1false");
		result = result.replaceAll(YES_EXPRESSION, "$1true");

		// Add a surrounding quote on value on FTL-line
		result = result.replaceAll(VALUES_MISSING_QUOTES_EXPRESSION, "$2=\"$3\"");

		// Replace key = with "key":
		result = result.replaceAll(KEY_AND_EQUALS_EXPRESSION, "\"$1\":");
		result = result.replaceAll("=", ":");

		// Surround whole thing with brackets
		result = "[ " + result + " ]";

		return result;
	}

	static String generateDuplicateLinesExpression(final String startOfLine, final int times) {
		final StringBuilder builder = new StringBuilder();

		builder.append(startOfLine);
		builder.append("=(\"[a-z_]*\")");

		for (int i = 1; i < times; i++) {
			builder.append("[\\r\\n]+\\s*")
				   .append(startOfLine)
				   .append("=(\"[a-z_]*\")");
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

	private static Gson createGson() {
		return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
								.registerTypeAdapter(AuthorityType.class, new AuthorityTypeAdapter())
								.registerTypeAdapter(CivicType.class, new CivicTypeAdapter())
								.registerTypeAdapter(EthicType.class, new EthicTypeAdapter())
								.registerTypeAdapter(GovernmentType.class, new GovernmentTypeAdapter())
								.registerTypeAdapter(PlanetClassType.class, new PlanetClassTypeAdapter())
								.registerTypeAdapter(SpeciesTraitType.class, new TraitTypeAdapter())
								.registerTypeAdapter(WeaponType.class, new WeaponTypeAdapter())
								.registerTypeAdapter(GenderType.class, new GenderTypeAdapter())
								.registerTypeAdapter(GraphicalCultureType.class, new GraphicalCultureTypeAdapter())
								.registerTypeAdapter(FtlType.class, new FtlTypeAdapter())
								.create();
	}
}
