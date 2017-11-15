package se.codeboss.ceo.converter.adapters;

import se.codeboss.ceo.model.enums.TraitType;

import java.util.function.Function;

public class TraitTypeAdapter extends PrefixedEnumTypeAdapter<TraitType> {
	@Override
	String getPrefix() {
		return "trait_";
	}

	@Override
	Function<String, TraitType> valueOfFunction() {
		return TraitType::valueOf;
	}
}
