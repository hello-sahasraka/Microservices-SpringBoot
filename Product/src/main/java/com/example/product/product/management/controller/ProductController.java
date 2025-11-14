package com.example.product.product.management.controller;

import com.example.product.product.management.dto.ProductDTO;
import com.example.product.product.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getproducts")
    public List<ProductDTO> getProduct() {
        return productService.getAllProducts();
    }

    @PostMapping("/createproduct")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO);
        return productService.createProduct(productDTO);
    }

    @PutMapping("/updateproduct")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {return productService.updateProduct(productDTO);}

    @DeleteMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("msg", "ok");
    }

}
