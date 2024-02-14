package Clases;

import common.Constantes;

import java.util.Scanner;

public class Tablero {
    private Pieza[][] tablero;

    /**
     * Método para construir el tablero creando un array 8*8 e introduciendo las distintas piezas en su lugar correspondiente.
     */
    public Tablero() {
        tablero = new Pieza[8][8];

        // Inicializar piezas negras y blancas
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                tablero[0][i] = new Torre("negro");
                tablero[7][i] = new Torre("blanco");
            } else if (i == 1 || i == 6) {
                tablero[0][i] = new Caballo("negro");
                tablero[7][i] = new Caballo("blanco");
            } else if (i == 2 || i == 5) {
                tablero[0][i] = new Alfil("negro");
                tablero[7][i] = new Alfil("blanco");
            } else if (i == 3) {
                tablero[0][i] = new Reina("negro");
                tablero[7][i] = new Reina("blanco");
            } else /*if (i == 4)*/ {
                tablero[0][i] = new Rey("negro");
                tablero[7][i] = new Rey("blanco");
            }

            tablero[1][i] = new Peon("negro");
            tablero[6][i] = new Peon("blanco");
        }
    }
    /**
     * Método para imprimir el tablero con una fila de letras arriba y los números correspodientes a cada fila en el lateral izquierdo. Si la casilla es nula imprime [].
     */
    public void pintarTablero() {
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        System.out.print("  ");
        for (int i = 0; i < letras.length; i++) {
            System.out.print(" " + letras[i] + " ");
        }
        System.out.println();
        for (int i = tablero.length-1; i>=0; i--) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == null) {
                    System.out.print("[] ");
                } else {
                    System.out.print(tablero[i][j]+" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Método para comprobar si hay pieza en una posición introducida definida por una fila y una columna.
     * @return tablero distinto de nulo
     */
    public boolean hayPieza(int fila, int columna) {
        return tablero[fila][columna] != null;
    }


    /**
     * Método para obtener el color de la pieza en una posición introducida definida por una fila y una columna.
     *
     * @param fila es la fila de la columna
     * @param columna es la columna
     * @return String con el color de la pieza (blanco o negro).
     */
    public String colorPieza(int fila, int columna) {
        return tablero[fila][columna].getColor(); //retorna blanco o negro
    }
    /**
     * Método para colocar una pieza en la fila y columna introducida.
     *
     * @param figura pieza que se va a colocar
     * @param fila   fila en la que se va a colocar la pieza
     * @param columna columna en la que se va a colocar la pieza
     */
    public void ponPieza(Pieza figura, int fila, int columna) {
        // Coloca la pieza en la posición especificada por fila y columna en el tablero.
        tablero[fila][columna] = figura;
    } // Clase padre Pieza que puede poner piezas en una posición determinada.

    /**
     * Método para colocar una pieza en la posición introducida.
     *
     * @param figura pieza que se va a colocar
     * @param pos    objeto Posicion que representa la fila y columna en la que se va a colocar la pieza
     */
    public void ponPieza(Pieza figura, Posicion pos) {
        // Coloca la pieza en la posición especificada por el objeto Posicion en el tablero.
        tablero[pos.getFila()][pos.getColumna()] = figura;
    }

    /**
     * Método para quitar una pieza en la posición introducida definida por una fila y una columna. Iguala la posición a 0.
     *
     * @param fila fila
     * @param columna columna
     */
    public void quitaPieza(int fila, int columna) {
        tablero[fila][columna] = null;
    }

    public void quitaPieza(Posicion pos) {
        tablero[pos.getFila()][pos.getColumna()] = null;
    }


    /**
     * Método para comprobar si hay alguna pieza en el trayecto de un movimiento definido por dos posiciones.
     *
     * @param mov
     * @return Booleano indicando si hay pieza en el trayecto del movimiento introducido.

    public boolean hayPiezaEntre(Movimiento mov) {
        boolean pieza = false;

        // int step = (mov.getPosInicial().getFila() - mov.getPosFinal().getFila() > 0) ? -1 : 1; (FORMA POSIBLE  DE RESUMIR)
        if (mov.esVertical()) { //Verifica es vertical
            if (mov.getPosInicial().getFila() - mov.getPosFinal().getFila() > 0) { //si la fila inicial es mayor que la fila final, se mueve para "abajo" (realmente llendo para arriba al ser su valor positivo)
                for (int i = 1; i < Math.abs(mov.saltoVertical()); i++) { // pi : 3,0 ; pf : 1,0   i<-2 ---
                    if (hayPieza((mov.getPosInicial().getFila() - i), mov.getPosInicial().getColumna())) { //se resta -i para que se evalue las filas que retroceden desde la posicion inicial, y evaluar sus columnas
                        pieza = true; // se cumple
                    }
                }
            } else {
                for (int i = 1; i < Math.abs(mov.saltoVertical()); i++) { //lo contario pi : 1,0 ; pf : 3,0   i<- |-2| = 2 ---
                    if (hayPieza((mov.getPosInicial().getFila() + i), mov.getPosInicial().getColumna())) { ///  2,0
                        pieza = true;
                    }
                }
            }
        }
        else if (mov.esHorizontal()) {
            if (mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna() > 0) {   // 3,5 - 3,3
                for (int i = 1; i < mov.saltoHorizontal(); i++) { // i < 2
                    if (hayPieza(mov.getPosInicial().getFila(), (mov.getPosInicial().getColumna() - i))) { //3,4 //si en esa columna hay pieza, se valida
                        pieza = true;
                    }
                }
            } else {
                for (int i = 1; i < mov.saltoHorizontal(); i++) { //SI ES DE 3,3 A 3,5, SE SUMA LA COLUMNA A 1 (3,4), SI ESA POSICION TIENE PIEZA,
                    if (hayPieza(mov.getPosInicial().getFila(), (mov.getPosInicial().getColumna() + i))) {
                        pieza = true;
                    }
                }
            }
        }
        else if (mov.esDiagonal()) { //POSICION (3,3) Y (1,1)
            int fila = mov.getPosInicial().getFila() - mov.getPosFinal().getFila();
            int columna = mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna(); //SE RESTA FILA Y COLUMNA
            if (fila < 0 && columna < 0) { //SI ES DE 1 1 A 3 3 = (-2,-2)
                for (int i = 1; i < Math.abs(fila); i++) { //(2)
                    if (hayPieza(mov.getPosInicial().getFila() + i, mov.getPosInicial().getColumna() + i)) //se suma +1 a la fila y columna inicial, que representa posicion y fila mas uno (2,2)
                        pieza = true;
                }
            }
            if (fila > 0 && columna < 0) {  // (3,3) Y (1,5)
                for (int i = 1; i < Math.abs(fila); i++) { //MAGNITUD DE DISTANCIA OBTENIDO CON FILA, ya que en el resultado mayor
                    if (hayPieza(mov.getPosInicial().getFila() - i, mov.getPosInicial().getColumna() + i)) //resulta en posicion 2,4
                        pieza = true;
                }
            }
            if (fila < 0 && columna > 0) { //SI ES DE 1,5 A 3,3
                for (int i = 1; i < Math.abs(fila); i++) {
                    if (hayPieza(mov.getPosInicial().getFila() + i, mov.getPosInicial().getColumna() - i)) //IGUAL, RESULTA EN 2,4
                        pieza = true;
                }
            }
            if (fila > 0 && columna > 0) { //AQUI SERIA DE 3 3 A 1,1
                for (int i = 1; i < Math.abs(fila); i++) { //RESULTADO = 2,2
                    if (hayPieza(mov.getPosInicial().getFila() - i, mov.getPosInicial().getColumna() - i))
                        pieza = true;
                }
            }
        }
        return pieza;
    }*/


    public boolean hayPiezaEntre(Movimiento mov) {
        boolean pieza = false;

        if (mov.esVertical()) {
            int step = (mov.getPosInicial().getFila() - mov.getPosFinal().getFila() > 0) ? -1 : 1;

            for (int i = 1; i < Math.abs(mov.saltoVertical()); i++) {
                int fila = mov.getPosInicial().getFila() + (i * step);
                if (hayPieza(fila, mov.getPosInicial().getColumna())) {
                    pieza = true;
                }
            }
        }

        if (mov.esHorizontal()) {
            int step = (mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna() > 0) ? -1 : 1;

            for (int i = 1; i < mov.saltoHorizontal(); i++) {
                int columna = mov.getPosInicial().getColumna() + (i * step);
                if (hayPieza(mov.getPosInicial().getFila(), columna)) {
                    pieza = true;
                }
            }
        }

        if (mov.esDiagonal()) {
            int filaStep = (mov.getPosInicial().getFila() - mov.getPosFinal().getFila() > 0) ? -1 : 1;
            int columnaStep = (mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna() > 0) ? -1 : 1;

            for (int i = 1; i < Math.abs(mov.getPosInicial().getFila() - mov.getPosFinal().getFila()); i++) { //desde fila, ya que es el valor mayor, o es el valor con el que se guia
                int fila = mov.getPosInicial().getFila() + (i * filaStep);
                int columna = mov.getPosInicial().getColumna() + (i * columnaStep);

                if (hayPieza(fila, columna)) {
                    pieza = true;
                }
            }
        }

        return pieza;
    }

    /**
     * Método para quitar una pieza en la posición introducida. Iguala la posición a 0.
     *
     * @param pos
     */

    /**
     * Método para devolver la pieza que se encuentra en la posición introducida.
     *
     * @param pos
     * @return Pieza que se encuentra en la posición introducida.
     */
    public Pieza devuelvePieza(Posicion pos) {
        return tablero[pos.getFila()][pos.getColumna()]; //retorna una pieza en la posicion fila y columna del tablero
    }

    // Busca la posición de una pieza en el tablero y devuelve la posición encontrada.
    public Posicion devuelvePosicion(Pieza pieza) {
        // Inicializa la variable posi como nula.
        Posicion posi = null;

        // Itera a través de todas las filas del tablero.
        for (int i = 0; i < tablero.length; i++) {
            // Itera a través de todas las columnas del tablero.
            for (int j = 0; j < tablero.length; j++) {
                // Comprueba si la pieza en la posición actual es igual a la pieza proporcionada.
                if (devuelvePieza(new Posicion(i, j)) == pieza) { //itero una nueva posicion , y ahi pongo las coordenadas
                    // Si hay coincidencia, asigna la posición actual a la variable posi.
                    posi = new Posicion(i, j);
                }
            }
        }
        // Devuelve la posición encontrada (si alguna) o nula si no se encontró coincidencia.
        return posi;
    }
    /**
     * Verifica si se cumple la regla de "en passant" en el ajedrez.
     * @param mov El movimiento que se está evaluando.
     * @return true si se cumple "en passant", false en caso contrario.
     */
    public boolean enPassant(Movimiento mov) {
        boolean passant = false;
        //lo mismo que las blancas pero en version negra

        Posicion pos = new Posicion(mov.getPosInicial().getFila(), mov.getPosFinal().getColumna()); //Inicializa la posicion potencial

        if (devuelvePieza(mov.getPosInicial()).getColor().equals("blanco") && mov.saltoVertical() == -1 && !hayPieza(mov.getPosFinal().getFila(), mov.getPosFinal().getColumna()) && mov.esDiagonal()) {
            // Verifica si la pieza en la posición potencial es un peón negro con un movimiento inicial de 1 y el peón blanco se encuentra en la fila correcta.
            if (devuelvePieza(pos) != null && devuelvePieza(pos).getColor().equals("negro") && devuelvePieza(pos).getMovimientos() == 1 && mov.getPosInicial().getFila() == 3) {
                passant = true; // Se cumple la condición "en passant".
            }
        }

        // Para el peón  (NEGRO)
        //mov.saltoVertical() == 1
        //por ejemplo de 4,2 a 5,3
        //devuelvePieza(pos).getColor().equals("blanco") = 4,3, que esta al costado del negro que esta en 4,2, es la POSICION POTENCIAL
        //devuelvePieza(pos).getMovimientos() == 1 //que la pieza en 4,3 tenga solo 1 movimiento
        //y que este en la columna 4 y no este nula
        //Si la pieza
        if (devuelvePieza(mov.getPosInicial()).getColor().equals("negro") && mov.saltoVertical() == 1 && !hayPieza(mov.getPosFinal().getFila(), mov.getPosFinal().getColumna()) && mov.esDiagonal()) {
            // Verifica si la pieza en la posición potencial es un peón blanco con un movimiento inicial de 1 y el peón negro se encuentra en la fila correcta.
            if (devuelvePieza(pos) != null && devuelvePieza(pos).getColor().equals("blanco") && devuelvePieza(pos).getMovimientos() == 1 && mov.getPosInicial().getFila() == 4) {
                passant = true; // Se cumple la condición "en passant".
            }
        }

        return passant; // Devuelve true si se cumple "en passant", de lo contrario, devuelve false.
    }
    /**
     * Método para mover las piezas al introducir un movimiento. Suma 1 movimiento al contador de la pieza, pone la pieza en la posición final, quita la pieza de la posición inicial y comprueba si algún peón promociona.
     *
     * @param mov
     */
    public void mover(Movimiento mov, Juego juego) {
        // Verifica si se trata de un movimiento "en passant"
        if (enPassant(mov)) {
            // Realiza el movimiento "en passant"
            ponPieza(devuelvePieza(mov.getPosInicial())  ,   mov.getPosFinal()); //Agarra del tablero la posicion inical de una pieza con el metodo (devuelvePieza) que retorna una pieza en esa posicion, pasa la posicion donde desea mover y ademas, usa el metodo ponerPieza para ponerla en el tablero
            quitaPieza(mov.getPosInicial()); //quita la pieza en la posicion inical
            quitaPieza(mov.getPosInicial().getFila(), mov.getPosFinal().getColumna()); //quito la posicion potencial
            devuelvePieza(mov.getPosFinal()).setMovimientos(); //me da la pieza y le coloco el conteo de los movimientos
        }
        // Si no es un enroque, realiza un movimiento normal
        else if (!enroque(mov, juego)) {
            // Realiza el movimiento normal
            ponPieza(devuelvePieza(mov.getPosInicial()), mov.getPosFinal());
            quitaPieza(mov.getPosInicial());
            devuelvePieza(mov.getPosFinal()).setMovimientos();
            // Verifica la promoción de peones
            promocionarPeon(mov);
        }
        // Si es un enroque, realiza el movimiento especial
        else {
            moverEnroque(mov, juego);
            quitaPieza(mov.getPosInicial());
            quitaPieza(mov.getPosFinal());
        }

        // Cambia el turno del juego
        juego.setTurno();
    }


    /**
     * Método para comprobar si algún peón promociona. Para ello comprueba si hay algún peón en la posición final y, en el caso de que así sea, pregunta al usuario cual es la pieza a la que quiere promocionar el peón.
     *
     * @param mov mov
     */
    public void promocionarPeon(Movimiento mov) {
        Scanner teclado = new Scanner(System.in);
        if (devuelvePieza(mov.getPosFinal()).getClass().getSimpleName().equals("Peon") && (mov.getPosFinal().getFila() == 0 || mov.getPosFinal().getFila() == 7)) {
            int opcion = 0;
            do {
                System.out.println(Constantes.INTRODUZCA_1_PARA_PROMOCIONAR_A_REINA_2_PARA_CABALLO_3_PARA_TORRE_O_4_PARA_ALFIL);
                opcion = teclado.nextInt();
                Pieza pieza = new Peon("blanco");
                switch (opcion) {
                    case 1: //Agarra la pieza de la posicion final (peon) y le pasa el color a la reina, aparte le asigna fila y columna de la posicion final, y lo agrega al tablero
                        ponPieza(new Reina(devuelvePieza(mov.getPosFinal()).getColor()), mov.getPosFinal().getFila(), mov.getPosFinal().getColumna());
                        break;
                    case 2:
                        ponPieza(new Caballo(devuelvePieza(mov.getPosFinal()).getColor()), mov.getPosFinal().getFila(), mov.getPosFinal().getColumna());
                        break;
                    case 3:
                        ponPieza(new Torre(devuelvePieza(mov.getPosFinal()).getColor()), mov.getPosFinal().getFila(), mov.getPosFinal().getColumna());
                        break;
                    case 4:
                        ponPieza(new Alfil(devuelvePieza(mov.getPosFinal()).getColor()), mov.getPosFinal().getFila(), mov.getPosFinal().getColumna());
                        break;
                    default:
                        System.out.println(Constantes.INTRODUCE_UNA_OPCION_VALIDA);
                }
            } while (opcion < 1 || opcion > 4);
        }
    }

    public boolean jaqueBlanco(Juego juego) {
        boolean jaque = false;
        int fila = 0;
        int colum = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] != null)
                    if (tablero[i][j].getClass().getSimpleName().equals("Rey")) {
                        if (tablero[i][j].getColor().equals("blanco")) {
                            fila = i;
                            colum = j;
                        }
                    }
            }
        }
        Posicion posi = new Posicion(fila, colum);
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] != null && tablero[i][j].getColor().equals("negro")) {
                    Posicion pos = new Posicion(i, j);
                    Movimiento mov = new Movimiento(pos, posi);
                    if (tablero[i][j].validoMovimiento(mov, this, juego)) {
                        jaque = true;
                    }
                }
            }
        }
        return jaque;
    }

    public boolean jaqueNegro(Juego juego) {
        boolean jaque = false;
        int fila = 0;
        int colum = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] != null)
                    if (tablero[i][j].getClass().getSimpleName().equals("Rey")) {
                        if (tablero[i][j].getColor().equals("negro")) {
                            fila = i;
                            colum = j;
                        }
                    }
            }
        }
        Posicion posi = new Posicion(fila, colum);
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] != null && tablero[i][j].getColor().equals("blanco")) {
                    Posicion pos = new Posicion(i, j);
                    Movimiento mov = new Movimiento(pos, posi);
                    if (tablero[i][j].validoMovimiento(mov, this, juego)) {
                        jaque = true;
                    }
                }
            }
        }
        return jaque;
    }

    public boolean evitaJaque(Movimiento mov, Juego juego) {
        boolean evita = false;
        Pieza aux = devuelvePieza(mov.getPosFinal());
        ponPieza(devuelvePieza(mov.getPosInicial()), mov.getPosFinal());
        quitaPieza(mov.getPosInicial());
        if (devuelvePieza(mov.getPosFinal()) == null) {

        } else if (devuelvePieza(mov.getPosFinal()).getColor().equals("blanco") && !jaqueBlanco(juego)) {
            evita = true;
        } else if (devuelvePieza(mov.getPosFinal()).getColor().equals("negro") && !jaqueNegro(juego)) {
            evita = true;
        }

        ponPieza(devuelvePieza(mov.getPosFinal()), mov.getPosInicial());
        ponPieza(aux, mov.getPosFinal());
        return evita;
    }

    public boolean provocaJaque(Movimiento mov, Juego juego) {
        boolean evita = false;
        Pieza aux = devuelvePieza(mov.getPosFinal());
        ponPieza(devuelvePieza(mov.getPosInicial()), mov.getPosFinal());
        quitaPieza(mov.getPosInicial());
        if (devuelvePieza(mov.getPosFinal()).getColor().equals("blanco") && jaqueBlanco(juego)) {
            evita = true;
        } else if (devuelvePieza(mov.getPosFinal()).getColor().equals("negro") && jaqueNegro(juego)) {
            evita = true;
        }
        ponPieza(devuelvePieza(mov.getPosFinal()), mov.getPosInicial());
        ponPieza(aux, mov.getPosFinal());
        return evita;
    }

    public boolean jaqueMateBlanco(Juego juego) {
        boolean jaquemate = true;
        int fila = 0;
        int colum = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] != null)
                    if (tablero[i][j].getClass().getSimpleName().equals("Rey")) {
                        if (tablero[i][j].getColor().equals("blanco")) {
                            fila = i;
                            colum = j;
                        }
                    }
            }
        }
        Posicion posi = new Posicion(fila, colum);
        Posicion[] posiciones = new Posicion[8];
        posiciones[0] = new Posicion(posi.getFila() - 1, posi.getColumna() - 1);
        posiciones[1] = new Posicion(posi.getFila() - 1, posi.getColumna());
        posiciones[2] = new Posicion(posi.getFila(), posi.getColumna() - 1);
        posiciones[3] = new Posicion(posi.getFila() - 1, posi.getColumna());
        posiciones[4] = new Posicion(posi.getFila() + 1, posi.getColumna() + 1);
        posiciones[5] = new Posicion(posi.getFila() + 1, posi.getColumna());
        posiciones[6] = new Posicion(posi.getFila(), posi.getColumna() + 1);
        posiciones[7] = new Posicion(posi.getFila() - 1, posi.getColumna());
        for (int j = 0; j < posiciones.length; j++) {
            if (juego.validadMovimiento(this, posi, posiciones[j], juego)) {
                jaquemate = false;
            } else
                posiciones[j] = null;
        }
        if (jaquemate) {
            Pieza[] piezascolor = new Pieza[16];
            int cont = 0;
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    if (tablero[i][j] != null)
                        if (tablero[i][j].getColor().equals("blanco")) {
                            piezascolor[cont] = tablero[i][j];
                            cont++;
                        }
                }
            }
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    for (int k = 0; k < piezascolor.length; k++) {
                        if (tablero[i][j] != null) {
                            if (piezascolor[k] != null) {
                                Posicion posfin = new Posicion(i, j);
                                Movimiento mov = new Movimiento(devuelvePosicion(piezascolor[k]), posfin);
                                if (juego.validadMovimiento(this, mov.getPosInicial(), mov.getPosFinal(), juego)) {
                                    if (evitaJaque(mov, juego)) {
                                        jaquemate = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return jaquemate;
    }

    public boolean jaqueMateNegro(Juego juego) {
        boolean jaquemate = true;
        int fila = 0;
        int colum = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] != null)
                    if (tablero[i][j].getClass().getSimpleName().equals("Rey")) {
                        if (tablero[i][j].getColor().equals("negro")) {
                            fila = i;
                            colum = j;
                        }
                    }
            }
        }
        Posicion posi = new Posicion(fila, colum);
        Posicion[] posiciones = new Posicion[8];
        posiciones[0] = new Posicion(posi.getFila() - 1, posi.getColumna() - 1);
        posiciones[1] = new Posicion(posi.getFila() - 1, posi.getColumna());
        posiciones[2] = new Posicion(posi.getFila(), posi.getColumna() - 1);
        posiciones[3] = new Posicion(posi.getFila() - 1, posi.getColumna());
        posiciones[4] = new Posicion(posi.getFila() + 1, posi.getColumna() + 1);
        posiciones[5] = new Posicion(posi.getFila() + 1, posi.getColumna());
        posiciones[6] = new Posicion(posi.getFila(), posi.getColumna() + 1);
        posiciones[7] = new Posicion(posi.getFila() - 1, posi.getColumna());
        for (int j = 0; j < posiciones.length; j++) {
            if (juego.validadMovimiento(this, posi, posiciones[j], juego)) {
                jaquemate = false;
            } else
                posiciones[j] = null;
        }
        if (jaquemate) {
            Pieza[] piezascolor = new Pieza[16];
            int cont = 0;
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    if (tablero[i][j] != null)
                        if (tablero[i][j].getColor().equals("negro")) {
                            piezascolor[cont] = tablero[i][j];
                            cont++;
                        }
                }
            }
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    for (int k = 0; k < piezascolor.length; k++) {
                        if (tablero[i][j] != null) {
                            if (piezascolor[k] != null) {
                                Posicion posfin = new Posicion(i, j);
                                Movimiento mov = new Movimiento(devuelvePosicion(piezascolor[k]), posfin);
                                if (juego.validadMovimiento(this, mov.getPosInicial(), mov.getPosFinal(), juego)) {
                                    if (evitaJaque(mov, juego)) {
                                        jaquemate = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return jaquemate;
    }

    public boolean enroque(Movimiento movimiento, Juego juego) {
        boolean enroque = false;

        String turnoActual = juego.getTurno();
        int filaInicial = movimiento.getPosInicial().getFila();
        int filaFinal = movimiento.getPosFinal().getFila();

        if ((turnoActual.equals("B") && filaInicial == 7 && filaFinal == 7) || (turnoActual.equals("N") && filaInicial == 0 && filaFinal == 0)) {
            Pieza piezaInicial = devuelvePieza(movimiento.getPosInicial());
            Pieza piezaFinal = devuelvePieza(movimiento.getPosFinal());

            if (piezaInicial != null && piezaFinal != null && piezaInicial.getMovimientos() == 0 && piezaFinal.getMovimientos() == 0) {
                String nombreClaseInicial = piezaInicial.getClass().getSimpleName();
                String nombreClaseFinal = piezaFinal.getClass().getSimpleName();

                boolean esReyTorre;

                if ((nombreClaseInicial.equals("Rey") && nombreClaseFinal.equals("Torre")) || (nombreClaseInicial.equals("Torre") && nombreClaseFinal.equals("Rey"))) {
                    esReyTorre = true;
                } else {
                    esReyTorre = false;
                }


                if (!hayPiezaEntre(movimiento) && esReyTorre) {
                    enroque = true;
                }
            }
        }

        return enroque;
    }

    public void moverEnroque(Movimiento mov, Juego juego) {
        if (juego.getTurno().equals("B")) {
            if (mov.getPosFinal().getColumna() == 0 || mov.getPosInicial().getColumna() == 0) {
                ponPieza(new Rey("blanco", 1), 7, 2);
                ponPieza(new Torre("blanco", 1), 7, 3);
            } else {
                ponPieza(new Rey("blanco", 1), 7, 6);
                ponPieza(new Torre("blanco", 1), 7, 5);
            }
        } else {
            if (mov.getPosFinal().getColumna() == 0 || mov.getPosInicial().getColumna() == 0) {
                ponPieza(new Rey("negro", 1), 0, 2);
                ponPieza(new Torre("negro", 1), 0, 3);
            } else {
                ponPieza(new Rey("negro", 1), 0, 6);
                ponPieza(new Torre("negro", 1), 0, 5);
            }
        }
    }



    public boolean reyAhogadoBlanco(Juego juego) {
        boolean ahogadoblanco = false;
        if (!jaqueBlanco(juego)) {
            ahogadoblanco = true;
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    Posicion posinicio = new Posicion(i, j);
                    if (tablero[i][j] != null && tablero[i][j].getColor().equals("blanco"))
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                Posicion posfin = new Posicion(k,l);
                                if (juego.validadMovimiento(this,posinicio,posfin,juego)){
                                    ahogadoblanco = false;
                                    k=8;
                                    l=8;
                                    i=8;
                                    j=8;
                                }
                            }
                        }
                }
            }
        }
        return ahogadoblanco;
    }
    public boolean reyAhogadoNegro(Juego juego) {
        boolean ahogadonegro = false;
        if (!jaqueNegro(juego)) {
            ahogadonegro = true;
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    Posicion posinicio = new Posicion(i, j);
                    if (tablero[i][j] != null && tablero[i][j].getColor().equals("negro"))
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                Posicion posfin = new Posicion(k,l);
                                if (juego.validadMovimiento(this,posinicio,posfin,juego)){
                                    ahogadonegro = false;
                                    k=8;
                                    l=8;
                                    i=8;
                                    j=8;
                                }
                            }
                        }
                }
            }
        }
        return ahogadonegro;
    }

    public boolean finJuego(Juego juego) {
        boolean fin = false;
        int cont = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == null) {

                } else if (tablero[i][j].getClass().getSimpleName().equals("Rey")) {
                    cont++;
                }
            }
        }
        if (cont != 2) {
            fin = true;
        }
        if (juego.getTurno().equals("B")&&reyAhogadoBlanco(juego)){
            fin=true;
        } else if (juego.getTurno().equals("N")&&reyAhogadoNegro(juego)) {
            fin=true;
        }


        return fin;
    }

}