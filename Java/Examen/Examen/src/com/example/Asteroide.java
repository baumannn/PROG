package com.example;

// Roland alexander Baumann A00814393
// Gilberto Garcia A01280019

import java.awt.Image;

public class Asteroide extends Planet {

    /**
     * Metodo constructor que hereda los atributos de la clase
     * <code>Animal</code>.
     *
     * @param posX es la <code>posiscion en x</code> del objeto raton.
     * @param posY es el <code>posiscion en y</code> del objeto raton.
     * @param image es la <code>imagen</code> del objeto raton.
     */
    public Asteroide(int posX, int posY, Image image) {
        super(posX, posY, image);
    }
}
