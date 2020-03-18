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

/**
 *
 * @author deivi
 */
public class Metodos
{

    Colors c = new Colors();

    public void draw_cabeza(GL gl, GLU glu, GLUquadric q, float WIDTH_HEAD,
            int SLICES, int STACKS, float WIDTH_EYES, float WIDTH_PUPILS,
            float RADIO_PICO, float TOP_PICO, float ALTURA_PICO)
    {
//        c.set_white_material(gl);
//        gl.glPushMatrix();
//        gl.glTranslatef(0.0f, 0.5f, 0f);
//        glu.gluSphere(q, WIDTH_HEAD, SLICES, STACKS);
//        gl.glPopMatrix();
        c.set_white_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.5f, 0f);
        glu.gluSphere(q, WIDTH_HEAD, SLICES, STACKS);
        gl.glPopMatrix();

        // Cuencas Ojos
        c.set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.045f, 0.49f, 0.205f);
        glu.gluSphere(q, WIDTH_EYES, SLICES, STACKS);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(0.045f, 0.49f, 0.205f);
        glu.gluSphere(q, WIDTH_EYES, SLICES, STACKS);
        gl.glPopMatrix();

        //Ojos
        c.set_grey_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.09f, 0.45f, 0.480f);
        glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
        gl.glPopMatrix();

        c.set_grey_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.09f, 0.45f, 0.480f);
        glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
        gl.glPopMatrix();

        //Nariz
        c.set_orange_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.25f, 0.15f);
        gl.glRotatef(0f, 0f, 0f, 0f);
        glu.gluCylinder(q, RADIO_PICO, TOP_PICO, ALTURA_PICO, SLICES, STACKS);
        gl.glPopMatrix();
    }

    public void draw_boca(GL gl, GLU glu, GLUquadric q, float DISK_BOCA,
            int SLICES, int STACKS, boolean jump)
    {
        c.set_red_material(gl);
        gl.glPushMatrix();
        //gl.glTranslatef(0f, 0.35f, 0f);

        if (jump)
        {
//            //Boca Asombrado
//            gl.glTranslatef(0.0f, 0.1f, 0.35f);
//            gl.glRotated(220, 1f, 0f, 0f);
//            glu.gluDisk(q, 0f, 0.07, SLICES, STACKS);
            //Boca sonrisa grande
        c.set_red_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.3f, 0.40f);
        gl.glRotated(0, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOCA, SLICES, STACKS);
        gl.glPopMatrix();
        } else
        {
            //Boca normal
            gl.glTranslatef(0.0f, 0.3f, 0.25f);
            gl.glRotated(160, 1f, 0f, 0f);
            glu.gluDisk(q, 0f, DISK_BOCA, SLICES, STACKS);
        }
        gl.glPopMatrix();

        //Boca sonrisa grande
//        c.set_red_material(gl);
//        gl.glPushMatrix();
//        gl.glTranslatef(0.0f, 0.3f, 0.40f);
//        gl.glRotated(0, 1f, 0f, 0f);
//        glu.gluDisk(q, 0f, DISK_BOCA, SLICES, STACKS);
//        gl.glPopMatrix();
        //Boca triste
