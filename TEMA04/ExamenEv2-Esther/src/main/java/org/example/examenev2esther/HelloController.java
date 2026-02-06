package org.example.examenev2esther;

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
import java.util.Objects;

public class HelloController {

    Connection con;
    private ResultSet rs;

    @FXML
    private Button informe1;
    @FXML
    private Button informe2;
    @FXML
    private Button informe3;
    @FXML
    private ComboBox<String> tipoComida;
    @FXML
    private ComboBox<String> proveedores;

    @FXML
    private void initialize(){
        String url = "jdbc:mysql://localhost:3306/examen";
        String user = "root";
        String pass = "1234";

        try {

            con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "select distinct(t.tipo) from examen.tipos t join examen.productos p on t.id = p.tipo";
            rs = statement.executeQuery(sql);
            while (rs.next()){
                tipoComida.getItems().add(rs.getString(1));
            }
            String sql1 = "select distinct(pr.proveedor) from examen.proveedores pr join examen.productos p on pr.id = p.proveedor";
            rs = statement.executeQuery(sql1);
            while (rs.next()){
                proveedores.getItems().add(rs.getString(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void setInforme1() throws JRException {
        String valorSel = tipoComida.getValue();

       //if (valorSel == "Dulces"){
           Map<String, Object> parametros = new HashMap<>();
           parametros.put("TipoParametro", valorSel);
           parametros.put("Imagen", "file:Imagenes/dulces.jpg");
           String fileRepo = "Informes/Informe1.jasper";
           JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, parametros, con);
           JasperViewer viewer = new JasperViewer(jpRepo,false);
           viewer.setTitle("TITULO INFORME");
           viewer.setVisible(true);
       //}
       /*if (valorSel == "Quesos") {
           Map<String, Object> parametros = new HashMap<>();
           parametros.put("TipoParametro", valorSel);
           parametros.put("Imagen", "file:Imagenes/Quesos.jpg");
           String fileRepo = "Informes/Informe1.jasper";
           JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, parametros, con);
           JasperViewer viewer = new JasperViewer(jpRepo,false);
           viewer.setTitle("TITULO INFORME");
           viewer.setVisible(true);
       }
       if (valorSel == "Legumbres"){
           Map<String, Object> parametros = new HashMap<>();
           parametros.put("TipoParametro", valorSel);
           parametros.put("Imagen", "file:Imagenes/Legumbres.jpg");
           String fileRepo = "Informes/Informe1.jasper";
           JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, parametros, con);
           JasperViewer viewer = new JasperViewer(jpRepo,false);
           viewer.setTitle("TITULO INFORME");
           viewer.setVisible(true);
       }*/
    }

}