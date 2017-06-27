package tvz.naprednaJava.rozi.AutoServis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tvz.naprednaJava.rozi.AutoServis.model.Station;
import tvz.naprednaJava.rozi.AutoServis.service.StationService;

@Controller
public class StationsController {
	@Autowired
	private StationService stationService;

	@RequestMapping("/auth/stations")
	public String index(Model model) {
		List<Station> stations = (List<Station>) stationService.getAll();
		model.addAttribute("stationsList", stations);

		return "authorized_pages/stations/index";
	}

	@RequestMapping("/auth/stations/edit/{id:\\d*$}")
	public String edit(@PathVariable Long id, Model model) {
		Station station = stationService.getById(id);
		model.addAttribute("station", station);

		return "authorized_pages/stations/edit-station";
	}

	@RequestMapping("/stations")
	public String publicStationsList(Model model) {
		List<Station> stations = (List<Station>) stationService.getAll();
		model.addAttribute("stations", stations);

		return "stations/index";
	}

	@RequestMapping("/private/stations")
	public String privateStationsList(Model model) {
		List<Station> stations = (List<Station>) stationService.getAll();
		model.addAttribute("stations", stations);

		return "authorized_pages/stations/index";
	}

	@RequestMapping("/private/station/new")
	public String add(Model model, RedirectAttributes redirectAttributes) {
		return "authorized_pages/stations/add-station";
	}

	@RequestMapping("/private/station/view/{station}")
	public String view(@PathVariable Station station, Model model, RedirectAttributes redirectAttributes) {
		return "";
	}

	@RequestMapping("/private/station/edit/{station}")
	public String edit(@PathVariable Station station, Model model, RedirectAttributes redirectAttributes) {
		return "authorized_pages/stations/edit-station";
	}
}
