package tvz.naprednaJava.rozi.AutoServis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tvz.naprednaJava.rozi.AutoServis.repository.ReceiptRepository;

/**
 * Created by Hrvoje
 */

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

}
