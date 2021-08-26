import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GuardarSiDos extends Simulacion{

    private JPanel GuardarSiD;
    private JButton atras;
    private JTextField prueba1;
    private JPasswordField passwordField1;
    private JButton crearCuentaButton;
    private JButton confirmarButton;
    private Image image,image2,image3;
    private ImageIcon i,i2,i3;
    static int id,numSimu,hola,cout;
    static String nombre2,apellido;
    static int[] codigosSimu;

    private void GettNombre(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                //for (int i; i <){
                    nombre2 = rs.getString("NOMBRE");
                    apellido = rs.getString("APELLIDO");
                //}
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void selectCout(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                cout = rs.getInt("TOTAL");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void GetCodSi(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            //while (rs.next()) {
            codigosSimu = new int[cout];
            for (int i = 0; i<cout;i++) {
                rs.next();
                codigosSimu[i] = rs.getInt("CODIGOSIMULACION");
                //System.out.println(codigosSimu[i]);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e){

        try {

            image = ImageIO.read(new File("Error.png"));
            i = new ImageIcon(image);
            image2 = ImageIO.read(new File("palomita.png"));
            i2 = new ImageIcon(image2);
            image3 = ImageIO.read(new File("pregunta.png"));
            i3 = new ImageIcon(image3);

        } catch (IOException ex){
        }

        String  idt = prueba1.getText();
        String con = passwordField1.getText();

        if (e.getSource()==confirmarButton){

            if(idt.isEmpty()){

                JOptionPane.showMessageDialog(null, "Ingresa tu ID", "ID incorrecto",JOptionPane.WARNING_MESSAGE,i);
            }

            else if(con.isEmpty()){

                JOptionPane.showMessageDialog(null, "Ingresa tu contraseña", "Contraseña incorrecta",JOptionPane.WARNING_MESSAGE,i);

            }

            //preguntar si existe el id en la base de datos
            //preguntar si existe la contraseña en la base de datos


            else {

                try {

                    String idC = prueba1.getText();
                    String contra = passwordField1.getText();//Captura el ID
                    PreparedStatement ps2;
                    ps2 = TransactionMySQL.conn.prepareStatement("SELECT `IDCLIENTE`, `CONTRASENA` FROM `CLIENTE` WHERE `IDCLIENTE` = ? AND `CONTRASENA` = ?");
                    ps2.setString(1,idC);
                    ps2.setString(2,contra);
                    ResultSet result2 = ps2.executeQuery();
                    if(result2.next()){
                        id = Integer.parseInt(idC);
                        String statement = "select NOMBRE,APELLIDO from CLIENTE where IDCLIENTE="+ id +";";
                        String statement2 = "select CODIGOSIMULACION from SIMULACION where IDCLIENTE="+id+";";
                        String statement3 = "select COUNT(CODIGOSIMULACION) as TOTAL from SIMULACION where IDCLIENTE=" + id + ";";
                        selectCout(statement3);
                        GettNombre(statement);
                        GetCodSi(statement2);
                        System.out.println(nombre2 + " " + apellido);
                        //System.out.println(hola);
                        /*for(int i = 0; i<codigosSimu.length; i++){
                            System.out.println(codigosSimu[i]);
                        }*/
                        Reservaciones re = new Reservaciones();
                        re.setVisible(true);
                        this.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión.", "Error en iniciar sesión", JOptionPane.WARNING_MESSAGE,i);
                        Simulacion si = new Simulacion();
                        si.setVisible(true);
                        this.dispose();
                    }


                } catch (NumberFormatException | SQLException ex) {

                    JOptionPane.showMessageDialog(null, "Es necesario que escribas un dato númerico", "ID incorrecto", JOptionPane.WARNING_MESSAGE, i);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        if (e.getSource()==crearCuentaButton){

            NewAcc newa = null;
            try {
                newa = new NewAcc();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            newa.setVisible(true);
            this.dispose();

        }
        if (e.getSource()==atras){
            Bienvenida bie = null;
            try {
                bie = new Bienvenida();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            bie.setVisible(true);
            this.dispose();

        }
    }

    public GuardarSiDos() throws Exception {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(NORMAL);
        setSize(319, 171);
        add(GuardarSiD);
        confirmarButton.addActionListener(this);
        crearCuentaButton.addActionListener(this);
        atras.addActionListener(this);

    }
}
