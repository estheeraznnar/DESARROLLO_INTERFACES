package org.example.ejercicio07;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    Connection con;
    private ResultSet rs;
    @FXML
    private ComboBox<String> autonomia;

    @FXML
    private Button listadoPoblacionJrxml;
    @FXML
    private Button listadoPoblacionJasper;

    @FXML
    private void initialize(){
        String url = "jdbc:mysql://localhost:3306/capitales";
        String user = "root";
        String pass = "1234";

        try {

            con = DriverManager.getConnection(url, user, pass);

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
    private void setListadoConGraficoJasper() throws JRException {
        String valorselect = autonomia.getValue();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Grafico", valorselect);
        String fileRepo = "Informes/Informe_Ejercicio7_Completo.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, parametros, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("TITULO INFORME");
        viewer.setVisible(true);
    }
}