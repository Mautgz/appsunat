package pe.sunat.sunatapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import io.micrometer.core.lang.Nullable;

import javax.persistence.Id;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_user")
public class Usuario implements Serializable{
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userID;

    @Nullable
    private String adminEmail;

    private BigInteger dniEmisor;

    private Integer tipoUsuario = 1;

}