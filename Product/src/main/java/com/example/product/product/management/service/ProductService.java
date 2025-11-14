package com.example.product.product.management.service;

import com.example.product.product.management.dto.ProductDTO;
import com.example.product.product.management.model.Product;
import com.example.product.product.management.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        return modelMapper.map(productRepo.findAll(),new TypeToken<List<ProductDTO>>(){}.getType());
    };

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product savedProduct = productRepo.save(modelMapper.map(productDTO, Product.class));
        return modelMapper.map(savedProduct,ProductDTO.class);
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product savedProduct = productRepo.save(modelMapper.map(productDTO, Product.class));
        return modelMapper.map(savedProduct,ProductDTO.class);
    }

    public String deleteProduct(int id) {
        productRepo.deleteById(id);
        return "Product deleted succesfully!";
    }
}
