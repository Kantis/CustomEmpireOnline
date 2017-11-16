package se.codeboss.stellaris.data;

import lombok.Data;
import se.codeboss.stellaris.data.enums.*;

@Data
public class Empire {
	String key;
	String shipPrefix;
	Species species;
	String name;
	String adjective;
	AuthorityType authority;
	GovernmentType government;
	FtlType ftl;
	WeaponType weapon;
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
