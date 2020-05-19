/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.javafx.geom.Vec3f;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author deivi
 */
class Keyboard implements KeyListener
{

    private GLCanvas canvas;
    public boolean[] keybuffer;

    GLU glu = new GLU();

    Keyboard(GLCanvas canvas)
    {
        this.canvas = canvas;
        keybuffer = new boolean[256];
    }

    public void keyTyped(KeyEvent e)
    {

        if (keybuffer['w'])
        {
            GLRenderer.jugador.avanzar();

        }
        if (keybuffer['a'])
        {
            GLRenderer.jugador.izquierda();
        }
        if (keybuffer['s'])
        {
            GLRenderer.jugador.retroceder();
        }
        if (keybuffer['d'])
        {
            GLRenderer.jugador.derecha();
        }
        if (e.getKeyChar() == 'p')
        {
            GLRenderer.option = 1;
            System.out.println("p");

        }
        if (e.getKeyChar() == 'o')
        {
            GLRenderer.option = 2;
            System.out.println("o");
        }
    }

    public void keyPressed(KeyEvent e)
    {
        keybuffer[e.getKeyChar()] = true;
    }

    public void keyReleased(KeyEvent e)
    {
        keybuffer[e.getKeyChar()] = false;

    }

}
