package com.example.ejer01capitales;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.HashMap;

public class HelloController {

    Connection con;
    private ResultSet rs;
    @FXML
    private ComboBox<String> autonomia;

    @FXML
    private RadioButton normal;
    @FXML
    private Button conTabla;
    @FXML
    private RadioButton agrupado;
    @FXML
    private Button mostrar;
    @FXML
    private TextField poblaMin;
    @FXML
    private TextField poblaMax;


    @FXML
    private void initialize(){
        String url = "jdbc:mysql://localhost:3306/capitales";
        String user = "root";
        String pass = "1234";

        try {



            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "select distinct(autonomía) from capitales.capitales";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                autonomia.getItems().add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @FXML
    private void setMostrar() throws JRException {

        double valormin = Double.parseDouble(poblaMin.getText());
        double valormax = Double.parseDouble(poblaMax.getText());
        if (normal.isSelected()){
            HashMap<String, Object> param = new HashMap<>();
            param.put("Ruta_imagen", "file:Images/Pezazul.png");
            JasperDesign d = JRXmlLoader.load("Informes/Informe_Capitales_SinOrdenar.jrxml"); //+ "' and población Between '" + valormin + "' and '" + valormax
            JRDesignQuery jq = new JRDesignQuery();
            jq.setText("SELECT * FROM capitales.capitales where autonomía = '" + autonomia.getValue() + "' and población Between '" + valormin + "' and '" + valormax + "';");
            d.setQuery(jq);
            JasperReport jr = JasperCompileManager.compileReport(d);
            JasperPrint jp = JasperFillManager.fillReport(jr,param,con);
            JasperViewer.viewReport(jp,false);
        } else {
            HashMap<String, Object> param = new HashMap<>();
            param.put("Ruta_imagen", "file:Images/Pezazul.png");
            JasperDesign d = JRXmlLoader.load("Informes/Informe_Capitales.jrxml");
            JRDesignQuery jq = new JRDesignQuery();
            jq.setText("SELECT * FROM capitales.capitales where autonomía = '" + autonomia.getValue() + "' and población Between '" + valormin + "' and '" + valormax + "';");
            d.setQuery(jq);
            JasperReport jr = JasperCompileManager.compileReport(d);
            JasperPrint jp = JasperFillManager.fillReport(jr,param,con);
            JasperViewer.viewReport(jp,false);
        }
    }

    @FXML
    private void setConTabla() throws JRException {
        String fileRepo = "Informes/Informe_Capitales_Tabla.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, null, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("TITULO INFORME");
        viewer.setVisible(true);
    }
}