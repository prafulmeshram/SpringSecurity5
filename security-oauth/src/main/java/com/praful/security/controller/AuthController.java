/**
 * 
 */
package com.praful.security.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jack
 *
 */
@RestController
@RequestMapping("/")
public class AuthController {

	@GetMapping
	public String getAuth(OAuth2AuthenticationToken token) {
		return token.getPrincipal().toString();
	}

}
