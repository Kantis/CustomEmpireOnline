package se.codeboss.ceo.converter.adapters;

import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.codeboss.ceo.converter.adapters.WeaponTypeAdapter;
import se.codeboss.stellaris.data.enums.WeaponType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class WeaponTypeAdapterTest {

	private WeaponTypeAdapter adapter;

	@Before
	public void setup() {
		adapter = new WeaponTypeAdapter();
	}

	@Test
	public void deserialize() throws IOException {
		Assert.assertEquals(WeaponType.TechMissiles1, adapter.deserialize("tech_missiles_1"));
		Assert.assertEquals(WeaponType.TechLasers1, adapter.deserialize("tech_lasers_1"));
	}

	@Test
	public void serialize() throws IOException {
		assertEquals("tech_missiles_1", adapter.serialize(WeaponType.TechMissiles1));
		assertEquals("tech_lasers_1", adapter.serialize(WeaponType.TechLasers1));
	}

	/**
	 * Going back and forth between serialized forms should *always* result in original value
	 */
	@Test
	public void isReflective() {
		for (val weaponType : WeaponType.values()) {
			Assert.assertEquals(weaponType, adapter.deserialize(adapter.serialize(weaponType)));
		}
	}

}