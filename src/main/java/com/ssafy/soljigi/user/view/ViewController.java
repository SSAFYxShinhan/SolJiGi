package com.ssafy.soljigi.user.view;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/view")
public class ViewController {

	@GetMapping("/index")
	public String mainPage(@AuthenticationPrincipal Principal principal, Model model) {
		if (principal == null) {
			log.warn("null Data");
		} else {
			log.warn("myname " + principal.getName());
		}
		return "user/index";
	}

	@GetMapping("/sign-in")
	public String signInPage() {
		return "user/sign-in";
	}

	@GetMapping("/sign-up")
	public String signUpPage() {
		return "user/sign-up";
	}

	@GetMapping("/one-auth")
	public String oneAuthPage() {
		return "user/one-auth";
	}

	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");

		response.addCookie(cookie);

		return "redirect:/view/index";
	}

}
