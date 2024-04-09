package vn.edu.iuh.fit.backend.pks;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
public class ProductPricePK implements Serializable {
    private long product;
    private LocalDateTime price_date_time;
}
