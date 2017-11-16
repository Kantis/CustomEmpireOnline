package se.codeboss.stellaris.data.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Found in %GAMEDIR%/common/species_classes/00_species_classes.txt
 */
public enum SpeciesClassType {
	@SerializedName("AVI")
	Avian,
	@SerializedName("REP")
	Reptilian,
	@SerializedName("MAM")
	Mammalian,
	@SerializedName("ART")
	Arthropoid

}
