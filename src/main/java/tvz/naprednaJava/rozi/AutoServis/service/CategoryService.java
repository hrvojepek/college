package tvz.naprednaJava.rozi.AutoServis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tvz.naprednaJava.rozi.AutoServis.repository.CategoryRepository;

/**
 * Created by Hrvoje
 */

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
