package se.codeboss.stellaris.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import se.codeboss.stellaris.data.enums.SpeciesClassType;
import se.codeboss.stellaris.data.enums.SpeciesTraitType;

@Data
public class Species {
	@SerializedName("class")
	SpeciesClassType classType;
	String portrait;
	String name;
	String plural;
	String adjective;
	String nameList;
	SpeciesTraitType[] traits;
}
