package com.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author marcos olavo silva buganeme - marcos.after@gmail.com
 *
 */

@Controller
public class HomeController {

	@RequestMapping("/")
	public String show() {

		return "index";
	}
}
