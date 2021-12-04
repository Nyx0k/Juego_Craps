package JuegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for view craps class
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    public static final String MENSAJE_INICIO="Bienvenido a Craps\n"
            + "Oprime el boton lanzar para iniciar el juego"
            + "\nSi tu tiro de salida es 7 u 11 ganas con Natural"
            + "\nSi tu tiro de salida es 2 u 3 pierdes con Craps"
            + "\nSi sacas cualquier otro valor, estableceras un punto"
            + "\nEstado en punto podras seguir lanzando los dados"
            + "\npero ahora ganaras si sacas nuevamente el valor del punto"
            + "\nSin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon ImagenDado;
    private JTextArea resultadosDados,mensajeSalida;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private JSeparator separator;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object

        escucha = new Escucha();

        modelCraps = new ModelCraps();


        //Set up JComponents
        headerProject = new Header("MESA JUEGO CRAPS", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        ImagenDado = new ImageIcon(getClass().getResource("/Recursos/all.JPG"));
        dado1= new JLabel(ImagenDado);
        dado2= new JLabel(ImagenDado);

        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);


        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(400,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados "));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);


        this.add(panelDados,BorderLayout.CENTER);


        mensajeSalida = new JTextArea(7,31);
        mensajeSalida.setText(MENSAJE_INICIO);
        //mensajeSalida.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        JScrollPane scroll = new JScrollPane(mensajeSalida);


        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370,180));

        this.add(panelResultados,BorderLayout.EAST);

        resultadosDados = new JTextArea(4,31);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(320,7));
        separator.setBackground(Color.BLUE);



    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */


    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            modelCraps.calcularTiro();
            int [] caras = modelCraps.getCaras();

            ImagenDado = new ImageIcon(getClass().getResource("/Recursos/"+caras[0]+".JPG"));
            dado1.setIcon(ImagenDado);

            ImagenDado = new ImageIcon(getClass().getResource("/Recursos/"+caras[1]+".JPG"));
            dado2.setIcon(ImagenDado);

            modelCraps.determinarJuego();

            panelResultados.removeAll();;
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados "));
            panelResultados.add(resultadosDados);
            panelResultados.add (separator);
            panelResultados.add(mensajeSalida);
            resultadosDados.setText(modelCraps.getEstadoTOString()[0]);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(modelCraps.getEstadoTOString()[1]);

            revalidate();
            repaint();

        }
    }
}
