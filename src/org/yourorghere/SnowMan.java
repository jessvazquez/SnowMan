package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import sun.audio.AudioStream;

/**
 * SnowMan.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class SnowMan extends JFrame implements GLEventListener,
        KeyListener, MouseListener, MouseMotionListener
{

    private float view_rotx = 0.01f;
    private float view_roty = 0.01f;
    private int oldMouseX;
    private int oldMouseY;
    boolean[] keys = new boolean[256]; //to know which key is pressed

    //position of m in the window
    private static final float X_POSITION = 0f;
    private static final float Y_POSITION = -0.08f;
    private static final float Z_POSITION = 0f;

    //Sonidos
    AudioStream audio;
    static InputStream sounds;
    Clip clip;

    String jump = "src/sonidos/jump.wav";

    public static ImageIcon imagen = new ImageIcon("src/fondos/paisaje.jpg");
    Texture t;

    public static void main(String[] args)
    {
        Frame frame = new Frame("SnowMan");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new SnowMan());
        frame.add(canvas);
        frame.setSize(720, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent e)
            {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable()
                {

                    public void run()
                    {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);
        // set up lighting
        float light_ambient[] =
        {
            0.9f, 0.9f, 0.9f, 1.0f
        };
        float light_diffuse[] =
        {
            0.3f, 0.3f, 0.3f, 1.0f
        };
        float light_specular[] =
        {
            0.0f, 0.0f, 0.0f, 0.0f
        };
        float light_position[] =
        {
            1.0f, 1.5f, 1.0f, 0.0f
        };
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.9f, 0.9f, 0.9f, 0.9f);

        try
        {
            File im = new File("src/fondos/paisaje.jpg");
            t = TextureIO.newTexture(im, true);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        gl.glShadeModel(GL.GL_SMOOTH);
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0)
        { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);

        gl.glLoadIdentity();
        glu.gluLookAt(0.1f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);

        Metodos m = new Metodos();
        DibujaSnowMan dsm = new DibujaSnowMan();

        //mueve la escena
        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(90, 0.0f, 0.0f, 1.0f);

//        gl.glPushMatrix();
//        gl.glScaled(1.0, 0.9, 0.5);
//        gl.glTranslated(0f, 0f, 0f);
//        gl.glRotatef(-0, 0f, 1.0f, 0.0f);
//        m.fondo(gl, glu, t);
//        gl.glPopMatrix();
//        gl.glFlush();
//
//        gl.glPushMatrix();
//        gl.glScaled(1.0, .9, 1.0);
//        gl.glTranslated(0f, -0f, -1f);
//        gl.glRotatef(-90, 1f, 0.0f, 0.0f);
//        m.fondo(gl, glu, t);
//        gl.glPopMatrix();
//        gl.glFlush();
//
//        gl.glPushMatrix();
//        gl.glScaled(1.0, 0.9, 1.0);
////        gl.glTranslated(-1f, -1f, -1f);
//        gl.glRotatef(90, 1f, 0.0f, 0.0f);
//        m.fondo(gl, glu, t);
//        gl.glPopMatrix();
//        gl.glFlush();
//
//        gl.glPushMatrix();
//        gl.glScaled(1.0, 0.9, 1.0);
////        gl.glTranslated(-1f, -1f, -1f);
//        gl.glRotatef(90, 0f, 1.0f, 0.0f);
//        m.fondo(gl, glu, t);
//        gl.glPopMatrix();
//        gl.glFlush();
////        
//        gl.glPushMatrix();
//        gl.glScaled(1.0, 0.9, 1.0);
////        gl.glTranslated(-1f, -1f, -1f);
//        gl.glRotatef(-90, 0f, 1.0f, 0.0f);
//        m.fondo(gl, glu, t);
//        gl.glPopMatrix();
//        gl.glFlush();
//
//        gl.glPushMatrix();
//        gl.glScaled(1.0, 0.9, 1.0);
//        gl.glTranslated(-1f, -1f, 10f);
//        gl.glRotatef(-0, 0f, .0f, 1.0f);
//        m.fondo(gl, glu, t);
//        gl.glPopMatrix();
//        gl.glFlush();
//        gl.glTranslated(0f, -0.65f, 0f);
        //  gl.glScalef(0.3f, 0.3f, 0f);
        gl.glScalef(0.85f, 0.85f, 0.85f);
        dsm.dibujaBody(gl, glu, keys['J'], keys['W']);
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
    {
    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() < 250 && keys[e.getKeyCode()] == false)
        {
            keys['W'] = false;
            keys['J'] = false;
            keys[e.getKeyCode()] = true;
        } else
        {
            keys[e.getKeyCode()] = false;
        }
        System.out.println("key press " + e.getKeyChar());
        switch (e.getKeyCode())
        {
            case 'J':
                reproducir(jump);
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaX = 360.0f * ((float) (x - oldMouseX) / (float) size.width);
        float thetaY = 360.0f * ((float) (oldMouseY - y) / (float) size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
    }

    public Insets getBorderInsets(Component c)
    {
        return null;
    }

    public boolean isBorderOpaque()
    {
        return false;
    }

    public void reproducir(String efecto)
    {
        try
        {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(efecto)));
            clip.start();
            // clip.loop(1000);

        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
