import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.table.*;

public class Gestion extends Empleado{
    private JPanel PanelPr;
    private JTable table1;
    private JButton clienteButton;
    private JButton circuitoButton;
    private JButton reservacionesButton;
    private JButton simulaciónButton;
    private JButton actualizarButton;
    private JButton atras;
    private JButton ciudadesButton;
    private JButton etapaButton;
    private JButton hotelButton;
    private JButton lugarAVisitarButton;
    private JButton fechaCircuitoButton;
    private JButton formatoViajeButton;
    private JButton tipoPagoButton;
    private JButton desayunosButton;
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model2 = new DefaultTableModel();
    private DefaultTableModel model3 = new DefaultTableModel();
    private DefaultTableModel model4 = new DefaultTableModel();
    private DefaultTableModel model5 = new DefaultTableModel();
    private DefaultTableModel model6 = new DefaultTableModel();
    private DefaultTableModel model7 = new DefaultTableModel();
    private DefaultTableModel model8 = new DefaultTableModel();
    private DefaultTableModel model9 = new DefaultTableModel();
    private DefaultTableModel model10 = new DefaultTableModel();
    private DefaultTableModel model11 = new DefaultTableModel();
    private DefaultTableModel model12 = new DefaultTableModel();
    private int co = 0, op = 0;

