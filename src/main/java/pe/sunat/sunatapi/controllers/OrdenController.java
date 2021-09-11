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
import pe.sunat.sunatapi.repositories.EmpresaRepository;
import pe.sunat.sunatapi.repositories.OrdenRepository;

@RestController
@RequestMapping(value = "api/orden", produces = "application/json")
public class OrdenController {
  private final OrdenRepository ordenData;
  private final EmpresaRepository empresaData;

    public OrdenController(OrdenRepository ordenData,EmpresaRepository empresaData) {
        this.ordenData = ordenData;
        this.empresaData = empresaData;
    }
     //Crear empresa
    @PostMapping(value = "/", produces =MediaType.APPLICATION_JSON_VALUE)
     public ResponseEntity<Integer> create(@RequestBody Orden orden){
        ordenData.save(orden);
        ordenData.flush(); // Crear id 
        Orden generada = orden;
        List<Empresa> listEmpresas = orden.getEmpresa();
        listEmpresas.stream().forEach(o -> o.setOrden(generada));
        empresaData.saveAllAndFlush(listEmpresas);
        return new ResponseEntity<Integer>(orden.getId(), HttpStatus.CREATED);
         
     }

     @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orden> findById(@PathVariable Integer id){
        Optional<Orden> optOrden =ordenData.findById(id);
        if(optOrden.isPresent()){
            Orden orden = optOrden.get();
            return new ResponseEntity<Orden>(orden,HttpStatus.OK);
        }else{
            return new ResponseEntity<Orden>(HttpStatus.NOT_FOUND);
        }

        
    }
}
