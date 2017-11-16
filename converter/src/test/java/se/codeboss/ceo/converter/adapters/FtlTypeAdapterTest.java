package se.codeboss.ceo.converter.adapters;

import org.junit.Before;
import org.junit.Test;
import se.codeboss.ceo.model.enums.FtlType;

import static org.junit.Assert.assertEquals;

public class FtlTypeAdapterTest {

	private FtlTypeAdapter adapter;

	@Before
	public void setup() {
		adapter = new FtlTypeAdapter();
	}

	@Test
	public void serialize() {
		assertEquals("warp", adapter.serialize(FtlType.Warp));
	}

}