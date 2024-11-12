package uz.urinov.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, unique = true)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",insertable = false, updatable = false)
    private CategoryEntity category;

}
