package uz.urinov.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResponseDto {

    private Integer id;

    private String name;

}
