package com.thinking.machines.client;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.applet.Applet;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.*;

class ui1 extends Frame 
{
public ui1()
{
super("Team Viewer");
prepareGUI();
}
public static void main(String gg[])
{
ui1  u= new ui1();  
TextField t1=new TextField(10);
TextField t2=new TextField(10);
Button b1=new Button("Connect");
Button b2=new Button("Cancel");
u.setLayout(null);
u.setVisible(true);
t1.setBounds(220,140,160,30);
t2.setBounds(220,190,160,30);
b1.setBounds(100,250,80,50);
b2.setBounds(220,250,80,50);
b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
String server;
int port;
server=t1.getText();
port=Integer.parseInt(t2.getText());
java.util.Timer timer = new java.util.Timer();
Test t=new Test();
byte[] bak=new byte[4];

timer.scheduleAtFixedRate(new TimerTask(){
public void run()
{
BufferedImage image;
try
{
Socket xyz=new Socket(server,port);
OutputStream os=xyz.getOutputStream();
int ak=10;
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
os.write(bak);
InputStream inputStream=xyz.getInputStream();
image=ImageIO.read(inputStream);
t.i.redraw(image);
}catch(Exception e)
{
System.out.println(e);
}
}
},0,2*1000);
}
});
b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
System.exit(0);
}
});
u.add(t1);
u.add(t2);
u.add(b1);
u.add(b2);
}
private void prepareGUI()
{
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();

setLocation(d.width/2-250,d.height/2-200);
setSize(400,400);
addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent windowEvent)
{
System.exit(0);
}        
}); 
}    
public void paint(Graphics g) 
{
Graphics2D g2 = (Graphics2D)g;        
Font plainFont = new Font("Serif", Font.BOLD, 40);        
g2.setFont(plainFont);
g2.drawString("Client",140,70); 
Calendar c=Calendar.getInstance();
String today=c.get(Calendar.DATE)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);

Font font = new Font("Serif", Font.PLAIN,20);        
g2.setFont(font);
g2.drawString("Date :"+today,15,380);
Font f = new Font("Serif", Font.BOLD,22);        
g2.setFont(f);
g2.drawString("Ip Address:",22,160);
g2.drawString("Enter Port Number:",22,210);
}
}


class Test extends Frame
{
public ImageCanvas i;
public Test()
{
i=new ImageCanvas();
Frame frame=new Frame("Team Viewer");
frame.addWindowListener(new WindowAdapter() {
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
});
frame.add(i);
frame.setUndecorated(true);
frame.pack();
frame.setVisible(true);
}
}

class ImageCanvas extends Canvas implements MouseMotionListener,MouseListener,KeyListener
{
private BufferedImage img;
int lastX;
int lastY;
ImageCanvas()
{
lastX=0;
lastY=0;
addMouseMotionListener(this);
addMouseListener(this);
addKeyListener(this);
setFocusable(true);
repaint();
}
public Dimension getPreferredSize()
{
return new Dimension(1366,768);
}
public void paint(Graphics g)
{
super.paint(g);
if(img !=null)
{
int x=(getWidth()-img.getWidth())/2;
int y=(getHeight()-img.getHeight())/2;
g.drawImage(img,x,y,this);
//g.drawImage(img,x,y,this);
}
}
public void redraw(BufferedImage image)
{
img=image;
repaint();
}
public void keyPressed(KeyEvent e)
{
int ak=100;
byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
int m=e.getKeyCode();
//int m=(int)c;
byte[] bm=new byte[4];
bm[0]=(byte)(m>>24);
bm[1]=(byte)(m>>16);
bm[2]=(byte)(m>>8);
bm[3]=(byte)m;
System.out.println("key  is : "+m);
try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();
os.write(bak);
os.flush();
os.write(bm);
os.flush();
}catch(Exception ee)
{
System.out.println(ee);
}
}
public void keyReleased(KeyEvent e)
{
int ak=110;
byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
int m=e.getKeyCode();
//int m=(int)c;
byte[] bm=new byte[4];
bm[0]=(byte)(m>>24);
bm[1]=(byte)(m>>16);
bm[2]=(byte)(m>>8);
bm[3]=(byte)m;
System.out.println("key  is : "+m);
try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();
os.write(bak);
os.flush();
os.write(bm);
os.flush();
}catch(Exception ee)
{
System.out.println(ee);
}
}
public void keyTyped(KeyEvent e)
{
}

