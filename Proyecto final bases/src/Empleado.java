import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Empleado extends Bienvenida{

    private JPanel Empleado;
    private JPanel Emple;
    private JButton atras;
    private JTextField Idcliente;
    private JPasswordField passwordField1;
    private JButton Confirmar;
    private Image image,image2,image3;
    private ImageIcon i,i2,i3;
    private Object[] options = {"Si","No"};

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

        String empl  = Idcliente.getText();
        String con = passwordField1.getText();

        if (e.getSource()== Confirmar){

            if(empl.isEmpty()){

                JOptionPane.showMessageDialog(null, "Ingresa tu ID", "ID incorrecto",JOptionPane.WARNING_MESSAGE,i);

            }

            else if(con.isEmpty()){

                JOptionPane.showMessageDialog(null, "Ingresa tu contraseña", "Contraseña incorrecta",JOptionPane.WARNING_MESSAGE,i);

            }

            else {

                try {
                    PreparedStatement ps;
                    ps = TransactionMySQL.conn.prepareStatement("SELECT `IDCLIENTE`, `CONTRASENA` FROM `CLIENTE` WHERE `IDCLIENTE` = ? AND `CONTRASENA` = ? AND `EMPLEADO` = 1");
                    ps.setString(1,empl);
                    ps.setString(2,con);
                    ResultSet result = ps.executeQuery();

                    if(result.next()){
                        //Abre nueva GUI de gestion de base de datos
                        Gestion ges = new Gestion();
                        ges.setVisible(true);
                        this.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión.", "Error en iniciar sesión", JOptionPane.WARNING_MESSAGE,i);
                        Bienvenida bien = new Bienvenida();
                        bien.setVisible(true);
                    }


                    this.dispose();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "Es necesario que escribas un dato númerico", "Código de empleado incorrecto", JOptionPane.WARNING_MESSAGE, i);

                }
            }
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

    public Empleado() throws Exception {
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(Emple);
        setExtendedState(NORMAL);
        setSize(340,181);
        Confirmar.addActionListener(this);
        atras.addActionListener(this);

    }
}
