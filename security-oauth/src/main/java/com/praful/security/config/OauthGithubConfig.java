/**
 * 
 */
package com.praful.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Component;

/**
 * @author jack
 *
 */
@Component
public class OauthGithubConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().anyRequest().authenticated().and().oauth2Login();

	}

	/*
	 * @SuppressWarnings("deprecation") private ClientRegistration
	 * clientRegistration() { ClientRegistration cr =
	 * ClientRegistration.withRegistrationId("github").clientId(
	 * "59f85e5e6c8599d2995d")
	 * .clientSecret("f3114676668c6e549ae52ae12d485f43e8daac5f").scope(new String[]
	 * { "read:user" })
	 * .authorizationUri("https://github.com/login/oauth.authorize")
	 * .tokenUri("https://github.com/login/oauth/access-token").userInfoUri(
	 * "https://api.github.com/user")
	 * .userNameAttributeName("id").clientName("Github")
	 * .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	 * .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}").build
	 * (); return cr; }
	 */

	private ClientRegistration clientRegistration() {
		return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("59f85e5e6c8599d2995d")
				.clientSecret("f3114676668c6e549ae52ae12d485f43e8daac5f").build();
	}

	@Bean
	public ClientRegistrationRepository cilentRepository() {
		ClientRegistration clientRegistration = clientRegistration();
		return new InMemoryClientRegistrationRepository(clientRegistration);
	}

}
