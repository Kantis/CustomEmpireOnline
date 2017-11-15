package se.codeboss.ceo.converter.adapters;

import com.google.gson.stream.JsonReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import se.codeboss.ceo.model.enums.AuthorityType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AuthorityTypeAdapterTest {

	private AuthorityTypeAdapter adapter;
	private JsonReader jsonReader;

	@Before
	public void setup() {
		adapter = new AuthorityTypeAdapter();
		jsonReader = Mockito.mock(JsonReader.class);
	}

	@Test
	public void read() throws IOException {
		when(jsonReader.toString()).thenReturn("auth_democratic");

		assertEquals(AuthorityType.Democratic, adapter.read(jsonReader));
	}

	@Test
	public void write() throws IOException {
		when(jsonReader.toString()).thenReturn("auth_democratic");

		assertEquals(AuthorityType.Democratic, adapter.read(jsonReader));
	}

}