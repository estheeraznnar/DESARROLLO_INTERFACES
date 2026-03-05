package org.example.examenpablonavarrocamacho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;

public class HelloController {
    Connection con;
    private ResultSet rs;
    @FXML
    private ComboBox<String> tipos;
    @FXML
    private ComboBox<String> proveedores;

    @FXML
    private Button mostrarInforme1;
    @FXML
    private Button mostrarInforme2;
    @FXML
    private Button mostrarInforme3;


    @FXML
    private void initialize() {
        String url = "jdbc:mysql://localhost:3306/examen";
        String user = "root";
        String pass = "1234";

        try {
            con = DriverManager.getConnection(url, user, pass);

            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sqltipos = """
                        SELECT tipo FROM tipos;
                    """;
            rs = statement.executeQuery(sqltipos);
            while (rs.next()){
                tipos.getItems().add(rs.getNString("tipo"));
            }
            String sqlproveedores = """
                        SELECT proveedor FROM proveedores
                    """;
            rs = statement.executeQuery(sqlproveedores);
            while (rs.next()){
                proveedores.getItems().add(rs.getNString("proveedor"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void mostrarInforme1() throws JRException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("tipo", tipos.getValue());


        if (tipos.getValue().equals("Legumbres")){
            InputStream imgStream =
                    getClass().getResourceAsStream("/Imagenes/Legumbres.jpg");
            param.put("foto", imgStream);
        } else if (tipos.getValue().equals("Quesos")) {
            InputStream imgStream =
                    getClass().getResourceAsStream("/Imagenes/Quesos.jpg");
            param.put("foto", imgStream);
        }else {
            InputStream imgStream =
                    getClass().getResourceAsStream("/Imagenes/Dulces.jpg");
            param.put("foto", imgStream);
        }


        //String fileRepo = "Informes/Informe1.jasper";
        InputStream reportStream =
                getClass().getResourceAsStream(
                        "/Informes/Informe1.jasper"
                );

        JasperPrint jp = JasperFillManager.fillReport(reportStream, param, con);
        JasperViewer viewer = new JasperViewer(jp,false);
        viewer.setTitle("INFORME CON FOTO");
        viewer.setVisible(true);

    }

    @FXML
    protected void mostrarInforme2() throws JRException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("pro", proveedores.getValue());



        //String fileRepo = "Informes/Informe1.jasper";
        InputStream reportStream =
                getClass().getResourceAsStream(
                        "/Informes/Informe2.jasper"
                );

        JasperPrint jp = JasperFillManager.fillReport(reportStream, param, con);
        JasperViewer viewer = new JasperViewer(jp,false);
        viewer.setTitle("INFORME CON GRÁFICOS");
        viewer.setVisible(true);

    }

    @FXML
    protected void mostrarInforme3() throws JRException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("foto", proveedores.getValue());



        InputStream imgStream =
                getClass().getResourceAsStream("/Imagenes/logo.jpg");
        param.put("foto", imgStream);
        InputStream reportStream =
                getClass().getResourceAsStream(
                        "/Informes/Informe3.jasper"
                );

        JasperPrint jp = JasperFillManager.fillReport(reportStream, param, con);
        JasperViewer viewer = new JasperViewer(jp,false);
        viewer.setTitle("INFORME CON GRÁFICOS");
        viewer.setVisible(true);

    }
}