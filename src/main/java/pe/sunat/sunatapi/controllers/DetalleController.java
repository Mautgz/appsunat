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
import pe.sunat.sunatapi.models.Detalle;
import pe.sunat.sunatapi.repositories.DetalleRepository;

@RestController
@RequestMapping(value = "api/detalle", produces = "application/json")
public class DetalleController {
  private final DetalleRepository detalleData;

    public DetalleController(DetalleRepository detalleData) {
        this.detalleData = detalleData;
    }
     //Crear empresa
    @PostMapping(value = "/", produces =MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<Integer> create(@RequestBody Detalle detalle){
        detalleData.save(detalle);
        detalleData.flush(); // Crear id 
        Detalle generada = detalle;
        return new ResponseEntity<Integer>(detalle.getIdDetalle(), HttpStatus.CREATED);
         
     }

     @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Detalle> findById(@PathVariable Integer id){
        Optional<Detalle> optDetalle =detalleData.findById(id);
        if(optDetalle.isPresent()){
            Detalle detalle = optDetalle.get();
            return new ResponseEntity<Detalle>(detalle,HttpStatus.OK);
        }else{
            return new ResponseEntity<Detalle>(HttpStatus.NOT_FOUND);
        }

        
    }
}
