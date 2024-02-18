package Clases;

import common.Constantes;

public class Juego {
    private int Turno = 0;

    public String getTurno() { //Turno de N si es par, B si no
        return (Turno % 2 == 0) ? "N" : "B";
    }
    public Juego() {
        this.Turno = 0;
    } //Inicia con N, aunque en el tablero salen blancas
    public void setTurno() {
        this.Turno = Turno + 1;
    }

    public boolean validadMovimiento(Tablero tablero, Posicion posoriginal, Posicion posfinal, Juego juego) {
        Movimiento movimiento = new Movimiento(posoriginal, posfinal);
        boolean jugadaa = false;
        if (posoriginal.getFila() >= 0 && posoriginal.getFila() <= 7 && posoriginal.getColumna() >= 0 && posoriginal.getColumna() <= 7 && posfinal.getFila() >= 0 && posfinal.getFila() <= 7 && posfinal.getColumna() >= 0 && posfinal.getColumna() <= 7) //valida dentro del rango
            if (tablero.hayPieza(posoriginal.getFila(), posoriginal.getColumna())) { //halla si hay pieza en esas posiciones
                if (tablero.devuelvePieza(posoriginal).validoMovimiento(movimiento, tablero, juego)) { //si la pieza que da concuerda con los movimientos validos implementados en sus propias clases
                    if (tablero.colorPieza(posoriginal.getFila(), posoriginal.getColumna()).equals("blanco") && getTurno().equals("B")) { //si es blanco y esta en su turno
                        if (tablero.jaqueBlanco(juego)) { //si hay jaque
                            if (tablero.evitaJaque(movimiento,juego)) { //si se evita
                                if (tablero.hayPieza(posfinal.getFila(), posfinal.getColumna())) { //si hay una pieza en la posicion final
                                    if (tablero.colorPieza(posfinal.getFila(), posfinal.getColumna()).equals("negro") || tablero.enroque(movimiento, juego)) { //si es del equipo contrario, o se puede realizar un enroque
                                        if (!tablero.provocaJaque(movimiento,juego)) { //y si esa jugada no provoca un jaque, se valida
                                            jugadaa = true;
                                        }
                                    }
                                } else {
                                    if (!tablero.provocaJaque(movimiento,juego)) { //si la posicion final, moverse alla no provoca jaque
                                        jugadaa = true;
                                    }
                                }
                            }
                        } else { //si no hay jaque
                            //copypaste
                            if (tablero.hayPieza(posfinal.getFila(), posfinal.getColumna())) {
                                if (tablero.colorPieza(posfinal.getFila(), posfinal.getColumna()).equals("negro") || tablero.enroque(movimiento, juego)) {
                                    if (!tablero.provocaJaque(movimiento,juego)) {
                                        jugadaa = true;
                                    }
                                }
                            } else {
                                if (!tablero.provocaJaque(movimiento,juego)) {
                                    jugadaa = true;
                                }
                            }
                        }
                    } else if (tablero.colorPieza(posoriginal.getFila(), posoriginal.getColumna()).equals("negro") && getTurno().equals("N")) { //para las negras, y lo mismo de arriba
                        if (tablero.jaqueNegro(juego)) {
                            if (tablero.evitaJaque(movimiento,juego)) {
                                if (tablero.hayPieza(posfinal.getFila(), posfinal.getColumna())) {
                                    if (tablero.colorPieza(posfinal.getFila(), posfinal.getColumna()).equals("blanco") || tablero.enroque(movimiento,juego)) {
                                        if (!tablero.provocaJaque(movimiento,juego)) {
                                            jugadaa = true;
                                        }
                                    }
                                } else {
                                    if (!tablero.provocaJaque(movimiento,juego)) {
                                        jugadaa = true;
                                    }
                                }
                            }
                        } else {
                            if (tablero.hayPieza(posfinal.getFila(), posfinal.getColumna())) {
                                if (tablero.colorPieza(posfinal.getFila(), posfinal.getColumna()).equals("blanco") || tablero.enroque(movimiento,juego)) {
                                    if (!tablero.provocaJaque(movimiento,juego)) {
                                        jugadaa = true;
                                    }
                                }
                            } else {
                                if (!tablero.provocaJaque(movimiento,juego)) {
                                    jugadaa = true;
                                }
                            }
                        }
                    }
                }
            }


        return jugadaa;
    }

