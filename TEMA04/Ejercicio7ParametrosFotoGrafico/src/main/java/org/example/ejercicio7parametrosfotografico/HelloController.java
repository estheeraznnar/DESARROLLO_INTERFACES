package org.example.ejercicio7parametrosfotografico;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;

public class HelloController {
    Connection con;
    private ResultSet rs;

    @FXML
    private TextField min;
    @FXML
    private TextField max;

    @FXML
    private Button mostrarJRXML;
    @FXML
    private Button mostrarJasper;

    @FXML
    private void initialize() {
        String url = "jdbc:mysql://localhost:3306/capitales";
        String user = "root";
        String pass = "1234";

        try {
            con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            /*String sql = "SELECT * from datos.empleados";
            rs = statement.executeQuery(sql);*/


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void mostrarInformeJasper() throws JRException {

        HashMap<String, Object> param = new HashMap<>();
        param.put("min", Integer.parseInt(min.getText()));
        param.put("max", Integer.parseInt(max.getText()));

        InputStream imgStream =
                getClass().getResourceAsStream("/Imagenes/App1.png");
        param.put("foto", imgStream);

        String fileRepo = "Informes/InformeParametrosFotoGrafico.jasper";

        JasperPrint jp = JasperFillManager.fillReport(fileRepo, param, con);
        JasperViewer viewer = new JasperViewer(jp,false);
        viewer.setTitle("INFORME CON Foto");
        viewer.setVisible(true);

    }


    @FXML
    private void mostrarInformeJRXML() throws JRException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("min", Integer.parseInt(min.getText()));
        param.put("max", Integer.parseInt(max.getText()));
        InputStream imgStream =
                getClass().getResourceAsStream("/Imagenes/App1.png");
        param.put("foto", imgStream);
        JasperDesign d = JRXmlLoader.load("Informes/InformeParametrosFotoGrafico.jrxml");
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT Provincia , Autonomía ,Población FROM capitales.capitales where Población Between $P{min} AND $P{max}");
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,param,con);
        JasperViewer.viewReport(jp,false);

    }



}

/*Para poner la foto.
 Ponemos la foto donde nos pidan el ejercicio.
 Y seleccionamos usar custom parameter .
 Vale , nos creamos un parametro llamado foto o como le queramos llamar.
 Y en foto tenemos que poner en class : java.io.InputStream
 Porque sino ira mal , y clickamos la foto y en expresion ponemos el nombre del parametro $P{foto}
 Y ya estaria .

 Para los gráficos necesitamos la extension jasperreports charts


 *
 */

