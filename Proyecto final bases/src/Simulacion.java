import com.toedter.calendar.JDateChooser;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JPanel;
import java.sql.*;
import java.io.*;

public class Simulacion extends Bienvenida {

    private JButton atras;
    private JPanel simul;
    private JButton guardarSimulaciónButton;
    private JButton verSimulacionesButton;
    private JComboBox PaisLL;
    private JComboBox CiudLL;
    private JComboBox lugar;
    private JComboBox PaisS;
    private JComboBox CiudaS;
    private JComboBox Dura;
    private JPanel fechaSal;
    private JComboBox lugar2;
    private JComboBox lugar3;
    private JButton folletoButton;
    private JSpinner lugarD3;
    private JSpinner lugarD2;
    private JSpinner lugarD;
    private JLabel labelValor;
    private JButton precioPorDíaButton;
    private JSpinner numPersonas;
    private JDateChooser date = new JDateChooser();
    private Image image, image3, image2;
    private ImageIcon i, i3, i2;
    static String PaLL,CiuLL, lug, lug2, lug3, PaiS, CiudS, CantiP,ciu_llegada,pais_llegada,ciu_salida,pais_salida,lugar1,lugar2_N,lugar3_N,lugar1_N;
    static int precioT,precioF=precioT,año, mes, dia, durLug, durLug2, durLug3, nPer,idCircuito,duraT, duraTF=duraT,lug1_dura,lug1_dura2,lug1_dura3,precio;
    private Object[] options = {"Si", "No"};
    private SpinnerNumberModel sp = new SpinnerNumberModel(0, 0, 14, 1);
    private SpinnerNumberModel sp2 = new SpinnerNumberModel(0, 0, 14, 1);
    private SpinnerNumberModel sp3 = new SpinnerNumberModel(0, 0, 14, 1);
    private SpinnerNumberModel sp4 = new SpinnerNumberModel(1, 1, 6, 1);