//        c.set_red_material(gl);
//        gl.glPushMatrix();
//        gl.glTranslatef(0.0f, 0.1f, 0.30f);
//        gl.glRotated(250, 1f, 0f, 0f);
//        glu.gluDisk(q, 0f, 0.12, SLICES, STACKS);
//        gl.glPopMatrix();
    }

    public void draw_sombrero(GL gl, GLU glu, GLUquadric q, float DISK_SOMBREROG,
            float DISK_SOMBREROP, int SLICES, int STACKS, float RADIO_SOMBREROG,
            float TOP_SOMBREROG, float HEIGHT_SOMBREROG,
            float RADIO_SOMBREROP, float TOP_SOMBREROP, float HEIGHT_SOMBREROP)
    {
        //Cilindro largo
        c.set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 1.45f, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(q, RADIO_SOMBREROG, TOP_SOMBREROG, HEIGHT_SOMBREROG,
                SLICES, STACKS);
        glu.gluDisk(q, 0f, DISK_SOMBREROG, SLICES, STACKS);
        //Cilindro corto
        gl.glPopMatrix();
        c.set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.9f, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(q, RADIO_SOMBREROP, TOP_SOMBREROP, HEIGHT_SOMBREROP,
                SLICES, STACKS);
        glu.gluDisk(q, 0f, DISK_SOMBREROP, SLICES, STACKS);
        gl.glPopMatrix();
    }

    public void draw_brazoD(GL gl, GLU glu, GLUquadric q, float RADIO_BRAZO,
            float TOP_BRAZO, float HEIGHT_BRAZO, int SLICES, int STACKS, boolean jump)
    {
        if (jump)
        {
            c.set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.40f, -0.23f, 0f);
            gl.glRotatef(90, 0, 1, 0);
            glu.gluCylinder(q, RADIO_BRAZO, TOP_BRAZO, HEIGHT_BRAZO, SLICES, STACKS);
            gl.glPopMatrix();
            //Mano Derecha
            c.set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.9f, -0.2f, 0f);
            glu.gluSphere(q, 0.125f, SLICES, STACKS);
            gl.glPopMatrix();
        } else
        {
            //Brazo derecho
            c.set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.40f, -0.23f, 0f);
            gl.glRotatef(90, 1, 1, 0);
            glu.gluCylinder(q, RADIO_BRAZO, TOP_BRAZO, HEIGHT_BRAZO, SLICES, STACKS);
            gl.glPopMatrix();
            //Mano Derecha
            c.set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.9f, -0.7f, 0f);
            glu.gluSphere(q, 0.125f, SLICES, STACKS);
            gl.glPopMatrix();
        }
    }

    public void draw_brazoI(GL gl, GLU glu, GLUquadric q, float RADIO_BRAZO,
            float TOP_BRAZO, float HEIGHT_BRAZO, int SLICES, int STACKS, boolean jump)
    {
        if (jump)
        {
            //BRazo izquierdo
            c.set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.95f, -0.30f, -0.3f);
            gl.glRotatef(180, 1, 0, 1);
            glu.gluCylinder(q, RADIO_BRAZO, TOP_BRAZO, HEIGHT_BRAZO, SLICES, STACKS);
            gl.glPopMatrix();
            //Mano izquierda
            c.set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.9f, -0.30f, -0.25f);
            glu.gluSphere(q, 0.125f, SLICES, STACKS);
            gl.glPopMatrix();
        } else
        {
            //BRazo izquierdo
            c.set_orange_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.85f, -0.65f, -0.3f);
            gl.glRotatef(90, 0, 1, 1);
            glu.gluCylinder(q, RADIO_BRAZO, TOP_BRAZO, HEIGHT_BRAZO, SLICES, STACKS);
            gl.glPopMatrix();
            //Mano izquierda
            c.set_red_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.9f, -0.63f, -0.25f);
            glu.gluSphere(q, 0.125f, SLICES, STACKS);
            gl.glPopMatrix();
        }
    }

    public void draw_cuerpo(GL gl, GLU glu, GLUquadric q, float WIDTH_BODY,
            float DISK_BOTON, int SLICES, int STACKS)
    {
        //Segunda esfera
        c.set_white_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.42f, 0f);
        glu.gluSphere(q, WIDTH_BODY, SLICES, STACKS);
        gl.glPopMatrix();

        //Tercera esfera
        c.set_white_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -1.15f, 0f);
        glu.gluSphere(q, WIDTH_BODY, SLICES, STACKS);
        gl.glPopMatrix();

//        //Pies
//        c.set_grey_material(gl);
//        gl.glPushMatrix();
//        gl.glTranslatef(-0.035f, -1.75f, 0.25f);
//        glu.gluSphere(q, 0.15f, SLICES, STACKS);
//        gl.glPopMatrix();
        //Botones
        c.set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.3f, 0.5f);
        gl.glRotated(165, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOTON, SLICES, STACKS);
        gl.glPopMatrix();

        c.set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.50f, 0.50f);
        gl.glRotated(190, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOTON, SLICES, STACKS);
        gl.glPopMatrix();

        c.set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.9f, 0.45f);
        gl.glRotated(155, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOTON, SLICES, STACKS);
        gl.glPopMatrix();

        c.set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -1.2f, 0.50f);
        gl.glRotated(180, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOTON, SLICES, STACKS);
        gl.glPopMatrix();

    }

    public void draw_pieD(GL gl, GLU glu, GLUquadric q, float WIDTH_FOOTS, int SLICES, int STACKS, boolean jump)
    {
        if (jump)
        {
            c.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.2f, -1.50f, 0.5f);
            glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
            gl.glPopMatrix();

            c.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.345f, -1.40f, 0.5f);
            glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
            gl.glPopMatrix();
        } else
        {
            c.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.2f, -1.70f, 0f);
            glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
            gl.glPopMatrix();

            c.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(0.345f, -1.70f, 0f);
            glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
            gl.glPopMatrix();
        }
    }

    public void draw_pieI(GL gl, GLU glu, GLUquadric q, float WIDTH_FOOTS, int SLICES, int STACKS, boolean jump)
    {
        if (jump)
        {
            c.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.2f, -1.50f, 0.5f);
            glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
            gl.glPopMatrix();

            c.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.35f, -1.40f, 0.5f);
            glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
            gl.glPopMatrix();
        } else
        {
            c.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.2f, -1.70f, 0f);
            glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
            gl.glPopMatrix();

            c.set_white_material(gl);
            gl.glPushMatrix();
            gl.glTranslatef(-0.02f, -1.70f, 0f);
            glu.gluSphere(q, WIDTH_FOOTS, SLICES, STACKS);
            gl.glPopMatrix();
        }
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

}
