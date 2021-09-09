package pe.sunat.sunatapi.repositories;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, BigInteger>{
   @Query(value = "SELECT o FROM Empresa o WHERE o.ruc=?1")
   Optional<Empresa> findByRuc(BigInteger ruc);
}
