package pe.sunat.sunatapi.controllers;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.sunat.sunatapi.models.Empresa;
import pe.sunat.sunatapi.models.Orden;
import pe.sunat.sunatapi.models.Servicio;
import pe.sunat.sunatapi.repositories.EmpresaRepository;
import pe.sunat.sunatapi.repositories.OrdenRepository;
import pe.sunat.sunatapi.repositories.ServicioRepository;

@RestController
@RequestMapping(value = "api/empresa", produces = "application/json")
public class EmpresaController {
    private final EmpresaRepository empresaData;
    private final ServicioRepository servicioData;

    public EmpresaController(EmpresaRepository empresaData, ServicioRepository servicioData) {
        this.empresaData = empresaData;
        this.servicioData = servicioData;
    }
     // Crear empresa
     @PostMapping(value = "/", produces =MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<Integer> create(@RequestBody Empresa em){
         empresaData.save(em);
         empresaData.flush(); // Crear id 
         Empresa generada = em;
 
         List<Servicio> listServicios = em.getServicio();
         listServicios.stream().forEach(o -> o.setEmpresa(generada));
         servicioData.saveAllAndFlush(listServicios);
         return new ResponseEntity<Integer>(em.getId(), HttpStatus.CREATED);   
     }
     @GetMapping(value = "/{ruc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Empresa> buscarPorRuc(@PathVariable BigInteger ruc){
        Optional<Empresa> optEmpresa = empresaData.findByRuc(ruc);
        if(optEmpresa.isPresent()){
            Empresa empresa = optEmpresa.get();
            return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Empresa>(HttpStatus.NOT_FOUND);
        }
    }
}
