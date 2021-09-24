package pe.sunat.sunatapi.repositories;

import java.math.BigInteger;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.sunat.sunatapi.models.Factura;

public interface FacturaRepository extends JpaRepository<Factura, BigInteger> {

    // Encontrar una factura
    @Query(value = "SELECT o FROM Factura o WHERE o.numeroFactura=?1")
    Optional<Factura> findByNumFactura(BigInteger numeroFactura);

}
