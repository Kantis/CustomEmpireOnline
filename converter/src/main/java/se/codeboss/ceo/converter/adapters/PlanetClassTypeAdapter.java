package se.codeboss.ceo.converter.adapters;

import se.codeboss.ceo.model.enums.PlanetClassType;

import java.util.function.Function;

public class PlanetClassTypeAdapter extends PrefixedEnumTypeAdapter<PlanetClassType> {
	@Override
	String getPrefix() {
		return "pc_";
	}

	@Override
	Function<String, PlanetClassType> valueOfFunction() {
		return PlanetClassType::valueOf;
	}
}
