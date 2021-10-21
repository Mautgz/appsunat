package pe.sunat.sunatapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.sunat.sunatapi.models.Usuario;

@Repository
public interface  UsuarioRepository extends JpaRepository<Usuario, String>{

    
}