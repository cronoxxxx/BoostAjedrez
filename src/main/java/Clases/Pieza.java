package Clases;

public abstract class Pieza {
    protected String color;
    protected int movimientos;

    /**
     * Contructor de pieza recibiendo un color y movimientos a 0.
     * @param color Color de la pieza
     */
    public Pieza(String color) {
        // Asigna el valor del parámetro 'color' al atributo 'color' de la instancia actual.
        this.color = color;

        // Inicializa el atributo 'movimientos' de la instancia actual a 0.
        // Indica que la pieza aún no se ha movido.
        this.movimientos = 0;
    }

    /**
     * Constructor de la pieza con un color y un número específico de movimientos.
     *
     * @param color       Color de la pieza (blanco o negro).
     * @param movimientos Número inicial de movimientos para la pieza.
     */
    public Pieza(String color, int movimientos) {
        // Asigna el valor del parámetro 'color' al atributo 'color' de la instancia actual.
        this.color = color;

        // Asigna el valor del parámetro 'movimientos' al atributo 'movimientos' de la instancia actual.
        // Permite establecer el número inicial de movimientos para la pieza.
        this.movimientos = movimientos;
    }

    /**
     * Método para devolver el color de una pieza.
     * @return String con el color de la pieza (blanco o negro).
     */
    public String getColor() {
        return color;
    }

    /**
     * Método para obtener el número de movimientos de una pieza.
     * @return int con el número de movimientos de la pieza.
     */
    public int getMovimientos() {
        return movimientos;
    }

    /**
     * Incrementa el contador de movimientos de la pieza en 1.
     * Este método se utiliza para rastrear cuántos movimientos ha realizado la pieza durante el juego.
     * Al llamar a este método, se aumenta en 1 el número de movimientos, indicando que la pieza ha realizado
     * un movimiento adicional en el tablero.
     */
    public void setMovimientos() {
        // Incrementa en 1 el número de movimientos de la pieza.
        this.movimientos = movimientos + 1;
    }
    /**
     * Método para comprobar si el movimiento introducido por el usuario es válido dependido de las condiciones de cada pieza.
     * @param mov     - Introducido por el usuario
     * @return Booleano que verifica si el movimiento introducido por el usuario es válido
     */
    public abstract boolean validoMovimiento(Movimiento mov, Tablero tablero, Juego juego); //Aplicada a hijas

    /**
     * Método para imprimir la inicial de la pieza y la inicial del color.
     *
     * @return String compuesto por la inicial de la letra y la inicial del color.
     */
    @Override
    public abstract String toString();

}
