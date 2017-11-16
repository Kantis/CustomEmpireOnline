package se.codeboss.ceo.converter.adapters;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import se.codeboss.ceo.converter.adapters.AuthorityTypeAdapter;
import se.codeboss.stellaris.data.enums.AuthorityType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AuthorityTypeAdapterTest {

	private AuthorityTypeAdapter adapter;

	@Before
	public void setup() {
		adapter = new AuthorityTypeAdapter();
	}

	@Test
	public void read() throws IOException {
		assertEquals(AuthorityType.Democratic, adapter.deserialize("auth_democratic"));
	}

	/**
	 * Going back and forth between serialized forms should *always* result in original value
	 */
	@Test
	public void isReflective() {
		for (val authorityType : AuthorityType.values()) {
			assertEquals(authorityType, adapter.deserialize(adapter.serialize(authorityType)));
		}
	}

}