package uz.urinov.mapper;


import org.mapstruct.Mapper;
import uz.urinov.dto.category.CategoryCreateDto;
import uz.urinov.dto.category.CategoryResponseDto;
import uz.urinov.dto.category.CategoryUpdateDto;
import uz.urinov.dto.product.ProductCreateDto;
import uz.urinov.dto.product.ProductResponseDto;
import uz.urinov.dto.product.ProductUpdateDto;
import uz.urinov.entity.CategoryEntity;
import uz.urinov.entity.ProductEntity;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(ProductEntity category);

    ProductEntity toEntity(ProductCreateDto dto);

    ProductEntity toEntity(ProductUpdateDto dto);

    List<ProductResponseDto> toDtos(List<ProductEntity> categories);


}
