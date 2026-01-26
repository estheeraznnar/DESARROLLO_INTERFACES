package com.example.ejer03empresa;

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

    Connection con;
    private ResultSet rs;

    @FXML
    private Button btnListar;
    @FXML
    private ComboBox<String> departamento;


    @FXML
    private void initialize(){
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String pass = "1234";

        try {

            con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "select distinct(d.departamento) from empresa.empleados e join empresa.departamentos d ON d.id = e.departamento";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                departamento.getItems().add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    private void setBtnListar() throws JRException {
        String valorselect = departamento.getValue();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("departamento", valorselect);
        String fileRepo = "Informes/Informe_Empresa_Listar_Empleados.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, parametros, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("TITULO INFORME");
        viewer.setVisible(true);
    }


}