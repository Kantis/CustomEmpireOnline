package se.codeboss.ceo.converter.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.val;
import se.codeboss.stellaris.data.enums.SpeciesClassType;

import java.io.IOException;
import java.util.stream.Stream;

public class SpeciesClassTypeAdapter extends TypeAdapter<SpeciesClassType> {
	@Override
	public void write(JsonWriter out, SpeciesClassType value) throws IOException {
		out.value(serialize(value));

	}

	@Override
	public SpeciesClassType read(JsonReader in) throws IOException {
		return deserialize(in.nextString());
	}

	public SpeciesClassType deserialize(final String serialized) {
		for (val speciesClassType : SpeciesClassType.values()) {
			if (speciesClassType.name().substring(0, 3).equalsIgnoreCase(serialized))
				return speciesClassType;
		}

		final String validValues = Stream.of(SpeciesClassType.values())
										 .map(Enum::name)
										 .reduce("", (s1, s2) -> s1 + ", " + s2);

		throw new IllegalArgumentException(serialized + " is not a valid value for SpeciesClassType. " +
				"Valid values are " + validValues);
	}

	public String serialize(final SpeciesClassType value) {
		return value.name().substring(0, 3).toUpperCase();
	}
}
