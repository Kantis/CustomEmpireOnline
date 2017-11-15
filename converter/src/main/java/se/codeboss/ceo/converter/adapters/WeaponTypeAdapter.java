package se.codeboss.ceo.converter.adapters;

import se.codeboss.ceo.model.enums.WeaponType;

import java.util.function.Function;

public class WeaponTypeAdapter extends UpperCameledEnumTypeAdapter<WeaponType> {
	@Override
	Function<String, WeaponType> valueOfFunction() {
		return WeaponType::valueOf;
	}
}
