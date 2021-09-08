package pe.sunat.sunatapi.models;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import lombok.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    private String nombreServicio;
    private String descripcionServicio;
    private float precio;

    @Transient
    @OneToMany(fetch = FetchType.LAZY)
    private List<Factura> factura;

    


}
