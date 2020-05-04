/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;
import sun.java2d.pipe.BufferedOpCodes;

/**
 *  
 * @author deivi
 */
public class DibujaSnowGirl
{

    private GLUquadric q = null;

    private static int mvt = 0;
    private static float mv = 0f;
    private static float mv2 = 0f;

    private static final float WIDTH_HEAD = 0.35f;

    private static final float WIDTH_BODY = 0.45f;

    private static final float WIDTH_FOOTS = 0.15f;

    private static final float WIDTH_PUPILS = 0.08f;

    private static final int SLICES = 40;
    private static final int STACKS = 40;

    private static final float RADIO_PICO = 0.1f;
    private static final float TOP_PICO = 0.0f;
    private static final float ALTURA_PICO = 0.5f;

    private static final float DISK_BOTON = 0.05f;

    private static final float DISK_BOCA = 0.25f;

    static float co = (float) 2.5;
    private static final float WIDTH_BALLON = 0.08f;

    Colors color = new Colors();

    public DibujaSnowGirl()
    {

    }

    public void dibujaBody(GL gl, GLU glu, boolean jump, boolean walk,
            boolean scale, boolean reflection, boolean original, boolean x,
            boolean y, boolean z, boolean t, boolean u, boolean i,
            boolean cortex, boolean cortey, boolean cortez, boolean colision)
    {
        q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);

