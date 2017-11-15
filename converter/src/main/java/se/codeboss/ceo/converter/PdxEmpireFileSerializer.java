package se.codeboss.ceo.converter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.codeboss.ceo.converter.adapters.*;
import se.codeboss.ceo.model.enums.*;

abstract class PdxEmpireFileSerializer {

	static Gson createGson() {
		return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
								.registerTypeAdapter(AuthorityType.class, new AuthorityTypeAdapter())
								.registerTypeAdapter(CivicType.class, new CivicTypeAdapter())
								.registerTypeAdapter(EthicType.class, new EthicTypeAdapter())
								.registerTypeAdapter(GovernmentType.class, new GovernmentTypeAdapter())
								.registerTypeAdapter(PlanetClassType.class, new PlanetClassTypeAdapter())
								.registerTypeAdapter(SpeciesTraitType.class, new TraitTypeAdapter())
								.registerTypeAdapter(WeaponType.class, new WeaponTypeAdapter())
								.registerTypeAdapter(GenderType.class, new GenderTypeAdapter())
								.registerTypeAdapter(GraphicalCultureType.class, new GraphicalCultureTypeAdapter())
								.create();
	}

}
