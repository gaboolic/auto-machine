package tk.gbl.game.dandantang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Date: 2015/8/8
 * Time: 21:10
 *
 * @author Tian.Dong
 */
public class MyKeyPressTest extends JFrame implements KeyListener {

  public MyKeyPressTest() {
    this.setTitle("ttt");
    this.setLayout(null);
    this.setSize(new Dimension(200,180));//定义窗口大小
    this.setVisible(true);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public static void main(String[] args){
    MyKeyPressTest test = new MyKeyPressTest();
    test.addKeyListener(test);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("type:"+e.getKeyCode());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("press:"+e.getKeyCode());

  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("release:"+e.getKeyCode());

  }
}
