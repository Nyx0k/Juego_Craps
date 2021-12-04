package JuegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayaout extends JFrame {


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
        private JButton lanzar,ayuda,salir;
        private JPanel panelDados;
        private ImageIcon ImagenDado;
        private JTextArea resultadosDados,mensajeSalida;
        private Escucha escucha;
        private ModelCraps modelCraps;


        public void GUIGridBagLayaout(){
            initGUI();

            this.setTitle("Juego Craps");
            this.pack();
            this.setResizable(true);
            this.setVisible(true);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

        private void initGUI() {


            //Set up JFrame Container's Layout

            this.getContentPane().setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            //Create Listener Object or Control Object

            escucha = new Escucha();
            modelCraps = new ModelCraps();

            //Set up JComponents
            headerProject = new Header("MESA JUEGO CRAPS", Color.BLACK);

            constraints.gridx=0;
            constraints.gridy=2;
            constraints.gridwidth=2;
            constraints.fill=GridBagConstraints.HORIZONTAL;
            this.add(headerProject,constraints); //Change this line if you change JFrame Container's Layout

            ayuda = new JButton(" ? ");
            ayuda.addActionListener(escucha);
            constraints.gridx=0;
            constraints.gridy=1;
            constraints.gridwidth=1;
            constraints.fill=GridBagConstraints.NONE;
            constraints.anchor=GridBagConstraints.LINE_START;
            this.add(ayuda,constraints);

            salir = new JButton("Salir");
            salir.addActionListener(escucha);
            constraints.gridx=1;
            constraints.gridy=1;
            constraints.gridwidth=1;
            constraints.fill=GridBagConstraints.NONE;
            constraints.anchor=GridBagConstraints.LINE_END;
            this.add(salir,constraints);


            ImagenDado = new ImageIcon(getClass().getResource("/Recursos/all.JPG"));
            dado1= new JLabel(ImagenDado);
            dado2= new JLabel(ImagenDado);


            panelDados = new JPanel();
            panelDados.setPreferredSize(new Dimension(400,180));
            panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados "));
            panelDados.add(dado1);
            panelDados.add(dado2);

            constraints.gridx=0;
            constraints.gridy=2;
            constraints.gridwidth=1;
            constraints.fill=GridBagConstraints.BOTH;
            constraints.anchor=GridBagConstraints.CENTER;
            this.add(panelDados,constraints);




        }

        public static void main(String[] args){
            EventQueue.invokeLater(() -> {
                GUIGridBagLayaout miProjectGUI = new GUIGridBagLayaout();
            });
        }

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
            resultadosDados.setText(modelCraps.getEstadoTOString()[0]);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(modelCraps.getEstadoTOString()[1]);


        }



    }}






