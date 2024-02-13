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
        Pieza TN1 = new Torre("negro");
        Pieza TN2 = new Torre("negro");
        Pieza CN1 = new Caballo("negro");
        Pieza CN2 = new Caballo("negro");
        Pieza AN1 = new Alfil("negro");
        Pieza AN2 = new Alfil("negro");
        Pieza QN = new Reina("negro");
        Pieza KN = new Rey("negro");
        tablero[0][0] = TN1;
        tablero[0][1] = CN1;
        tablero[0][2] = AN1;
        tablero[0][5] = AN2;
        tablero[0][6] = CN2;
        tablero[0][3] = QN;
        tablero[0][4] = KN;
        tablero[0][7] = TN2;
        for (int i = 0; i < tablero.length; i++) {
            tablero[1][i] = new Peon("negro");
        }
        Pieza TB1 = new Torre("blanco");
        Pieza TB2 = new Torre("blanco");
        Pieza CB1 = new Caballo("blanco");
        Pieza CB2 = new Caballo("blanco");
        Pieza AB1 = new Alfil("blanco");
        Pieza AB2 = new Alfil("blanco");
        Pieza QB = new Reina("blanco");
        Pieza KB = new Rey("blanco");
        tablero[7][0] = TB1;
        tablero[7][1] = CB1;
        tablero[7][2] = AB1;
        tablero[7][5] = AB2;
        tablero[7][6] = CB2;
        tablero[7][3] = QB;
        tablero[7][4] = KB;
        tablero[7][7] = TB2;
        for (int i = 0; i < tablero.length; i++) {
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
     *
     * @param fila fila
     * @param columna columna
     * @return Booleano indicando que hay pieza en la posición introducida mientras la matriz no ande nula
     */
    public boolean hayPieza(int fila, int columna) {
        boolean haypieza = false;
        if (tablero[fila][columna] != null) {
            haypieza = true;
        }
        return haypieza;
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

    /**
     * Método para comprobar si hay alguna pieza en el trayecto de un movimiento definido por dos posiciones.
     *
     * @param mov
     * @return Booleano indicando si hay pieza en el trayecto del movimiento introducido.
     */
    public boolean hayPiezaEntre(Movimiento mov) {
        boolean pieza = false;
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
        if (mov.esHorizontal()) {
            if (mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna() > 0) {   // 3,5 - 3,3
                for (int i = 1; i < mov.saltoHorizontal(); i++) { // i < 2
                    if (hayPieza(mov.getPosInicial().getFila(), (mov.getPosInicial().getColumna() - i))) { //3,4 //si en esa columna no hay pieza, se valida
                        pieza = true;
                    }
                }
            } else {
                for (int i = 1; i < mov.saltoHorizontal(); i++) {
                    if (hayPieza(mov.getPosInicial().getFila(), (mov.getPosInicial().getColumna() + i))) {
                        pieza = true;
                    }
                }
            }
        }
        if (mov.esDiagonal()) {
            int fila = mov.getPosInicial().getFila() - mov.getPosFinal().getFila();
            int columna = mov.getPosInicial().getColumna() - mov.getPosFinal().getColumna();
            if (fila < 0 && columna < 0) {
                for (int i = 1; i < Math.abs(fila); i++) {
                    if (hayPieza(mov.getPosInicial().getFila() + i, mov.getPosInicial().getColumna() + i))
                        pieza = true;
                }
            }
            if (fila > 0 && columna < 0) {
                for (int i = 1; i < Math.abs(fila); i++) {
                    if (hayPieza(mov.getPosInicial().getFila() - i, mov.getPosInicial().getColumna() + i))
                        pieza = true;
                }
            }
            if (fila < 0 && columna > 0) {
                for (int i = 1; i < Math.abs(fila); i++) {
                    if (hayPieza(mov.getPosInicial().getFila() + i, mov.getPosInicial().getColumna() - i))
                        pieza = true;
                }
            }
            if (fila > 0 && columna > 0) {
                for (int i = 1; i < Math.abs(fila); i++) {
                    if (hayPieza(mov.getPosInicial().getFila() - i, mov.getPosInicial().getColumna() - i))
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
    public void quitaPieza(Posicion pos) {
        tablero[pos.getFila()][pos.getColumna()] = null;
    }

    /**
     * Método para devolver la pieza que se encuentra en la posición introducida.
     *
     * @param pos
     * @return Pieza que se encuentra en la posición introducida.
     */
    public Pieza devuelvePieza(Posicion pos) {
        return tablero[pos.getFila()][pos.getColumna()];
    }

    public Posicion devuelvePosicion(Pieza pieza) {
        Posicion posi = null;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (devuelvePieza(new Posicion(i, j)) == pieza) {
                    posi = new Posicion(i, j);
                }
            }
        }
        return posi;
    }

    /**
     * Método para mover las piezas al introducir un movimiento. Suma 1 movimiento al contador de la pieza, pone la pieza en la posición final, quita la pieza de la posición inicial y comprueba si algún peón promociona.
     *
     * @param mov
     */
    public void mover(Movimiento mov, Juego juego) {
        if (enPassant(mov)) {
            ponPieza(devuelvePieza(mov.getPosInicial()), mov.getPosFinal());
            quitaPieza(mov.getPosInicial());
            quitaPieza(mov.getPosInicial().getFila(), mov.getPosFinal().getColumna());
            devuelvePieza(mov.getPosFinal()).setMovimientos();
        } else if (!enroque(mov, juego)) {
            ponPieza(devuelvePieza(mov.getPosInicial()), mov.getPosFinal());
            quitaPieza(mov.getPosInicial());
            devuelvePieza(mov.getPosFinal()).setMovimientos();
            promocionarPeon(mov);
        } else {
            moverEnroque(mov, juego);
            quitaPieza(mov.getPosInicial());
            quitaPieza(mov.getPosFinal());
        }
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
                    case 1:
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
                String nombreClaseInicial = piezaInicial.getClass().getSimpleName().strip();
                String nombreClaseFinal = piezaFinal.getClass().getSimpleName().strip();

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

    public boolean enPassant(Movimiento mov) {
        boolean passant = false;
        Posicion pos = new Posicion(mov.getPosInicial().getFila(), mov.getPosFinal().getColumna());
        if (devuelvePieza(mov.getPosInicial()).getColor().equals("blanco") && mov.saltoVertical() == -1 && !hayPieza(mov.getPosFinal().getFila(), mov.getPosFinal().getColumna()) && mov.esDiagonal()) {
            if (devuelvePieza(pos) != null && devuelvePieza(pos).getColor().equals("negro") && devuelvePieza(pos).getMovimientos() == 1 && mov.getPosInicial().getFila() == 3) {
                passant = true;
            }
        }
        if (devuelvePieza(mov.getPosInicial()).getColor().equals("negro") && mov.saltoVertical() == 1 && !hayPieza(mov.getPosFinal().getFila(), mov.getPosFinal().getColumna()) && mov.esDiagonal()) {
            if (devuelvePieza(pos) != null && devuelvePieza(pos).getColor().equals("blanco") && devuelvePieza(pos).getMovimientos() == 1 && mov.getPosInicial().getFila() == 4) {
                passant = true;
            }
        }
        return passant;
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