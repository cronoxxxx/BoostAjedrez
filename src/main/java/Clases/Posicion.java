package Clases;

/**
 * La clase Posicion representa las coordenadas de fila y columna en un tablero de ajedrez.
 * El constructor vacío inicializa la posición a (0, 0), proporcionando una posición predeterminada.
 * Esto es útil al crear instancias sin valores específicos o como punto de partida.
 */
public class Posicion {
    private int fila;
    private int columna;

    /**
     * Constructor vacío de la posición que inicializa la fila y la columna en 0.
     */
    public Posicion() {
        fila = 0;
        columna = 0;
    }

    /**
     * Constructor de posición con los valores introducidos por el usuario.
     *
     * @param fila    Número de la fila.
     * @param columna Número de la columna.
     */
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Obtiene el número de la fila.
     *
     * @return Número de la fila.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Cambia la fila de una posición con el valor introducido por el usuario.
     *
     * @param fila Nuevo valor de la fila.
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Obtiene el número de la columna.
     *
     * @return Número de la columna.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Cambia la columna de una posición con el valor introducido por el usuario.
     *
     * @param columna Nuevo valor de la columna.
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * Obtiene un String con la representación de fila y columna de una posición.
     *
     * @return String con la representación de la fila y la columna de la posición.
     */
    @Override
    public String toString() {
        return "Posicion{" +
                "fila=" + fila +
                ", columna=" + columna +
                '}';
    }
}
