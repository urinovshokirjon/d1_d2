package uz.urinov.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponseDto {

    private Integer id;

    private String name;

    private Double price;

}
