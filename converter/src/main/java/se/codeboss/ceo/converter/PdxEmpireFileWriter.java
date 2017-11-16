package se.codeboss.ceo.converter;

import lombok.val;
import org.apache.commons.lang.text.StrBuilder;
import se.codeboss.ceo.converter.adapters.*;
import se.codeboss.stellaris.data.*;

import java.util.List;

class PdxEmpireFileWriter {

	private int tabIndex;

	private AuthorityTypeAdapter authorityTypeAdapter = new AuthorityTypeAdapter();
	private CivicTypeAdapter civicTypeAdapter = new CivicTypeAdapter();
	private EthicTypeAdapter ethicTypeAdapter = new EthicTypeAdapter();
	private GenderTypeAdapter genderTypeAdapter = new GenderTypeAdapter();
	private GovernmentTypeAdapter governmentTypeAdapter = new GovernmentTypeAdapter();
	private GraphicalCultureTypeAdapter graphicalCultureTypeAdapter = new GraphicalCultureTypeAdapter();
	private PlanetClassTypeAdapter planetClassTypeAdapter = new PlanetClassTypeAdapter();
	private TraitTypeAdapter traitTypeAdapter = new TraitTypeAdapter();
	private WeaponTypeAdapter weaponTypeAdapter = new WeaponTypeAdapter();
	private SpeciesClassTypeAdapter speciesClassTypeAdapter = new SpeciesClassTypeAdapter();
	private FtlTypeAdapter ftlTypeAdapter = new FtlTypeAdapter();

	public String write(final List<Empire> empires) {
		final StrBuilder builder = new StrBuilder();

		for (val empire : empires) {
			builder.append(write(empire));
		}

		return builder.toString();
	}

	public String write(final Empire empire) {
		final StrBuilder builder = new StrBuilder();

		tabIndex = 1;

		builder.append("\"").append(empire.getName()).appendln("\"")
			   .appendln("={")
			   .append(write("key", empire.getName()))
			   .append(write("ship_prefix", empire.getShipPrefix()))
			   .append(write(empire.getSpecies()))
			   .append(write("name", empire.getName()))
			   .append(write("adjective", empire.getAdjective()))
			   .append(write("authority", authorityTypeAdapter.serialize(empire.getAuthority())))
			   .append(write("government", governmentTypeAdapter.serialize(empire.getGovernment())))
			   .append(writeWithoutQuotingRhs("ftl", ftlTypeAdapter.serialize(empire.getFtl())))
			   .append(write("weapon", weaponTypeAdapter.serialize(empire.getWeapon())))
			   .append(write("planet_name", empire.getPlanetName()))
			   .append(write("planet_class", planetClassTypeAdapter.serialize(empire.getPlanetClass())))
			   .append(write("system_name", empire.getSystemName()))
			   .append(write("initializer", empire.getInitializer()))
			   .append(write("graphical_culture", graphicalCultureTypeAdapter.serialize(empire.getGraphicalCulture())))
			   .append(write("city_graphical_culture", graphicalCultureTypeAdapter.serialize(empire.getCityGraphicalCulture())))
			   .append(write(empire.getEmpireFlag()))
			   .append(write(empire.getRuler()))
			   .append(write("spawn_as_fallen", empire.isSpawnAsFallen()))
			   .append(write("ignore_portrait_duplication", empire.isIgnorePortraitDuplication()))
			   .append(write("room", empire.getRoom()))
			   .append(write("spawn_enabled", empire.isSpawnEnabled()));

		for (val ethic : empire.getEthics()) {
			builder.append(write("ethic", ethicTypeAdapter.serialize(ethic)));
		}

		// Civics
		builder.append(writeHeader("civics"));
		for (val civic : empire.getCivics()) {
			   builder.append(write(civicTypeAdapter.serialize(civic)));
		}
		builder.append(writeFooter()); // Civics

		builder.append(writeFooter()); // Empire

		return builder.toString();
	}

