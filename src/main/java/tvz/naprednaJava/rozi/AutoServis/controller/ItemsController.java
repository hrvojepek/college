package tvz.naprednaJava.rozi.AutoServis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tvz.naprednaJava.rozi.AutoServis.model.Item;
import tvz.naprednaJava.rozi.AutoServis.model.Manufacturer;
import tvz.naprednaJava.rozi.AutoServis.service.ItemService;

@Controller
public class ItemsController {

	@Autowired
	private ItemService itemService;

	@RequestMapping("/items")
	public String index(Model model) {
		List<Item> items = (List<Item>) itemService.getAll();
		model.addAttribute("items", items);

		return "items/index";
	}

	@RequestMapping("/private/items")
	@PreAuthorize(value = "hasAnyAuthority('item.view', 'item.edit', 'item.create')")
	public String privateProductTable(Model model) {
		List<Item> items = (List<Item>) itemService.getAll();
		model.addAttribute("items", items);

		return "authorized_pages/items/index";
	}

	@RequestMapping("/private/items/manufacturer/{manufacturer}")
	@PreAuthorize(value = "hasAnyAuthority('item.view', 'item.edit', 'item.create')")
	public String privateProductTableOfManufacturer(@PathVariable Manufacturer manufacturer, Model model) {
		
		List<Item> items = (List<Item>) itemService.getAllActiveByManufacturer(manufacturer);
		model.addAttribute("items", items);

		return "authorized_pages/items/index";
	}

	@RequestMapping("/private/item/new")
	@PreAuthorize(value = "hasAuthority('item.create')")
	public String add(Model model, RedirectAttributes redirectAttributes) {
		return "authorized_pages/items/add-item";
	}

	@RequestMapping("/private/item/edit/{item}")
	@PreAuthorize(value = "hasAuthority('item.edit')")
	public String edit(@PathVariable Item item, Model model, RedirectAttributes redirectAttributes) {
		return "authorized_pages/items/edit-item";
	}

	@RequestMapping("/private/item/view/{item}")
	@PreAuthorize(value = "hasAuthority('item.view')")
	public String view(@PathVariable Item item, Model model, RedirectAttributes redirectAttributes) {
		return "authorized_pages/items/view-item";
	}
}
