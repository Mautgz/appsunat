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
@RequestMapping(value = "api/servicio", produces = "application/json")
public class ServicioController {
    private final ServicioRepository servicioData;

    public ServicioController(ServicioRepository servicioData) {
        this.servicioData = servicioData;
    }
     // Crear servicio 
     @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<Integer> create(@RequestBody Servicio serv){
 
         servicioData.save(serv);
         servicioData.flush();
 
         return new ResponseEntity<Integer>(serv.getId(), HttpStatus.CREATED);
 
     }
      // obtener servicio
     @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<Servicio> findByNumber(@PathVariable Integer id)
     {
         Optional<Servicio> optionServicio= servicioData.findById(id);
         if(optionServicio.isPresent()) {
             Servicio servicio = optionServicio.get();
             return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
         }else{
             return new ResponseEntity<Servicio>(HttpStatus.NOT_FOUND);
         }
 
      }

}