	private String write(final Ruler ruler) {
		final StrBuilder builder = new StrBuilder();

		builder.append(writeHeader("ruler"))
			   .append(writeWithoutQuotingRhs("gender", genderTypeAdapter.serialize(ruler.getGender())))
			   .append(write("name", ruler.getName()))
			   .append(write("portrait", ruler.getPortrait()))
			   .append(write("texture", ruler.getTexture()))
			   .append(write("hair", ruler.getHair()))
			   .append(write("clothes", ruler.getClothes()));

		if (ruler.getRulerTitleFemale() != null)
			builder.append(write("ruler_title_female", ruler.getRulerTitleFemale()));

		if (ruler.getRulerTitle() != null)
			builder.append(write("ruler_title", ruler.getRulerTitle()));

		builder.append(writeFooter());

		return builder.toString();
	}

	private String write(final Species species) {
		final StrBuilder builder = new StrBuilder();

		builder.append(writeHeader("species"))
			   .append(write("class", speciesClassTypeAdapter.serialize(species.getClassType())))
			   .append(write("portrait", species.getPortrait()))
			   .append(write("name", species.getName()))
			   .append(write("plural", species.getPlural()))
			   .append(write("adjective", species.getAdjective()))
			   .append(write("name_list", species.getNameList()));

		for (val trait : species.getTraits()) {
			builder.append(write("trait", traitTypeAdapter.serialize(trait)));
		}

		builder.append(writeFooter());

		return builder.toString();
	}

	private String writeHeader(final String header) {
		return String.format("%s%s={%n", createTabs(tabIndex++), header);
	}

	private String writeFooter() {
		return String.format("%s%s%n", createTabs(--tabIndex), "}");
	}

	private String write(final EmpireFlag flag) {
		final StrBuilder builder = new StrBuilder();

		builder.append(writeHeader("empire_flag"))
			   .append(write("icon", flag.getIcon()))
			   .append(write("background", flag.getBackground()));

		builder.append(writeHeader("colors"));

		for (final String color : flag.getColors()) {
			builder.append(write(color));
		}

		builder.append(writeFooter()); // Colors
		builder.append(writeFooter()); // EmpireFlag

		return builder.toString();
	}

	private String write(final String key, final CategoryAndFilename categoryAndFilename) {
		final StrBuilder builder = new StrBuilder();

		builder.append(writeHeader(key))
			   .append(write("category", categoryAndFilename.getCategory()))
			   .append(write("file", categoryAndFilename.getFile()))
			   .append(writeFooter());

		return builder.toString();
	}

	private String write(final String value) {
		final StrBuilder builder = new StrBuilder();
		builder.append(createTabs(tabIndex)).appendln(String.format("\"%s\"", value));
		return builder.toString();
	}

	private String write(final String key, final String value) {
		final StrBuilder builder = new StrBuilder();
		builder.append(createTabs(tabIndex)).appendln(String.format("%s=\"%s\"", key, value));
		return builder.toString();
	}

	private String write(final String key, final int value) {
		final StrBuilder builder = new StrBuilder();
		builder.append(createTabs(tabIndex)).appendln(String.format("%s=%d", key, value));
		return builder.toString();
	}

	private String write(final String key, final boolean value) {
		final StrBuilder builder = new StrBuilder();
		builder.append(createTabs(tabIndex)).append(key).append("=");

		builder.appendln(value ? "yes" : "no");

		return builder.toString();
	}

	private String writeWithoutQuotingRhs(final String key, final String value) {
		final StrBuilder builder = new StrBuilder();
		builder.append(createTabs(tabIndex)).appendln(String.format("%s=%s", key, value));
		return builder.toString();
	}

	private String createTabs(final int tabs) {
		final StrBuilder builder = new StrBuilder();

		for (int i = 0; i < tabs; i++)
			builder.append("\t");

		return builder.toString();
	}

}
