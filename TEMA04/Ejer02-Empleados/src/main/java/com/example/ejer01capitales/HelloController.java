package com.example.ejer01capitales;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;

public class HelloController {

    Connection con;
    private ResultSet rs;
    @FXML
    private ComboBox<String> localidad;

    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton agrupado;
    @FXML
    private Button mostrar;

    @FXML
    private void initialize(){
        String url = "jdbc:mysql://localhost:3306/datos";
        String user = "root";
        String pass = "1234";

        try {
            con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "select distinct(localidad) from datos.empleados";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                localidad.getItems().add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    private void setMostrar() throws JRException {
        JasperDesign d = JRXmlLoader.load("Informes/Informe_Datos_Empleados_SinOrdenar.jrxml");
        JRDesignQuery jq = new JRDesignQuery();
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
        if (normal.isSelected()){
            jq.setText("SELECT * FROM datos.empleados where Localidad = '" + localidad.getValue() + "';");
            d.setQuery(jq);
            JasperViewer.viewReport(jp,false);
        } else {
            jq.setText("SELECT * FROM datos.empleados where Localidad = '" + localidad.getValue() + "';");
            d.setQuery(jq);
            JasperViewer.viewReport(jp,false);
        }
    }
}