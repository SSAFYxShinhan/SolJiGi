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

	@GetMapping("/signin")
	public String signInPage() {
		return "user/signin";
	}

	@GetMapping("/one-auth")
	public String oneAuthPage() {
		return "user/one-auth";
	}
}
