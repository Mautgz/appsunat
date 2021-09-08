package pe.sunat.sunatapi.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Empresa;
import pe.sunat.sunatapi.models.Servicio;


public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

}
