package se.codeboss.ceo.converter.adapters;

import se.codeboss.ceo.model.enums.AuthorityType;

import java.util.function.Function;

public class AuthorityTypeAdapter extends PrefixedEnumTypeAdapter<AuthorityType> {

	@Override
	String getPrefix() {
		return "auth_";
	}

	@Override
	Function<String, AuthorityType> valueOfFunction() {
		return AuthorityType::valueOf;
	}
}
