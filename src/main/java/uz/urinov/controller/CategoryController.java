package uz.urinov.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.dto.category.CategoryCreateDto;
import uz.urinov.dto.category.CategoryResponseDto;
import uz.urinov.dto.category.CategoryUpdateDto;
import uz.urinov.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> addCategory(@Valid @RequestBody CategoryCreateDto dto) {
        return ResponseEntity.ok().body(categoryService.addCategory(dto));
    }

    @PutMapping
    public ResponseEntity<CategoryResponseDto> updateCategory(@Valid @RequestBody CategoryUpdateDto dto) {
        return ResponseEntity.ok().body(categoryService.updateCategory(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getIdCategory(@PathVariable int id) {
        return ResponseEntity.ok().body(categoryService.getIdCategory(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCategory(@RequestParam int id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }
}
