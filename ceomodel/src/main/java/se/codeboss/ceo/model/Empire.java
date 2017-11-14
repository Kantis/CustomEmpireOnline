package se.codeboss.ceo.model;

import lombok.Value;

@Value
public class Empire {
	String key;
	String shipPrefix;
	Species species;
	String name;
	String adjective;
	AuthorityType authority;
	GovernmentType government;
	FtlType ftl;
	WeaponType weapons;
	String planetName;
	PlanetClassType planetClass;
	String systemName;
	String initializer;
	GraphicalCultureType graphicalCulture;
	GraphicalCultureType cityGraphicalCulture;
	EmpireFlag empireFlag;
	Ruler ruler;
	boolean spawnAsFallen;
	boolean ignorePortraitDuplication;
	String room;
	boolean spawnEnabled;
	EthicType[] ethics;
	CivicType[] civics;

}
