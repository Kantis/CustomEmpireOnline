package se.codeboss.ceo.converter;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.codeboss.ceo.model.Empire;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class SerializationTest {

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void serializationIsReflective() throws IOException {
		final String empireData = FileUtils.readFileToString(resourceLoader.getResource("classpath:/SingleEmpire.txt").getFile());

		final List<Empire> parsedEmpires = PdxEmpireFileReader.read(empireData);

		final PdxEmpireFileWriter writer = new PdxEmpireFileWriter();
		final String serializedData = writer.write(parsedEmpires);

		assertEquals(empireData, serializedData);
	}

	@Test
	public void serializationIsReflective_ParseTwice() throws IOException {
		final String empireData = FileUtils.readFileToString(resourceLoader.getResource("classpath:/SingleEmpire.txt").getFile());

		final List<Empire> parsedEmpires = PdxEmpireFileReader.read(empireData);

		final PdxEmpireFileWriter writer = new PdxEmpireFileWriter();
		final String serializedData = writer.write(parsedEmpires);
		final List<Empire> secondParsing = PdxEmpireFileReader.read(serializedData);

		// Also makes sure writer is in a stable state after writing the first time..
		final String secondSerialization = writer.write(secondParsing);

		assertEquals(empireData, secondSerialization);
	}

}
