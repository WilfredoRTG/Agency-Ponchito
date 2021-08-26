import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reservaciones extends GuardarSiDos{
    private JPanel panel1;
    private JButton reservarButton;
    private JButton verSimuButton;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton button1;
    private JLabel nombre;
    private JTable table2;
    private JTable table3;
    private JLabel lugar1label;
    private JLabel lugar2label;
    private JLabel lugar3label;
    private JLabel no1;
    private JLabel no2;
    private JLabel no3;
    private JLabel descuento;
    private JButton borrarSimulaciónButton;
    private JTable table4;
    private JButton verReservacionesButton;
    private Image image,image2,image3;
    private ImageIcon i,i2,i3;
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel model2 = new DefaultTableModel();
    private DefaultTableModel model3 = new DefaultTableModel();
    private DefaultTableModel model4 = new DefaultTableModel();
    private int precioNuevo,val,idCir,precio,duracion,numPer,duracion1,duracion2,duracion3,numcuar,precioDes,precioCuar,precioLugar,emp,idReser;
    private String[] circuito = new String[8];
    private String[] etapas = new String[6];
    private String[] hotel = new String[5];
    private String[] descripcion = new String[3];
    private String valores,ciudadSal,paisSal,ciudadLL,paisLL,dura,pre,fecha,numP,orden1,orden2,orden3,dur1,dur2,dur3,nombreHotel,dirH,numC,preDes,preCua,preLu;

    public void ejecutarCam(String statement){
        try {
            TransactionMySQL.stmt.executeUpdate(statement);
            TransactionMySQL.conn.commit();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void limpiarTabla(DefaultTableModel modelL){
        while (modelL.getColumnCount() != 0 && modelL.getRowCount() != 0) {
            for (int j = 0; j < modelL.getColumnCount(); j++) {
                for (int i = 0; i < modelL.getRowCount(); i++) {
                    modelL.removeRow(i);
                }
            }
        }
    }
    private void GetDescripcion(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            for (int i = 0; i<3;i++) {
                rs.next();
                descripcion[i] = rs.getString("DESCRIPCION");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void GetReserid(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()) {
                idReser = rs.getInt("CODIGORESERVACION");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void CalcularPrecio(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()) {
                precioNuevo = rs.getInt("PRECIO");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void GetHotel(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                nombreHotel = rs.getString("NOMBREHOTEL");
                dirH = rs.getString("DIRECCIONHOTEL");
                numcuar = rs.getInt("NUMCUARTOS");
                precioCuar = rs.getInt("PRECIOCUARTOS");
                precioDes = rs.getInt("PRECIODESAYUNO");
            }
            rs.close();
            numC = Integer.toString(numcuar);
            preCua = Integer.toString(precioCuar);
            preDes = Integer.toString(precioDes);
            hotel[0] = nombreHotel;
            hotel[1] = dirH;
            hotel[2] = numC;
            hotel[3] = preCua;
            hotel[4] = preDes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void GetEtapa(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                orden1 = rs.getString("ORDENLUGAR1");
                durLug = rs.getInt("DURACION1");
                orden2 = rs.getString("ORDENLUGAR2");
                durLug2 = rs.getInt("DURACION2");
                orden3 = rs.getString("ORDENLUGAR3");
                durLug3 = rs.getInt("DURACION3");
            }
            rs.close();
            dur1 = Integer.toString(durLug);
            dur2 = Integer.toString(durLug2);
            dur3 = Integer.toString(durLug3);
            etapas[0] = orden1;
            etapas[1] = dur1;
            etapas[2] = orden2;
            etapas[3] = dur2;
            etapas[4] = orden3;
            etapas[5] = dur3;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void GettIDCir(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                idCir = rs.getInt("IDENTIFICADORCIRCUITO");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void GettEmpleado(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                emp = rs.getInt("EMPLEADO");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void GettCircuito(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                ciudadSal = rs.getString("CIUDADSALIDA");
                paisSal = rs.getString("PAISSALIDA");
                ciudadLL = rs.getString("CIUDADLLEGADA");
                PaLL = rs.getString("PAISLLEGADA");
                duraTF = rs.getInt("DURACION");
                precio = rs.getInt("PRECIO");
                fecha = rs.getString("FECHASALIDA");
                numPer = rs.getInt("NUMEROPERSONAS");
            }
            rs.close();
            dura = Integer.toString(duraTF);
            pre = Integer.toString(precio);
            numP = Integer.toString(numPer);
            circuito[0] = ciudadSal;
            circuito[1] = paisSal;
            circuito[2] = ciudadLL;
            circuito[3] = PaLL;
            circuito[4] = dura;
            circuito[5] = pre;
            circuito[6] = fecha;
            circuito[7] = numP;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void modelSet(int cod){
        model.setColumnIdentifiers(new String[]{"ID","Ciudad salida","País salida","Ciudad llegada","País llegada","Duración","Precio total","Fecha salida","Num. personas"});
        model2.setColumnIdentifiers(new String[]{"Lugar 1", "Duracion","Lugar 2", "Duracion","Lugar 3","Duracion"});
        model3.setColumnIdentifiers(new String[]{"Nombre de hotel", "Dirección hotel","Num. cuartos","Precio cuarto por noche","Precio desayuno del viaje"});
        String statement = "select IDENTIFICADORCIRCUITO from SIMULACION where CODIGOSIMULACION=" + cod + ";";
        GettIDCir(statement);
        String statement2 = "SELECT CIUDADSALIDA, PAISSALIDA, CIUDADLLEGADA, PAISLLEGADA, DURACION, PRECIO, F.FECHASALIDA AS FECHASALIDA, F.NUMEROPERSONAS AS NUMEROPERSONAS FROM CIRCUITO C JOIN FECHACIRCUITO F ON C.IDENTIFICADORCIRCUITO = F.IDENTIFICADORCIRCUITO WHERE C.IDENTIFICADORCIRCUITO ="+idCir+";";
        GettCircuito(statement2);
        model.addRow(new Object[]{idCir,circuito[0],circuito[1],circuito[2],circuito[3],circuito[4],circuito[5],circuito[6],circuito[7]});
        String statement3 = "SELECT ORDENLUGAR1, DURACION1, ORDENLUGAR2, DURACION2, ORDENLUGAR3, DURACION3 from ETAPA E JOIN CIRCUITO C ON E.IDENTIFICADORCIRCUITO = C.IDENTIFICADORCIRCUITO WHERE C.IDENTIFICADORCIRCUITO =" + idCir +";";
        GetEtapa(statement3);
        model2.addRow(new Object[]{etapas[0],etapas[1],etapas[2],etapas[3],etapas[4],etapas[5]});
        String statement4 = "SELECT NOMBREHOTEL, DIRECCIONHOTEL, NUMCUARTOS, PRECIOCUARTOS, D.PRECIODESAYUNO FROM HOTEL H JOIN DESAYUNOS D ON H.CODIGODESAYUNO = D.CODIGODESAYUNO WHERE NOMBRECIUDAD ="+"\""+ciudadLL+"\""+";";
        GetHotel(statement4);
        model3.addRow(new Object[]{hotel[0],hotel[1],hotel[2],hotel[3],hotel[4]});
        String statement5 = "select DESCRIPCION,PRECIO from LUGARAVISITAR where NOMBRELUGAR="+"\""+orden1 +"\" "+ "or NOMBRELUGAR=" +"\""+orden2 +"\" "+ "or NOMBRELUGAR=" +"\""+ orden3 +"\";";
        GetDescripcion(statement5);
    }

    public void actionPerformed(ActionEvent e){
        try {
            image = ImageIO.read(new File("Error.png"));
            i = new ImageIcon(image);
            image2 = ImageIO.read(new File("Confi.png"));
            i2 = new ImageIcon(image2);
            image3 = ImageIO.read(new File("pregunta.png"));
            i3 = new ImageIcon(image3);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String statement2 = "select EMPLEADO from CLIENTE where IDCLIENTE="+id+";";
        GettEmpleado(statement2);
        //System.out.println(emp2);
        if(emp==1){
            descuento.setVisible(true);
            //System.out.println("hola");
        }
        if(e.getSource()==button1){
            Bienvenida bie = null;
            try {
                bie = new Bienvenida();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            bie.setVisible(true);
            descuento.setVisible(false);
            this.dispose();
        }
        if (e.getSource()==reservarButton){
            try {


                int valores = (int) comboBox1.getSelectedItem();
                String statement = "insert into RESERVACION(CODIGOSIMULACION, IDCLIENTE) values (" + valores + "," + id + ");";
                String statementPrecio = "select PRECIO from CIRCUITO where IDENTIFICADORCIRCUITO="+idCir+";";
                String statementID = "SELECT CODIGORESERVACION from RESERVACION order by CODIGORESERVACION DESC LIMIT 1;";
                CalcularPrecio(statementPrecio);
                TransactionMySQL.stmt.executeUpdate(statement);
                TransactionMySQL.conn.commit();
                GetReserid(statementID);
                JOptionPane.showMessageDialog(null, "Reservación confirmada, ID de reservación: " + idReser +"\nCon precio de: " + precioNuevo + " Dolares"+"\nRealiza tu pago en:\nhttps://www.paypal.com/mx/ponchitoagency", "Reservación exitosa", JOptionPane.WARNING_MESSAGE, i2);

            }
            catch (SQLException sqlException){
                JOptionPane.showMessageDialog(null, "Ya hay una reservación con ese código de simulación", "Reservación existente", JOptionPane.WARNING_MESSAGE, i);
            }
        }
        if(e.getSource()==verSimuButton){
            valores = comboBox1.getSelectedItem().toString();
            limpiarTabla(model);
            limpiarTabla(model2);
            limpiarTabla(model3);
            val = Integer.parseInt(valores);
            modelSet(val);
            no1.setText(etapas[0]);
            no2.setText(etapas[2]);
            no3.setText(etapas[4]);
            lugar1label.setText(descripcion[0]);
            lugar2label.setText(descripcion[1]);
            lugar3label.setText(descripcion[2]);
            lugar1label.setVisible(true);
            lugar2label.setVisible(true);
            lugar3label.setVisible(true);
            no1.setVisible(true);
            no2.setVisible(true);
            no3.setVisible(true);
            for(int i = 0;i<descripcion.length;i++) {
                descripcion[i]=null;
            }
        }

        if(e.getSource()==borrarSimulaciónButton){
            Simulacion si = null;

            int pregunta = JOptionPane.showConfirmDialog(null,"Confirmas que quieres borrar tu simulación, se cancelara tu reservación igualmente, en caso de tener alguna","Confirmación",JOptionPane.YES_OPTION,1,i3);

            if(pregunta==0){
                //BORRAR
                String va = comboBox1.getSelectedItem().toString();
                int val2 = Integer.parseInt(va);

                String statementCon1 = "select IDENTIFICADORCIRCUITO from SIMULACION where CODIGOSIMULACION=" + val2 + ";";
                GettIDCir(statementCon1);
                System.out.println(idCir);
                int codig = idCir;

                String statementBorro1 = "DELETE FROM RESERVACION WHERE CODIGOSIMULACION="+val2+";";
                String statementBorro2 = "DELETE FROM SIMULACION WHERE CODIGOSIMULACION="+val2+";";
                String statementBorro3 = "DELETE FROM FECHACIRCUITO WHERE IDENTIFICADORCIRCUITO="+codig+";";
                String statementBorro4 = "DELETE FROM ETAPA WHERE IDENTIFICADORCIRCUITO="+codig+";";
                String statementBorro5 = "DELETE FROM CIRCUITO WHERE IDENTIFICADORCIRCUITO="+codig+";";

                ejecutarCam(statementBorro1);
                ejecutarCam(statementBorro2);
                ejecutarCam(statementBorro3);
                ejecutarCam(statementBorro4);
                ejecutarCam(statementBorro5);


                try {
                    si = new Simulacion();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                si.setVisible(true);
                this.dispose();

            } else{
                Reservaciones re = null;
                try {
                    re = new Reservaciones();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                re.setVisible(true);
                this.dispose();
            }
        }
        if(e.getSource()==verReservacionesButton){
            limpiarTabla(model4);
            String sql = "SELECT CODIGORESERVACION, CODIGOSIMULACION FROM RESERVACION WHERE IDCLIENTE ="+ id +";";
            Statement st;
            model4.setColumnIdentifiers(new String[]{"Codigo reservación","Codigo simulación"});
            String[] dato = new String[2];
            try {
                st = TransactionMySQL.conn.createStatement();
                ResultSet result = st.executeQuery(sql);
                while (result.next()){
                    dato[0] = result.getString(1);
                    dato[1] = result.getString(2);
                    model4.addRow(dato);
                }
            } catch (SQLException sqlException){
            }
        }
    }

    public Reservaciones() throws Exception {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel1);
        setExtendedState(MAXIMIZED_BOTH);
        nombre.setText("Simulaciones de: "+nombre2 + " " + apellido);
        for(int i = 0; i<codigosSimu.length; i++){
            comboBox1.addItem(codigosSimu[i]);
        }
        table1.setModel(model);
        table2.setModel(model2);
        table3.setModel(model3);
        table4.setModel(model4);
        table1.setEnabled(false);
        table2.setEnabled(false);
        table3.setEnabled(false);
        table4.setEnabled(false);
        button1.addActionListener(this);
        verSimuButton.addActionListener(this);
        reservarButton.addActionListener(this);
        lugar1label.setVisible(false);
        lugar2label.setVisible(false);
        lugar3label.setVisible(false);
        no1.setVisible(false);
        no2.setVisible(false);
        no3.setVisible(false);
        descuento.setVisible(false);
        borrarSimulaciónButton.addActionListener(this);
        verReservacionesButton.addActionListener(this);
    }
}
