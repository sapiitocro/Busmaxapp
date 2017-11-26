package com.tecsup.integrador;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.model.ParaderoApi;
import com.tecsup.integrador.services.ParaderoService;

@Controller
public class ParaderoController {
	private static final Logger logger = LoggerFactory.getLogger(ParaderoController.class);
	
	@Autowired
	private ApplicationContext context;
	@Autowired
	ParaderoService paraderoService;

	@GetMapping("/admin/prd/list")
	public String list(@ModelAttribute("paraderos") ParaderoApi paradero, ModelMap model) {

		try {
			model.addAttribute("paraderos", paraderoService.findAll());
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("message", e.getMessage());
		}

		return "admin/prd/list";
	}

	@GetMapping("/{id_paradero}")
	public ModelAndView home(@PathVariable int id_paradero, ModelMap model) {

		ModelAndView modelAndView = null;
		ParaderoApi prd = new ParaderoApi();
		try {
			prd = paraderoService.find(id_paradero);
			logger.info(prd.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		modelAndView = new ModelAndView("home", "command", prd);

		return modelAndView;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////
	// SHOW EDIT FORM OR DELETE FORM//
	//////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/admin/prd/{action}/{id_paradero}")
	public ModelAndView form(@PathVariable String action, @PathVariable int id_paradero, ModelMap model) {

		// action = {"editform","deleteform"}
		logger.info("action = " + action);
		ModelAndView modelAndView = null;

		try {
			ParaderoApi prd = paraderoService.find(id_paradero);
			logger.info(prd.toString());
			modelAndView = new ModelAndView("admin/prd/" + action, "paradero", prd);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/prd/" + action, "paradero", new ParaderoApi());
		}

		return modelAndView;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	// UPDATE //
	//////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/admin/prd/editsave")
	public ModelAndView editsave(@ModelAttribute("paradero") @Valid ParaderoApi prd, BindingResult result,
			ModelMap model) {

		// logger.info("emp = " + emp);

		ModelAndView modelAndView = null;

		if (result.hasErrors()) {
			modelAndView = new ModelAndView("admin/prd/editform", "paradero", prd);
		} else {
			try {
				paraderoService.update(prd.getNombre(),prd.getLatitud(),prd.getLongitud(),prd.getId_paradero());
				modelAndView = new ModelAndView("redirect:/admin/prd/list");

			} catch (Exception e) {
				// model.addAttribute("message", e.getMessage());
				// modelAndView = new ModelAndView("redirect:/admin/emp/list");
				model.addAttribute("message", e.getMessage());
				modelAndView = new ModelAndView("admin/prd/editform", "paradero", prd);
			}

		}

		return modelAndView;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	// DELETE //
	//////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/admin/prd/delete")
	public ModelAndView delete(@ModelAttribute("paradero") @Valid ParaderoApi prd, BindingResult result, ModelMap model) {

		ModelAndView modelAndView = null;

		if (result.hasErrors()) {
			modelAndView = new ModelAndView("admin/prd/deleteform/" + prd.getId_paradero(), "paradero", prd);
		} else {
			try {
				paraderoService.delete(prd.getNombre());
				modelAndView = new ModelAndView("redirect:/admin/prd/list");
			} catch (Exception e) {
				// model.addAttribute("message", e.getMessage());
				// modelAndView = new ModelAndView("redirect:/admin/emp/list");
				model.addAttribute("message", e.getMessage());
				modelAndView = new ModelAndView("admin/prd/deleteform", "paradero", prd);
			}
		}

		return modelAndView;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	// CREATION //
	//////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/admin/prd/createform")
	public ModelAndView createform() {

		ParaderoApi prd = new ParaderoApi();

		ModelAndView modelAndView = new ModelAndView("admin/prd/createform", "paradero", prd);

		return modelAndView;
	}

	@PostMapping("/admin/prd/create")
	public ModelAndView create(@ModelAttribute("paradero") @Valid ParaderoApi prd, BindingResult result, ModelMap model) {

		// String msg = context.getMessage("employee.salary.min", null, Locale.US);
		// logger.info("message = " + msg);

		ModelAndView modelAndView = null;

		if (result.hasErrors()) {

			logger.info("result.getAllErrors();= " + result.getAllErrors());

			for (ObjectError error : result.getAllErrors()) {
				String theMessage = context.getMessage(error.getCode(), error.getArguments(), Locale.US);
				logger.info(error.getCode() + " = " + theMessage);
			}

			modelAndView = new ModelAndView("admin/prd/createform", "paradero", prd);

		} else {
			try {
				paraderoService.create(prd.getNombre(),prd.getLatitud(),prd.getLongitud());
				logger.info("new Paradero NOmbre = " + prd.getNombre());
				modelAndView = new ModelAndView("redirect:/admin/prd/list");

			} catch (DAOException e) {
				logger.error(e.getMessage());
				model.addAttribute("message", e.getMessage());
				modelAndView = new ModelAndView("admin/prd/createform", "paradero", prd);
			}
		}
		//
		return modelAndView;
	}

}
