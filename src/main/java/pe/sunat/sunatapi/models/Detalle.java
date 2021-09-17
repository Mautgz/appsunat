package pe.sunat.sunatapi.models;
import java.math.BigDecimal;
import java.util.List;
import lombok.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_detalle")

public class Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetalle;
    private String codigoItem;
    private Integer cantidad;
    private String descripcion;
    private BigDecimal precioUnitario;
    private BigDecimal impuesto;
    private BigDecimal totalVenta;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="factura_id")
    private Factura factura;


    


}
