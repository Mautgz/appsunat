package pe.sunat.sunatapi.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Empresa;
import pe.sunat.sunatapi.models.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer>  {
    @Query(value = "SELECT o FROM Servicio o WHERE o.empresa=?1")
    List<Servicio> findServiciosByEmpresa(Empresa empresa);

    @Query(value = "SELECT o FROM Servicio o WHERE o.id=?1")
   Optional<Servicio> findById(Integer id);
}
