package se.codeboss.ceo.converter.adapters;

import se.codeboss.ceo.model.enums.FtlType;

import java.util.function.Function;

public class FtlTypeAdapter extends UpperCameledEnumTypeAdapter<FtlType> {
	@Override
	Function<String, FtlType> valueOfFunction() {
		return FtlType::valueOf;
	}
}
