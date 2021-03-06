package se.codeboss.ceo.converter.adapters;

import se.codeboss.stellaris.data.enums.FtlType;

import java.util.function.Function;

public class FtlTypeAdapter extends UpperCameledEnumTypeAdapter<FtlType> {
	@Override
	Function<String, FtlType> valueOfFunction() {
		return FtlType::valueOf;
	}
}
