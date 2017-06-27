package tvz.naprednaJava.rozi.AutoServis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tvz.naprednaJava.rozi.AutoServis.model.RepairService;
import tvz.naprednaJava.rozi.AutoServis.service.RepairServiceService;

@Controller
public class RepairServiceController {
	
	@Autowired
	private RepairServiceService repairServicesService;

	@RequestMapping("/repairServices")
	private String showUserTable(Model model) {
		List<RepairService> services = (List<RepairService>) repairServicesService.getAll();
		model.addAttribute("services", services);

		return "repair_services/index";
	}

	@RequestMapping("/private/repairServices")
	private String privateServicesTable(Model model) {
		List<RepairService> services = (List<RepairService>) repairServicesService.getAll();
		model.addAttribute("services", services);

		return "authorized_pages/repair_services/index";
	}

	@RequestMapping("/private/repairServices/new")
	public String add(Model model, RedirectAttributes redirectAttributes) {
		return "authorized_pages/repair_services/add-repair-service";
	}

	@RequestMapping("/private/repairServices/view/{repairService}")
	public String view(@PathVariable RepairService repairService, Model model, RedirectAttributes redirectAttributes) {
		return "";
	}

	@RequestMapping("/private/repairServices/edit/{repairService}")
	public String edit(@PathVariable RepairService repairService, Model model, RedirectAttributes redirectAttributes) {
		return "authorized_pages/repair_services/edit-repair-service";
	}
}
