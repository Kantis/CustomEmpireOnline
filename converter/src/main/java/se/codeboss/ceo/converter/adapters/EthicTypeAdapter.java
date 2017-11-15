package se.codeboss.ceo.converter.adapters;

import se.codeboss.ceo.model.enums.EthicType;

import java.util.function.Function;

public class EthicTypeAdapter extends PrefixedEnumTypeAdapter<EthicType> {
	@Override
	String getPrefix() {
		return "ethic_";
	}

	@Override
	Function<String, EthicType> valueOfFunction() {
		return EthicType::valueOf;
	}
}
