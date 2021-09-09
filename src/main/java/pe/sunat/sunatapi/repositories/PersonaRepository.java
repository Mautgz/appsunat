package pe.sunat.sunatapi.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, BigInteger>  {
    
}
