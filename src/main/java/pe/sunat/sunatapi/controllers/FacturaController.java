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
import pe.sunat.sunatapi.models.Detalle;

import pe.sunat.sunatapi.repositories.FacturaRepository;
import pe.sunat.sunatapi.repositories.DetalleRepository;


@RestController
@RequestMapping(value = "api/factura", produces = "application/json")
public class FacturaController {
    private final FacturaRepository facturaData;
    private final DetalleRepository detalleData;

    public FacturaController(FacturaRepository facturaData, DetalleRepository detalleData) {
        this.facturaData = facturaData;
        this.detalleData = detalleData;
    }

    // Crear empresa
    @PostMapping(value = "/", produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody Factura f){
        facturaData.save(f);
        facturaData.flush(); // Crear id 
        Factura generada = f;

        List<Detalle> listDetalles = f.getDetalle();
        listDetalles.stream().forEach(o -> o.setFactura(generada));
        detalleData.saveAllAndFlush(listDetalles);
        return new ResponseEntity<Integer>(f.getIdFactura(), HttpStatus.CREATED);   
    }

    @GetMapping(value = "/{codigoFactura}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Factura> findByCodigo(@PathVariable BigInteger codigoFactura){
        Optional<Factura> optFactura =facturaData.findByCodigo(codigoFactura);
        if(optFactura.isPresent()){
            Factura factura = optFactura.get();
            return new ResponseEntity<Factura>(factura,HttpStatus.OK);
        }else{
            return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
        }   
    }
}
