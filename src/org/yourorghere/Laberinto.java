/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import java.util.ArrayList;
import javax.media.opengl.GL;

/**
 *
 * @author deivi
 */
public class Laberinto {

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

    public Cube piso, p1, p2, p3, p4, p41, p21;
    public Cube pz1, pz2, pz3, pz4, pz5, pz6, pz7;
    public Cube p1z, p2z, p3z, p4z, p5z, p6z, p7z, p8z;
    public Cube px1, px2, px3, px4, px5, px6;
    public Cube p1x, p2x, p3x, p4x, p5x, p6x, p7x, p8x;

    public ArrayList<Cube> paredesX;
    public ArrayList<Cube> paredesZ;

    private Texture floor, paredes, cerramiento;

    public static int nivel = 0;

    public Laberinto(GL gl, float x, float y, float z, float w, float h, float d, float r, float g, float b, int nivel) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.h = h;
        this.d = d;
        this.r = r;
        this.g = g;
        this.b = b;
        this.nivel = nivel;

        switch (nivel) {
            case 1:
                try {
                    floor = TextureIO.newTexture(new File("src/imagenes/textura2.jpg"), true);
                    cerramiento = TextureIO.newTexture(new File("src/imagenes/textura3.jpg"), true);
                    paredes = TextureIO.newTexture(new File("src/imagenes/textura.jpg"), true);

                } catch (Exception e) {
                    System.out.println("Error con la textura we: " + e);
                    System.exit(1);
                }

                piso = new Cube(gl, x, y, z, w * 4, h / 5, d * 4, 1f, 1f, 1f, 1f, floor);
                p1 = new Cube(gl, x - w * 2f, y + h / 2, z, w / 20, h, d * 4, 1f, 1f, 1f, 1f, cerramiento); //Paredes laterales
                p2 = new Cube(gl, x - 0.5f, y + h / 2, z - d * 2, w * 3, h, d / 20, 1f, 1f, 1f, 1f, cerramiento);
                p3 = new Cube(gl, x + w * 2, y + h / 2, z, w / 20, h, d * 4, 1f, 1f, 1f, 1f, cerramiento);
                p4 = new Cube(gl, x - 1.2f, y + h / 2, z + d * 2, w * 1.6f, h, d / 20, 1f, 1f, 1f, 1f, cerramiento);
                p41 = new Cube(gl, x + 1.2f, y + h / 2, z + d * 2, w * 1.6f, h, d / 20, 1f, 1f, 1f, 1f, cerramiento);

                //Paredes Laterales de la parte superior
                pz1 = new Cube(gl, x - 1.3f, y + h / 2, z + 1.3f, w * 1.4f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz2 = new Cube(gl, x - 1.3f, y + h / 2, z + 1f, w / 2, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz3 = new Cube(gl, x - 1.3f, y + h / 2, z + 0.5f, w * 0.6f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz4 = new Cube(gl, x - 1.1f, y + h / 2, z, w + 0.2f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz5 = new Cube(gl, x - 0.75f, y + h / 2, z - 0.5f, w + 0.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz6 = new Cube(gl, x - 0.6f, y + h / 2, z - 0.8f, w * 0.6f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz7 = new Cube(gl, x - 1.2f, y + h / 2, z - 1f, w * 0.6f, h, d / 20, 1f, 1f, 1f, 1f, paredes);

                //Paredes laterales parte inferior
                p1z = new Cube(gl, x + 1.2f, y + h / 2.5f, z + 1.5f, w + 0.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p2z = new Cube(gl, x + 1.5f, y + h / 2, z + 1f, w / 2f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p3z = new Cube(gl, x + 0.75f, y + h / 2, z + 0.25f, w * 1.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p4z = new Cube(gl, x + 1.6f, y + h / 2, z - 0.25f, w * 0.8f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p5z = new Cube(gl, x + 0.5f, y + h / 2, z - 0.25f, w * 0.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p6z = new Cube(gl, x + 0.5f, y + h / 2, z - 1f, w * 1.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p7z = new Cube(gl, x + 0.2f, y + h / 2, z - 1.5f, w * 1.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p8z = new Cube(gl, x + 1.5f, y + h / 2, z - 1.5f, w / 2f, h, d / 20, 1f, 1f, 1f, 1f, paredes);

                //Paredes horizontales parte superior
                px1 = new Cube(gl, x - 0.6f, y + h / 2, z + 0.6f, w / 20, h, d * 1.2f, 1f, 1f, 1f, 1f, paredes);/////////////////////////??????????????????
                px2 = new Cube(gl, x - 1.6f, y + h / 2, z + 0.4f, w / 20f, h, d * 1.2f, 1f, 1f, 1f, 1f, paredes);
                px3 = new Cube(gl, x - 1.5f, y + h / 2, z + 0.8f, w / 20f, h, d * 0.6f, 1f, 1f, 1f, 1f, paredes);
                px4 = new Cube(gl, x - 0.9f, y + h / 2, z - 1.4f, w / 20f, h, d + 0.2f, 1f, 1f, 1f, 1f, paredes);
                px5 = new Cube(gl, x, y + h / 2, z + 0.5f, w / 20f, h, d * 1, 1f, 1f, 1f, 1f, paredes);

                //Paredes horizontales parte inferior
                p1x = new Cube(gl, x + 0.75f, y + h / 2, z + 1.1f, w / 20, h, d * 1.1f, 1f, 1f, 1f, 1f, paredes);/////////////////////////??????????????????
                p2x = new Cube(gl, x + 1.4f, y + h / 2, z + 0.8f, w / 20, h, d / 2, 1f, 1f, 1f, 1f, paredes);
                p3x = new Cube(gl, x + 0.3f, y + h / 2, z - 0.25f, w / 20, h, d / 2, 1f, 1f, 1f, 1f, paredes);
                p4x = new Cube(gl, x + 1f, y + h / 2, z - 0.6f, w / 20, h, d, 1f, 1f, 1f, 1f, paredes);
                p5x = new Cube(gl, x - 0.1f, y + h / 2, z - 1.6f, w / 20, h, d * 0.8f, 1f, 1f, 1f, 1f, paredes);
                p6x = new Cube(gl, x + 1.8f, y + h / 2, z - 1.6f, w / 20, h, d * 0.8f, 1f, 1f, 1f, 1f, paredes);
                break;

            case 2:
                try {
                    floor = TextureIO.newTexture(new File("src/imagenes/t1n2.jpg"), true);
                    cerramiento = TextureIO.newTexture(new File("src/imagenes/t2n2.jpg"), true);
                    paredes = TextureIO.newTexture(new File("src/imagenes/t3n2.jpg"), true);

                } catch (Exception e) {
                    System.out.println("Error con la textura we: " + e);
                    System.exit(1);
                }

                piso = new Cube(gl, x, y, z, w * 4, h / 5, d * 4, 1f, 1f, 1f, 1f, floor);
                p1 = new Cube(gl, x - w * 2f, y + h / 2, z, w / 20, h, d * 4, 1f, 1f, 1f, 1f, cerramiento); //Paredes laterales
                p2 = new Cube(gl, x - 0.5f, y + h / 2, z - d * 2, w * 3, h, d / 20, 1f, 1f, 1f, 1f, cerramiento);
                p3 = new Cube(gl, x + w * 2, y + h / 2, z, w / 20, h, d * 4, 1f, 1f, 1f, 1f, cerramiento);
                p4 = new Cube(gl, x - 1.2f, y + h / 2, z + d * 2, w * 1.6f, h, d / 20, 1f, 1f, 1f, 1f, cerramiento);
                p41 = new Cube(gl, x + 1.2f, y + h / 2, z + d * 2, w * 1.6f, h, d / 20, 1f, 1f, 1f, 1f, cerramiento);

                //Paredes Laterales de la parte superior
                pz1 = new Cube(gl, x - 1.3f, y + h / 2, z + 0.7f, w * 1.4f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz2 = new Cube(gl, x - 1.3f, y + h / 2, z + 1f, w / 2, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz3 = new Cube(gl, x - 1.3f, y + h / 2, z + 0.15f, w * 0.6f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz4 = new Cube(gl, x - 1.1f, y + h / 2, z - 0.75f, w + 0.9f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz5 = new Cube(gl, x - 0.75f, y + h / 2, z - 0.3f, w + 0.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz6 = new Cube(gl, x - 0.6f, y + h / 2, z - 0.8f, w * 0.6f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                pz7 = new Cube(gl, x - 1.2f, y + h / 2, z - 1f, w * 0.6f, h, d / 20, 1f, 1f, 1f, 1f, paredes);

                //Paredes laterales parte inferior
                p1z = new Cube(gl, x + 1.2f, y + h / 2, z + 0.7f, w + 0.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p2z = new Cube(gl, x + 1.5f, y + h / 2, z + 1f, w / 2f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p3z = new Cube(gl, x + 0.65f, y + h / 2, z + 0.25f, w * 1.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p4z = new Cube(gl, x + 1.6f, y + h / 2, z - 0.25f, w * 0.8f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p5z = new Cube(gl, x + 0.63f, y + h / 2, z - 0.25f, w * 0.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p6z = new Cube(gl, x + 0.5f, y + h / 2, z - 1f, w * 1.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p7z = new Cube(gl, x + 0.2f, y + h / 2, z - 1.5f, w * 1.5f, h, d / 20, 1f, 1f, 1f, 1f, paredes);
                p8z = new Cube(gl, x + 1.2f, y + h / 2, z - 1.25f, w / 2f, h, d / 20, 1f, 1f, 1f, 1f, paredes);

                //Paredes horizontales parte superior
                px1 = new Cube(gl, x - 1.2f, y + h / 2, z + 0.6f, w / 20, h, d * 1.2f, 1f, 1f, 1f, 1f, paredes);/////////////////////////??????????????????
                px2 = new Cube(gl, x - 0.4f, y + h / 2, z + 0.6f, w / 20, h, d * 1.2f, 1f, 1f, 1f, 1f, paredes);
                px3 = new Cube(gl, x - 0.1f, y + h / 2, z + 0.9f, w / 20, h, d * 1.2f, 1f, 1f, 1f, 1f, paredes);
                px4 = new Cube(gl, x - 0.1f + 1.3f, y + h / 2, z + 0.6f, w / 20, h, d * 1.2f, 1f, 1f, 1f, 1f, paredes);
                px5 = new Cube(gl, x - 0.05f + 1.3f, y + h / 2, z + 0.6f, w / 20, h, d * 1.2f, 1f, 1f, 1f, 1f, paredes);

                //Paredes horizontales parte inferior
                p1x = new Cube(gl, x + 0.75f, y + h / 2, z + 1.1f, w / 20, h, d * 1.1f, 1f, 1f, 1f, 1f, paredes);/////////////////////////??????????????????
                p2x = new Cube(gl, x + 0.40f, y + h / 2, z + 1.1f, w / 20, h, d * 1.1f, 1f, 1f, 1f, 1f, paredes);
                p3x = new Cube(gl, x + 0.3f, y + h / 2, z - 0.25f, w / 20, h, d / 2, 1f, 1f, 1f, 1f, paredes);
                p4x = new Cube(gl, x + 1.3f, y + h / 2, z - 1f, w / 20, h, d, 1f, 1f, 1f, 1f, paredes);
                p5x = new Cube(gl, x - 0.1f, y + h / 2, z - 1.6f, w / 20, h, d * 0.8f, 1f, 1f, 1f, 1f, paredes);
                p6x = new Cube(gl, x + 1.8f, y + h / 2, z - 1.6f, w / 20, h, d * 0.8f, 1f, 1f, 1f, 1f, paredes);
                break;
            case 3:
                break;
        }

        paredesX = new ArrayList<Cube>();
        paredesX.add(px1);
        paredesX.add(px2);
        paredesX.add(px3);
        paredesX.add(px4);
        paredesX.add(px5);
        paredesX.add(p1x);
        paredesX.add(p2x);
        paredesX.add(p3x);
        paredesX.add(p4x);
        paredesX.add(p5x);
        paredesX.add(p6x);
        paredesX.add(p1);
        paredesX.add(p3);

        paredesZ = new ArrayList<Cube>();
        paredesZ.add(pz1);
        paredesZ.add(pz2);
        paredesZ.add(pz3);
        paredesZ.add(pz4);
        paredesZ.add(pz5);
        paredesZ.add(pz6);
        paredesZ.add(pz7);
        paredesZ.add(p1z);
        paredesZ.add(p2z);
        paredesZ.add(p3z);
        paredesZ.add(p4z);
        paredesZ.add(p5z);
        paredesZ.add(p6z);
        paredesZ.add(p7z);
        paredesZ.add(p8z);
        paredesZ.add(p2);
        paredesZ.add(p4);
        paredesZ.add(p41);

    }

    public void DrawFloor() {
        for (Cube v : paredesX) {
            v.DrawCube();
        }
        for (Cube v : paredesZ) {
            v.DrawCube();
        }
        piso.DrawCube();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

}
