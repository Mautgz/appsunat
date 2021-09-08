package pe.sunat.sunatapi.repositories;

import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Orden;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>  {
    @Query(value = "SELECT o FROM Orden o WHERE o.id=?1")
    Optional<Orden> findById(Integer ruc);
}
