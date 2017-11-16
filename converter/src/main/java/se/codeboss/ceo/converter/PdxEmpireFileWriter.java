package se.codeboss.ceo.converter;

import org.apache.commons.lang.text.StrBuilder;
import se.codeboss.ceo.converter.adapters.*;
import se.codeboss.ceo.model.Empire;
import se.codeboss.ceo.model.Species;

import java.util.List;

import static se.codeboss.ceo.converter.Constants.NEW_LINE;

class PdxEmpireFileWriter {

	private AuthorityTypeAdapter authorityTypeAdapter = new AuthorityTypeAdapter();
	private CivicTypeAdapter civicTypeAdapter = new CivicTypeAdapter();
	private EthicTypeAdapter ethicTypeAdapter = new EthicTypeAdapter();
	private GenderTypeAdapter genderTypeAdapter = new GenderTypeAdapter();
	private GovernmentTypeAdapter governmentTypeAdapter = new GovernmentTypeAdapter();
	private GraphicalCultureTypeAdapter graphicalCultureTypeAdapter = new GraphicalCultureTypeAdapter();
	private PlanetClassTypeAdapter planetClassTypeAdapter = new PlanetClassTypeAdapter();
	private TraitTypeAdapter traitTypeAdapter = new TraitTypeAdapter();
	private WeaponTypeAdapter weaponTypeAdapter = new WeaponTypeAdapter();


	public String write(final List<Empire> empireList) {
		// Attempting to do this using Gson turned out to be too inconvenient.. It's easier to manually write it in
		// the PDX-format

		return empireList.stream()
				.map(this::write)
				.reduce("", (s1, s2) -> s2 + NEW_LINE + s1);
	}

	public String write(final Empire empire) {
		final StrBuilder builder = new StrBuilder();

		builder.append("\"").append(empire.getName()).appendln("\"")
			   .appendln("={")
			   .append("\tkey=\"").append(empire.getName()).appendln("\"");

		return builder.toString();
	}

	public String write(final Species species) {
		final StrBuilder builder = new StrBuilder();

		builder.appendln("species={")
				.append("\tclass=\"").append("");

		return builder.toString();
	}

}
