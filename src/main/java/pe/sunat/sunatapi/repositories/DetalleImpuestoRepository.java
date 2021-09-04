package pe.sunat.sunatapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.DetalleImpuesto;
import pe.sunat.sunatapi.models.Empresa;

@Repository
public interface DetalleImpuestoRepository extends JpaRepository<DetalleImpuesto, Integer> {
    @Query(value = "SELECT o FROM DetalleImpuesto o WHERE o.empresa=?1")
    List<DetalleImpuesto> findItemsByEmpresa(Empresa empresa);

}
