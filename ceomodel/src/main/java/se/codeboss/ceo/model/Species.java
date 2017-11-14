package se.codeboss.ceo.model;

import com.google.gson.annotations.SerializedName;
import lombok.Value;

@Value
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
