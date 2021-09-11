package pe.sunat.sunatapi.controllers;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.sunat.sunatapi.models.Factura;
import pe.sunat.sunatapi.models.Persona;
import pe.sunat.sunatapi.repositories.FacturaRepository;
import pe.sunat.sunatapi.repositories.PersonaRepository;

@RestController
@RequestMapping(value = "api/persona", produces = "application/json")
public class PersonaController {
    private final PersonaRepository personaData;
    private final FacturaRepository facturaData;

    public PersonaController(PersonaRepository personaData, FacturaRepository facturaData) {
        this.personaData = personaData;
        this.facturaData = facturaData;
    }
    // Crear empresa
    @PostMapping(value = "/", produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody Persona p){
        personaData.save(p);
        personaData.flush(); // Crear id 
        Persona generada = p;

        List<Factura> listFacturas = p.getFactura();
        listFacturas.stream().forEach(o -> o.setPersona(generada));
        facturaData.saveAllAndFlush(listFacturas);
        return new ResponseEntity<Integer>(p.getId(), HttpStatus.CREATED);
        
    }

    @GetMapping(value = "/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> buscarPorId(@PathVariable BigInteger dni){
        Optional<Persona> optPersona = personaData.buscarPorDni(dni);
        if(optPersona.isPresent()){
            Persona persona = optPersona.get();
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<Persona>(HttpStatus.NOT_FOUND);
        }
    }

}
