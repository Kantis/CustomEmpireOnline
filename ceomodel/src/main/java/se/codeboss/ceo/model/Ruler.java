package se.codeboss.ceo.model;

import lombok.Data;
import se.codeboss.ceo.model.enums.GenderType;

@Data
public class Ruler {
	GenderType gender;
	String name;
	String portrait;
	int texture;
	int hair;
	int clothes;
	String rulerTitleFemale;
	String rulerTitle;
}
