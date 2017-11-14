package se.codeboss.ceo.model;

import com.google.gson.annotations.SerializedName;

public enum AuthorityType {
	@SerializedName("auth_democracy")
	Democratic,
	Oligarchic,
	Dictatorial,
	Imperial
}
