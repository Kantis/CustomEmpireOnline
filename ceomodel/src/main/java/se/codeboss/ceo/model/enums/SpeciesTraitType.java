package se.codeboss.ceo.model.enums;

import lombok.RequiredArgsConstructor;

/**
 * Found in %GAMEDIR%/common/traits/04_species_traits.txt
 */
@RequiredArgsConstructor
public enum SpeciesTraitType {
	Agrarian(2),
	Thrifty(2),
	Industrious(2),
	Intelligent(2),
	NaturalEngineers(1),
	NaturalPhysicists(1),
	NaturalSociologists(1),
	ExtremelyAdaptive(4),
	Adaptive(2),
	Nonadaptive(-2),
	RapidBreeders(1),
	SlowBreeders(-1),
	Talented(1),
	QuickLearners(1),
	SlowLearners(-1),
	VeryStrong(3),
	Strong(1),
	Weak(-1),
	Nomadic(1),
	Sedentary(-1),
	Communal(1),
	Solitary(-1),
	Charismatic(1),
	Repugnant(-1),
	// Uplifted(0)
	Conformists(2),
	Deviants(-1),
	Venerable(4),
	Enduring(1),
	Fleeting(-1),
	Decadent(-1),
	Resilient(1),
	Conservational(1),
	Wasteful(-1);

	private final int cost;
}
