package tvz.naprednaJava.rozi.AutoServis.service;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tvz.naprednaJava.rozi.AutoServis.enums.Status;
import tvz.naprednaJava.rozi.AutoServis.model.RepairService;
import tvz.naprednaJava.rozi.AutoServis.repository.RepairServiceRepository;

@Service
@Transactional
public class RepairServiceService {

	@Autowired
	private RepairServiceRepository repairServiceRepository;

	public RepairService getById(Long id) {
		return repairServiceRepository.getOne(id);
	}

	public Collection<RepairService> getByName(String name) {
		return repairServiceRepository.findByName(name);
	}

	public Collection<RepairService> getByNameLike(String name) {
		return repairServiceRepository.findByNameLike("%" + name);
	}

	public Collection<RepairService> getByNameStartingWith(String name) {
		return repairServiceRepository.findByNameStartingWith(name);
	}

	public Collection<RepairService> getByNameEndingWith(String name) {
		return repairServiceRepository.findByNameEndingWith(name);
	}

	public Collection<RepairService> getByPricePerHour(BigDecimal pricePerHour) {
		return repairServiceRepository.findByPricePerHour(pricePerHour);
	}

	public Collection<RepairService> getByPricePerHourBetween(BigDecimal pricePerHour1, BigDecimal pricePerHour2) {
		return repairServiceRepository.findByPricePerHourBetween(pricePerHour1, pricePerHour2);
	}

	public Collection<RepairService> getByPricePerHourLessThan(BigDecimal pricePerHour) {
		return repairServiceRepository.findByPricePerHourLessThan(pricePerHour);
	}

	public Collection<RepairService> getByPricePerHourGreaterThan(BigDecimal pricePerHour) {
		return repairServiceRepository.findByPricePerHourGreaterThan(pricePerHour);
	}

	public Collection<RepairService> getAll() {
		return repairServiceRepository.findAll();
	}

	public Collection<RepairService> getAllActive() {
		return repairServiceRepository.findAllByStatus(Status.ACTIVE);
	}

	@Transactional(readOnly = false)
	public RepairService create(RepairService repairService) {
		return repairServiceRepository.saveAndFlush(repairService);
	}

	@Transactional(readOnly = false)
	public RepairService update(RepairService repairService) {
		// TODO implement custom logic
		return repairServiceRepository.saveAndFlush(repairService);
	}

	@Transactional(readOnly = false)
	public boolean delete(RepairService repairService) {
		repairService.setStatus(Status.DELETED);
		if (repairServiceRepository.saveAndFlush(repairService) != null) {
			return true;
		}
		return false;
	}
}
