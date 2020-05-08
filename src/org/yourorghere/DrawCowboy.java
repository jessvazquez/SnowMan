package org.yourorghere;
import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class DrawCowboy {
    private static final int SLICES = 40;//
    private static final int STACKS = 40;//
    private static final float HEIGHT_BODY = 2.2f;//
    private static final float TOP_BODY = 0.90f;//.90
    private static final float BOTTOM_BODY = 0.90f;//.90
    private static final float RADIO_OJOS = 0.35f;
    private static final float RADIO_OJOS2 = 0.05f;
    
    private GLUquadric q = null;
    private float iX, iY, rY, iZ;
    private boolean pair_odd;
    private int slc_stck = 100;
    private float size;
   
    private float radius_head;
    private float radius_eyes, radius_pupile;
    private float width_eyebrow, height_eyebrow;
    private float width_neck, height_neck;
    private float width_ear, height_ear;
    private float radius_mouth;
    private float width_body, height_body;
    private static final float WIDTH_PUPILS = 0.03f;
    
    
  public DrawCowboy(){
      
  }
    public DrawCowboy(float x, float y, float z, float sz)
    {
        iX = x; iY = y; rY = y; iZ = z; size = sz; pair_odd = false;
    }
    
    public void draw(GL gl, boolean walk, boolean jump)
    {
        GLU glu = new GLU();
        q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);
        iY = rY;
        if (jump == true && pair_odd == true)
        {
            iY = rY + 1.0f;
        }       
        radius_head = size - 0.2f;
        //0.854f, 0.050f, 0.0f=ROJO
        //0.705f, 0.941f, 0.945f=VERDE(OJOS Y BOCA)
        //0.039f, 0.341f, 0.313f=VERDE FUERTE (BRAZOS Y PIES)
        //Cabeza
        material_rgb(gl, 1.0f, 0.623f, 0.196f);//NARANJA
        gl.glPushMatrix();
        gl.glTranslatef(iX - (height_body*0.0f), iY + 0.45f , iZ - 0.03f);//-1.10
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(q, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY-0.80f, slc_stck, slc_stck);
        gl.glRotatef(90f, -1f, 0f, 0f);
        gl.glTranslatef(0.0f, -HEIGHT_BODY*0f, 0f); //-HEIGHT_BODY*.50f
        gl.glRotatef(90f, -1f, 0f, 0f);
        glu.gluDisk(q, 0f, BOTTOM_BODY, SLICES, STACKS);//cabeza tapa de arriba
        gl.glPopMatrix();
        
       /*
       //ojos circular 
        material_rgb(gl, 0.0f, 0.0f, 0.0f);

        gl.glPushMatrix();
        gl.glTranslatef(0.2f, 0.1f, 0.68f);

        glu.gluSphere(q, RADIO_OJOS, SLICES, STACKS);
        gl.glPopMatrix();

        gl.glPushMatrix();

        material_rgb(gl, 0.0f, 0.0f, 0.0f);

        gl.glTranslatef(-0.2f, 0.1f, 0.68f);
        glu.gluSphere(q, RADIO_OJOS, SLICES, STACKS);
        gl.glPopMatrix();
        
        material_rgb(gl, 1.0f, 1.0f, 1.0f);
        gl.glPushMatrix();
        gl.glTranslatef(0.2f, 0.1f, 1.0f);
        glu.gluSphere(q, RADIO_OJOS2, SLICES, STACKS);
        gl.glTranslatef(-0.4f, -0.0f, 0.0f);
        glu.gluSphere(q, RADIO_OJOS2, SLICES, STACKS);
        gl.glPopMatrix();
        //ojos circular termina 
        */
  
        
        
        //cabeza tapa de abajo
        material_rgb(gl, 1.0f, 0.623f, 0.196f);
        gl.glPushMatrix();
        gl.glTranslatef(iX - (height_body*0.0f), iY + .62f , iZ - 0.03f);//-1.10
        gl.glRotatef(90f, 1f, 0f, 0f);
        //glu.gluCylinder(q, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY-1.08f, slc_stck, slc_stck);
        gl.glRotatef(90f, -1f, 0f, 0f);
        gl.glTranslatef(0.0f, -HEIGHT_BODY*0.71f, 0f); //-HEIGHT_BODY*.50f
        gl.glRotatef(90f, -1f, 0f, 0f);
        glu.gluDisk(q, 0f, BOTTOM_BODY, SLICES, STACKS);//cabeza tapa de abajo
        gl.glPopMatrix();
        
        
        radius_eyes = radius_head / 4.8f;
           
        width_eyebrow = radius_eyes*0.2f;
        height_eyebrow = radius_eyes*1.7f;
        
        //orejas
        material_rgb(gl, 0.854f, 0.050f, 0.0f); //oreja izquierda 
        gl.glPushMatrix();
        gl.glTranslatef(iX - (height_body *0.57f), iY + 0.0f, iZ - 0.03f);
        gl.glRotatef(90, 0, 1, 0);
        glu.gluCylinder(q, width_body * 0.45f, width_body * 0.45f, height_body * .20f, slc_stck, slc_stck);
        gl.glPopMatrix();
        material_rgb(gl, 0.854f, 0.050f, 0.0f); //oreja derecha
        gl.glPushMatrix();
        gl.glTranslatef(iX + (height_body * 0.57f), iY + 0.0f, iZ - 0.03f);
        gl.glRotatef(90, 0, 1, 0);
        glu.gluCylinder(q, width_body * 0.45f, width_body * 0.45f, -height_body * 0.20f, slc_stck, slc_stck);
        gl.glPopMatrix(); 
        
        //sombrero
        material_rgb(gl, 0.741f, 0.321f, 0.0f); //sombrero parte de abajo
        gl.glPushMatrix();
        gl.glTranslatef(iX - (height_body * 0.76f), iY + .60f, iZ - 0.03f);
        gl.glRotatef(90, 0, 1, 0);
        glu.gluCylinder(q, width_body * 0.35f, width_body * 0.35f, height_body * 1.50f, slc_stck, slc_stck);
        gl.glPopMatrix();
        
        material_rgb(gl, 0.741f, 0.321f, 0.0f); //sombrero parte de abajo
        gl.glPushMatrix();
        gl.glTranslatef(iX - (height_body * 0.40f), iY + 1.2f, iZ - 0.03f);
        gl.glRotatef(90, 0, 1, 0);
        glu.gluCylinder(q, width_body * 0.35f, width_body * 0.35f, height_body * 0.8f, slc_stck, slc_stck);
        gl.glPopMatrix();
        
        material_rgb(gl, 0.741f, 0.321f, 0.0f);//cafe
        gl.glPushMatrix();
        gl.glTranslatef(iX, iY + height_neck*2.70f + 0.20f, iZ);
        gl.glRotatef(90, 1,0,0);
        glu.gluCylinder(q, width_neck*0.60, width_neck*0.60, height_neck+0.08, slc_stck, slc_stck);       
        gl.glPopMatrix();
        /*
        material_rgb(gl, 0.741f, 0.321f, 0.0f); //sombreroo parte de arriba
        gl.glPushMatrix();
        gl.glTranslatef(iX + (height_body * 0.62f), iY + 1.4f, iZ - 0.03f);
        gl.glRotatef(90, 0, 1, 0);
        glu.gluCylinder(q, width_body * 0.7f, width_body * 0.7f, -height_body * 1.25f, slc_stck, slc_stck);
        gl.glPopMatrix(); */
        
        /*
        //ojo izquierdo
        material_rgb(gl, 0.0f, 0.0f, 0.0f);
        gl.glPushMatrix();
        gl.glTranslatef(iX - 0.43f, iY + 0.26f, iZ + 0.73f);
        gl.glRotatef(90, 1,0,0);
        gl.glRotatef(75, 0,1,0);
        glu.gluCylinder(q, -width_eyebrow*2.5, width_eyebrow-.04f, height_eyebrow-.02f, slc_stck, slc_stck);       
        gl.glPopMatrix();       
        //ojo derecho
        material_rgb(gl, 0.0f, 0.0f, 0.0f);
        gl.glPushMatrix();
        gl.glTranslatef(iX + 0.43f, iY + 0.26f, iZ + 0.73f);
        gl.glRotatef(90, 1,0,0);
        gl.glRotatef(-75, 0,1,0);
        glu.gluCylinder(q, -width_eyebrow*2.5, width_eyebrow-.05f, height_eyebrow-.02f, slc_stck, slc_stck);       
        gl.glPopMatrix();
        */
        radius_pupile = radius_eyes * 0.5f;
        width_neck = size / 4; height_neck = size / 3;
        //Cuello
        material_rgb(gl, 1.0f, 0.623f, 0.196f);//NARANJA
        gl.glPushMatrix();
        gl.glTranslatef(iX, iY - height_neck*3.2f + 0.20f, iZ);
        gl.glRotatef(90, 1,0,0);
        glu.gluCylinder(q, width_neck, width_neck, height_neck+0.2, slc_stck, slc_stck);       
        gl.glPopMatrix();
        
        width_ear = size * 0.25f;
        height_ear = width_ear * 1.5f;
        
       

        //ojos cuadrados  izquierda      
        gl.glPushMatrix();
        material_rgb(gl, 1.0f, 1.0f, 1.0f);
        gl.glTranslatef(-0.4f, -0.2f, +0.8f);
        gl.glRotatef(0f, 0f, 0f, 0f);
        gl.glScalef(0.30f, 0.30f, 0.1f);
        box(gl);
        gl.glPopMatrix();

        //ojos cuadrados  derecha      
        gl.glPushMatrix();
        material_rgb(gl, 1.0f, 1.0f, 1.0f);
        gl.glTranslatef(-0.0f, -0.2f, +0.8f);
        gl.glRotatef(0f, 0f, 0f, 0f);
        gl.glScalef(0.30f, 0.30f, 0.1f);
        box(gl);
        gl.glPopMatrix();
        
         //boca
        gl.glPushMatrix();
        material_rgb(gl, 1.0f, 1.0f, 1.0f);
        gl.glTranslatef(-0.4f, -0.7f, +0.8f);
        gl.glRotatef(0f, 0f, 0f, 0f);
        gl.glScalef(0.80f, 0.2f, 0.1f);
        box(gl);
        gl.glPopMatrix();
        
         //Letra C
         /*
        gl.glPushMatrix();
        material_rgb(gl, 1.0f, 1.0f, 1.0f);
        gl.glTranslatef(-0.4f, -1.9f, +0.8f);
        gl.glScalef(0.80f, 0.20f, 0.1f);
        box(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        material_rgb(gl, 1.0f, 1.0f, 1.0f);
        gl.glTranslatef(-0.4f, -2.8f, +0.8f);
        gl.glScalef(0.20f, 1.0f, 0.1f);
        box(gl);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        material_rgb(gl, 1.0f, 1.0f, 1.0f);
        gl.glTranslatef(-0.4f, -3.0f, +0.8f);
        gl.glScalef(0.80f, 0.20f, 0.1f);
        box(gl);
        gl.glPopMatrix();
        */
         
        radius_mouth=size*0.1f;
        if (jump == true && pair_odd == true)
        {
            /*
            //Boca
            material_rgb(gl, 0.0f, 0.0f, 0.0f);
            gl.glPushMatrix();
            gl.glTranslatef(iX, iY - 0.3f, iZ + 0.71f);
            gl.glRotatef(22, 1,0,0);
            gluDonut(gl, radius_mouth+0.10f, radius_mouth+0.10f, slc_stck, slc_stck);
            gl.glPopMatrix();*/
        }
        else
        {
            /*
            //Boca parte de arriba

            material_rgb(gl, 0.1f, 0.1f, 0.1f);
            gl.glPushMatrix();
            gl.glTranslatef(iX - (width_ear * 0.0f), iY + 0.0f * height_ear - 0.2f, iZ + 0.90f);
            gl.glRotatef(270, 1, 0, 0);
            gl.glRotatef(0, 0, 1, 0);
            glu.gluCylinder(q, width_ear * 0.6, 0.02f, height_ear * 0.2, slc_stck, slc_stck);
            gl.glPopMatrix();

            //Boca parte de abajo
            material_rgb(gl, 0.1f, 0.1f, 0.1f);
            gl.glPushMatrix();
            gl.glTranslatef(iX + (width_ear * 0.0f), iY - 0.0f * height_ear - 0.2f, iZ + 0.90f);
            gl.glRotatef(100, 1, 0, 0);
            gl.glRotatef(0, 0, 1, 0);
            glu.gluCylinder(q, width_ear * 0.6, 0.02f, height_ear * 0.55, slc_stck, slc_stck);
            gl.glPopMatrix();*/

            //Boca
            /*
            material_rgb(gl, 0.0f, 0.0f, 0.0f);
            gl.glPushMatrix();
            gl.glTranslatef(iX, iY - 0.3f, iZ + 0.626f);
            gl.glRotatef(22, 1,0,0);
            gluDonut(gl, radius_mouth, radius_mouth, slc_stck, slc_stck);
            gl.glPopMatrix();*/
        }
        //Hombros
        width_body = size * 0.4f;
        height_body = size * 2.4f;
        /*material_rgb(gl, 1.0f, 1.0f, 1.0f);
        gl.glPushMatrix();
        gl.glTranslatef(iX - (height_body*0.4f), iY - 2.10f , iZ - 0.03f);
        gl.glRotatef(90, 0,1,0);
        glu.gluCylinder(q, width_body*2.8f, width_body*2.8f, height_body*0.8f, slc_stck, slc_stck);     
        gl.glPopMatrix();   
        */
       
        
        material_rgb(gl, 1.0f, 0.623f, 0.196f);
        gl.glPushMatrix();
        gl.glTranslatef(iX - (height_body*0.0f), iY - 1.4f , iZ - 0.03f);//-1.10
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluCylinder(q, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY-0.2f, slc_stck, slc_stck);
        gl.glRotatef(90f, -1f, 0f, 0f);
        gl.glTranslatef(0.0f, -HEIGHT_BODY*0.90f, 0f);
        gl.glRotatef(90f, -1f, 0f, 0f);
        glu.gluDisk(q, 0f, BOTTOM_BODY, SLICES, STACKS);//cuerpo tapa de abajo
        gl.glPopMatrix();
        
        //cuerpo tapa de arriba   
        material_rgb(gl, 1.0f, 0.623f, 0.196f);
        gl.glPushMatrix();
        gl.glTranslatef(iX - (height_body*0.0f), iY - 1.4f , iZ - 0.03f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        //glu.gluCylinder(q, TOP_BODY, BOTTOM_BODY, HEIGHT_BODY, SLICES, STACKS);
        gl.glRotatef(90f, -1f, 0f, 0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        glu.gluDisk(q, 0f, BOTTOM_BODY, SLICES, STACKS);
        gl.glPopMatrix();
       

       
        if (walk == true)
        {
            if (pair_odd == true)
            {
                material_rgb(gl, 0.039f, 0.341f, 0.313f); //Brazo izquierda 
                gl.glPushMatrix();
                gl.glTranslatef(iX - (height_body * 0.50f), iY - 1.7f, iZ - 0.03f);
                gl.glRotatef(90, 0, 1, 0);
                gl.glRotatef(90, 1, 0, 0);
                glu.gluCylinder(q, width_body * 0.7f, width_body * 0.7f, height_body * 0.50f, slc_stck, slc_stck);
                gl.glPopMatrix();
                material_rgb(gl, 0.039f, 0.341f, 0.313f); //Brazo derecha
                gl.glPushMatrix();
                gl.glTranslatef(iX + (height_body * 0.50f), iY - 1.7f, iZ - 0.03f);
                gl.glRotatef(90, 0, 1, 0);
                gl.glRotatef(90, 1, 0, 0);
                glu.gluCylinder(q, width_body * 0.7f, width_body * 0.7f, height_body * 0.50f, slc_stck, slc_stck);
                gl.glPopMatrix(); 


                material_rgb(gl, 0.039f, 0.341f, 0.313f); //Pierna izquierda
                gl.glPushMatrix();
                gl.glTranslatef(iX - (height_body*0.2f), iY - 3.2f , iZ + 0.17f);//************************************************************************
                gl.glRotatef(265, 1,0,0);
                gl.glRotatef(180, 0,1,0);
                glu.gluCylinder(q, width_body*0.7f, width_body*0.7f, height_body*0.50f, slc_stck, slc_stck);     
                gl.glPopMatrix();  
                material_rgb(gl, 0.039f, 0.341f, 0.313f); //Pierna derecha
                gl.glPushMatrix();
                gl.glTranslatef(iX + (height_body*0.2f), iY - 3.2f , iZ - 0.12f);
                gl.glRotatef(275, 1,0,0);
                gl.glRotatef(180, 0,1,0);
                glu.gluCylinder(q, width_body*0.7f, width_body*0.7f, height_body*0.50f, slc_stck, slc_stck);     
                gl.glPopMatrix();  

                
            }
            else
            {
                material_rgb(gl, 0.039f, 0.341f, 0.313f); //Brazo izquierda 
                gl.glPushMatrix();
                gl.glTranslatef(iX - (height_body * 0.50f), iY - 1.7f, iZ - 0.03f);
                gl.glRotatef(90, 0, 1, 0);
                gl.glRotatef(90, 1, 0, 0);
                glu.gluCylinder(q, width_body * 0.7f, width_body * 0.7f, height_body * 0.50f, slc_stck, slc_stck);
                gl.glPopMatrix();
                material_rgb(gl, 0.039f, 0.341f, 0.313f); //Brazo derecha
                gl.glPushMatrix();
                gl.glTranslatef(iX + (height_body * 0.50f), iY - 1.7f, iZ - 0.03f);
                gl.glRotatef(90, 0, 1, 0);
                gl.glRotatef(90, 1, 0, 0);
                glu.gluCylinder(q, width_body * 0.7f, width_body * 0.7f, height_body * 0.50f, slc_stck, slc_stck);
                gl.glPopMatrix();

                material_rgb(gl, 0.039f, 0.341f, 0.313f); //Pierna izquierda
                gl.glPushMatrix();
                gl.glTranslatef(iX - (height_body*0.2f), iY - 3.2f , iZ - 0.12f);
                gl.glRotatef(275, 1,0,0);
                gl.glRotatef(180, 0,1,0);
                glu.gluCylinder(q, width_body*0.7f, width_body*0.7f, height_body*0.50f, slc_stck, slc_stck);     
                gl.glPopMatrix();  
                material_rgb(gl, 0.039f, 0.341f, 0.313f); //Pierna derecha
                gl.glPushMatrix();
                gl.glTranslatef(iX + (height_body*0.2f), iY - 3.2f , iZ + 0.1f);
                gl.glRotatef(265, 1,0,0);
                gl.glRotatef(180, 0,1,0);
                glu.gluCylinder(q, width_body*0.7f, width_body*0.7f, height_body*0.50f, slc_stck, slc_stck);     
                gl.glPopMatrix();                
            }
        }
        else //###################################### no walking
        {
            material_rgb(gl, 0.039f, 0.341f, 0.313f); //Brazo izquierda 
            gl.glPushMatrix();
            gl.glTranslatef(iX - (height_body * 0.50f), iY - 1.7f, iZ - 0.03f);
            gl.glRotatef(90, 0, 1, 0);
            gl.glRotatef(90, 1, 0, 0);
            glu.gluCylinder(q, width_body * 0.7f, width_body * 0.7f, height_body * 0.50f, slc_stck, slc_stck);
            gl.glPopMatrix();
            material_rgb(gl, 0.039f, 0.341f, 0.313f); //Brazo derecha
            gl.glPushMatrix();
            gl.glTranslatef(iX + (height_body * 0.50f), iY - 1.7f, iZ - 0.03f);
            gl.glRotatef(90, 0, 1, 0);
            gl.glRotatef(90, 1, 0, 0);
            glu.gluCylinder(q, width_body * 0.7f, width_body * 0.7f, height_body * 0.50f, slc_stck, slc_stck);
            gl.glPopMatrix();

            material_rgb(gl, 0.039f, 0.341f, 0.313f); //Pierna izquierda 
            gl.glPushMatrix();
            gl.glTranslatef(iX - (height_body*0.2f), iY - 3.2f , iZ - 0.03f);
            gl.glRotatef(90, 0,1,0);
            gl.glRotatef(90, 1,0,0);
            glu.gluCylinder(q, width_body*0.7f, width_body*0.7f, height_body*0.50f, slc_stck, slc_stck);     
            gl.glPopMatrix();  
            material_rgb(gl, 0.039f, 0.341f, 0.313f); //Pierna derecha
            gl.glPushMatrix();
            gl.glTranslatef(iX + (height_body*0.2f), iY - 3.2f , iZ - 0.03f);
            gl.glRotatef(90, 0,1,0);
            gl.glRotatef(90, 1,0,0);
            glu.gluCylinder(q, width_body*0.7f, width_body*0.7f, height_body*0.50f, slc_stck, slc_stck);     
            gl.glPopMatrix();  
        }
        
        
        if (pair_odd == false)
        {
            pair_odd = true;
        }
        else
        {
            pair_odd = false;
        }
    }
    
     private void gluDonut(GL gl, float R, float r, int N, int n) {

        int maxn = 1000;
        n = Math.min(n, maxn - 1);
        N = Math.min(N, maxn - 1);
        float rr = 1.5f * r;
        double dv = 2 * Math.PI / n;
        double dw = 2 * Math.PI / N;
        double v = 0.0f;
        double w = 0.0f;
        while (w < 2 * Math.PI + dw) {
            v = 0.0f;
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            while (v < 2 * Math.PI + dv) {
                gl.glNormal3d(
                        (R + rr * Math.cos(v)) * Math.cos(w)
                        - (R + r * Math.cos(v)) * Math.cos(w),
                        (R + rr * Math.cos(v)) * Math.sin(w)
                        - (R + r * Math.cos(v)) * Math.sin(w),
                        (rr * Math.sin(v) - r * Math.sin(v)));
                gl.glVertex3d((R + r * Math.cos(v)) * Math.cos(w),
                        (R + r * Math.cos(v)) * Math.sin(w),
                        r * Math.sin(v));
                gl.glNormal3d(
                        (R + rr * Math.cos(v + dv)) * Math.cos(w + dw)
                        - (R + r * Math.cos(v + dv)) * Math.cos(w + dw),
                        (R + rr * Math.cos(v + dv)) * Math.sin(w + dw)
                        - (R + r * Math.cos(v + dv)) * Math.sin(w + dw),
                        rr * Math.sin(v + dv) - r * Math.sin(v + dv));
                gl.glVertex3d((R + r * Math.cos(v + dv)) * Math.cos(w + dw),
                        (R + r * Math.cos(v + dv)) * Math.sin(w + dw),
                        r * Math.sin(v + dv));
                v += dv;
            }
            gl.glEnd();
            w += dw;
        }
    }
 
    private void material_rgb(GL gl, float r, float g, float b){
        float mat_ambient[] = {r,g,b,1.0f};
        float mat_diffuse[] = {0.5f,0.5f,0.5f,1.0f}; //Difuminado
        float mat_specular[] = {0.5f,0.5f,0.5f,1.0f};
        float shine = 100; //Reflejo de luz blanco
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
    }
    
    
    public void box(GL gl) {
        gl.glBegin(GL.GL_POLYGON);/* f1: front */
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f2: bottom */
        gl.glNormal3f(0.0f, 0.0f, -1.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f3:back */
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f4: top */
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f5: left */
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glEnd();
        gl.glBegin(GL.GL_POLYGON);/* f6: right */
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glEnd();
    } 
  public void fondo(GL gl, GLU glu, Texture t){
        int m=t.getTextureObject();
        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBindTexture(GL.GL_TEXTURE_2D, m);
        gl.glBegin(gl.GL_QUADS);
        gl.glTexCoord2d(0.0f,1.0f);
        gl.glVertex3f(-6.0f, -6.0f, -6f);
        gl.glTexCoord2d(1.0f,1.0f);
        gl.glVertex3f(6.0f, -6f, -6f);
        gl.glTexCoord2d(1.0f,0.0f);
        gl.glVertex3f(6.0f,6.0f, -6f);
        gl.glTexCoord2d(0.0f,0.0f);
        gl.glVertex3f(-6.0f, 6.0f, -6f);
        gl.glEnd();
        gl.glFlush();
        gl.glDisable(gl.GL_TEXTURE_2D);
    }

}

