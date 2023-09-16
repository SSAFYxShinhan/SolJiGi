package com.ssafy.soljigi.user.view;

import com.ssafy.soljigi.base.error.AppException;
import com.ssafy.soljigi.base.error.ErrorCode;
import com.ssafy.soljigi.user.dto.response.UserDto;
import com.ssafy.soljigi.user.repository.UserRepository;
import com.ssafy.soljigi.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Log4j2
@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class ViewController {

	private final UserRepository userRepository;
	private final UserService userService;

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

	@GetMapping("/my-page")
	public String myPage(Principal principal, Model model) {
		if (principal == null) {
			return "redirect:/view/index";
		}
		String name = principal.getName();
		Long userId = userRepository.findByUsername(name).orElseThrow().getId();

		UserDto userInfo = userService.findUserInfo(userId);
		model.addAttribute("user", userInfo);
		return "user/my-page";
	}
}
