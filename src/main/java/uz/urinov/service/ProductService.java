package uz.urinov.service;

import org.springframework.stereotype.Service;

import uz.urinov.dto.product.ProductCreateDto;
import uz.urinov.dto.product.ProductResponseDto;
import uz.urinov.dto.product.ProductUpdateDto;
import uz.urinov.entity.ProductEntity;
import uz.urinov.mapper.ProductMapper;
import uz.urinov.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }


    public ProductResponseDto addProduct(ProductCreateDto dto) {
        categoryService.getById(dto.getCategoryId());
        ProductEntity entity = productRepository.save(productMapper.toEntity(dto));
        return productMapper.toDto(entity);
    }


    public ProductResponseDto updateProduct(ProductUpdateDto dto) {
        getById(dto.getId());
        categoryService.getById(dto.getCategoryId());
        ProductEntity entity = productRepository.save(productMapper.toEntity(dto));
        return productMapper.toDto(entity);
    }

    public ProductResponseDto getIdProduct(int id) {
        ProductEntity entity = getById(id);
        return productMapper.toDto(entity);
    }

    public List<ProductResponseDto> getAllProduct() {
        List<ProductEntity> all = productRepository.findAll();
        return productMapper.toDtos(all);
    }

    public String deleteProduct(int id) {
        ProductEntity entity = getById(id);
        productRepository.delete(entity);
        return "Product deleted" + id;
    }


    public ProductEntity getById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Error: No product found for given id: " + id);
        });
    }



}
