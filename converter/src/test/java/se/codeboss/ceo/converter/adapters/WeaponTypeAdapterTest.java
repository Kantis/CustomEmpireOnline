package se.codeboss.ceo.converter.adapters;

import com.google.gson.stream.JsonReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.codeboss.ceo.model.enums.WeaponType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class WeaponTypeAdapterTest {

	private WeaponTypeAdapter adapter;
	private JsonReader jsonReader;

	@Before
	public void setup() {
		adapter = new WeaponTypeAdapter();
		jsonReader = Mockito.mock(JsonReader.class);
	}

	@Test
	public void read() throws IOException {
		when(jsonReader.nextString()).thenReturn("tech_missiles_1");

		assertEquals(WeaponType.TechMissiles1, adapter.read(jsonReader));
	}

}