    public void limpiarTabla(DefaultTableModel modelL){
        while (modelL.getColumnCount() != 0 && modelL.getRowCount() != 0) {
            for (int j = 0; j < modelL.getColumnCount(); j++) {
                for (int i = 0; i < modelL.getRowCount(); i++) {
                    modelL.removeRow(i);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==atras){
            Bienvenida bien = null;
            try {
                bien = new Bienvenida();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            bien.setVisible(true);
            this.dispose();
        }

        if(e.getSource()==clienteButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);
            JTableHeader header = table1.getTableHeader();
            table1.setShowGrid(true);
            table1.setShowVerticalLines(true);
            table1.setShowHorizontalLines(true);
            header.setBackground(Color.CYAN);
            table1.setGridColor(Color.BLACK);
            String sql = "SELECT * FROM CLIENTE;";
            Statement st;
            model.setColumnIdentifiers(new String[]{"ID", "Nombre","Apellido", "Ciudad","Contraseña","Empleado","Cod.P.","Form.V."});
            table1.setEnabled(false);
            table1.setModel(model);
            String[] dato = new String[8];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    dato[2] = result.getString(3);
                    dato[3] = result.getString(4);
                    dato[4] = result.getString(5);
                    dato[5] = result.getString(6);
                    dato[6] = result.getString(7);
                    dato[7] = result.getString(8);
                    model.addRow(dato);

                }
            } catch (SQLException sqlException){
                //e.printStackTrace();
            }
        }

        if(e.getSource()==circuitoButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);
            JTableHeader header = table1.getTableHeader();
            table1.setShowGrid(true);
            table1.setShowVerticalLines(true);
            table1.setShowHorizontalLines(true);
            header.setBackground(Color.CYAN);
            table1.setGridColor(Color.BLACK);
            String sql = "SELECT * FROM CIRCUITO;";
            Statement st;
            model2.setColumnIdentifiers(new String[]{"ID", "Ciudad salida","País salida", "Ciudad llegada","País llegada","Duración","Precio"});
            table1.setEnabled(false);
            table1.setModel(model2);
            String[] dato = new String[7];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    dato[2] = result.getString(3);
                    dato[3] = result.getString(4);
                    dato[4] = result.getString(5);
                    dato[5] = result.getString(6);
                    dato[6] = result.getString(7);
                    model2.addRow(dato);
                }
            } catch (SQLException sqlException){
                //e.printStackTrace();
            }
        }

        if(e.getSource()==simulaciónButton) {
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);
            JTableHeader header = table1.getTableHeader();
            table1.setShowGrid(true);
            table1.setShowVerticalLines(true);
            table1.setShowHorizontalLines(true);
            header.setBackground(Color.CYAN);
            table1.setGridColor(Color.BLACK);

            String sql = "SELECT * FROM SIMULACION;";
            Statement st;
            model3.setColumnIdentifiers(new String[]{"ID", "ID circuito","ID cliente"});
            table1.setEnabled(false);
            table1.setModel(model3);
            String[] dato = new String[3];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    dato[2] = result.getString(3);
                    model3.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==reservacionesButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            JTableHeader header = table1.getTableHeader();
            table1.setShowGrid(true);
            table1.setShowVerticalLines(true);
            table1.setShowHorizontalLines(true);
            header.setBackground(Color.CYAN);
            table1.setGridColor(Color.BLACK);

            String sql = "SELECT * FROM RESERVACION;";
            Statement st;
            model4.setColumnIdentifiers(new String[]{"ID", "ID simulación","ID cliente"});
            table1.setEnabled(false);
            table1.setModel(model4);
            String[] dato = new String[3];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    dato[2] = result.getString(3);
                    model4.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==ciudadesButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);
            JTableHeader header = table1.getTableHeader();
            table1.setShowGrid(true);
            table1.setShowVerticalLines(true);
            table1.setShowHorizontalLines(true);
            header.setBackground(Color.CYAN);
            table1.setGridColor(Color.BLACK);

            String sql = "SELECT * FROM CIUDADES ORDER BY PAIS;";
            Statement st;
            model5.setColumnIdentifiers(new String[]{"Nombre ciudad", "País"});
            table1.setEnabled(false);
            table1.setModel(model5);
            String[] dato = new String[2];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    model5.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==etapaButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);
            JTableHeader header = table1.getTableHeader();
            table1.setShowGrid(true);
            table1.setShowVerticalLines(true);
            table1.setShowHorizontalLines(true);
            header.setBackground(Color.CYAN);
            table1.setGridColor(Color.BLACK);

            String sql = "SELECT * FROM ETAPA;";
            Statement st;
            model6.setColumnIdentifiers(new String[]{"ID", "ID circuito","Orden lugar","Duración","Orden lugar 2","Duración","Orden lugar 3","Duración"});
            table1.setEnabled(false);
            table1.setModel(model6);
            String[] dato = new String[8];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    dato[2] = result.getString(3);
                    dato[3] = result.getString(4);
                    dato[4] = result.getString(5);
                    dato[5] = result.getString(6);
                    dato[6] = result.getString(7);
                    dato[7] = result.getString(8);
                    model6.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==hotelButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);
            JTableHeader header = table1.getTableHeader();
            table1.setShowGrid(true);
            table1.setShowVerticalLines(true);
            table1.setShowHorizontalLines(true);
            header.setBackground(Color.CYAN);
            table1.setGridColor(Color.BLACK);

            String sql = "SELECT * FROM HOTEL;";
            Statement st;
            model7.setColumnIdentifiers(new String[]{"Nombre hotel", "Dirección hotel","Número de cuartos","Precio cuartos","Codigo desayuno","Nombre ciudad","País"});
            table1.setEnabled(false);
            table1.setModel(model7);
            String[] dato = new String[7];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    dato[2] = result.getString(3);
                    dato[3] = result.getString(4);
                    dato[4] = result.getString(5);
                    dato[5] = result.getString(6);
                    dato[6] = result.getString(7);
                    model7.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==lugarAVisitarButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);

            String sql = "SELECT * FROM LUGARAVISITAR;";
            Statement st;
            model8.setColumnIdentifiers(new String[]{"Nombre lugar", "Dirección lugar","Descripción","Precio","Nombre ciudad","País"});
            table1.setEnabled(false);
            table1.setModel(model8);
            String[] dato = new String[6];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    dato[2] = result.getString(3);
                    dato[3] = result.getString(4);
                    dato[4] = result.getString(5);
                    dato[5] = result.getString(6);
                    model8.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==fechaCircuitoButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);

            String sql = "SELECT * FROM FECHACIRCUITO;";
            Statement st;
            model9.setColumnIdentifiers(new String[]{"ID","Fecha salida", "Número de personas","ID circuito"});
            table1.setEnabled(false);
            table1.setModel(model9);
            String[] dato = new String[4];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    dato[2] = result.getString(3);
                    dato[3] = result.getString(4);
                    model9.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==formatoViajeButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);

            String sql = "SELECT * FROM FORMATOVIAJE;";
            Statement st;
            model10.setColumnIdentifiers(new String[]{"ID","Tipo de viaje"});
            table1.setEnabled(false);
            table1.setModel(model10);
            String[] dato = new String[2];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    model10.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==tipoPagoButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);

            String sql = "SELECT * FROM TIPOPAGO;";
            Statement st;
            model11.setColumnIdentifiers(new String[]{"Codigo tipo pago","Pago"});
            table1.setEnabled(false);
            table1.setModel(model11);
            String[] dato = new String[2];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    model11.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==desayunosButton){
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            limpiarTabla(model4);
            limpiarTabla(model5);
            limpiarTabla(model6);
            limpiarTabla(model7);
            limpiarTabla(model8);
            limpiarTabla(model9);
            limpiarTabla(model10);
            limpiarTabla(model11);
            limpiarTabla(model12);
            String sql = "SELECT * FROM DESAYUNOS;";
            Statement st;
            model12.setColumnIdentifiers(new String[]{"Codigo desayuno","Precio desayuno"});
            table1.setEnabled(false);
            table1.setModel(model12);
            String[] dato = new String[2];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    model12.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }

        if(e.getSource()==actualizarButton){
            CambiaCliente cam = null;
            try {
                cam = new CambiaCliente();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            cam.setVisible(true);
        }

    }

    public Gestion() throws Exception {
        super();
        add(PanelPr);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        atras.addActionListener(this);
        circuitoButton.addActionListener(this);
        simulaciónButton.addActionListener(this);
        clienteButton.addActionListener(this);
        reservacionesButton.addActionListener(this);
        actualizarButton.addActionListener(this);
        ciudadesButton.addActionListener(this);
        fechaCircuitoButton.addActionListener(this);
        hotelButton.addActionListener(this);
        tipoPagoButton.addActionListener(this);
        etapaButton.addActionListener(this);
        formatoViajeButton.addActionListener(this);
        lugarAVisitarButton.addActionListener(this);
        desayunosButton.addActionListener(this);

    }
}
