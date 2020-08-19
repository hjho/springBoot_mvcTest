package com.example.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.front.model.User;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/tyf")
@Slf4j
public class UserTestController {

	@GetMapping("/view")
	public String getUser(Model model) {
		log.info("/tyf/view 호출");
		User user = new User("hjho","123","web");
		model.addAttribute("user", user);
		return "view";
	}
}
