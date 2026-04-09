package mx.edu.utez.mentorias.controllers;

import mx.edu.utez.mentorias.services.Reporte.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:5173")
public class ReporteController {


        @Autowired
        private ReporteService reporteService;

        @GetMapping("/mentorias")
        public ResponseEntity<byte[]> descargar(
                @RequestParam(required = false) String mentor,
                @RequestParam(required = false) String materia,
                @RequestParam(required = false) String fechaInicio,
                @RequestParam(required = false) String fechaFin
        ) {
            try {
                // se manda a hacer el reporte
                byte[] pdf = reporteService.generarReporte(mentor, materia, fechaInicio, fechaFin);

                //para que sepa el navegador sobre el archivo
                HttpHeaders contenido = new HttpHeaders();

                // como se llama el reporte
                contenido.setContentDispositionFormData("nombre", "Reporte_Mentorias.pdf");

                // que tipo es, aqui es pdf
                contenido.setContentType(MediaType.APPLICATION_PDF);

                return new ResponseEntity<>(pdf, contenido, HttpStatus.OK);

            } catch (Exception e) {

                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

