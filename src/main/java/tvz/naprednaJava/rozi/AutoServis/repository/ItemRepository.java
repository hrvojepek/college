package tvz.naprednaJava.rozi.AutoServis.repository;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.naprednaJava.rozi.AutoServis.enums.Status;
import tvz.naprednaJava.rozi.AutoServis.model.Item;
import tvz.naprednaJava.rozi.AutoServis.model.Manufacturer;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Collection<Item> findByName(String name);

	Collection<Item> findByNameLike(String name);

	Collection<Item> findByNameStartingWith(String name);

	Collection<Item> findByNameEndingWith(String name);

	Collection<Item> findByPricePerUnit(BigDecimal price);

	Collection<Item> findByPricePerUnitBetween(BigDecimal price1, BigDecimal price2);

	Collection<Item> findByPricePerUnitLessThan(BigDecimal price);

	Collection<Item> findByPricePerUnitGreaterThan(BigDecimal price);

	Collection<Item> findByUnitsInStock(int unitsInStock);

	Collection<Item> findByUnitsInStockBetween(int unitsInStock1, int unitsInStock2);

	Collection<Item> findByUnitsInStockLessThan(int unitsInStock);

	Collection<Item> findByUnitsInStockGreaterThan(int unitsInStock);

	Collection<Item> findAllByStatus(Status status);

	Collection<Item> findAllByStatusAndManufacturer(Status status, Manufacturer manufacturer);
}
