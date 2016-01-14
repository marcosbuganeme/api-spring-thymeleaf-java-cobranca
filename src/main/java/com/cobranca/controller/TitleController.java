package com.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobranca.model.EnumStatusTitle;
import com.cobranca.model.Title;
import com.cobranca.repository.filter.TitleFilter;
import com.cobranca.service.TitleService;

/**
 * @author marcos olavo silva buganeme - marcos.after@gmail.com
 *
 */

@Controller
@RequestMapping("/title")
public class TitleController {

	private static final String FORM_ADD = "views/title/add";

	private static final String URL_NEW = "redirect:/title/new";

	private static final String FORM_LIST = "views/title/list";

	@Autowired
	private TitleService service;

	@RequestMapping("/new")
	public ModelAndView showForm() {

		return new ModelAndView("views/title/add").addObject(new Title());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveOrUpdate(@Validated final Title title, final Errors fields, final RedirectAttributes redirect) {

		if (fields.hasErrors()) {

			return TitleController.FORM_ADD;
		}

		try {

			this.service.saveOrUpdate(title);

			redirect.addFlashAttribute("message", "Registro armazenado com êxito");

			return TitleController.URL_NEW;

		} catch (final IllegalArgumentException exception) {

			fields.rejectValue("dateExpiration", null, exception.getMessage());

			return TitleController.FORM_ADD;
		}
	}

	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") final TitleFilter filter) {

		return new ModelAndView(TitleController.FORM_LIST).addObject("titles", this.service.findByDescription(filter));
	}

	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") final Title title) {

		return new ModelAndView(TitleController.FORM_ADD).addObject(title);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable final Long id, final RedirectAttributes attributes) {

		try {

			this.service.delete(id);

			attributes.addFlashAttribute("message", "Registro excluído com êxito");

		} catch (final IllegalArgumentException exception) {

			attributes.addFlashAttribute("message", exception.getMessage());
		}

		return "redirect:/title";
	}

	@RequestMapping(value = "/{id}/get", method = RequestMethod.PUT)
	public @ResponseBody String get(@PathVariable final Long id) {

		return this.service.get(id);
	}

	@ModelAttribute("allStatus")
	public List<EnumStatusTitle> allStatus() {

		return Arrays.asList(EnumStatusTitle.values());
	}
}