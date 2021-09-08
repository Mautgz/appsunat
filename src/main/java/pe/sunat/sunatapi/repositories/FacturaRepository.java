package pe.sunat.sunatapi.repositories;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.sunat.sunatapi.models.Empresa;
import pe.sunat.sunatapi.models.Factura;
import pe.sunat.sunatapi.models.Persona;

public interface FacturaRepository extends JpaRepository<Factura, BigInteger> {
    // Encontrar una factura
    @Query(value = "SELECT o FROM Factura o WHERE o.codigoFactura=?1")
    Optional<Factura> findByCodigo(BigInteger codigoFactura);

    @Query(value = "SELECT o FROM Persona o WHERE o.factura=?1")
    List<Factura> findFacturasByPersona(Persona persona);

    @Query(value = "SELECT o FROM Empresa o WHERE o.factura=?1")
    List<Factura> findFacturasByEmpresa(Empresa empresa);
   

}
