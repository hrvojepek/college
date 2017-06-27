package tvz.naprednaJava.rozi.AutoServis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tvz.naprednaJava.rozi.AutoServis.enums.FormMode;
import tvz.naprednaJava.rozi.AutoServis.enums.Status;
import tvz.naprednaJava.rozi.AutoServis.form.ManufacturerForm;
import tvz.naprednaJava.rozi.AutoServis.model.Manufacturer;
import tvz.naprednaJava.rozi.AutoServis.service.ManufacturerService;

@Controller
@RequestMapping("/private/")
@PreAuthorize(value = "hasAnyAuthority('manufacturer.view', 'manufacturer.edit', 'manufacturer.create')")
public class ManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;

	@PreAuthorize(value = "hasAuthority('manufacturer.create')")
	@RequestMapping("manufacturer/new")
	public String add(Model model) {
		model.addAttribute("form", new ManufacturerForm(FormMode.NEW));
		return "/manufacturers/manufacturer-form";
	}

	@PreAuthorize(value = "hasAuthority('manufacturer.edit')")
	@RequestMapping("manufacturer/edit/{manufacturer}")
	public String edit(@PathVariable Manufacturer manufacturer, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("form", new ManufacturerForm(FormMode.EDIT, manufacturer));
		return "/manufacturers/manufacturer-form";
	}

	@PreAuthorize(value = "hasAuthority('manufacturer.view')")
	@RequestMapping("manufacturer/view/{manufacturer}")
	public String view(@PathVariable Manufacturer manufacturer, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("manufacturer", manufacturer);
		return "/manufacturers/manufacturer-view";
	}

	@RequestMapping("manufacturers")
	public String tableView(Model model) {
		List<Manufacturer> manufacturers = (List<Manufacturer>) manufacturerService.getAllActive();
		model.addAttribute("manufacturers", manufacturers);
		return "/manufacturers/manufacturer-list";
	}

	@PreAuthorize(value = "hasAuthority('manufacturer.create')")
	@RequestMapping(value = "manufacturer/create", method = RequestMethod.POST)
	public String create(@ModelAttribute ManufacturerForm form, Model model, RedirectAttributes redirectAttributes) {

		if (form.getManufacturer().getName() == null || form.getManufacturer().getName().isEmpty()) {
			redirectAttributes.addFlashAttribute("errors", "Podrebno je odrediti naziv proizvođača.");
			return "redirect:/private/manufacturers";
		}
		if (form.getManufacturer().getName().length() < 3) {
			redirectAttributes.addFlashAttribute("errors", "Naziv proizvođača mora imati više od 3 znaka.");
			return "redirect:/private/manufacturers";
		}
		if (manufacturerService.getActiveByName(form.getManufacturer().getName()) != null) {
			redirectAttributes.addFlashAttribute("errors",
					String.format("Nije moguće kreirati proizvođača naziva %s zato što on već postoji", form.getManufacturer().getName()));
			return "redirect:/private/manufacturers";
		}

		Manufacturer manufacturer = form.getManufacturer();
		manufacturer.setStatus(Status.ACTIVE);

		if (manufacturerService.create(manufacturer) == null) {
			redirectAttributes.addFlashAttribute("errors",
					String.format("Došlo je do pogreške prilikom kreiranja proizvođača naziva %s.", form.getManufacturer().getName()));
			return "redirect:/private/manufacturers";
		}

		redirectAttributes.addFlashAttribute("success", "Proizvođač je uspješno kreiran.");

		return "redirect:/private/manufacturers";
	}

	@PreAuthorize(value = "hasAuthority('manufacturer.edit')")
	@RequestMapping(value = "manufacturer/update", method = RequestMethod.POST)
	public String update(@RequestParam String action, @ModelAttribute ManufacturerForm form, Model model, RedirectAttributes redirectAttributes) {

		if (action.isEmpty()) {
			redirectAttributes.addFlashAttribute("errors", "Potrebno je odrediti akciju nad objektom.");
			return "redirect:/private/manufacturers";
		} else if (!action.equals("delete") && !action.equals("save")) {
			redirectAttributes.addFlashAttribute("errors", "Akcija " + action + " nije dozvoljena.");
			return "redirect:/private/manufacturers";
		} else if (action.equals("delete")) {
			manufacturerService.delete(form.getManufacturer());
			redirectAttributes.addFlashAttribute("success", "Proizvođač je uspješno izbrisan.");
			return "redirect:/private/manufacturers";
		}

		if (form.getManufacturer().getName().length() < 3) {
			redirectAttributes.addFlashAttribute("errors", "Naziv proizvođača mora imati više od 3 znaka.");
			return "redirect:/private/manufacturers";
		}

		Manufacturer m = manufacturerService.getActiveByName(form.getManufacturer().getName());
		if (m != null && m.getId() != form.getManufacturer().getId()) {
			redirectAttributes.addFlashAttribute("errors", String.format("Proizvođač %s već postoji.", form.getManufacturer().getName()));
			return "redirect:/private/manufacturers";
		}

		if (manufacturerService.update(form.getManufacturer()) == null) {
			redirectAttributes.addFlashAttribute("errors",
					String.format("Došlo je do pogreške prilikom ažuriranja proizvođača naziva s id-om %d", form.getManufacturer().getId()));
			return "redirect:/private/manufacturers";
		}

		redirectAttributes.addFlashAttribute("success", "Proizvođač je uspješno ažuriran.");
		return "redirect:/private/manufacturers";
	}
}
