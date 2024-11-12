package uz.urinov.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uz.urinov.dto.product.ProductCreateDto;
import uz.urinov.dto.product.ProductResponseDto;
import uz.urinov.dto.product.ProductUpdateDto;
import uz.urinov.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductCreateDto dto) {
        return ResponseEntity.ok().body(productService.addProduct(dto));
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductUpdateDto dto) {
        return ResponseEntity.ok().body(productService.updateProduct(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getIdProduct(@PathVariable int id) {
        return ResponseEntity.ok().body(productService.getIdProduct(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProduct() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam int id) {
        return ResponseEntity.ok().body(productService.deleteProduct(id));
    }
}
