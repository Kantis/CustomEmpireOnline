package se.codeboss.ceo.converter.adapters;

import se.codeboss.stellaris.data.enums.GenderType;

import java.util.function.Function;

public class GenderTypeAdapter extends UpperCameledEnumTypeAdapter<GenderType> {
	@Override
	Function<String, GenderType> valueOfFunction() {
		return GenderType::valueOf;
	}
}
