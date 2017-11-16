package se.codeboss.ceo.converter.adapters;

import se.codeboss.stellaris.data.enums.GovernmentType;

import java.util.function.Function;

public class GovernmentTypeAdapter extends PrefixedEnumTypeAdapter<GovernmentType> {
	@Override
	String getPrefix() {
		return "gov_";
	}

	@Override
	Function<String, GovernmentType> valueOfFunction() {
		return GovernmentType::valueOf;
	}
}
