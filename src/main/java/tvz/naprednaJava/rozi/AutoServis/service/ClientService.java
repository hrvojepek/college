package tvz.naprednaJava.rozi.AutoServis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tvz.naprednaJava.rozi.AutoServis.repository.ClientRepository;

/**
 * Created by Hrvoje
 */

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
}
