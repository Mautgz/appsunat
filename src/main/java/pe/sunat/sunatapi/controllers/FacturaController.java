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

import pe.sunat.sunatapi.models.Factura;
import pe.sunat.sunatapi.models.Orden;

import pe.sunat.sunatapi.repositories.FacturaRepository;
import pe.sunat.sunatapi.repositories.OrdenRepository;


@RestController
@RequestMapping(value = "api/factura", produces = "application/json")
public class FacturaController {
    private final FacturaRepository facturaData;
    private final OrdenRepository ordenData;

    public FacturaController(FacturaRepository facturaData, OrdenRepository ordenData) {
        this.facturaData = facturaData;
        this.ordenData = ordenData;
    }

    // Crear empresa
    @PostMapping(value = "/", produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody Factura f){
        facturaData.save(f);
        facturaData.flush(); // Crear id 
        Factura generada = f;

        List<Orden> listOrdenes = f.getOrden();
        listOrdenes.stream().forEach(o -> o.setFactura(generada));
        ordenData.saveAllAndFlush(listOrdenes);
        return new ResponseEntity<Integer>(f.getId(), HttpStatus.CREATED);
        
    }

}