    private void PrecioGetter(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                precio += rs.getInt("PRECIO");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void PrecioHotel(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                precio += rs.getInt("PRECIOCUARTOS");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void PrecioDesayuno(String statement) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(statement);
            while (rs.next()) {
                precio += rs.getInt("PRECIODESAYUNO");
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void Clean(){
        //precio = 0;
        //lug1_dura = 0;
        //lug1_dura2 = 0;
        //lug1_dura3 = 0;
        //precioT = 0;
        //duraT = 0;
        //pais_llegada = "";
    }

    public void actionPerformed(ActionEvent e) {

        try {

            image = ImageIO.read(new File("Error.png"));
            i = new ImageIcon(image);
            image3 = ImageIO.read(new File("pregunta.png"));
            i3 = new ImageIcon(image3);
            image2 = ImageIO.read(new File("dinero.png"));
            i2 = new ImageIcon(image2);

        } catch (IOException ex) {
        }

        if (e.getSource() == folletoButton) {
            File file = new File("Folleto.jpeg");

            try {

                Desktop.getDesktop().open(file);

            } catch (IOException ioException) {

                ioException.printStackTrace();

            }
        }
        //Pais llegada

        if (e.getSource() == PaisLL) {
            Clean();
            PaLL = PaisLL.getSelectedItem().toString();

            if (PaLL == "--País de llegada--") {
                try {
                    CiudLL.removeAllItems();

                } catch (NullPointerException nullPointerException) {
                }
                CiudLL.addItem("--Ciudad de llegada--");
            }
            if (PaLL == "México") {
                try {
                    CiudLL.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                CiudLL.addItem("--Ciudad de llegada--");
                CiudLL.addItem("Puebla");
                CiudLL.addItem("Monterrey");
                CiudLL.addItem("Tequila");
                labelValor.setVisible(false);
                lugarD.setVisible(false);
            }
            if (PaLL == "España") {
                try {
                    CiudLL.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                CiudLL.addItem("--Ciudad de llegada--");
                CiudLL.addItem("Madrid");
                CiudLL.addItem("Barcelona");
                CiudLL.addItem("Sevilla");
                labelValor.setVisible(false);
                lugarD.setVisible(false);
            }
            if (PaLL == "Argentina") {
                try {
                    CiudLL.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                CiudLL.addItem("--Ciudad de llegada--");
                CiudLL.addItem("Buenos aires");
                CiudLL.addItem("Mendoza");
                CiudLL.addItem("Cordoba");
                labelValor.setVisible(false);
                lugarD.setVisible(false);
            }
        }

        //Pais salida

        if (e.getSource() == PaisS) {
            PaiS = PaisS.getSelectedItem().toString();
            if (PaiS == "--País salida--") {
                try {
                    CiudaS.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                CiudaS.addItem("--Ciudad salida--");
            }
            if (PaiS == "México") {
                try {
                    CiudaS.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                CiudaS.addItem("--Ciudad salida--");
                CiudaS.addItem("Aeropuerto Internacional de la ciudad de México");
                CiudaS.addItem("Aeropuerto Internacional de Monterrey");
                CiudaS.addItem("Aeropuerto Internacional de Guadalajara");
                CiudaS.addItem("Aeropuerto Internacional de Cancún");
            }
            if (PaiS == "España") {
                try {
                    CiudaS.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                CiudaS.addItem("--Ciudad salida--");
                CiudaS.addItem("Aeropuerto Adolfo Suárez Madrid-Barajas");
                CiudaS.addItem("Aeropuerto Internacional de la Región de Murcia");
                CiudaS.addItem("Aeropuerto de Bilbao");
                CiudaS.addItem("Aeropuerto Josep Tarradellas Barcelona-El Prat");
            }
            if (PaiS == "Argentina") {
                try {
                    CiudaS.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                CiudaS.addItem("--Ciudad de salida--");
                CiudaS.addItem("Aeropuerto Internacional Ministro Pistarini");
                CiudaS.addItem("Ingeniero Aeronáutico Ambrosio L.V. Taravella aeropuerto internacional");
                CiudaS.addItem("Presidente Perón aeropuerto internacional");
                CiudaS.addItem("Aeropuerto Comodoro Rivadavia");
            }
        }

        //Ciudad de llegada

        if (e.getSource() == CiudLL) {
            CiuLL = CiudLL.getSelectedItem().toString();
            if (CiuLL == "--Ciudad de llegada--") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");

            }
            if (CiuLL == "Puebla") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("Ruta centro histórico y la catedral de la ciudad de Puebla");
                lugar.addItem("Ruta San Pedro Cholula");
                lugar.addItem("Ruta Museos de la ciudad de Puebla");
            }
            if (CiuLL == "Monterrey") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("Grutas de Garcia y Recorrido Ciudad");
                lugar.addItem("Parque Cola de caballo");
                lugar.addItem("Ruta de Museos de Monterrey");

            }
            if (CiuLL == "Tequila") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("Tequila Tour");
                lugar.addItem("Expreso Guadalajara a Tequila: Jose Cuervo");
                lugar.addItem("Ruta Museos y Ciudad");
            }
            //nuevos lugares pais españa
            if (CiuLL == "Madrid") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("Centro de la ciudad");
                lugar.addItem("Puerta de Alcala y Restaurantes");
                lugar.addItem("Ruta Futbulistica");
            }
            if (CiuLL == "Barcelona") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("Barrios de alrededor");
                lugar.addItem("La Sagrada Familia y Barrio Gotico");
                lugar.addItem("Miradores");

            }
            if (CiuLL == "Sevilla") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("Centro Historico");
                lugar.addItem("Centros culturales");
                lugar.addItem("Plaza de Espana y Museos de Bellas Artes");
            }

            //nuevos lugares pais argentina
            if (CiuLL == "Mendoza") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("Bodega Ruca Malen Experiencia Premium");
                lugar.addItem("Centro ciudad y Recorrido");
                lugar.addItem("Parque Provincial Aconcagua");
            }
            if (CiuLL == "Cordoba") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("El Champaqui");
                lugar.addItem("La Capital");
                lugar.addItem("Parque El Durazno");

            }
            if (CiuLL == "Buenos aires") {
                try {
                    lugar.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar.addItem("--Lugar 1--");
                lugar.addItem("La Reserva Ecologica");
                lugar.addItem("Palermo");
                lugar.addItem("Ruta Centro Historico");
            }
        }

        //Lugar 2 puebla

        if (e.getSource() == lugar) {
            lug = lugar.getSelectedItem().toString();
            //Puebla
            if (CiuLL == "Puebla") {
                precioPorDíaButton.setVisible(true);
                if (lug == "Ruta centro histórico y la catedral de la ciudad de Puebla") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Ruta San Pedro Cholula");
                    lugar2.addItem("Ruta Museos de la ciudad de Puebla");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "Ruta San Pedro Cholula") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Ruta centro histórico y la catedral de la ciudad de Puebla");
                    lugar2.addItem("Ruta Museos de la ciudad de Puebla");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Ruta Museos de la ciudad de Puebla") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Ruta centro histórico y la catedral de la ciudad de Puebla");
                    lugar2.addItem("Ruta San Pedro Cholula");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }
            //Monterrey
            if (CiuLL == "Monterrey") {
                precioPorDíaButton.setVisible(true);
                if (lug == "Grutas de Garcia y Recorrido Ciudad") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Parque Cola de caballo");
                    lugar2.addItem("Ruta de Museos de Monterrey");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "Parque Cola de caballo") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Grutas de Garcia y Recorrido Ciudad");
                    lugar2.addItem("Ruta de Museos de Monterrey");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Ruta de Museos de Monterrey") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Grutas de Garcia y Recorrido Ciudad");
                    lugar2.addItem("Parque Cola de caballo");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }
            //Tequila
            if (CiuLL == "Tequila") {
                precioPorDíaButton.setVisible(true);
                if (lug == "Tequila Tour") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Expreso Guadalajara a Tequila: Jose Cuervo");
                    lugar2.addItem("Ruta Museos y Ciudad");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "Expreso Guadalajara a Tequila: Jose Cuervo") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Tequila Tour");
                    lugar2.addItem("Ruta Museos y Ciudad");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Ruta Museos y Ciudad") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Tequila Tour");
                    lugar2.addItem("Expreso Guadalajara a Tequila: Jose Cuervo");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }
            //Madrid
            if (CiuLL == "Madrid") {
                precioPorDíaButton.setVisible(true);
                if (lug == "Centro de la ciudad") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Puerta de Alcala y Restaurantes");
                    lugar2.addItem("Ruta Futbulistica");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "Puerta de Alcala y Restaurantes") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Centro de la ciudad");
                    lugar2.addItem("Ruta Futbulistica");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Ruta Futbulistica") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Centro de la ciudad");
                    lugar2.addItem("Puerta de Alcala y Restaurantes");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }
            //Barcelona
            if (CiuLL == "Barcelona") {
                precioPorDíaButton.setVisible(true);
                if (lug == "Barrios de alrededor") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("La Sagrada Familia y Barrio Gotico");
                    lugar2.addItem("Miradores");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "La Sagrada Familia y Barrio Gotico") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Barrios de alrededor");
                    lugar2.addItem("Miradores");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Miradores") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Barrios de alrededor");
                    lugar2.addItem("La Sagrada Familia y Barrio Gotico");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }
            //Sevilla
            if (CiuLL == "Sevilla") {
                precioPorDíaButton.setVisible(true);
                if (lug == "Centro Historico") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Centros culturales");
                    lugar2.addItem("Plaza de Espana y Museos de Bellas Artes");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "Centros culturales") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Centro Historico");
                    lugar2.addItem("Plaza de Espana y Museos de Bellas Artes");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Plaza de Espana y Museos de Bellas Artes") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Centro Historico");
                    lugar2.addItem("Centros culturales");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }

            //Mendoza
            if (CiuLL == "Mendoza") {
                precioPorDíaButton.setVisible(true);
                if (lug == "Bodega Ruca Malen Experiencia Premium") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Centro ciudad y Recorrido");
                    lugar2.addItem("Parque Provincial Aconcagua");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "Centro ciudad y Recorrido") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Bodega Ruca Malen Experiencia Premium");
                    lugar2.addItem("Parque Provincial Aconcagua");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Parque Provincial Aconcagua") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Bodega Ruca Malen Experiencia Premium");
                    lugar2.addItem("Centro ciudad y Recorrido");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }
            //Cordoba
            if (CiuLL == "Cordoba") {
                precioPorDíaButton.setVisible(true);
                if (lug == "El Champaqui") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("La Capital");
                    lugar2.addItem("Parque El Durazno");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "La Capital") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("El Champaqui");
                    lugar2.addItem("Parque El Durazno");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Parque El Durazno") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("El Champaqui");
                    lugar2.addItem("La Capital");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }
            //Buenos aires
            if (CiuLL == "Buenos aires") {
                precioPorDíaButton.setVisible(true);
                if (lug == "La Reserva Ecologica") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("Palermo");
                    lugar2.addItem("Ruta Centro Historico");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
                if (lug == "Palermo") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("La Reserva Ecologica");
                    lugar2.addItem("Ruta Centro Historico");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugar3.setVisible(false);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                }
                if (lug == "Ruta Centro Historico") {
                    try {
                        lugar2.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar2.addItem("--Lugar 2--");
                    lugar2.addItem("La Reserva Ecologica");
                    lugar2.addItem("Palermo");
                    labelValor.setVisible(true);
                    lugar2.setVisible(true);
                    lugarD.setVisible(true);
                    lugarD2.setVisible(false);
                    lugarD3.setVisible(false);
                    lugar3.setVisible(false);
                }
            }
            if (lug == "--Lugar 1--") {
                try {
                    lugar2.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugarD.setValue(0);
                lugarD2.setValue(0);
                lugarD3.setValue(0);
                lugar2.setVisible(false);
                lugarD2.setVisible(false);
                lugarD3.setVisible(false);
                lugar3.setVisible(false);
                labelValor.setVisible(false);
                lugarD.setVisible(false);
                precioPorDíaButton.setVisible(false);
            }

        }

        //Lugar 3 puebla

        if (e.getSource() == lugar2) {
            lug2 = lugar2.getSelectedItem().toString();
            lug = lugar.getSelectedItem().toString();
            //Puebla
            if (CiuLL == "Puebla") {
                if (lug2 == "Ruta centro histórico y la catedral de la ciudad de Puebla") {
                    try {
                        lugarD3.setValue(0);
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Ruta San Pedro Cholula");
                    lugar3.addItem("Ruta Museos de la ciudad de Puebla");
                    lugar3.removeItem(lug);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
                if (lug2 == "Ruta San Pedro Cholula") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Ruta centro histórico y la catedral de la ciudad de Puebla");
                    lugar3.addItem("Ruta Museos de la ciudad de Puebla");
                    lugar3.removeItem(lug);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
                if (lug2 == "Ruta Museos de la ciudad de Puebla") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Ruta centro histórico y la catedral de la ciudad de Puebla");
                    lugar3.addItem("Ruta San Pedro Cholula");
                    lugar3.removeItem(lug);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            //Monterrey
            if (CiuLL == "Monterrey") {
                if (lug2 == "Grutas de Garcia y Recorrido Ciudad") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Grutas de Garcia y Recorrido Ciudad");
                    lugar3.addItem("Parque Cola de caballo");
                    lugar3.addItem("Ruta de Museos de Monterrey");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Parque Cola de caballo") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Grutas de Garcia y Recorrido Ciudad");
                    lugar3.addItem("Parque Cola de caballo");
                    lugar3.addItem("Ruta de Museos de Monterrey");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Ruta de Museos de Monterrey") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Grutas de Garcia y Recorrido Ciudad");
                    lugar3.addItem("Parque Cola de caballo");
                    lugar3.addItem("Ruta de Museos de Monterrey");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            //Tequila
            if (CiuLL == "Tequila") {
                if (lug2 == "Tequila Tour") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Tequila Tour");
                    lugar3.addItem("Expreso Guadalajara a Tequila: Jose Cuervo");
                    lugar3.addItem("Ruta Museos y Ciudad");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Expreso Guadalajara a Tequila: Jose Cuervo") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Tequila Tour");
                    lugar3.addItem("Expreso Guadalajara a Tequila: Jose Cuervo");
                    lugar3.addItem("Ruta Museos y Ciudad");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Ruta Museos y Ciudad") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Tequila Tour");
                    lugar3.addItem("Expreso Guadalajara a Tequila: Jose Cuervo");
                    lugar3.addItem("Ruta Museos y Ciudad");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            //Madrid
            if (CiuLL == "Madrid") {
                if (lug2 == "Centro de la ciudad") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Centro de la ciudad");
                    lugar3.addItem("Puerta de Alcala y Restaurantes");
                    lugar3.addItem("Ruta Futbulistica");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Puerta de Alcala y Restaurantes") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Centro de la ciudad");
                    lugar3.addItem("Puerta de Alcala y Restaurantes");
                    lugar3.addItem("Ruta Futbulistica");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Ruta Futbulistica") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Centro de la ciudad");
                    lugar3.addItem("Puerta de Alcala y Restaurantes");
                    lugar3.addItem("Ruta Futbulistica");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            //Barcelona
            if (CiuLL == "Barcelona") {
                if (lug2 == "Barrios de alrededor") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Barrios de alrededor");
                    lugar3.addItem("La Sagrada Familia y Barrio Gotico");
                    lugar3.addItem("Miradores");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "La Sagrada Familia y Barrio Gotico") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Barrios de alrededor");
                    lugar3.addItem("La Sagrada Familia y Barrio Gotico");
                    lugar3.addItem("Miradores");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Miradores") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Barrios de alrededor");
                    lugar3.addItem("La Sagrada Familia y Barrio Gotico");
                    lugar3.addItem("Miradores");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            //Sevilla
            if (CiuLL == "Sevilla") {
                if (lug2 == "Centro Historico") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Centro Historico");
                    lugar3.addItem("Centros culturales");
                    lugar3.addItem("Plaza de Espana y Museos de Bellas Artes");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Centros culturales") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Centro Historico");
                    lugar3.addItem("Centros culturales");
                    lugar3.addItem("Plaza de Espana y Museos de Bellas Artes");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Plaza de Espana y Museos de Bellas Artes") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Centro Historico");
                    lugar3.addItem("Centros culturales");
                    lugar3.addItem("Plaza de Espana y Museos de Bellas Artes");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            //Mendoza
            if (CiuLL == "Mendoza") {
                if (lug2 == "Bodega Ruca Malen Experiencia Premium") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Bodega Ruca Malen Experiencia Premium");
                    lugar3.addItem("Centro ciudad y Recorrido");
                    lugar3.addItem("Parque Provincial Aconcagua");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Centro ciudad y Recorrido") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Bodega Ruca Malen Experiencia Premium");
                    lugar3.addItem("Centro ciudad y Recorrido");
                    lugar3.addItem("Parque Provincial Aconcagua");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Parque Provincial Aconcagua") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("Bodega Ruca Malen Experiencia Premium");
                    lugar3.addItem("Centro ciudad y Recorrido");
                    lugar3.addItem("Parque Provincial Aconcagua");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            //Cordoba
            if (CiuLL == "Cordoba") {
                if (lug2 == "El Champaqui") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("El Champaqui");
                    lugar3.addItem("La Capital");
                    lugar3.addItem("Parque El Durazno");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "La Capital") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("El Champaqui");
                    lugar3.addItem("La Capital");
                    lugar3.addItem("Parque El Durazno");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Parque El Durazno") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("El Champaqui");
                    lugar3.addItem("La Capital");
                    lugar3.addItem("Parque El Durazno");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            //Buenos aires
            if (CiuLL == "Buenos aires") {
                if (lug2 == "La Reserva Ecologica") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("La Reserva Ecologica");
                    lugar3.addItem("Palermo");
                    lugar3.addItem("Ruta Centro Historico");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Palermo") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("La Reserva Ecologica");
                    lugar3.addItem("Palermo");
                    lugar3.addItem("Ruta Centro Historico");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);

                }
                if (lug2 == "Ruta Centro Historico") {
                    try {
                        lugar3.removeAllItems();
                    } catch (NullPointerException nullPointerException) {
                    }
                    lugar3.addItem("--Lugar 3--");
                    lugar3.addItem("La Reserva Ecologica");
                    lugar3.addItem("Palermo");
                    lugar3.addItem("Ruta Centro Historico");
                    lugar3.removeItem(lug);
                    lugar3.removeItem(lug2);
                    lugar3.setVisible(true);
                    lugarD2.setVisible(true);
                    lugarD3.setVisible(false);
                }
            }
            if (lug2 == "--Lugar 2--") {
                try {
                    lugarD3.setValue(0);
                    lugar3.removeAllItems();
                    lugar3.addItem("--Lugar 3--");
                } catch (NullPointerException nullPointerException) {
                }
                lugarD2.setValue(0);
                lugarD3.setValue(0);
                lugar3.setVisible(false);
                lugarD2.setVisible(false);
                lugar2.setVisible(false);
                lugarD2.setVisible(false);
            }
        }

        if (e.getSource() == lugar3) {
            lug3 = lugar3.getSelectedItem().toString();
            lugarD3.setVisible(true);
            if (lug3 == "--Lugar 3--") {
                try {
                    lugar3.removeAllItems();
                } catch (NullPointerException nullPointerException) {
                }
                lugar3.addItem("--Lugar 3--");
                lugarD3.setValue(0);
                lugar3.setVisible(false);
                lugarD3.setVisible(false);
            }
        }
        //Botón de suma
        if (e.getSource() == precioPorDíaButton) {
            guardarSimulaciónButton.setVisible(true);
            try {
                try {
                    lug2 = lugar2.getSelectedItem().toString();
                } catch (NullPointerException nullPointerException) {
                    lug2 = null;
                }
                try {
                    lug3 = lugar3.getSelectedItem().toString();
                } catch (NullPointerException nullPointerException) {
                    lug3 = null;
                }
                if (lug2 == "--Lugar 2--") {
                    lug2 = null;
                }
                if (lug3 == "--Lugar 3--") {
                    lug3 = null;
                }

                CiudS = CiudaS.getSelectedItem().toString();
                PaiS = PaisS.getSelectedItem().toString();
                durLug = (Integer) lugarD.getValue();
                durLug2 = (Integer) lugarD2.getValue();
                durLug3 = (Integer) lugarD3.getValue();
                nPer = (Integer) numPersonas.getValue();
                ciu_llegada = CiuLL.toUpperCase();
                pais_llegada = PaLL.toUpperCase();
                ciu_salida = CiudS.toUpperCase();
                pais_salida = PaiS.toUpperCase();
                lugar1_N = lug;
                lug1_dura = durLug;
                lugar2_N = lug2;
                lug1_dura2 = durLug2;
                lugar3_N = lug3;
                lug1_dura3 = durLug3;


                duraT = lug1_dura + lug1_dura2 + lug1_dura3;

                String statement = "select " + "\"" + lug1_dura + "\"" + "*" + "PRECIO as PRECIO from LUGARAVISITAR where NOMBRELUGAR=" + "\"" + lugar1_N + "\";";
                String statement2 = "select " + "\"" + lug1_dura2 + "\"" + "*" + "PRECIO as PRECIO from LUGARAVISITAR where NOMBRELUGAR=" + "\"" + lugar2_N + "\";";
                String statement3 = "select " + "\"" + lug1_dura3 + "\"" + "*" + "PRECIO as PRECIO from LUGARAVISITAR where NOMBRELUGAR=" + "\"" + lugar3_N + "\";";
                String statement4 = "select " + "\"" + duraT + "\"" + "*" + "PRECIOCUARTOS as PRECIOCUARTOS from HOTEL where NOMBRECIUDAD=" + "\"" + ciu_llegada + "\";";
                String statement5 = "select PRECIODESAYUNO from DESAYUNOS D join HOTEL H on D.CODIGODESAYUNO= H.CODIGODESAYUNO where H.NOMBRECIUDAD =" + "\"" + ciu_llegada + "\";";
                String statement6 = "select PRECIOCUARTOS from HOTEL where NOMBRECIUDAD=" + "\"" + ciu_llegada + "\";";


                //String statement6 = "select ";
                PrecioGetter(statement); //precio lugar
                PrecioGetter(statement2); //Precio lugar
                PrecioGetter(statement3); //Precio lugar
                PrecioHotel(statement4); //Precio hotel
                PrecioDesayuno(statement5); //Precio desayuno
                //CodigoGetter(statement6); //Codigo circuito

                if(precio==99 || precio==199) {
                    PrecioHotel(statement6);
                }
                precioT = precio * nPer;
                precioF = precioT;
                JOptionPane.showMessageDialog(null, "Precio total de: " + precioT + "\n" + "Con una estancia de: " + duraT + " días\n"+ "Por "+ nPer + " personas", "Precio", JOptionPane.WARNING_MESSAGE, i2);

                Clean();
            }
            catch (NullPointerException nullPointerException) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un país de llegada", "País erroneo", JOptionPane.WARNING_MESSAGE, i);
            }
        }

        //fin de combo box
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

        if (e.getSource()==verSimulacionesButton) {

            // Carga las cosas de la bases de datos
            GuardarSiDos guar = null;
            try {
                guar = new GuardarSiDos();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            guar.setVisible(true);
            this.dispose();
        }

        if (e.getSource()==guardarSimulaciónButton) {
            try {
                lug = lugar.getSelectedItem().toString();
                try {
                    lug2 = lugar2.getSelectedItem().toString();
                } catch (NullPointerException nullPointerException) {
                    lug2 = null;
                }
                try {
                    lug3 = lugar3.getSelectedItem().toString();
                } catch (NullPointerException nullPointerException) {
                    lug3 = null;
                }
                if (lug2 == "--Lugar 2--") {
                    lug2 = null;
                }
                if (lug3 == "--Lugar 3--") {
                    lug3 = null;
                }
                PaiS = PaisS.getSelectedItem().toString();
                CiudS = CiudaS.getSelectedItem().toString();
                año = date.getCalendar().get(Calendar.YEAR);
                mes = date.getCalendar().get(Calendar.MONTH) + 1;
                dia = date.getCalendar().get(Calendar.DAY_OF_MONTH);
                durLug = (Integer) lugarD.getValue();
                durLug2 = (Integer) lugarD2.getValue();
                durLug3 = (Integer) lugarD3.getValue();
                System.out.println(año+"-"+mes+"-"+dia);


                if (PaLL == "--País de llegada--") {
                    JOptionPane.showMessageDialog(null, "Debes ingresar un país de llegada", "País erroneo", JOptionPane.WARNING_MESSAGE, i);
                }
                else if (CiuLL == "--Ciudad de llegada--") {
                    JOptionPane.showMessageDialog(null, "Debes ingresar una ciudad de llegada", "Ciudad erronea", JOptionPane.WARNING_MESSAGE, i);
                }
                else if (lug == "--Lugar 1--") {
                    JOptionPane.showMessageDialog(null, "Debes ingresar un lugar", "Lugar erroneo", JOptionPane.WARNING_MESSAGE, i);
                }
                else if (año <= 2021 && mes <= 4) {
                    JOptionPane.showMessageDialog(null, "No puedes reservar fechas pasadas", "Fecha erronea", JOptionPane.WARNING_MESSAGE, i);
                }
                else if (PaiS == "--País salida--") {
                    JOptionPane.showMessageDialog(null, "Debes ingresar un país de salida", "País salida erroneo", JOptionPane.WARNING_MESSAGE, i);
                }
                else if (CiudS == "--Ciudad salida--") {
                    JOptionPane.showMessageDialog(null, "Debes ingresar una ciudad de salida", "Ciudad salida erronea", JOptionPane.WARNING_MESSAGE, i);
                }
                else if (durLug == 0) {
                    JOptionPane.showMessageDialog(null, "Debes ingresar una duración en lugar 1", "Duración erronea", JOptionPane.WARNING_MESSAGE, i);
                }
                else if (lug2 != null && durLug2 == 0) {
                    JOptionPane.showMessageDialog(null, "Debes ingresar una duración en lugar 2", "Duración erronea", JOptionPane.WARNING_MESSAGE, i);
                }
                else if (lug3 != null && durLug3 == 0) {
                    JOptionPane.showMessageDialog(null, "Debes ingresar una duración en lugar 3", "Duración erronea", JOptionPane.WARNING_MESSAGE, i);
                }
                else {
                    durLug = (Integer) lugarD.getValue();
                    durLug2 = (Integer) lugarD2.getValue();
                    durLug3 = (Integer) lugarD3.getValue();
                    nPer = (Integer) numPersonas.getValue();
                    ciu_llegada = CiuLL.toUpperCase();
                    pais_llegada = PaLL.toUpperCase();
                    ciu_salida = CiudS.toUpperCase();
                    pais_salida = PaiS.toUpperCase();
                    lugar1_N = lug;
                    lug1_dura = durLug;
                    lugar2_N = lug2;
                    lug1_dura2 = durLug2;
                    lugar3_N = lug3;
                    lug1_dura3 = durLug3;

                    duraT = lug1_dura + lug1_dura2 + lug1_dura3;

                    String statement = "select " + "\"" + lug1_dura + "\"" + "*" + "PRECIO as PRECIO from LUGARAVISITAR where NOMBRELUGAR=" + "\"" + lugar1 + "\";";
                    String statement2 = "select " + "\"" + lug1_dura2 + "\"" + "*" + "PRECIO as PRECIO from LUGARAVISITAR where NOMBRELUGAR=" + "\"" + lugar2 + "\";";
                    String statement3 = "select " + "\"" + lug1_dura3 + "\"" + "*" + "PRECIO as PRECIO from LUGARAVISITAR where NOMBRELUGAR=" + "\"" + lugar3 + "\";";
                    String statement4 = "select " + "\"" + duraT + "\"" + "*" + "PRECIOCUARTOS as PRECIOCUARTOS from HOTEL where NOMBRECIUDAD=" + "\"" + ciu_llegada + "\";";
                    String statement5 = "select PRECIODESAYUNO from DESAYUNOS D join HOTEL H on D.CODIGODESAYUNO= H.CODIGODESAYUNO where H.NOMBRECIUDAD =" + "\"" + ciu_llegada + "\";";

                    PrecioGetter(statement); //precio lugar
                    PrecioGetter(statement2); //Precio lugar
                    PrecioGetter(statement3); //Precio lugar
                    PrecioHotel(statement4); //Precio hotel
                    PrecioDesayuno(statement5); //Precio desayuno
//                    String añadirCircuito = "Insert into CIRCUITO(CIUDADSALIDA, PAISSALIDA, CIUDADLLEGADA, PAISLLEGADA, DURACION, PRECIO) values (" + "\"" + ciu_salida + "\"" + "," + "\"" + pais_salida + "\"" + "," + "\"" + ciu_llegada + "\"" + "," + "\"" + pais_llegada + "\""+ "," + "\"" + duraT + "\""+ "," + "\"" + precio + "\"" + ");";
                    //AñadirCircuito(añadirCircuito);
                    //CodigoGetter(statement6); //Codigo circuito

                    GuardarSi gua = new GuardarSi();
                    gua.setVisible(true);
                    this.dispose();
                }
            } catch (NullPointerException nul) {

                JOptionPane.showMessageDialog(null, "Ingrese una fecha de salida", "Fecha erronea", JOptionPane.WARNING_MESSAGE, i);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public Simulacion() throws Exception {
        super();
        Clean();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        add(simul);
        atras.addActionListener(this);
        fechaSal.add(date);
        verSimulacionesButton.addActionListener(this);
        guardarSimulaciónButton.addActionListener(this);
        PaisLL.addActionListener(this);
        PaisS.addActionListener(this);
        CiudLL.addActionListener(this);
        CiudaS.addActionListener(this);
        lugar.addActionListener(this);
        lugar2.addActionListener(this);
        lugar3.addActionListener(this);
        folletoButton.addActionListener(this);
        lugar2.setVisible(false);
        lugar3.setVisible(false);
        lugarD.setVisible(false);
        lugarD2.setVisible(false);
        lugarD3.setVisible(false);
        guardarSimulaciónButton.setVisible(false);
        precioPorDíaButton.setVisible(false);
        labelValor.setVisible(false);
        lugarD.setModel(sp);
        lugarD2.setModel(sp2);
        lugarD3.setModel(sp3);
        numPersonas.setModel(sp4);
        precioPorDíaButton.addActionListener(this);
    }
}