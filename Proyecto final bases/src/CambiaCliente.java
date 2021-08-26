import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class CambiaCliente extends Gestion implements ActionListener {

    private JPanel PanelPr;
    private JSpinner DatoTF;
    private JTextField Idcliente;
    private JButton Confirmar;
    private Image image;
    private ImageIcon i;
    private SpinnerNumberModel sp = new SpinnerNumberModel(0, 0, 1, 1);


    public void actionPerformed(ActionEvent e){

        try {
            image = ImageIO.read(new File("Error.png"));
            i = new ImageIcon(image);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        if(e.getSource()==Confirmar){
            String idC = Idcliente.getText();
            int idC2 = Integer.parseInt(idC);
            int dat = (Integer) DatoTF.getValue();
            try {
                String statement = "update CLIENTE set EMPLEADO=" + dat + " where IDCLIENTE=" + idC2 + ";";
                TransactionMySQL.stmt.executeUpdate(statement);
                TransactionMySQL.conn.commit();

            } catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "No se encuetra en la base de datos", "Error",JOptionPane.WARNING_MESSAGE,i);
            }
            this.dispose();
        }
    }

    public CambiaCliente() throws Exception {
        super();
        setExtendedState(NORMAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 171);
        add(PanelPr);
        Confirmar.addActionListener(this);
        DatoTF.setModel(sp);
    }
}
