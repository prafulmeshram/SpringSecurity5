/**
 * 
 */
package com.praful.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jack
 *
 */
@RestController
public class BalanceController {
	@GetMapping("/balance")
	public String getBalance() {
		return "Balance";
	}
}
