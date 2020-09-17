package com.multiple.authentication.provider;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.multiple.authentication.auth.OTPAuthToken;
import com.multiple.authentication.model.UserSecurityKey;
import com.multiple.authentication.repo.UserSecretKeyRepo;

@Component
public class OTPAuthProvider implements AuthenticationProvider {

	@Autowired
	UserSecretKeyRepo secretkeyRepo;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		Optional<UserSecurityKey> user = secretkeyRepo.findByusername(authentication.getName());
		if (user.isPresent()) {
			return new OTPAuthToken(authentication.getName(), authentication.getCredentials(),
					Arrays.asList(() -> "read"));
		}
		throw new BadCredentialsException("Failed secret key Authentication !!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return OTPAuthToken.class.equals(authentication);
	}

}