public void mouseMoved(MouseEvent ev)
{
int x=ev.getX();
int y=ev.getY();
int ak=20;
byte[] bx=new byte[4];
bx[0]=(byte)(x>>24);
bx[1]=(byte)(x>>16);
bx[2]=(byte)(x>>8);
bx[3]=(byte)x;

byte[] by=new byte[4];
by[0]=(byte)(y>>24);
by[1]=(byte)(y>>16);
by[2]=(byte)(y>>8);
by[3]=(byte)y;

byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;

try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();

os.write(bak);
os.flush();
os.write(bx);
os.flush();
os.write(by);
os.flush();
}catch(Exception e)
{
System.out.println(e);
}
}
public void mouseDragged(MouseEvent ev)
{
int x=ev.getX();
int y=ev.getY();
int ak=90;
byte[] bx=new byte[4];
bx[0]=(byte)(x>>24);
bx[1]=(byte)(x>>16);
bx[2]=(byte)(x>>8);
bx[3]=(byte)x;

byte[] by=new byte[4];
by[0]=(byte)(y>>24);
by[1]=(byte)(y>>16);
by[2]=(byte)(y>>8);
by[3]=(byte)y;

byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;

try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();

os.write(bak);
os.flush();
os.write(bx);
os.flush();
os.write(by);
os.flush();
}catch(Exception e)
{
System.out.println(e);
}




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
public void mousePressed(MouseEvent e)
{
if(e.getModifiers()==MouseEvent.BUTTON1_MASK)
{
int ak=30;
byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
System.out.println("Button 1 pressed");
try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();
os.write(bak);
os.flush();
}catch(Exception ee)
{
System.out.println(ee);
}
}
if(e.getModifiers()==MouseEvent.BUTTON2_MASK)
{
int ak=50;
byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
System.out.println("Button 2 pressed");
try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();
os.write(bak);
os.flush();
}catch(Exception ee)
{
System.out.println(ee);
}
}
if(e.getModifiers()==MouseEvent.BUTTON3_MASK)
{
int ak=70;
byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
System.out.println("Button 3 pressed");

try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();
os.write(bak);
os.flush();
}catch(Exception ee)
{
System.out.println(ee);
}
}
}
public void mouseReleased(MouseEvent e)
{
if(e.getModifiers()==MouseEvent.BUTTON1_MASK)
{
int ak=40;
byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
System.out.println("Button 1 released");

try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();
os.write(bak);
os.flush();
}catch(Exception ee)
{
System.out.println(ee);
}
}
if(e.getModifiers()==MouseEvent.BUTTON2_MASK)
{
int ak=60;
byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
System.out.println("Button 2 released");
try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();
os.write(bak);
os.flush();
}catch(Exception ee)
{
System.out.println(ee);
}
}
if(e.getModifiers()==MouseEvent.BUTTON3_MASK)
{
int ak=80;
byte[] bak=new byte[4];
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
System.out.println("Button 3 released");
try
{
Socket socket=new Socket("111.111.111.111",5000);
OutputStream os=socket.getOutputStream();
os.write(bak);
os.flush();
}catch(Exception ee)
{
System.out.println(ee);
}
}
}
}


/*
class Client
{
public static void main(String gg[])
{
String server=gg[0];
int port=Integer.parseInt(gg[1]);
Timer timer = new Timer();
Test t=new Test();
byte[] bak=new byte[4];

timer.scheduleAtFixedRate(new TimerTask(){
public void run()
{
BufferedImage image;
try
{
Socket xyz=new Socket(server,port);
OutputStream os=xyz.getOutputStream();
int ak=10;
bak[0]=(byte)(ak>>24);
bak[1]=(byte)(ak>>16);
bak[2]=(byte)(ak>>8);
bak[3]=(byte)ak;
os.write(bak);
InputStream inputStream=xyz.getInputStream();
image=ImageIO.read(inputStream);
t.i.redraw(image);
}catch(Exception e)
{
System.out.println(e);
}
}
}, 2*1000,3*1000);
}
}
*/