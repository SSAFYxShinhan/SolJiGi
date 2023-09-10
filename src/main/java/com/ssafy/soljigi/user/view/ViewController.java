package com.ssafy.soljigi.user.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
	@GetMapping("/main")
	public String mainTestPage() {
		return "user/main";
	}

	@GetMapping("/index")
	public String mainPage() {
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

}
