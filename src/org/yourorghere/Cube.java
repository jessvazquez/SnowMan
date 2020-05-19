/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;

/**
 *
 * @author deivi
 */
public class Cube
{

    private GL gl;
    public float x;
    public float y;
    public float z;
    public float w; //Ancho
    public float h; //Alto
    public float d; //Profundidad
    public float r; //Rojo
    public float g; //Verde
    public float b; //Azul
    public float angle; //Angulo de rotacion
    private Texture cara;

    public Cube(GL gl, float x, float y, float z, float w, float h, float d, float r, float g, float b, float angle, Texture cara)
    {
        this.gl = gl;
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
        this.r = r;
        this.g = g;
        this.b = b;
        this.angle = angle;
        this.cara = cara;
    }



//    Cube(GL gl, float x, float y, float z, float f, float f0, float f1, int i, float f2, float f3, float f4)
//    {
//        
//    }

    public void drawCube(float a)
    {
        gl.glPushMatrix();

        gl.glTranslatef(x, y, z); //Trasladamos el eje de puntos al centro
        gl.glRotatef(angle, 0, 1, 0); //
        gl.glTranslatef(-x, -y, -z); //Nos permite volver al origen

        cara.enable();
        cara.bind();
        gl.glNormal3f(0, 0, 1);
        gl.glBegin(GL.GL_QUADS);//Cara Frontal
        gl.glColor3f(r, g, b);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(x - w / 2, y + h / 2, z - d / 2);
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(x - w / 2, y - h / 2, z - d / 2);
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(x + w / 2, y - h / 2, z - d / 2);
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(x + w / 2, y + h / 2, z - d / 2);
        gl.glEnd();
        cara.disable();

        cara.enable();
        cara.bind();
        gl.glNormal3f(0, 0, -1);
        gl.glBegin(GL.GL_QUADS);//Cara Trasera
        gl.glColor3f(r, g, b);
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(x - w / 2, y + h / 2, z + d / 2);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(x - w / 2, y - h / 2, z + d / 2);
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(x + w / 2, y - h / 2, z + d / 2);
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(x + w / 2, y + h / 2, z + d / 2);
        gl.glEnd();
        cara.disable();

        cara.enable();
        cara.bind();
        gl.glNormal3f(-1, 0, 0);
        gl.glBegin(GL.GL_QUADS);//Cara Izquierda
        gl.glColor3f(r, g, b);
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(x - w / 2, y + h / 2, z - d / 2);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(x - w / 2, y - h / 2, z - d / 2);
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(x - w / 2, y - h / 2, z + d / 2);
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(x - w / 2, y + h / 2, z + d / 2);
        gl.glEnd();
        cara.disable();

        cara.enable();
        cara.bind();
        gl.glNormal3f(1, 0, 0);
        gl.glBegin(GL.GL_QUADS);//Cara Derecha
        gl.glColor3f(r, g, b);
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(x + w / 2, y + h / 2, z - d / 2);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(x + w / 2, y - h / 2, z - d / 2);
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(x + w / 2, y - h / 2, z + d / 2);
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(x + w / 2, y + h / 2, z + d / 2);
        gl.glEnd();
        cara.disable();

        cara.enable();
        cara.bind();
        gl.glNormal3f(0, 1, 0);
        gl.glBegin(GL.GL_QUADS);//Cara Arriba
        gl.glColor3f(r, g, b);
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(x - w / 2, y + h / 2, z - d / 2);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(x + w / 2, y + h / 2, z - d / 2);
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(x + w / 2, y + h / 2, z + d / 2);
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(x - w / 2, y + h / 2, z + d / 2);
        gl.glEnd();
        cara.disable();

        cara.enable();
        cara.bind();
        gl.glNormal3f(0, -1, 0);
        gl.glBegin(GL.GL_QUADS);//Cara Abajo
        gl.glColor3f(r, g, b);
        gl.glTexCoord2f(0, 1);
        gl.glVertex3f(x - w / 2, y - h / 2, z - d / 2);
        gl.glTexCoord2f(0, 0);
        gl.glVertex3f(x + w / 2, y + h / 2, z - d / 2);
        gl.glTexCoord2f(1, 0);
        gl.glVertex3f(x + w / 2, y - h / 2, z + d / 2);
        gl.glTexCoord2f(1, 1);
        gl.glVertex3f(x - w / 2, y - h / 2, z + d / 2);
        gl.glEnd();
        cara.disable();

        gl.glPopMatrix();

    }

    public void DrawCube()
    {
        drawCube(0);
    }

    public float getAngle()
    {
        return angle;
    }

    public void setAngle(float angle)
    {
        this.angle = angle;
    }

    /**
     * @return the x
     */
    public float getX()
    {
        return x;
    }

    public float getW()
    {
        return w;
    }

    /**
     * @return the z
     */
    public float getZ()
    {
        return z;
    }

    public float getD()
    {
        return d;
    }

}
