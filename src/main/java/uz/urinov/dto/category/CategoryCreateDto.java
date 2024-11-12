package uz.urinov.dto.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryCreateDto {
// oksavol bo'lsa aloqdaman telegram dan tel qilingok?

    @NotBlank(message = "Name  bo'sh bo'lishi mumkin emas")
    @Size(min = 3, max = 50, message = "Berilgan category (Name) ning uzunligi 3 va 50 orasida bo'lishi kerak")
    private String name;


}
