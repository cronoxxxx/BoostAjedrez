package Clases;

public class Caballo extends Pieza {
    /**
     * Método para construir un caballo y asignarle color.
     *
     * @param color
     */
    public Caballo(String color) {
        super(color);
    }

    /**
     * Método para validar movimiento del caballo, este debe ser en L. Moviéndose 2 filas y 1 columna o viceversa.
     *
     * @param mov     - Introducido por el usuario
     * @param tablero
     * @return Booleano que verifica si el movimiento introducido por el usuario es válido
     */
    @Override
    public boolean validoMovimiento(Movimiento mov, Tablero tablero, Juego juego) {
        boolean valido = false;
        if (Math.abs(mov.getPosInicial().getFila() - mov.getPosFinal().getFila()) >= 1 && Math.abs(mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna()) >= 1) {
            if ((Math.abs(mov.getPosInicial().getFila() - mov.getPosFinal().getFila()) + (Math.abs(mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna()))) == 3) {
                valido = true;
            }
        }
        return valido;
    }

    /**
     * Método para imprimir el nombre de la pieza a imprimir dependiendo del color de esta.
     *
     * @return String compuesto por la inicial de la letra y la inicial del color
     */
    public String toString() {
        String pieza;
        if (Caballo.super.getColor().equals("negro"))
            pieza = "CB";
        else
            pieza = "CN";
        return pieza;
    }
}
