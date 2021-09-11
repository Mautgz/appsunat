package pe.sunat.sunatapi.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Factura;
import pe.sunat.sunatapi.models.Persona;

import org.springframework.data.jpa.repository.Query.*;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, BigInteger>  {
    @Query(value = "SELECT o FROM Persona o WHERE o.id=?1")
    Optional<Persona> buscarPorId(int id);
}
