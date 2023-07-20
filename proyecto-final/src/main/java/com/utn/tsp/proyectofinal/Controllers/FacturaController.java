package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Model.DTO.FacturaDTO;
import com.utn.tsp.proyectofinal.Model.DTO.FiltroFechasDTO;
import com.utn.tsp.proyectofinal.Model.Factura;
import com.utn.tsp.proyectofinal.Services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/factura")
@CrossOrigin("http://localhost:4200")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    /**
     *
     * @return
     */
    @GetMapping
    public List<Factura> getFacturas() {
        return facturaService.getFacturas();
    }

    /**
     *
     * @param facturaId
     * @return
     */
    @GetMapping("/{facturaId}")
    public Optional<Factura> getFacturaById(@PathVariable("facturaId") Long facturaId) {
        return facturaService.getFacturaById(facturaId);
    }

    /**
     *
     * @param filtroFechasDTO
     * @return
     */
    @PostMapping ("/customFilter")
    public List<Factura> getFacturasByFechaDesdeFechaHasta(@RequestBody FiltroFechasDTO filtroFechasDTO) {
        return facturaService.getFacturasByFechaDesdeFechaHasta(filtroFechasDTO.getFechaDesde(), filtroFechasDTO.getFechaHasta());
    }

    /**
     *
     * @param facturaDTO
     * @return
     */
    @PostMapping
    public FacturaDTO saveFactura(@RequestBody FacturaDTO facturaDTO) {
        return facturaService.saveFactura(facturaDTO);
    }

    /**
     *
     * @param facturaId
     * @param mailTo
     * @return
     * @throws MessagingException
     */
    @GetMapping("/sendMail")
    public boolean sendMail(@RequestParam("facturaId") String facturaId, @RequestParam("mail") String mailTo) throws MessagingException {
        return this.facturaService.sendMail(facturaId, mailTo);
    }

    /**
     *
     * @param facturaId
     * @return
     */
    @GetMapping("/files/{facturaId:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFacturaPDF(@PathVariable("facturaId") Long facturaId) {
        Resource file = this.facturaService.getFacturaPDF(facturaId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
