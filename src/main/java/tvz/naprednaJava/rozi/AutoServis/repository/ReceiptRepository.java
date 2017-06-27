package tvz.naprednaJava.rozi.AutoServis.repository;

import org.springframework.data.repository.CrudRepository;
import tvz.naprednaJava.rozi.AutoServis.model.Receipt;

/**
 * Created by Hrvoje
 */
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
}
