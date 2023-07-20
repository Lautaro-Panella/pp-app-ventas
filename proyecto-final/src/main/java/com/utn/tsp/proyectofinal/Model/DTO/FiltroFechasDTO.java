package com.utn.tsp.proyectofinal.Model.DTO;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroFechasDTO {

    private Date fechaDesde;
    private Date fechaHasta;

}
