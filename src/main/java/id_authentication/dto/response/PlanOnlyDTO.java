package id_authentication.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlanOnlyDTO {
    private long id;
    private String name;
     private String description;
}
