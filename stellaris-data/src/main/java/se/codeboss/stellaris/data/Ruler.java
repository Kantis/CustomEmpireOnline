package se.codeboss.stellaris.data;

import lombok.Data;
import se.codeboss.stellaris.data.enums.GenderType;

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
