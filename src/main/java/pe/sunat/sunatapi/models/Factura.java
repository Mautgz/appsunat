package pe.sunat.sunatapi.models;
import  pe.sunat.sunatapi.models.Detalle;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
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
@Table(name = "t_factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;
    private String codigoFactura;
    private String emisor;
    private String receptor;
    private Date fechaEmision;
    private BigDecimal totalIgv;
    private BigDecimal totalVenta;

    @Transient
    @OneToMany(fetch = FetchType.LAZY)
    private List<Detalle> detalle;



}
