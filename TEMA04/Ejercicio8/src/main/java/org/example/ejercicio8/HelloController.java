package org.example.ejercicio8;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    @FXML
    private Button EjercicioA;
    @FXML
    private Button EjercicioB;
    @FXML
    private Button EjercicioC;
    @FXML
    private ComboBox<String> categoria;

    Connection con;
    private ResultSet rs;

    @FXML
    private void initialize(){
        String url = "jdbc:mysql://localhost:3306/ventas";
        String user = "root";
        String pass = "1234";

        try {
            con = DriverManager.getConnection(url, user, pass);
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "select distinct(categoría) from ventas.ventas";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                categoria.getItems().add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private  void  setEjercicioA() throws JRException {
        JasperDesign d = JRXmlLoader.load("Informes/Informe_Ej8_Capos_Calculados.jrxml"); //+ "' and población Between '" + valormin + "' and '" + valormax
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("select Nombre, PrecioUnidad, CantidadVendida, PrecioUnidad*CantidadVendida as TotalVentas from ventas.ventas");
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
        JasperViewer.viewReport(jp,false);
    }
    @FXML
    private  void  setEjercicioB() throws JRException {
        JasperDesign d = JRXmlLoader.load("Informes/Ej8_CampCalc_B.jrxml"); //+ "' and población Between '" + valormin + "' and '" + valormax
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT Nombre, Categoría, CantidadVendida, SUM(CantidadVendida) as TotalVentas\n" +
                "FROM ventas.ventas\n" +
                "GROUP BY Categoría, Nombre, CantidadVendida\n" +
                "ORDER BY Categoría");
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
        JasperViewer.viewReport(jp,false);
    }

    @FXML
    private void setEjercicioC() throws JRException {
        String valorselect = categoria.getValue();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Grafico", valorselect);
        String fileRepo = "Informes/Ej8_CamposCalculados_C.jasper.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, parametros, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("TITULO INFORME");
        viewer.setVisible(true);
    }
}