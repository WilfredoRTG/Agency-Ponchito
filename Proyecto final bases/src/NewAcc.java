import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JPanel;
import java.io.File;
import javax.swing.ImageIcon;
import java.sql.*;

public class NewAcc extends Bienvenida{
    private JButton return1;
    private JTextField apellidoTextField;
    private JTextField ciudadTextField;
    private JTextField nombreTextField;
    private JButton Confirm;
    private JPanel NewAcco;
    private JPanel Datos;
    private JPasswordField passwordField1;
    private JComboBox Forma;
    private JComboBox tipoPago;
    private Image image,image2,image3;
    private Icon i,i2,i3;
    private Object[] options = {"Si","No"};
    private Object[] options2 = {"Entendido"};
    private boolean TF;
    private String statement;
    private int tipago,formVia;
    private String id;




    public String consigueID(ResultSet rset) throws SQLException {

        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();

        while( rset.next() ) {

            for( int j = 1; j <= i; j++ ) {
                id = rset.getString(j);

            }

            System.out.println();

        }

        return id;

    }

    public void query(String statement) throws SQLException {

        ResultSet rset = TransactionMySQL.stmt.executeQuery( statement );
        consigueID( rset );
        rset.close();
    }
    public static boolean noNum(String cad){

        if((cad.contains("0")||cad.contains("1")||cad.contains("2"))||cad.contains("3")||cad.contains("4")||cad.contains("5")||cad.contains("6")||cad.contains("7")||cad.contains("8")||cad.contains("9")||cad.contains("!")) {

            return true;

        } else {

            return false;

        }
    }

    public void actionPerformed(ActionEvent e){

        try {

            image = ImageIO.read(new File("Confi.png"));
            i = new ImageIcon(image);
            image2 = ImageIO.read(new File("Error.png"));
            i2 = new ImageIcon(image2);
            image3 = ImageIO.read(new File("pregunta.png"));
            i3 = new ImageIcon(image3);

        } catch (IOException ex){
        }

        String nom = nombreTextField.getText();
        String ape = apellidoTextField.getText();
        String ciu = ciudadTextField.getText();
        String form = Forma.getSelectedItem().toString();
        String pass = passwordField1.getText();
        String pago = tipoPago.getSelectedItem().toString();

        try{

            Integer.parseInt(nom);
            TF = true;

        } catch (NumberFormatException NumF){

            TF = false;

        }

        if (e.getSource() == return1) {

            Bienvenida Bienve = null;
            try {
                Bienve = new Bienvenida();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            Bienve.setVisible(true);
            this.dispose();

        }
        if (e.getSource() == Confirm){

            if (nom.isEmpty() || noNum(nom)) {

                if (noNum(nom)){

                    JOptionPane.showMessageDialog(null, "No se puede poner números en tu nombre", "Nombre incorrecto", JOptionPane.WARNING_MESSAGE, i2);

                }

                if(nom.isEmpty()){

                    JOptionPane.showMessageDialog(null, "Ingresa tu nombre", "Nombre incorrecto", JOptionPane.WARNING_MESSAGE, i2);

                }
            }
            else if (ape.isEmpty() || noNum(ape)) {

                if (noNum(ape)){

                    JOptionPane.showMessageDialog(null, "No se puede poner números en tu apellido", "Apellido incorrecto", JOptionPane.WARNING_MESSAGE, i2);

                }
                if (ape.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Ingresa tu apellido", "Apellido incorrecto", JOptionPane.WARNING_MESSAGE, i2);

                }

            }
            else if (ciu.isEmpty() || noNum(ciu)){

                if (noNum(ciu)){

                    JOptionPane.showMessageDialog(null, "No se puede poner números en tu ciudad", "Ciudad incorrecta", JOptionPane.WARNING_MESSAGE, i2);
                }

                if (ciu.isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Ingresa tu ciudad", "Ciudad incorrecta", JOptionPane.WARNING_MESSAGE, i2);

                }

            }
            else if (pass.isEmpty()){

                JOptionPane.showMessageDialog(null, "Ingresa tu contraseña", "Contraseña incorrecto",JOptionPane.WARNING_MESSAGE,i2);

            }
             else {
                 //Tipo pago
                 if(pago=="Efectivo"){
                     tipago = 1;
                 }

                 if(pago=="Credito"){
                     tipago = 2;
                 }

                 if(pago=="Debito"){
                     tipago = 3;
                 }

                 //tipo de viaje
                 if(form=="Individual"){
                    formVia = 1;
                 }

                 if(form=="Empresarial"){
                     formVia = 2;
                 }
                 if(form=="Grupo") {
                     formVia = 3;
                 }

                 int a = JOptionPane.showOptionDialog(null, "¿Tus datos son correctos?" + "\n" + "Nombre: " + nom+ "\n" + "Apellido: "+ ape + "\n" + "Contraseña: " +pass, "Confirmado",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,i3,options,options[0]);

                 if (a==JOptionPane.YES_OPTION){

                     //Inserción de base de datos en cliente
                     statement = "insert into CLIENTE(NOMBRE, APELLIDO, CIUDAD, CONTRASENA, EMPLEADO, CODIGOTIPOPAGO, IDFORMATODEVIAJE) values (" + "\"" + nom + "\"" + "," + "\"" + ape + "\"" + "," + "\"" + ciu + "\"" + "," + "\"" + pass + "\"" + "," + 0 + "," +  tipago  + "," + formVia + ");";
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

                     try {
                         query("select IDCLIENTE from CLIENTE where NOMBRE=" +"\"" + nom + "\"" + ";");
                     } catch (SQLException throwables) {
                         throwables.printStackTrace();
                     }
                     JOptionPane.showOptionDialog(null,"Gracias por registrarte\n" + "Te has registrado con el ID: "+ id +"\n" + "\n<html><b>IMPORTANTE</b></html>\n" + "Guarda este ID para poder iniciar sesión después" ,"Registro",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,i,options2,options2[0]);
                     this.dispose();
                     Bienvenida bien = null;
                     try {
                         bien = new Bienvenida();
                     } catch (Exception exception) {
                         exception.printStackTrace();
                     }
                     bien.setVisible(true);

                 }
            }
        }
    }

    public NewAcc() throws Exception {
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(NewAcco);
        setSize(350, 280);
        Confirm.addActionListener(this);
        return1.addActionListener(this);

    }
}