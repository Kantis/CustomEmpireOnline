package se.codeboss.ceo.converter.adapters;

import se.codeboss.ceo.model.enums.GraphicalCultureType;

import java.util.function.Function;

public class GraphicalCultureTypeAdapter extends UpperCameledEnumTypeAdapter<GraphicalCultureType> {
	@Override
	Function<String, GraphicalCultureType> valueOfFunction() {
		return GraphicalCultureType::valueOf;
	}
}