    public Posicion movimientosValidos(Tablero tablero, String pieza, Juego juego) {
        int cont = 0;
        Posicion pos = null;
        pieza = pieza.toUpperCase();
        if (pieza.length() == 2) {
            if (pieza.charAt(0) >= 'A' && pieza.charAt(0) <= 'H' && pieza.charAt(1) >= '1' && pieza.charAt(1) <= '8') { //Verifica si el primer caracter es una letra y seguido un numero
                //columna
                int poc = (pieza.charAt(0) - 65); //65 es el valor ASCII de A, resta uno a la letra que esté, si fuese el valor B de B2, restaria B (valor 66) - A (65), saldria 1 (de 0 a 7) y pasaria a ser un entero
                //fila
                int pof = (pieza.charAt(1) - 49); //49 es el valor ASCII de 1, resta uno al numero que esté, si fuese el valor 2 de A2, restaria 2 (valor 50) - 49 (1), saldria 1 (de 0 a 7) y pasaria a ser un entero
                Posicion posorigen = new Posicion(pof, poc); //a posicion de origen
                if (tablero.hayPieza(pof, poc)) { //comprueba si hay pieza en esa posicion
                    //si hay de color negro o color blanco
                    if ((tablero.colorPieza(pof, poc).equals("blanco") && getTurno().equals("B")) || (tablero.colorPieza(pof, poc).equals("negro") && getTurno().equals("N"))) { //evalua el color y el turno al mismo tiempo
                        System.out.println(Constantes.POSICIONES_FINALES_VÁLIDAS);
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 8; j++) {
                                Posicion posfin = new Posicion(i, j); //se imprimen todas las posiciones por haber
                                if (validadMovimiento(tablero, posorigen, posfin, juego)) {  //Si hay movimientos validos en las posiciones finales de la pieza
                                    char col = (char) (j + 65); //pasa la columna a char y lo suma a 65 (valor de A) para darle valor, si fuese cero, lo paso a char, y al hacerlo le sumo 65, ahora la columna 0 pasaria ser A, y asi sucesivamente
                                    System.out.println(col + "" + (i + 1)); //igual suma a la fila, pero lo hago directo en el print al ser entero
                                    cont++; //conteo para verificar si hay movimientos validos
                                }
                            }
                        }
                        if (cont != 0) {
                            pos = posorigen; //si se encontraron posiciones validas, el objeto pos guarda la posicion inicial
                        } else {
                            System.out.println("No puedes mover esta pieza a ninguna posición.");
                        }
                    } else {
                        System.out.println("Solo puedes mover piezas de tu color.");
                    }
                } else {
                    System.out.println("En la posición introducida no hay pieza.");
                }
            } else {
                System.out.println("Introduzca una posición válida columna A-H, fila 1-8.");
            }
        } else {
            System.out.println("La posición debe ser de dos caracteres.");
        }
        return pos;
    }

    public Movimiento validarPosFinal(Tablero tablero, String posfinal, Posicion posorigen, Juego juego) {
        Movimiento mov = null;
        posfinal = posfinal.toUpperCase(); //posicionFinal en string
        if (posfinal.length() == 2) {
            if (posfinal.charAt(0) >= 'A' && posfinal.charAt(0) <= 'H' && posfinal.charAt(1) >= '1' && posfinal.charAt(1) <= '8') { //se verifica si esta dentro
                int pfc = (posfinal.charAt(0) - 65);
                int pff = (posfinal.charAt(1) - 49); //resta filas y columnas
                Posicion posfin = new Posicion(pff, pfc); //se almacenan en la clase posicion
                mov = new Movimiento(posorigen, posfin);
                if (!validadMovimiento(tablero, posorigen, posfin, juego)) { //si no hay jugadas validas
                    mov = null; //se anula el movimiento
                    System.out.println("El movimiento introducido no es válido.");
                }
            } else {
                System.out.println("Introduzca una posición válida columna A-H, fila 1-8.");
            }
        } else {
            System.out.println("La posición debe ser de dos caracteres.");
        }
        return mov;
    }



}
