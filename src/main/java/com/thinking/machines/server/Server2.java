package com.thinking.machines.server;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;

import java.util.*;
import java.awt.geom.*;
class ui2 extends Frame 
{
public ui2()
{
super("Team Viewer");
prepareGUI();
}
public static void main(String gg[])
{
ui2  u= new ui2();  
TextField t1=new TextField(10);
Button b1=new Button("Start Server");
Button b2=new Button("Cancel");
u.setLayout(null);
u.setVisible(true);
t1.setBounds(220,130,160,40);
b1.setBounds(100,220,80,50);
b2.setBounds(220,220,80,50);
b1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
int x;
try
{
x=Integer.parseInt(t1.getText());
sc s=new sc(x);
}catch(NumberFormatException nfe)
{
}
}
});
b2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
System.exit(0);
}
});
u.add(t1);
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
g2.drawString("Server",140,70); 
Calendar c=Calendar.getInstance();
String today=c.get(Calendar.DATE)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);

Font font = new Font("Serif", Font.PLAIN,20);        
g2.setFont(font);
g2.drawString("Date :"+today,15,380);
Font f = new Font("Serif", Font.BOLD,22);        
g2.setFont(f);
g2.drawString("Enter Port Number:",22,160);
}
}


class sc
{
private int port ;
private ServerSocket serverSocket;
sc(int port)
{
this.port=port;
try
{
serverSocket=new ServerSocket(port);
startListening();
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public void startListening()
{
try
{
while(true)
{
System.out.println("Server Start Listening on port:"+port);
Socket socket=serverSocket.accept();
new RequestProcessor(socket);
}
}catch(Exception e)
{
System.out.println(e);
}
}

}
class RequestProcessor extends Thread
{
private Socket socket;
RequestProcessor(Socket xyz)
{
this.socket=xyz;
start();
}
public void run()
{
try
{
while(true)
{
OutputStream outputStream;
InputStream inputStream;
byte b[]=new byte[4];
outputStream=socket.getOutputStream();
inputStream=socket.getInputStream();
inputStream.read(b);
int x;
x=(b[0] & 0xFF)<<24 | (b[1] & 0xFF)<<16 | (b[2] & 0xFF)<<8 | (b[3] & 0xFF);
//System.out.println(x);
if(x==10) image(outputStream);
if(x==20) mouseMove(inputStream);
if(x==30) mouse1Press(inputStream);
if(x==40) mouse1Release(inputStream);
if(x==50) mouse2Press(inputStream);
if(x==60) mouse2Release(inputStream);
if(x==70) mouse3Press(inputStream);
if(x==80) mouse3Release(inputStream);
if(x==90) mouseDragged(inputStream);
if(x==100) keyPressed(inputStream);
if(x==110) keyReleased(inputStream);
}
}catch(Exception e)
{
System.out.println(e);
}
}
public void image(OutputStream outputStream)
{
try
{
Robot robot=new Robot();
Rectangle screen=new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
BufferedImage bi;
bi=robot.createScreenCapture(screen);
ImageIO.write(bi,"png",outputStream);
outputStream.flush();
}catch(Exception e)
{
System.out.println(e);
}
}
public void mouseMove(InputStream inputStream)
{
try
{
Robot robot=new Robot();
byte bx[]=new byte[4];
inputStream.read(bx);
int x=(bx[0] & 0xFF)<<24 | (bx[1] & 0xFF)<<16 | (bx[2] & 0xFF)<<8 | (bx[3] & 0xFF);
byte by[]=new byte[4];
inputStream.read(by);
int y=(by[0] & 0xFF)<<24 | (by[1] & 0xFF)<<16 | (by[2] & 0xFF)<<8 | (by[3] & 0xFF);
//System.out.println("X: "+x+ "," +"Y: "+y);
robot.mouseMove(x,y);
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}


public void mouseDragged(InputStream inputStream)
{
try
{
Robot robot=new Robot();
byte bx[]=new byte[4];
inputStream.read(bx);
int x=(bx[0] & 0xFF)<<24 | (bx[1] & 0xFF)<<16 | (bx[2] & 0xFF)<<8 | (bx[3] & 0xFF);
byte by[]=new byte[4];
inputStream.read(by);
int y=(by[0] & 0xFF)<<24 | (by[1] & 0xFF)<<16 | (by[2] & 0xFF)<<8 | (by[3] & 0xFF);
//System.out.println("X: "+x+ "," +"Y: "+y);
robot.mouseMove(x,y);
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public void mouse1Press(InputStream inputStream)
{
try
{
Robot robot=new Robot();
robot.mousePress(InputEvent.BUTTON1_MASK);
//robot.mouseRelease(InputEvent.BUTTON1_MASK);
System.out.println("Mouse1 Pressed");
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public void mouse2Press(InputStream inputStream)
{
try
{
Robot robot=new Robot();
robot.mousePress(InputEvent.BUTTON2_MASK);
//robot.mouseRelease(InputEvent.BUTTON1_MASK);
System.out.println("Mouse2 Pressed");
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public void mouse3Press(InputStream inputStream)
{
try
{
Robot robot=new Robot();
robot.mousePress(InputEvent.BUTTON3_MASK);
//robot.mouseRelease(InputEvent.BUTTON1_MASK);
System.out.println("Mouse 3Pressed");
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}


public void mouse1Release(InputStream inputStream)
{
try
{
Robot robot=new Robot();
//robot.mousePress(InputEvent.BUTTON1_MASK);
robot.mouseRelease(InputEvent.BUTTON1_MASK);
System.out.println("Mouse1 Realsed");
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public void mouse2Release(InputStream inputStream)
{
try
{
Robot robot=new Robot();
//robot.mousePress(InputEvent.BUTTON1_MASK);
robot.mouseRelease(InputEvent.BUTTON2_MASK);
System.out.println("Mouse2 Realsed");
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public void mouse3Release(InputStream inputStream)
{
try
{
Robot robot=new Robot();
//robot.mousePress(InputEvent.BUTTON1_MASK);
robot.mouseRelease(InputEvent.BUTTON3_MASK);
System.out.println("Mouse3 Realsed");
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}

public void keyPressed(InputStream inputStream)
{
try
{
Robot robot=new Robot();
byte bx[]=new byte[4];
inputStream.read(bx);

int x=(bx[0] & 0xFF)<<24 | (bx[1] & 0xFF)<<16 | (bx[2] & 0xFF)<<8 | (bx[3] & 0xFF);
char a=(char)x;
System.out.println("Key Pressed:"+a);
robot.keyPress(a);
//robot.keyRelease(a);
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public void keyReleased(InputStream inputStream)
{
try
{
Robot robot=new Robot();
byte bx[]=new byte[4];
inputStream.read(bx);

int x=(bx[0] & 0xFF)<<24 | (bx[1] & 0xFF)<<16 | (bx[2] & 0xFF)<<8 | (bx[3] & 0xFF);
char a=(char)x;
System.out.println("Key Pressed:"+a);
//robot.keyPress(a);
robot.keyRelease(a);
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}

}