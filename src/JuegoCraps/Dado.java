package JuegoCraps;

import java.util.Random;

/**
* Clase dado que genera un valora aleatorio entre 1 y 6
 * @author Jeison Perea
 * @code : 2077250
 * @version: v1.0
 * @fecha: 01/12/2021
 */
public class Dado {
    private int  cara;


    /**
     * Metodo que genera los numeros aleatorios para la cara
     * @return numero (1,6)
     * @author Jeison Perea
     * @code : 2077250
     * @version: v1.0
     * @fecha: 01/12/2021
     */

    public int getCara() {

        Random aleatorio  = new Random();
        cara = aleatorio.nextInt(6)+1;
        return cara;
    }
}