        //Caminar
        if (walk && mvt % 20 + 10 > 20)
        {
            dibujaPiernas(gl, glu, 'W', false);
            dibujaPiernas(gl, glu, ' ', true);
            dibujaManoIzq(gl, glu, ' ');
            dibujaManoDer(gl, glu, 'W');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');

        } else if (walk && mvt % 20 + 10 <= 20)
        {
            dibujaPiernas(gl, glu, ' ', false);
            dibujaPiernas(gl, glu, 'W', true);
            dibujaManoIzq(gl, glu, 'W');
            dibujaManoDer(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');

            //Saltar
        } else if (jump && mvt % 20 + 10 > 20)
        {
            gl.glTranslatef(0f, 0.35f, 0f);
            dibujaPiernas(gl, glu, 'J', false);
            dibujaPiernas(gl, glu, 'J', true);
            dibujaManoIzq(gl, glu, 'J');
            dibujaManoDer(gl, glu, 'J');
            dibujaCabeza(gl, glu, true, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');

        } else if (original)
        {
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        } else if (scale)
        { //Escalacion
            gl.glScalef(2.2f, 2.2f, 2.2f);
            dibujaPiernas(gl, glu, 'J', false);
            dibujaPiernas(gl, glu, 'J', true);
            dibujaManoIzq(gl, glu, 'J');
            dibujaManoDer(gl, glu, 'J');
            dibujaCabeza(gl, glu, true, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        } else if (reflection)
        { //Reflexion x
            gl.glTranslatef(0f, 2f, 0f);
            gl.glScalef(-1f, -1f, 1f);
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        } else if (x)
        {
            //Rotacion X
            gl.glRotatef(40f, 1f, 0f, 0f);
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        } else if (y)
        { //Rotacion Y
            gl.glRotatef(40f, 0f, 1f, 0f);
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        } else if (z)
        {//Rotacion Z
            gl.glRotatef(40f, 0f, 0f, 1f);
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        } else if (t)
        { //Traslacion
            gl.glTranslatef(-0.3f, -0.2f, 0f);
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        } else if (u)
        {//Reflexion Y
            gl.glRotatef(180, 0f, 1f, 0f);
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        } else if (i)
        {
            //  Reflexion Z
            gl.glTranslatef(0.1f, 0f, 0f);
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            gl.glTranslatef(-0.1f, 0f, 0f);
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
            gl.glTranslatef(-0.1f, 0f, 0f);
        } else if (cortex)
        {
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, 'B');
        } else if (colision)
        {
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
            co = (float) (co - 0.1);
            draw_pelota(gl, glu, co);
        } else
        {
            //Posicion al iniciar el programa
            dibujaPiernas(gl, glu, ' ', true);
            dibujaPiernas(gl, glu, ' ', false);
            dibujaManoDer(gl, glu, ' ');
            dibujaManoIzq(gl, glu, ' ');
            dibujaCabeza(gl, glu, false, ' ');
            dibujaCuerpo(gl, glu);
            dibujaSombrero(gl, glu, ' ');
        }

        mvt++;

    }

    public void draw_pelota(GL gl, GLU glu, float cl)
    {

        color.set_red_material(gl);
        if (co > 0.5)
        {
            gl.glPushMatrix();
            gl.glTranslatef(co + 0f, 0.5f, 0.1f);
            glu.gluSphere(q, WIDTH_BALLON * 2, SLICES, STACKS);
            gl.glPopMatrix();
        }
    }

    public void dibujaPiernas(GL gl, GLU glu, char c, boolean left)
    {
        gl.glPushMatrix();
        if (c == 'W')
        {
            gl.glTranslatef(0f, -0.1f, -0.2f);
            gl.glRotatef(30, -100f, 0f, 0f);
        }

        color.set_white_material(gl);
        gl.glPushMatrix();
        if (left)
        {
            gl.glTranslatef(-0.45f, 0.1f + 0.008f, 0.09f);
        } else
        {
            gl.glTranslatef(0.1f, 0.1f + 0.008f, 0.09f);
        }
        gl.glTranslatef(0.21f, -1.70f, 0f);
        glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
        gl.glPopMatrix();
        gl.glPopMatrix();
    }

    private void dibujaManoDer(GL gl, GLU glu, char c)
    {
        color.set_pink_material(gl);
        gl.glPushMatrix();

        if (c == 'J')
        {
            gl.glTranslatef(0.7f, -0.20f, 0f);
            glu.gluSphere(q, 0.145f, SLICES, STACKS);
            gl.glTranslatef(-0.6f + 0.45f, -0.7f + 0.7f, 0f);
            glu.gluSphere(q, 0.075f, SLICES, STACKS);
        }

        if (c == 'W')
        {
            gl.glTranslatef(0.55f, -0.45f, 0f);
            glu.gluSphere(q, 0.145f, SLICES, STACKS);
            gl.glTranslatef(-0.55f + 0.43f, -0.45f + 0.38f, 0f);
            glu.gluSphere(q, 0.075f, SLICES, STACKS);
        }
        if (c == ' ')
        {
            gl.glTranslatef(0.6f, -0.7f, 0f);
            glu.gluSphere(q, 0.145f, SLICES, STACKS);
            gl.glTranslatef(-0.6f + 0.45f, -0.7f + 0.7f, 0f);
            glu.gluSphere(q, 0.075f, SLICES, STACKS);
        }
        gl.glPopMatrix();

        gl.glPopMatrix();
    }

    private void dibujaManoIzq(GL gl, GLU glu, char c)
    {
        color.set_pink_material(gl);
        gl.glPushMatrix();

        if (c == 'J')
        {
            gl.glTranslatef(-0.70f, -0.20f, 0.05f);
            glu.gluSphere(q, 0.145f, SLICES, STACKS);
            gl.glTranslatef(-0.55f + 0.68f, -0.67f + 0.65f, 0f);
            glu.gluSphere(q, 0.075f, SLICES, STACKS);
        }
        if (c == 'W')
        {
            gl.glTranslatef(-0.5f, -0.40f, 0.24f);
            glu.gluSphere(q, 0.145f, SLICES, STACKS);
            gl.glTranslatef(-0.5f + 0.67f, 0.015f, 0.05f);
            glu.gluSphere(q, 0.075f, SLICES, STACKS);
        }
        if (c == ' ')
        {
            gl.glTranslatef(-0.55f, -0.67f, 0.05f);
            glu.gluSphere(q, 0.145f, SLICES, STACKS);
            gl.glTranslatef(-0.55f + 0.68f, -0.67f + 0.65f, 0f);
            glu.gluSphere(q, 0.075f, SLICES, STACKS);
        }
        gl.glPopMatrix();

        gl.glPushMatrix();
        if (c == 'W')
        {
            gl.glRotatef(40, -1f, 0f, 0f);
        }
        gl.glPopMatrix();
    }

    public void fondo(GL gl, GLU glu, Texture t)
    {
        int m = t.getTextureObject();
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBindTexture(GL.GL_TEXTURE_2D, m);
        gl.glBegin(gl.GL_QUADS);
        gl.glTexCoord2d(0.0f, 1.0f);
        gl.glVertex3f(-6.0f, -6.0f, -6f);
        gl.glTexCoord2d(1.0f, 1.0f);
        gl.glVertex3f(6.0f, -6f, -6f);
        gl.glTexCoord2d(1.0f, 0.0f);
        gl.glVertex3f(6.0f, 6.0f, -6f);
        gl.glTexCoord2d(0.0f, 0.0f);
        gl.glVertex3f(-6.0f, 6.0f, -6f);
        gl.glEnd();
        gl.glFlush();
        gl.glDisable(gl.GL_TEXTURE_2D);
    }

    public void cambiaFondo(GL gl, GLU glu, Texture t, String paisaje)
    {
        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 0.5);
        gl.glTranslated(0f, 0f, 0f);
        gl.glRotatef(-0, 0f, 1.0f, 0.0f);
        fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();
//
        gl.glPushMatrix();
        gl.glScaled(1.0, .9, 1.0);
        gl.glTranslated(0f, -0f, -1f);
        gl.glRotatef(-90, 1f, 0.0f, 0.0f);
        fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();
//
        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 1.0);
//        gl.glTranslated(-1f, -1f, -1f);
        gl.glRotatef(90, 1f, 0.0f, 0.0f);
        fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 1.0);
