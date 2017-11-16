package se.codeboss.ceo.converter.adapters;

import se.codeboss.stellaris.data.enums.SpeciesTraitType;

import java.util.function.Function;

public class TraitTypeAdapter extends PrefixedEnumTypeAdapter<SpeciesTraitType> {
	@Override
	String getPrefix() {
		return "trait_";
	}

	@Override
	Function<String, SpeciesTraitType> valueOfFunction() {
		return SpeciesTraitType::valueOf;
	}
}
