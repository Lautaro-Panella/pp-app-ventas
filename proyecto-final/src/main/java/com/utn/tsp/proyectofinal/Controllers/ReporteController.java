package com.utn.tsp.proyectofinal.Controllers;

import com.utn.tsp.proyectofinal.Model.DTO.FiltroFechasDTO;
import com.utn.tsp.proyectofinal.Model.DTO.HistoricoVentasDTO;
import com.utn.tsp.proyectofinal.Model.DTO.RankingProductosDTO;
import com.utn.tsp.proyectofinal.Services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/reporte")
@CrossOrigin("http://localhost:4200")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    /**
     *
     * @param filtroFechasDTO
     * @return
     */
    @PostMapping("/ganancia")
    public Double getGanancia(@RequestBody FiltroFechasDTO filtroFechasDTO) {
        return reporteService.getGanancia(filtroFechasDTO.getFechaDesde(), filtroFechasDTO.getFechaHasta());
    }

    /**
     *
     * @return
     */
    @GetMapping("/masVendidos")
    public List<RankingProductosDTO> getProductosMasVendidos() {
        return reporteService.getProductosMasVendidos();
    }

    /**
     *
     * @return
     */
    @GetMapping("/menosVendidos")
    public List<RankingProductosDTO> getProductosMenosVendidos() {
        return reporteService.getProductosMenosVendidos();
    }

    /**
     *
     * @return
     */
    @GetMapping("/historicoVentas")
    public List<HistoricoVentasDTO> getHistoricoVentas() {
        return reporteService.getHistoricoVentas();
    }

}