//        gl.glTranslated(-1f, -1f, -1f);
        gl.glRotatef(90, 0f, 1.0f, 0.0f);
        fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();
//        
        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 1.0);
//        gl.glTranslated(-1f, -1f, -1f);
        gl.glRotatef(-90, 0f, 1.0f, 0.0f);
        fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 1.0);
        gl.glTranslated(-1f, -1f, 10f);
        gl.glRotatef(-0, 0f, .0f, 1.0f);
        fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();
    }

    public void dibujaCabeza(GL gl, GLU glu, boolean jump, char c)
    {

        if (co < 0.5)
        {
            gl.glRotatef(20f, 0f, 0f, 1f);
            gl.glTranslatef(0.18f, 0.0f, 0f);
            color.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.25f, 0f);
            glu.gluSphere(q, WIDTH_HEAD, SLICES, STACKS);
            gl.glPopMatrix();
            //Ojos
            color.set_grey_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.09f, 0.35f, 0.310f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
            color.set_grey_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.09f, 0.35f, 0.310f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
            //Nariz
            color.set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.2f, 0.15f);
            gl.glRotatef(0f, 0f, 0f, 0f);
            glu.gluCylinder(q, RADIO_PICO, TOP_PICO, ALTURA_PICO, SLICES, STACKS);
            gl.glPopMatrix();

            color.set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.0f, 0.40f);
            gl.glRotated(0, 1f, 0f, 0f);
            glu.gluDisk(q, 0f, 0.08, SLICES, STACKS);
            gl.glPopMatrix();
        } else
        {

            color.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.25f, 0f);
            glu.gluSphere(q, WIDTH_HEAD, SLICES, STACKS);
            gl.glPopMatrix();
            //Ojos
            color.set_grey_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.09f, 0.35f, 0.310f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
            color.set_grey_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.09f, 0.35f, 0.310f);
            glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
            gl.glPopMatrix();
            //Nariz
            color.set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0f, 0.2f, 0.15f);
            gl.glRotatef(0f, 0f, 0f, 0f);
            glu.gluCylinder(q, RADIO_PICO, TOP_PICO, ALTURA_PICO, SLICES, STACKS);
            gl.glPopMatrix();
            if (jump)
            {
                //Boca abierta
                color.set_red_material(gl);
                gl.glPushMatrix();
                gl.glTranslatef(0.0f, 0.0f, 0.40f);
                gl.glRotated(0, 1f, 0f, 0f);
                glu.gluDisk(q, 0f, 0.08, SLICES, STACKS);
                gl.glPopMatrix();
            } else
            {
                //Boca normal
                color.set_red_material(gl);
                gl.glPushMatrix();
                gl.glTranslatef(0.0f, 0.2f, 0.25f);
                gl.glRotated(160, 1f, 0f, 0f);
                glu.gluDisk(q, 0f, DISK_BOCA, SLICES, STACKS);
                gl.glPopMatrix();
            }
        }
    }

    private void dibujaCuerpo(GL gl, GLU glu)
    {
        //Segunda esfera
        color.set_white_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.42f, 0f);
        glu.gluSphere(q, 0.37f, SLICES, STACKS);
        gl.glPopMatrix();

        //Tercera esfera
        color.set_white_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -1.15f, 0f);
        glu.gluSphere(q, WIDTH_BODY, SLICES, STACKS);
        gl.glPopMatrix();

        //Botones
        color.set_pink_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.3f, 0.37f);
        gl.glRotated(165, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOTON, SLICES, STACKS);
        gl.glPopMatrix();

        color.set_pink_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.50f, 0.38f);
        gl.glRotated(190, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOTON, SLICES, STACKS);
        gl.glPopMatrix();

        color.set_pink_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.9f, 0.38f);
        gl.glRotated(155, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOTON, SLICES, STACKS);
        gl.glPopMatrix();

        color.set_pink_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -1.2f, 0.46f);
        gl.glRotated(180, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOTON, SLICES, STACKS);
        gl.glPopMatrix();
    }

    private void dibujaSombrero(GL gl, GLU glu, char c)
    {
        // color.set_yellow_material(gl);
        gl.glPushMatrix();
        if (c == ' ')
        {
            //Gorrito bola amarilla.
            color.set_yellow_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0f, 0.77f, 0f);
//        glu.gluSphere(q, 0.250f, SLICES, STACKS);
            glu.gluSphere(q, 0.070f, SLICES, STACKS);
            gl.glPopMatrix();

            //Gorrito bola rosa
            color.set_pink_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0f, 0.45f, 0f);
