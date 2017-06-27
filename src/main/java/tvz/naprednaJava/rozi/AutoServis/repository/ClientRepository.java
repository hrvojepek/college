package tvz.naprednaJava.rozi.AutoServis.repository;

import org.springframework.data.repository.CrudRepository;
import tvz.naprednaJava.rozi.AutoServis.model.Client;

/**
 * Created by Hrvoje
 */
public interface ClientRepository extends CrudRepository<Client, Long> {
}
