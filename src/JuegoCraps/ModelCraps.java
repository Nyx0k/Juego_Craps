package JuegoCraps;


/**
 * author: Jeison Perea
 * Version: V 1.0
 * fecha: 02/12/2021
 */

public class ModelCraps {

    public Dado dado1,dado2;

    private int tiro, punto, estado,flag;
    private String [] estadoTOString;
    private int [] caras;


    /**
    * Clase constructora - Objetos de los atributos
    */
    public ModelCraps () {
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
        estadoTOString = new String[2];
        flag = 0;
    }

    /**
     * Metodo que establece el valor del tiro en cada cara.
     */

    public void calcularTiro(){
        caras[0]=dado1.getCara();
        caras[1]=dado2.getCara();
        tiro = caras[0]+caras[1];
    }


    /**
     * metodo que establece el ESTADO segun el valor del tiro de cada dado.
     * metodo que aplica las reglas del juego.
     * estado 1 = ganador natural
     * estado 2 = perdedor
     * estado 3 = establece punto
     * estado 4 = punto ganador
     * estado 5 = punto perdedor
     *
     */

    public void determinarJuego(){

        if (flag==0){

            if (tiro==7 || tiro == 11){
                estado = 1;
            }
            else{
                if (tiro == 2 || tiro == 3 || tiro == 12){
                    estado = 2;
                }
                else{
                    estado = 3;
                    punto = tiro;
                    flag = 1;
                }
            }

        }
        else {
             // ronda punto
            rondaPunto();
        }
    }

    /**
     * Este metodo establece el estado de acuerdo al tiro.
     */

    private void rondaPunto() {

        if (tiro == punto){
            estado = 4;
            flag=0;
        }
        else   {
        }
        if (tiro == 7){
            estado =5;
            flag=0;
        }else   {
            estado= 6;
        }

    }


    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     *
     * @return el mensaje de cada uno de los estados.
     */

    public String [] getEstadoTOString() {
        switch (estado){

            case 1:
                estadoTOString[0]= "tiro de salida =" +tiro;
                estadoTOString[1]= "Sacaste Natural, has ganado!!";
            break;

            case 2:
                estadoTOString[0]= "tiro de salida =" +tiro;
                estadoTOString[1]= "Sacaste Craps, has perdido!!";
            break;

            case 3:
                estadoTOString[0]= "tiro de salida =" +tiro +" \n Punto = " +punto;
                estadoTOString[1]= "Estableciste Punto en  "+punto+ " debes seguir lanzando!"+
                                    "\n Pero si sacas 7 antes que " +punto+ " Përderas";
            break;

            case 4:
                estadoTOString[0]= "tiro de salida =" +punto +" Punto = "
                        +"\n el valor del nuevo tiro es: "+tiro;

                estadoTOString[1]= "Volviste a sacar "+punto+ ", has ganado.";
            break;

            case 5:
                estadoTOString[0]= "tiro de salida " +punto +" Punto  "
                        +"\n el valor del nuevo tiro es: "+tiro;
                estadoTOString[1]= "Sacaste 7 antes que "+punto+" has perdido.";
            break;

            case 6:
                estadoTOString[0]= "tiro de salida " +punto +" \nPunto  "+punto
                        +"\n el valor del nuevo tiro es: "+tiro;

                estadoTOString[1]= "Estas en punto, ebes seguir lanzando!! "
                        +"\n pero si sacas 7 antes que "+ punto+ " perderas";
                break;

        }
            return estadoTOString;
    }

    public int[] getCaras() {
        return caras;
    }

}
