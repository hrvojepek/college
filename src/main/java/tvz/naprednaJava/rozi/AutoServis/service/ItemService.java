package tvz.naprednaJava.rozi.AutoServis.service;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tvz.naprednaJava.rozi.AutoServis.enums.Status;
import tvz.naprednaJava.rozi.AutoServis.model.Item;
import tvz.naprednaJava.rozi.AutoServis.model.Manufacturer;
import tvz.naprednaJava.rozi.AutoServis.repository.ItemRepository;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item getById(Long id) {
		return itemRepository.getOne(id);
	}

	public Collection<Item> getByName(String name) {
		return itemRepository.findByName(name);
	}

	public Collection<Item> getByNameLike(String name) {
		return itemRepository.findByNameLike("%" + name);
	}

	public Collection<Item> getByNameStartingWith(String name) {
		return itemRepository.findByNameStartingWith(name);
	}

	public Collection<Item> getByNameEndingWith(String name) {
		return itemRepository.findByNameEndingWith(name);
	}

	public Collection<Item> getByPricePerUnit(BigDecimal price) {
		return itemRepository.findByPricePerUnit(price);
	}

	public Collection<Item> getByPricePerUnitBetween(BigDecimal price1, BigDecimal price2) {
		return itemRepository.findByPricePerUnitBetween(price1, price2);
	}

	public Collection<Item> getByPricePerUnitLessThan(BigDecimal price) {
		return itemRepository.findByPricePerUnitLessThan(price);
	}

	public Collection<Item> getByPricePerUnitGreaterThan(BigDecimal price) {
		return itemRepository.findByPricePerUnitGreaterThan(price);
	}

	public Collection<Item> getByUnitsInStock(int unitsInStock) {
		return itemRepository.findByUnitsInStock(unitsInStock);
	}

	public Collection<Item> getByUnitsInStockBetween(int unitsInStock1, int unitsInStock2) {
		return itemRepository.findByUnitsInStockBetween(unitsInStock1, unitsInStock2);
	}

	public Collection<Item> getByUnitsInStockLessThan(int unitsInStock) {
		return itemRepository.findByUnitsInStockLessThan(unitsInStock);
	}

	public Collection<Item> getByUnitsInStockGreaterThan(int unitsInStock) {
		return itemRepository.findByUnitsInStockGreaterThan(unitsInStock);
	}

	public Collection<Item> getAll() {
		return itemRepository.findAll();
	}

	public Collection<Item> getAllActive() {
		return itemRepository.findAllByStatus(Status.ACTIVE);
	}

	public Collection<Item> getAllActiveByManufacturer(Manufacturer manufacturer) {
		return itemRepository.findAllByStatusAndManufacturer(Status.ACTIVE, manufacturer);
	}

	@Transactional(readOnly = false)
	public Item create(Item item) {
		return itemRepository.saveAndFlush(item);
	}

	@Transactional(readOnly = false)
	public Item update(Item item) {
		// TODO implement custom logic
		return itemRepository.saveAndFlush(item);
	}

	@Transactional(readOnly = false)
	public boolean delete(Item item) {
		item.setStatus(Status.DELETED);
		if (itemRepository.saveAndFlush(item) != null) {
			return true;
		}
		return false;
	}
}
