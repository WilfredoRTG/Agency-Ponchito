import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;


public class GuardarSi extends Simulacion implements ActionListener {

    private JPanel GuardarS;
    private JButton crearCuentaButton;
    private JButton confirmarButton;
    private JPasswordField contraseñaT;
    private JTextField IDTextField;
    private JButton atras;
    private Image image,image2,image3;
    private ImageIcon i,i2,i3;
    private int id;
    private Object[] options = {"Si","No"};
    static int ultIC;
    static double precioE;

    public void AñadirNuevo(String statement){
        try {
            TransactionMySQL.stmt.executeUpdate(statement);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            TransactionMySQL.conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void UltimoIC(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                ultIC = rs.getInt("IDENTIFICADORCIRCUITO");
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

        String  idt = IDTextField.getText();
        String con = contraseñaT.getText();

        if (e.getSource()==confirmarButton){

            if(idt.isEmpty()){

                JOptionPane.showMessageDialog(null, "Ingresa tu ID", "ID incorrecto",JOptionPane.WARNING_MESSAGE,i);

            }
            else if(con.isEmpty()){

                JOptionPane.showMessageDialog(null, "Ingresa tu contraseña", "Contraseña incorrecta",JOptionPane.WARNING_MESSAGE,i);

            }

            else {

                try {

                    //Chequeo
                    /*
                     if id == idt && contra == con {

                     *///preguntar si existe el id en la base de datos
                    //preguntar si existe la contraseña en la base de datos
                    String empl  = IDTextField.getText();
                    String contra = contraseñaT.getText();
                    PreparedStatement ps;
                    ps = TransactionMySQL.conn.prepareStatement("SELECT `IDCLIENTE`, `CONTRASENA` FROM `CLIENTE` WHERE `IDCLIENTE` = ? AND `CONTRASENA` = ? AND `EMPLEADO` = 1");
                    ps.setString(1,empl);
                    ps.setString(2,contra);
                    ResultSet result = ps.executeQuery();
                    PreparedStatement ps2;
                    ps2 = TransactionMySQL.conn.prepareStatement("SELECT `IDCLIENTE`, `CONTRASENA` FROM `CLIENTE` WHERE `IDCLIENTE` = ? AND `CONTRASENA` = ?");
                    ps2.setString(1,empl);
                    ps2.setString(2,contra);
                    ResultSet result2 = ps2.executeQuery();

                    if(result.next()){
                        precioE = precioF*0.85;
                        int d = lug1_dura + lug1_dura2 + lug1_dura3;
                        String añadirCircuito = "Insert into CIRCUITO(CIUDADSALIDA, PAISSALIDA, CIUDADLLEGADA, PAISLLEGADA, DURACION, PRECIO) values (" + "\"" + ciu_salida + "\"" + "," + "\"" + pais_salida + "\"" + "," + "\"" + ciu_llegada + "\"" + "," + "\"" + PaLL + "\""+ "," + "\"" + d + "\""+ "," + "\"" + precioE + "\"" + ");";
                        AñadirNuevo(añadirCircuito);
                        //JOptionPane.showMessageDialog(null, "Se ha reservado correctamente\n"+"Con precio total de: "+ precio +"\nIngresa al siguiente link para proceder a tu pago\nhttps://www.paypal.com/mx/ponchitoagency", "Reservación exitosa", JOptionPane.WARNING_MESSAGE, i);
                        String statement = "SELECT IDENTIFICADORCIRCUITO FROM CIRCUITO ORDER BY IDENTIFICADORCIRCUITO DESC LIMIT 1;";
                        UltimoIC(statement);
                        String statement2 = "INSERT INTO FECHACIRCUITO (FECHASALIDA, NUMEROPERSONAS, IDENTIFICADORCIRCUITO) VALUES (" + "\"" + año+"-"+mes+"-"+dia + "\"" + "," + nPer + ","+ ultIC + ");";
                        AñadirNuevo(statement2);
                        if(lugar2_N==null&&lugar3_N==null){
                            String statement3 = "INSERT INTO ETAPA (IDENTIFICADORCIRCUITO, ORDENLUGAR1, DURACION1, ORDENLUGAR2, DURACION2, ORDENLUGAR3, DURACION3) VALUES ("+ ultIC + "," + "\""+ lugar1_N + "\"" +"," + lug1_dura + ","  + null + "," + 0 + "," + null + "," + 0 + ");";
                            AñadirNuevo(statement3);
                        }
                        if(lugar2_N!=null&&lugar3_N==null){
                            String statement3 = "INSERT INTO ETAPA (IDENTIFICADORCIRCUITO, ORDENLUGAR1, DURACION1, ORDENLUGAR2, DURACION2, ORDENLUGAR3, DURACION3) VALUES ("+ ultIC + "," + "\""+ lugar1_N + "\"" +"," + lug1_dura + ","+ "\""  + lugar2_N + "\"" +"," + lug1_dura2 + "," + null + "," + 0 + ");";
                            AñadirNuevo(statement3);
                        }
                        if(lugar3_N!=null&&lugar2_N!=null){
                            String statement3 = "INSERT INTO ETAPA (IDENTIFICADORCIRCUITO, ORDENLUGAR1, DURACION1, ORDENLUGAR2, DURACION2, ORDENLUGAR3, DURACION3) VALUES ("+ ultIC + "," + "\""+ lugar1_N + "\"" +"," + lug1_dura + ","+ "\""  + lugar2_N + "\"" +"," + lug1_dura2 + "," + "\"" + lugar3_N + "\"" + "," + lug1_dura3 + ");";
                            AñadirNuevo(statement3);
                        }

                        //System.out.println(statement3);
                        String statement4 = "INSERT INTO SIMULACION (IDENTIFICADORCIRCUITO, IDCLIENTE) VALUES ("+ ultIC + "," +empl+");";
                        AñadirNuevo(statement4);
                        JOptionPane.showMessageDialog(null, "Se ha guardado tu simulación", "Simulación correctamente guardada", JOptionPane.WARNING_MESSAGE, i2);
                        //System.out.println(ultIC);

                    }else if(result2.next()){
                        System.out.println(PaLL);
                        int d = lug1_dura + lug1_dura2 + lug1_dura3;
                        String añadirCircuito = "Insert into CIRCUITO(CIUDADSALIDA, PAISSALIDA, CIUDADLLEGADA, PAISLLEGADA, DURACION, PRECIO) values (" + "\"" + ciu_salida + "\"" + "," + "\"" + pais_salida + "\"" + "," + "\"" + ciu_llegada + "\"" + "," + "\"" + PaLL + "\""+ "," + "\"" + d + "\""+ "," + "\"" + precioF + "\"" + ");";
                        AñadirNuevo(añadirCircuito);
                        String statement = "SELECT IDENTIFICADORCIRCUITO FROM CIRCUITO ORDER BY IDENTIFICADORCIRCUITO DESC LIMIT 1;";
                        UltimoIC(statement);
                        String statement2 = "INSERT INTO FECHACIRCUITO(FECHASALIDA, NUMEROPERSONAS, IDENTIFICADORCIRCUITO) VALUES (" + "\"" + año+"-"+mes+"-"+dia + "\"" + "," + nPer + ","+ ultIC + ");";
                        AñadirNuevo(statement2);
                        if(lugar2_N==null&&lugar3_N==null){
                            String statement3 = "INSERT INTO ETAPA (IDENTIFICADORCIRCUITO, ORDENLUGAR1, DURACION1, ORDENLUGAR2, DURACION2, ORDENLUGAR3, DURACION3) VALUES ("+ ultIC + "," + "\""+ lugar1_N + "\"" +"," + lug1_dura + ","  + null + "," + 0 + "," + null + "," + 0 + ");";
                            AñadirNuevo(statement3);
                        }
                        if(lugar2_N!=null&&lugar3_N==null){
                            String statement3 = "INSERT INTO ETAPA (IDENTIFICADORCIRCUITO, ORDENLUGAR1, DURACION1, ORDENLUGAR2, DURACION2, ORDENLUGAR3, DURACION3) VALUES ("+ ultIC + "," + "\""+ lugar1_N + "\"" +"," + lug1_dura + ","+ "\""  + lugar2_N + "\"" +"," + lug1_dura2 + "," + null + "," + 0 + ");";
                            AñadirNuevo(statement3);
                        }
                        if(lugar3_N!=null&&lugar2_N!=null){
                            String statement3 = "INSERT INTO ETAPA (IDENTIFICADORCIRCUITO, ORDENLUGAR1, DURACION1, ORDENLUGAR2, DURACION2, ORDENLUGAR3, DURACION3) VALUES ("+ ultIC + "," + "\""+ lugar1_N + "\"" +"," + lug1_dura + ","+ "\""  + lugar2_N + "\"" +"," + lug1_dura2 + "," + "\"" + lugar3_N + "\"" + "," + lug1_dura3 + ");";
                            AñadirNuevo(statement3);
                        }
                        String statement4 = "INSERT INTO SIMULACION(IDENTIFICADORCIRCUITO, IDCLIENTE) VALUES ("+ ultIC + "," +empl+");";
                        AñadirNuevo(statement4);
                        JOptionPane.showMessageDialog(null, "Se ha guardado tu simulación", "Simulación correctamente guardada", JOptionPane.WARNING_MESSAGE, i2);
                        //System.out.println(ultIC);
                    } else{
                        JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión.", "Error en iniciar sesión", JOptionPane.WARNING_MESSAGE,i);
                        System.out.println(empl);
                        Simulacion si = new Simulacion();
                        si.setVisible(true);
                        this.dispose();
                    }



                /*
                    int a = JOptionPane.showOptionDialog(null, "Se ha guardado la simulación, ¿Desea reservar?", "Confirmado",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,i2,options,options[0]);
                        //Guardar la simulacion en la base de datos

                    if (a == JOptionPane.YES_OPTION) {

                        System.out.println("si");

                        //Abrir interfaz de reservaciones
                        Reservaciones reser = new Reservaciones();
                        reser.setVisible(true);
                        Simulacion sim = new Simulacion(); //Temporal
                        sim.setVisible(true);//Temporal

                    } else {

                        Simulacion sim = new Simulacion();
                        sim.setVisible(true);

                    }
                */
                    //}
                    Simulacion si = new Simulacion();
                    si.setVisible(true);
                    this.dispose();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "Es necesario que escribas un dato númerico", "ID incorrecto", JOptionPane.WARNING_MESSAGE, i);

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

            Bienvenida bien = null;
            try {
                bien = new Bienvenida();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            bien.setVisible(true);
            this.dispose();

        }
    }

    public GuardarSi() throws Exception {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(GuardarS);
        setExtendedState(NORMAL);
        setSize(319, 171);
        confirmarButton.addActionListener(this);
        crearCuentaButton.addActionListener(this);
        atras.addActionListener(this);

    }
}
