package vn.edu.iuh.fit.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Product;

@Service
public interface IProductService {
    public Page<Product> findAll(int pageNo, int sizeNo, String sortBy, String sortDirection);
}
