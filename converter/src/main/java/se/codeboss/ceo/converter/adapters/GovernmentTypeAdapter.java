package se.codeboss.ceo.converter.adapters;

import se.codeboss.ceo.model.enums.GovernmentType;

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
