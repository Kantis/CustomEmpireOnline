package se.codeboss.ceo.model;

import lombok.Value;

@Value
public class Ruler {
	GenderType gender;
	String name;
	String portrait;
	int texture;
	int hair;
	int clothes;
	String rulerTitleFemale;
	String rulerTitleMale;
}