//        glu.gluSphere(q, 0.250f, SLICES, STACKS);
            glu.gluSphere(q, 0.288f, SLICES, STACKS);
            gl.glPopMatrix();

            //Cilindro amarillo
            color.set_yellow_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0007f, 0.50f, 0f);
            gl.glRotatef(90, 1, 0, 0);
            glu.gluCylinder(q, 0.3, 0.3, 0.09, SLICES, STACKS);
            gl.glPopMatrix();
        }
        gl.glPopMatrix();
        if (c == 'B')
        {
            //Gorrito bola rosa
            color.set_pink_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0f, 0.45f, 0f);
//        glu.gluSphere(q, 0.250f, SLICES, STACKS);
            glu.gluSphere(q, 0.288f, SLICES, STACKS);
            gl.glPopMatrix();

            //Cilindro amarillo
            color.set_yellow_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0007f, 0.50f, 0f);
            gl.glRotatef(90, 1, 0, 0);
            glu.gluCylinder(q, 0.3, 0.3, 0.09, SLICES, STACKS);
            gl.glPopMatrix();
        }
        if (c == 'N')
        {
            //Gorrito bola rosa
            color.set_pink_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0f, 0.45f, 0f);
//        glu.gluSphere(q, 0.250f, SLICES, STACKS);
            glu.gluSphere(q, 0.288f, SLICES, STACKS);
            gl.glPopMatrix();

            //Cilindro amarillo
            color.set_yellow_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0007f, 0.50f, 0f);
            gl.glRotatef(90, 1, 0, 0);
            glu.gluCylinder(q, 0.3, 0.3, 0.09, SLICES, STACKS);
            gl.glPopMatrix();
        }
        if (c == 'M')
        {
            //Gorrito bola rosa
            color.set_pink_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0f, 0.45f, 0f);
//        glu.gluSphere(q, 0.250f, SLICES, STACKS);
            glu.gluSphere(q, 0.288f, SLICES, STACKS);
            gl.glPopMatrix();

            //Cilindro amarillo
            color.set_yellow_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.0007f, 0.50f, 0f);
            gl.glRotatef(90, 1, 0, 0);
            glu.gluCylinder(q, 0.3, 0.3, 0.09, SLICES, STACKS);
            gl.glPopMatrix();
        }
    }

}
