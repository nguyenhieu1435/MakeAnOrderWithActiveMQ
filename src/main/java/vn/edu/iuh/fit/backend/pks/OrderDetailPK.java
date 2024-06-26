package vn.edu.iuh.fit.backend.pks;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailPK implements Serializable {
    private long order;
    private long product;
}
