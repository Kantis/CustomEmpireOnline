package se.codeboss.ceo.converter.adapters;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import se.codeboss.ceo.model.enums.SpeciesClassType;

import static org.junit.Assert.assertEquals;

public class SpeciesClassTypeAdapterTest {

	private SpeciesClassTypeAdapter adapter;

	@Before
	public void setup() {
		adapter = new SpeciesClassTypeAdapter();
	}

	@Test
	public void isReflective() {
		for (val speciesClassType : SpeciesClassType.values()) {
			assertEquals(speciesClassType, adapter.deserialize(adapter.serialize(speciesClassType)));
		}
	}

}