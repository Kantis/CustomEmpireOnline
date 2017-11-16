package se.codeboss.ceo.converter.adapters;

import se.codeboss.stellaris.data.enums.CivicType;

import java.util.function.Function;

public class CivicTypeAdapter extends PrefixedEnumTypeAdapter<CivicType> {
	@Override
	String getPrefix() {
		return "civic_";
	}

	@Override
	Function<String, CivicType> valueOfFunction() {
		return CivicType::valueOf;
	}
}
