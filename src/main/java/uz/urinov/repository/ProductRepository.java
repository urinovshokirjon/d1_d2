package uz.urinov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.entity.CategoryEntity;
import uz.urinov.entity.ProductEntity;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
