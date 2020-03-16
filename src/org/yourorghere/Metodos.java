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

        // Ojos
        c.set_black_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.045f, 0.49f, 0.205f);
        glu.gluSphere(q, WIDTH_EYES, SLICES, STACKS);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslatef(0.045f, 0.49f, 0.205f);
        glu.gluSphere(q, WIDTH_EYES, SLICES, STACKS);
        gl.glPopMatrix();

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
            int SLICES, int STACKS)
    {
        c.set_red_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, 0.3f, 0.25f);
        gl.glRotated(160, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, DISK_BOCA, SLICES, STACKS);
        gl.glPopMatrix();
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
            float TOP_BRAZO, float HEIGHT_BRAZO, int SLICES, int STACKS)
    {
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

    public void draw_brazoI(GL gl, GLU glu, GLUquadric q, float RADIO_BRAZO, 
            float TOP_BRAZO, float HEIGHT_BRAZO, int SLICES, int STACKS)
    {
        c.set_orange_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.9f, -0.15f, 0f);
        gl.glRotatef(90, 1, 1, 1);
        glu.gluCylinder(q, RADIO_BRAZO, TOP_BRAZO, HEIGHT_BRAZO, SLICES, STACKS);
        gl.glPopMatrix();
        //Mano izquierda
        c.set_red_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.9f, -0.15f, 0f);
        glu.gluSphere(q, 0.125f, SLICES, STACKS);
        gl.glPopMatrix();
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

}
