package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MCowboy extends JFrame implements GLEventListener, KeyListener, MouseListener, MouseMotionListener
{

    private boolean[] keys = new boolean[256];
    private static boolean sounds;
    private float viewRotX = 0.01f;
    private float viewRotY = 0.01f;
    private float posX = 0f;
    private float posY = -0.08f;
    private float posZ = 0f;
    private int oldMouseX;
    private int oldMouseY;
    private DrawCowboy tepe;
    private static Clip music, mWalk, mJump;
    private static JComboBox cmbx;
    Texture t;
    //sonidos
    //AudioStream audio;
    //static InputStream sounds;
    Clip clip;
    String sonido = "src/sonidos/sonido.wav";
    String sonido1 = "src/sonidos/sonido1.wav";
    String sonido2 = "src/sonidos/sonido2.wav";
    String sonido3 = "src/sonidos/sonido3.wav";
    String sonido4 = "src/sonidos/sonido4.wav";
    public static ImageIcon imagen = new ImageIcon("src/fondos/espacio1.jpg");

    int p;
    int n;

    public MCowboy(int p, int n)
    {
        setTitle("SnowMan's Adventure!");
        setSize(960, 550);
        //Generamos al centro de la pantalla con null
        setLocationRelativeTo(null);
        this.p = p;
        this.n = n;

    }

    public static void main(String[] args)
    {
        Frame frame = new Frame("Presiona W o J/ sonidos W,J,S,R,H");
        try
        {
            music = AudioSystem.getClip();
            AudioInputStream isMusic = AudioSystem.getAudioInputStream(new File("src/sonidos/granja.wav"));
            music.open(isMusic);

            mWalk = AudioSystem.getClip();
            AudioInputStream isWalk = AudioSystem.getAudioInputStream(new File("src/sonidos/Caminar.wav"));
            mWalk.open(isWalk);

            mJump = AudioSystem.getClip();
            AudioInputStream isJump = AudioSystem.getAudioInputStream(new File("src/sonidos/jump.wav"));
            mJump.open(isJump);
        } catch (Exception e)
        {
        }
        sounds = true;
        cmbx = new JComboBox();
        cmbx.addItem("----------");
        cmbx.addItem("Enable Music");
        cmbx.addItem("Disable Music");
        cmbx.addItem("Enable Sounds");
        cmbx.addItem("Disable Sounds");
        cmbx.setVisible(true);
        cmbx.setForeground(Color.BLACK);
        cmbx.setBounds(10, 30, 120, 30);
        cmbx.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (((String) cmbx.getSelectedItem()).equals("Enable Music"))
                {
                    music.start();
                    System.out.println("Music started");
                } else if (((String) cmbx.getSelectedItem()).equals("Disable Music"))
                {
                    music.stop();
                    System.out.println("Music stoped");
                } else if (((String) cmbx.getSelectedItem()).equals("Enable Sounds"))
                {
                    sounds = true;
                } else if (((String) cmbx.getSelectedItem()).equals("Disable Sounds"))
                {
                    sounds = false;
                }
            }
        });
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new MCowboy(0,0));
        //frame.add(cmbx);
        frame.add(canvas);
        frame.setSize(900, 675);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);
        // ##### Configurar luces de ambiente
        float light_ambient[] =
        {
            0.9f, 0.9f, 0.9f, 1.0f
        };
        float light_diffuse[] =
        {
            0.8f, 0.8f, 0.8f, 1.0f
        };
        float light_specular[] =
        {
            1.0f, 1.0f, 1.0f, 1.0f
        };
        float light_position[] =
        {
            .40f, .5f, -.30f, 0.0f
        };
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);

        try
        {
            File im = new File("src/fondos/espacio1.jpg");
            t = TextureIO.newTexture(im, true);
        } catch (IOException ex)
        {
            Logger.getLogger(MCowboy.class.getName()).log(Level.SEVERE, null, ex);
        }

        gl.glShadeModel(GL.GL_SMOOTH);
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        //#####Configurar color de fondo y agregar métodos escuchadores del mouse
        gl.glClearColor(0.670f, 1.0f, 0.250f, 0.0f); //fondo  0.5f, 0.5f,0.5f,1.0f
        gl.glShadeModel(GL.GL_SMOOTH);
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        //##### Iniciar dios Tepeyollotl     
        tepe = new DrawCowboy(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0)
        {
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
        glu.gluLookAt(0.1f, 0.0f, 4.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f);
        //##### Cambiar persepectiva

        //##### Dibujar dios Tepeyollot
        DrawCowboy dc = new DrawCowboy();
        //gl.glScalef (0.85f,0.85f,0.85f);
        gl.glTranslatef(posX, posY, posZ);
        gl.glRotatef(viewRotX, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(viewRotY, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);

        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 0.5);
        gl.glTranslated(0f, 0f, 0f);
//        gl.glRotatef(-0, 0f, 1.0f, 0.0f);
        dc.fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(1.0, .9, 1.0);
        gl.glTranslated(0f, -0f, -1f);
        gl.glRotatef(-90, 1f, 0.0f, 0.0f);
        dc.fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 1.0);
//        gl.glTranslated(-1f, -1f, -1f);
        gl.glRotatef(90, 1f, 0.0f, 0.0f);
        dc.fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 1.0);
//        gl.glTranslated(-1f, -1f, -1f);
        gl.glRotatef(90, 0f, 1.0f, 0.0f);
        dc.fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();
//        
        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 1.0);
//        gl.glTranslated(-1f, -1f, -1f);
        gl.glRotatef(-90, 0f, 1.0f, 0.0f);
        dc.fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glPushMatrix();
        gl.glScaled(1.0, 0.9, 1.0);
        gl.glTranslated(-1f, -1f, 10f);
        gl.glRotatef(-0, 0f, .0f, 1.0f);
        dc.fondo(gl, glu, t);
        gl.glPopMatrix();
        gl.glFlush();

        gl.glTranslatef(.30f, .30f, .30f);
        gl.glScalef(0.40f, 0.40f, 0.40f);
        tepe.draw(gl, keys['W'], keys['J']);

        //End of drawing
        gl.glFlush();
    }

    //##### Métodos para mouse
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
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
        viewRotX += thetaX;
        viewRotY += thetaY;
    }

    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
        System.out.println("Key: " + e.getKeyChar() + " ON");
        System.out.println("key press" + e.getKeyChar());
        switch (e.getKeyCode())
        {
            case 'J':
                reproducir(sonido1);
                break;
            case 'S':
                reproducir(sonido2);
                break;
            case 'R':
                reproducir(sonido3);
                break;
            case 'H':
                reproducir(sonido4);
                break;
            case 'W':
                reproducir(sonido);
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
        System.out.println("Key: " + e.getKeyChar() + " OFF");
    }

    private void reproducir(String efecto)
    {
        try
        {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(efecto)));
            clip.start();
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }

    }

}
