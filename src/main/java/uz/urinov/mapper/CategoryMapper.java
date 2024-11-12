package uz.urinov.mapper;


import org.mapstruct.Mapper;
import uz.urinov.dto.category.CategoryCreateDto;
import uz.urinov.dto.category.CategoryResponseDto;
import uz.urinov.dto.category.CategoryUpdateDto;
import uz.urinov.entity.CategoryEntity;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto toDto(CategoryEntity category);

    CategoryEntity toEntity(CategoryCreateDto dto);

    CategoryEntity toEntity(CategoryUpdateDto dto);

    List<CategoryResponseDto> toDtos(List<CategoryEntity> categories);


}
