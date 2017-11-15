package se.codeboss.ceo.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import se.codeboss.ceo.model.enums.SpeciesClassType;
import se.codeboss.ceo.model.enums.TraitType;

@Data
public class Species {
	@SerializedName("class")
	SpeciesClassType classType;
	String portrait;
	String name;
	String plural;
	String adjective;
	String nameList;
	TraitType[] traits;
}
