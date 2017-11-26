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
import com.tecsup.integrador.model.EmpresaApi;
import com.tecsup.integrador.services.EmpresaService;

@Controller
public class EmpresaController {
	private static final Logger logger = LoggerFactory.getLogger(EmpresaController.class);
	
	@Autowired
	private ApplicationContext context;
	@Autowired
	EmpresaService empresaService;

	@GetMapping("/admin/emp/list")
	public String list(@ModelAttribute("empresas") EmpresaApi empresa, ModelMap model) {

		try {
			model.addAttribute("empresas", empresaService.findAll());
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("message", e.getMessage());
		}

		return "admin/emp/list";
	}

	@GetMapping("/{id_empresa}")
	public ModelAndView home(@PathVariable int id_empresa, ModelMap model) {

		ModelAndView modelAndView = null;
		EmpresaApi emp = new EmpresaApi();
		try {
			emp = empresaService.find(id_empresa);
			logger.info(emp.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		modelAndView = new ModelAndView("home", "command", emp);

		return modelAndView;
	}

	@GetMapping("/admin/menu")
	public String menu() {

		return "/admin/menu";
	}
	//////////////////////////////////////////////////////////////////////////////////////
	// SHOW EDIT FORM OR DELETE FORM//
	//////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/admin/emp/{action}/{id_empresa}")
	public ModelAndView form(@PathVariable String action, @PathVariable int id_empresa, ModelMap model) {

		// action = {"editform","deleteform"}
		logger.info("action = " + action);
		ModelAndView modelAndView = null;

		try {
			EmpresaApi emp = empresaService.find(id_empresa);
			logger.info(emp.toString());
			modelAndView = new ModelAndView("admin/emp/" + action, "empresa", emp);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			modelAndView = new ModelAndView("admin/emp/" + action, "empresa", new EmpresaApi());
		}

		return modelAndView;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	// UPDATE //
	//////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/admin/emp/editsave")
	public ModelAndView editsave(@ModelAttribute("empresa") @Valid EmpresaApi emp, BindingResult result,
			ModelMap model) {

		// logger.info("emp = " + emp);

		ModelAndView modelAndView = null;

		if (result.hasErrors()) {
			modelAndView = new ModelAndView("admin/emp/editform", "empresa", emp);
		} else {
			try {
				empresaService.update(emp.getNombre(),emp.getDescripcion(),emp.getId_empresa());
				modelAndView = new ModelAndView("redirect:/admin/emp/list");

			} catch (Exception e) {
				// model.addAttribute("message", e.getMessage());
				// modelAndView = new ModelAndView("redirect:/admin/emp/list");
				model.addAttribute("message", e.getMessage());
				modelAndView = new ModelAndView("admin/emp/editform", "employee", emp);
			}

		}

		return modelAndView;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	// DELETE //
	//////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/admin/emp/delete")
	public ModelAndView delete(@ModelAttribute("employee") @Valid EmpresaApi emp, BindingResult result, ModelMap model) {

		ModelAndView modelAndView = null;

		if (result.hasErrors()) {
			modelAndView = new ModelAndView("admin/emp/deleteform/" + emp.getId_empresa(), "empresa", emp);
		} else {
			try {
				empresaService.delete(emp.getNombre());
				modelAndView = new ModelAndView("redirect:/admin/emp/list");
			} catch (Exception e) {
				// model.addAttribute("message", e.getMessage());
				// modelAndView = new ModelAndView("redirect:/admin/emp/list");
				model.addAttribute("message", e.getMessage());
				modelAndView = new ModelAndView("admin/emp/deleteform", "empresa", emp);
			}
		}

		return modelAndView;
	}

	//////////////////////////////////////////////////////////////////////////////////////
	// CREATION //
	//////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/admin/emp/createform")
	public ModelAndView createform() {

		EmpresaApi emp = new EmpresaApi();

		ModelAndView modelAndView = new ModelAndView("admin/emp/createform", "empresa", emp);

		return modelAndView;
	}

	@PostMapping("/admin/emp/create")
	public ModelAndView create(@ModelAttribute("empresa") @Valid EmpresaApi emp, BindingResult result, ModelMap model) {

		// String msg = context.getMessage("employee.salary.min", null, Locale.US);
		// logger.info("message = " + msg);

		ModelAndView modelAndView = null;

		if (result.hasErrors()) {

			logger.info("result.getAllErrors();= " + result.getAllErrors());

			for (ObjectError error : result.getAllErrors()) {
				String theMessage = context.getMessage(error.getCode(), error.getArguments(), Locale.US);
				logger.info(error.getCode() + " = " + theMessage);
			}

			modelAndView = new ModelAndView("admin/emp/createform", "empresa", emp);

		} else {
			try {
				empresaService.create(emp.getNombre(),emp.getDescripcion());
				logger.info("new Empresa NOmbre = " + emp.getNombre());
				modelAndView = new ModelAndView("redirect:/admin/emp/list");

			} catch (DAOException e) {
				logger.error(e.getMessage());
				model.addAttribute("message", e.getMessage());
				modelAndView = new ModelAndView("admin/emp/createform", "empresa", emp);
			}
		}
		//
		return modelAndView;
	}

}
