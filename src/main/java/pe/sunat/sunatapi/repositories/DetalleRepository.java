package pe.sunat.sunatapi.repositories;

import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Factura;
import pe.sunat.sunatapi.models.Detalle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Integer>  {
    @Query(value = "SELECT o FROM Detalle o WHERE o.id=?1")
    Optional<Detalle> findById(Integer id);

    @Query(value = "SELECT o FROM Detalle o WHERE o.factura=?1")
    List<Detalle> findOrdenesByFactura(Factura factura);
}
