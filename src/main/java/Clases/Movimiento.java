package Clases;

/**
 * La clase Movimiento representa un movimiento en el juego de ajedrez entre dos posiciones:
 * la posición inicial y la posición final.
 */
public class Movimiento {
    Posicion posInicial; // Posición inicial del movimiento
    Posicion posFinal;   // Posición final del movimiento

    /**
     * Método para obtener la posición inicial del movimiento.
     * @return Posicion objeto representando la posición inicial.
     */
    public Posicion getPosInicial() {
        return posInicial;
    }

    /**
     * Método para obtener la posición final del movimiento.
     * @return Posicion objeto representando la posición final.
     */
    public Posicion getPosFinal() {
        return posFinal;
    }

    /**
     * Constructor para crear un movimiento en blanco.
     * Inicializa las posiciones inicial y final en la posición predeterminada (0, 0). y les da un valor por defecto
     */
    public Movimiento() {
        posInicial = new Posicion();
        posFinal = new Posicion();
    }

    /**
     * Constructor para crear un movimiento con posiciones inicial y final proporcionadas.
     * @param posInicial Posición inicial del movimiento.
     * @param posFinal   Posición final del movimiento.
     */
    public Movimiento(Posicion posInicial, Posicion posFinal) {
        this.posInicial = posInicial;
        this.posFinal = posFinal;
    }

    /**
     * Comprueba si el movimiento es vertical.
     * @return true si el movimiento es vertical, false de lo contrario.
     * Pertenecen a la misma columna, o sea la columna es 0 (A), 1(B), pero la fila puede ser 1, 2, 3 , etc.
     */
    public boolean esVertical() {
        return posInicial.getColumna() == posFinal.getColumna() && posInicial.getFila() != posFinal.getFila();
    }

    /**
     * Comprueba si el movimiento es horizontal.
     * @return true si el movimiento es horizontal, false de lo contrario.
     * Lo mismo aqui pero al reves, la fila solo puede 1, ni dos ni tres, pero sus columnas puedes ser 0 (A), 1(B), etc.
     */
    public boolean esHorizontal() {
        return posInicial.getColumna() != posFinal.getColumna() && posInicial.getFila() == posFinal.getFila();
    }

    /**
     * Comprueba si el movimiento es diagonal.
     * @return true si el movimiento es diagonal, false de lo contrario.
     */
    public boolean esDiagonal() {
        //Se usa Math abs porque yo quiero agarrar el valor absoluto del numero que voy a restar, si no se pone muy restrictiva
        //con numeros inversos, si fuese (1,5) y (5,1) al no haber valor absoluto seria -4=4, en cambio con valor absoluto seria 4=4, por lo que seria verdadero

        return Math.abs(posInicial.getFila() - posFinal.getFila()) == Math.abs(posInicial.getColumna() - posFinal.getColumna());
    }

    /**
     * Obtiene el número de casillas que recorre una pieza en un movimiento horizontal.
     * @return Int con el número de casillas que salta la pieza en el movimiento horizontal.
     */
    public int saltoHorizontal() {
        //El math abs hace que se obtenga la magnitud de diferencia a positivo, y se obtenga un resultado mas limpio y positivo
        return Math.abs(posFinal.getColumna() - posInicial.getColumna());
    }

    /**
     * Obtiene el número de casillas que recorre una pieza en un movimiento vertical.
     * @return Int con el número de casillas que salta la pieza en el movimiento vertical.
     */
    public int saltoVertical() {
        //aqui usamos el abs en hayPiezasEntre para mejor legibilidad
        return posFinal.getFila() - posInicial.getFila();
    }
}