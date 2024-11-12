package uz.urinov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.urinov.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    Optional<CategoryEntity> findByName(String name);
}
