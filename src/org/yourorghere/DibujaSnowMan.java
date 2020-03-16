/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author deivi
 */
public class DibujaSnowMan
{

    private GLUquadric q = null;
    private static final float WIDTH_HEAD = 0.5f;

    private static final float WIDTH_BODY = 0.5f;

    private static final float WIDTH_EYES = 0.3f;
    private static final float WIDTH_PUPILS = 0.1f;

    private static final int SLICES = 40;
    private static final int STACKS = 40;

    private static final float RADIO_PICO = 0.1f;
    private static final float TOP_PICO = 0.0f;
    private static final float ALTURA_PICO = 0.5f;

    private static final float RADIO_SOMBREROG = 0.35f;
    private static final float TOP_SOMBREROG = 0.35f;
    private static final float HEIGHT_SOMBREROG = 0.58f;
    private static final float DISK_SOMBREROG = 0.35f;

    private static final float RADIO_SOMBREROP = 0.65f;
    private static final float TOP_SOMBREROP = 0.65f;
    private static final float HEIGHT_SOMBREROP = 0.15f;
    private static final float DISK_SOMBREROP = 0.65f;

    private static final float DISK_BOTON = 0.05f;

    private static final float DISK_BOCA = 0.25f;

    private static final float RADIO_BRAZO = 0.05f;
    private static final float TOP_BRAZO = 0.05f;
    private static final float HEIGHT_BRAZO = 0.6f;

    Colors c = new Colors();
    Metodos m = new Metodos();

    public DibujaSnowMan()
    {

    }

    public void dibujaBody(GL gl, GLU glu)
    {

        q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);
        //Cabeza
        m.draw_cabeza(gl, glu, q, WIDTH_HEAD, SLICES, STACKS, WIDTH_EYES, 
                WIDTH_PUPILS, RADIO_PICO, TOP_PICO, ALTURA_PICO);
        //Boca
        m.draw_boca(gl, glu, q, DISK_BOCA, SLICES, STACKS);
        //Cuerpo
        m.draw_cuerpo(gl, glu, q, WIDTH_BODY,DISK_BOTON, SLICES, STACKS);
        //Brazo Derecho
        m.draw_brazoD(gl, glu, q, RADIO_BRAZO, TOP_BRAZO, HEIGHT_BRAZO, 
                SLICES, STACKS);
        //Brazo Izquierdo
        m.draw_brazoI(gl, glu, q, RADIO_BRAZO, TOP_BRAZO, HEIGHT_BRAZO, 
                SLICES, STACKS);
        //Sombrero
        m.draw_sombrero(gl, glu, q, DISK_SOMBREROG, DISK_SOMBREROP, SLICES, 
                STACKS, RADIO_SOMBREROG, TOP_SOMBREROG, HEIGHT_SOMBREROG, 
                RADIO_SOMBREROP, TOP_SOMBREROP, HEIGHT_SOMBREROP);  
    }

}
