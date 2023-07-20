package com.utn.tsp.proyectofinal.Services;

import com.utn.tsp.proyectofinal.Model.DTO.HistoricoVentasDTO;
import com.utn.tsp.proyectofinal.Model.DTO.RankingProductosDTO;
import com.utn.tsp.proyectofinal.Repositories.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;

    /**
     *
     * @param fechaDesde
     * @param fechaHasta
     * @return
     */
    public Double getGanancia(Date fechaDesde, Date fechaHasta) {
        return reporteRepository.getGanancia(fechaDesde, fechaHasta);
    }

    /**
     *
     * @return
     */
    public List<RankingProductosDTO> getProductosMasVendidos() {
        return reporteRepository.getProductosMasVendidos();
    }

    /**
     *
     * @return
     */
    public List<RankingProductosDTO> getProductosMenosVendidos() {
        return reporteRepository.getProductosMenosVendidos();
    }

    /**
     *
     * @return
     */
    public List<HistoricoVentasDTO> getHistoricoVentas() {
        return reporteRepository.getHistoricoVentas();
    }

}
