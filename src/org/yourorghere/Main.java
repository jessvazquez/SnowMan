package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureCoords;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class Main extends JFrame implements GLEventListener,
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

    //Fondos
    private static String f = "paisaje";
    private File arch;
    private Texture texture;
    private boolean newTexture = true;
    float v = (float) 4.0;
    float l = (float) 0.0;

    //Sonidos
    // AudioStream audio;
    //static InputStream sounds;
    Clip clip;

    String jump = "src/sonidos/jump.wav";
    String zoomin = "src/sonidos/zoomin.wav";
    String boss = "src/sonidos/boss.wav";
    String bloop = "src/sonidos/bloop.wav";

    String blessure = "src/sonidos/blessure.wav";
    String bowser = "src/sonidos/bowser.wav";
    String coinhit = "src/sonidos/coinhit.wav";
    String select = "src/sonidos/select.wav";

    public static ImageIcon imagen = new ImageIcon("src/fondos/paisaje.jpg");
    Texture t;

    public static void main(String[] args)
    {
        Frame frame = new Frame("'J' to jump, 'S' to Scale, 'R' to reflexion Y, "
                + "'W' to walk, 'O' to original, 'Y' rotation X,'Z',"
                + "'T' to traslation,'I' to Corte Y,'U' to reflexion Z, 'Z'Rotation Z,"
                + "'X' rotation Y");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Main());
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

//        try
//        {
//            File im = new File(paisaje);
//            t = TextureIO.newTexture(im, true);
//        } catch (IOException ex)
//        {
//            ex.printStackTrace();
//        }
      //  reproducir(boss);

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

        DibujaSnowMan dsm = new DibujaSnowMan();
        //mueve la escena
        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(90, 0.0f, 0.0f, 1.0f);

        //Fondos
        texturaFondo(gl);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);

        gl.glScalef(0.85f, 0.85f, 0.85f);
        //dsm.dibujaBody(gl, glu, keys['J'], keys['W'], keys['S'], keys['R']);
        dsm.dibujaBody(gl, glu, keys['J'], keys['W'], keys['S'], keys['R'],
                keys['O'], keys['X'], keys['Y'], keys['Z'], keys['T'],
                keys['U'], keys['I'], keys['B'], keys['N'], keys['M'], keys['P']);
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
            keys['S'] = false;
            keys['R'] = false;
            keys['O'] = false;
            keys['X'] = false;
            keys['Y'] = false;
            keys['Z'] = false;
            keys['T'] = false;
            keys['U'] = false;
            keys['I'] = false;
            keys['B'] = false;
            keys['N'] = false;
            keys['M'] = false;
            keys['P'] = false;

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
                f = "paisaje3";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'S':
                reproducir(zoomin);
                f = "paisaje2";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'R':
                reproducir(bloop);
                f = "paisaje4";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'W':
                reproducir(blessure);
                f = "paisaje5";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'X':
                reproducir(bowser);
                f = "paisaje7";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'Y':
                reproducir(bowser);
                f = "paisaje7";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'Z':
                reproducir(bowser);
                f = "paisaje8";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'T':
                reproducir(select);
                f = "paisaje9";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'U':
                reproducir(select);
                f = "paisaje10";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'I':
                reproducir(select);
                f = "paisaje11";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'B':
                f = "paisaje12";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'N':
                f = "paisaje13";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'M':
                f = "paisaje14";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'O':
                f = "paisaje";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
            case 'P':
                f = "paisaje4";
                v = (float) 4.0;
                l = (float) 0.0;
                newTexture = true;
                break;
        }
    }

    public void texturaFondo(GL gl)
    {
        arch = new File("src/fondos/" + f + ".jpg");
        if (newTexture)
        {
            newTexture = false;

            if (texture != null)
            {
                texture.dispose();
                texture = null;
            }

            try
            {
                System.err.println("Loading texture...");
                texture = TextureIO.newTexture(arch, true);
                System.err.println("Texture estimated memory size = " + texture.getEstimatedMemorySize());
            } catch (IOException e)
            {
                e.printStackTrace();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(bos));
                JOptionPane.showMessageDialog(null,
                        bos.toString(),
                        "Error loading texture",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (texture != null)
        {
            texture.enable();
            texture.bind();
            gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
            TextureCoords coords = texture.getImageTexCoords();

            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(coords.left(), coords.bottom());
            gl.glVertex3f(3.2f, -2.5f, -1f);
            gl.glTexCoord2f(coords.right(), coords.bottom());
            gl.glVertex3f(-3.2f, -2.5f, -1f);
            gl.glTexCoord2f(coords.right(), coords.top());
            gl.glVertex3f(-3.2f, 2.5f, -1f);
            gl.glTexCoord2f(coords.left(), coords.top());
            gl.glVertex3f(3.3f, 2.5f, -1f);
            gl.glEnd();
            texture.disable();
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
