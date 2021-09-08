package pe.sunat.sunatapi.repositories;

import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Orden;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>  {
    
}
