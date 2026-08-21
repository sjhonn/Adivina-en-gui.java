import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JuegoAdivinanzaGUI extends JFrame {
    private int numeroSecreto;
    private int contadorIntentos;

    private JTextField txtIntento;
    private JLabel lblMensaje;
    private JLabel lblIntentos;
    private JButton btnAdivinar;
    private JButton btnReiniciar;

    public JuegoAdivinanzaGUI() {
        setTitle("Adivina el Número (0 - 100)");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        inicializarJuego();

        JLabel lblInstrucciones = new JLabel("Adivina el número entre 0 y 100:", SwingConstants.CENTER);
        lblInstrucciones.setFont(new Font("Arial", Font.BOLD, 14));

        txtIntento = new JTextField();
        txtIntento.setHorizontalAlignment(JTextField.CENTER);
        txtIntento.setFont(new Font("Arial", Font.PLAIN, 16));

        btnAdivinar = new JButton("Probar suerte");
        btnReiniciar = new JButton("Nuevo Juego");
        btnReiniciar.setEnabled(false);

        lblMensaje = new JLabel("¡Ingresa tu primer número!", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.ITALIC, 13));

        lblIntentos = new JLabel("Intentos: 0", SwingConstants.CENTER);

        JPanel panelEntrada = new JPanel(new BorderLayout(5, 5));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        panelEntrada.add(txtIntento, BorderLayout.CENTER);
        panelEntrada.add(btnAdivinar, BorderLayout.EAST);

        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.add(lblIntentos);
        panelInferior.add(btnReiniciar);

        add(lblInstrucciones);
        add(panelEntrada);
        add(lblMensaje);
        add(panelInferior);

        btnAdivinar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarIntento();
            }
        });

        txtIntento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprobarIntento();
            }
        });

        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inicializarJuego();
            }
        });
    }

    private void inicializarJuego() {
        Random rand = new Random();
        numeroSecreto = rand.nextInt(101);
        contadorIntentos = 0;

        if (lblMensaje != null) {
            lblMensaje.setText("¡Nuevo juego iniciado! Adivina el número.");
            lblMensaje.setForeground(Color.BLACK);
            lblIntentos.setText("Intentos: 0");
            txtIntento.setText("");
            txtIntento.setEnabled(true);
            btnAdivinar.setEnabled(true);
            btnReiniciar.setEnabled(false);
        }
    }

    private void comprobarIntento() {
        try {
            int intento = Integer.parseInt(txtIntento.getText().trim());

            if (intento < 0 || intento > 100) {
                lblMensaje.setText("Por favor ingresa un número válido entre 0 y 100.");
                lblMensaje.setForeground(Color.RED);
                return;
            }

            contadorIntentos++;
            lblIntentos.setText("Intentos: " + contadorIntentos);

            if (intento < numeroSecreto) {
                lblMensaje.setText("¡El número es MAYOR!");
                lblMensaje.setForeground(Color.BLUE);
            } else if (intento > numeroSecreto) {
                lblMensaje.setText("¡El número es MENOR!");
                lblMensaje.setForeground(Color.BLUE);
            } else {
                lblMensaje.setText("¡CORRECTO! Lo adivinaste en " + contadorIntentos + " intentos.");
                lblMensaje.setForeground(new Color(0, 128, 0));
                txtIntento.setEnabled(false);
                btnAdivinar.setEnabled(false);
                btnReiniciar.setEnabled(true);
            }

            txtIntento.selectAll();

        } catch (NumberFormatException ex) {
            lblMensaje.setText("Error: Ingresa un número entero válido.");
            lblMensaje.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JuegoAdivinanzaGUI().setVisible(true);
            }
        });
    }
}