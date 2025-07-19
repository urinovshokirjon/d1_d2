package uz.urinov.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.urinov.dto.category.CategoryCreateDto;
import uz.urinov.dto.category.CategoryResponseDto;
import uz.urinov.dto.category.CategoryUpdateDto;
import uz.urinov.entity.CategoryEntity;
import uz.urinov.mapper.CategoryMapper;
import uz.urinov.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    public CategoryResponseDto addCategory(CategoryCreateDto dto) {
        checkName(dto.getName());
        CategoryEntity entity = categoryRepository.save(categoryMapper.toEntity(dto));
        return categoryMapper.toDto(entity);
    }


    public CategoryResponseDto updateCategory(CategoryUpdateDto dto) {
        checkName(dto.getName());
        getById(dto.getId());
        CategoryEntity entity = categoryRepository.save(categoryMapper.toEntity(dto));
        return categoryMapper.toDto(entity);
    }


    public CategoryResponseDto getIdCategory(int id) {
        CategoryEntity entity = getById(id);
        return categoryMapper.toDto(entity);
    }

    public List<CategoryResponseDto> getAllCategory() {
        List<CategoryEntity> all = categoryRepository.findAll();
        return categoryMapper.toDtos(all);
    }

    public String deleteCategory(int id) {
        CategoryEntity entity = getById(id);
        categoryRepository.delete(entity);
        return "Category deleted" + id;
    }


    public CategoryEntity getById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Error: No category found for given id: " + id);
        });
    }

    public void checkName(String name) {
        Optional<CategoryEntity> entityOptional = categoryRepository.findByName(name);
        if (entityOptional.isPresent()) {
            throw new RuntimeException("Error: No category found for given name: " + name);
        }
    }

}
