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
		final String empireData = FileUtils.readFileToString(resourceLoader.getResource("classpath:/KallesJunta.txt").getFile());

		final List<Empire> parsedEmpires = PdxEmpireFileReader.read(empireData);

		final PdxEmpireFileWriter writer = new PdxEmpireFileWriter();
		final String serializedData = writer.write(parsedEmpires);

		assertEquals(empireData, serializedData);
	}

}
