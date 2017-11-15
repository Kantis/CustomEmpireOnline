package se.codeboss.ceo.converter.adapters;

import com.google.common.base.CaseFormat;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.function.Function;

public abstract class PrefixedEnumTypeAdapter<T extends Enum> extends TypeAdapter<T> {

	abstract String getPrefix();

	abstract Function<String, T> valueOfFunction();


	@Override
	public void write(JsonWriter out, T value) throws IOException {
		out.value(getPrefix() + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, value.name()));
	}

	@Override
	public T read(JsonReader in) throws IOException {
		final String name = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, in.nextString().replaceAll(getPrefix(), ""));
		return valueOfFunction().apply(name);
	}
}
