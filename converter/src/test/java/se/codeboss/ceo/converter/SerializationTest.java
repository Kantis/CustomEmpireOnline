package se.codeboss.ceo.converter;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.codeboss.ceo.converter.PdxEmpireFileReader;
import se.codeboss.ceo.converter.PdxEmpireFileWriter;
import se.codeboss.stellaris.data.Empire;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerializationTest {

	@RunWith(SpringJUnit4ClassRunner.class)
	public static class SingleEmpire {
		private String empireData;

		@Autowired
		private ResourceLoader resourceLoader;

		@Before
		public void setup() throws IOException {
			empireData = FileUtils.readFileToString(resourceLoader.getResource("classpath:/SingleEmpire.txt").getFile());
		}

		@Test
		public void serializationIsReflective() throws IOException {

			final List<Empire> parsedEmpires = PdxEmpireFileReader.read(empireData);

			final PdxEmpireFileWriter writer = new PdxEmpireFileWriter();
			final String serializedData = writer.write(parsedEmpires);

			assertEquals(empireData, serializedData);
		}

		@Test
		public void serializationIsReflective_ParseTwice() throws IOException {
			final List<Empire> parsedEmpires = PdxEmpireFileReader.read(empireData);

			final PdxEmpireFileWriter writer = new PdxEmpireFileWriter();
			final String serializedData = writer.write(parsedEmpires);
			final List<Empire> secondParsing = PdxEmpireFileReader.read(serializedData);

			// Also makes sure writer is in a stable state after writing the first time..
			final String secondSerialization = writer.write(secondParsing);

			assertEquals(empireData, secondSerialization);
		}
	}

	@RunWith(SpringJUnit4ClassRunner.class)
	public static class TwoEmpires {
		private String empireData;

		@Autowired
		private ResourceLoader resourceLoader;

		@Before
		public void setup() throws IOException {
			empireData = FileUtils.readFileToString(resourceLoader.getResource("classpath:/TwoEmpires.txt").getFile());
		}

		@Test
		public void serializationIsReflective() throws IOException {

			final List<Empire> parsedEmpires = PdxEmpireFileReader.read(empireData);

			final PdxEmpireFileWriter writer = new PdxEmpireFileWriter();
			final String serializedData = writer.write(parsedEmpires);

			assertEquals(empireData, serializedData);
		}

		@Test
		public void serializationIsReflective_ParseTwice() throws IOException {
			final List<Empire> parsedEmpires = PdxEmpireFileReader.read(empireData);

			final PdxEmpireFileWriter writer = new PdxEmpireFileWriter();
			final String serializedData = writer.write(parsedEmpires);
			final List<Empire> secondParsing = PdxEmpireFileReader.read(serializedData);

			// Also makes sure writer is in a stable state after writing the first time..
			final String secondSerialization = writer.write(secondParsing);

			assertEquals(empireData, secondSerialization);
		}
	}

}
