package mx.edu.utez.mentorias.services.Reporte;

import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReporteService {

        @Autowired
        private DataSource dataSource; //conexion a la bd

        public byte[] generarReporte(String mentor, String materia, String fechaI, String fechaF) {
            try {

                //busca el archivo de jasper
                InputStream reporte = getClass().getResourceAsStream("/reportes/MENTORIAS.jrxml");

                // para que jasper entienda el xml
                JasperReport reporteCompilado = JasperCompileManager.compileReport(reporte);

                // los filtros que se crearon en los parametros
                // en map se agregan los usuarios que cumplen los filtros
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("p_mentor", mentor);
                parametros.put("p_materia", materia);
                if (fechaI != null && !fechaI.isEmpty()) {
                    parametros.put("p_fecha_inicio", Date.valueOf(fechaI));
                } else {
                    parametros.put("p_fecha_inicio", null);
                }

                if (fechaF != null && !fechaF.isEmpty()) {
                    parametros.put("p_fecha_fin", Date.valueOf(fechaF));
                } else {
                    parametros.put("p_fecha_fin", null);
                }


                //se manda toda la informacion a jasper
                JasperPrint reporteLleno = JasperFillManager.fillReport(reporteCompilado, parametros, dataSource.getConnection());

              //se convierte el archivo y retornamos
                return JasperExportManager.exportReportToPdf(reporteLleno);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }


