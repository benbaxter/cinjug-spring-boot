package io.benbaxter.guestbook.web.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestBookPageController {

	@RequestMapping("/people.html")
	public String people() {
		return "people/index";
	}
}
