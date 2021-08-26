import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JPanel;

public class Bienvenida extends TransactionMySQL implements ActionListener{

    private JPanel Bienve;
    private JLabel NameAgen;
    private JButton StartSimu;
    private JButton CuentaButton;
    private JPanel Botones;
    private JButton folletoB;
    private JLabel Imagen1;
    private JButton adminButton;
    private JPanel Title;

    public void actionPerformed(ActionEvent e)  {
        if (e.getSource() == StartSimu) {

            Simulacion sim = null;
            try {
                sim = new Simulacion();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            sim.setVisible(true);
            this.dispose();

        }

        if (e.getSource() == CuentaButton) {

            NewAcc newa = null;
            try {
                newa = new NewAcc();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            newa.setVisible(true);
            this.dispose();

        }
        if (e.getSource() == folletoB){

            File file = new File("Folleto.jpeg");

            try {

                Desktop.getDesktop().open(file);

            } catch (IOException ioException) {

                ioException.printStackTrace();

            }
        }
        if(e.getSource()==adminButton){
            Empleado emp = null;
            try {
                emp = new Empleado();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            emp.setVisible(true);
            this.dispose();
        }
    }

    public Bienvenida() throws Exception {
        super();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(Bienve);
        setExtendedState(MAXIMIZED_BOTH);
        StartSimu.addActionListener(this);
        CuentaButton.addActionListener(this);
        folletoB.addActionListener(this);
        adminButton.addActionListener(this);

    }
}
