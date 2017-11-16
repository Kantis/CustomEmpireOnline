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
		out.value(serialize(value));
	}

	@Override
	public T read(JsonReader in) throws IOException {
		return deserialize(in.nextString());
	}

	public T deserialize(final String serialized) {
		final String name = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, serialized.replaceAll(getPrefix(), ""));
		return valueOfFunction().apply(name);
	}

	public String serialize(final T value) {
		// Add underscore before ending digits
		final String name = value.name().replaceAll("(\\d+)$", "_$1");
		return getPrefix() + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
	}
}
