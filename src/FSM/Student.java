package FSM;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.BufferedImage;



public class Student {
private Image student;
private int x=0,y=0;

public Student(){
    student=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("resource/stud.png"));
    scaleS(50,20);

}

public void scaleS(int w,int h){
    student=student.getScaledInstance(w,h,Image.SCALE_SMOOTH);
}

public Image getS(){
    return student;
}

public int getW(){
    try{
        return student.getWidth(null);
    }catch(Exception e){
        return -1;
    }
}

public int getH(){
    try{
        return student.getHeight(null);
    }catch(Exception e){
        return -1;
    }
}

public void setX(int ix){
    x=ix;
}

public int getX(){
    return x;
}

public void setY(int why){
    y=why;
}

public int getY(){
    return y;
}

public Rectangle getR(){
    return (new Rectangle(x,y,student.getWidth(null),student.getHeight(null)));
}

public BufferedImage getBI(){
    BufferedImage bi =new BufferedImage(student.getWidth(null),student.getHeight(null),BufferedImage.TYPE_INT_ARGB);
    Graphics g=bi.getGraphics();
    g.drawImage(student,0,0,null);
    g.dispose();
    return bi;
}
}
