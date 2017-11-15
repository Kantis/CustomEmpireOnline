package se.codeboss.ceo.converter.adapters;

import java.util.function.Function;

/**
 * Specialized case of PrefixedEnumTypeAdapter where we dont have any prefix, just using the same Case-conversions
 * @param <T>
 */
public abstract class UpperCameledEnumTypeAdapter<T extends Enum> extends PrefixedEnumTypeAdapter<T> {

	abstract Function<String, T> valueOfFunction();

	@Override
	String getPrefix() {
		return "";
	}
}
