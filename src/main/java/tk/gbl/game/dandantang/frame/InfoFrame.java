package tk.gbl.game.dandantang.frame;

import tk.gbl.game.dandantang.GlobalValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Date: 2015/8/12
 * Time: 18:08
 *
 * @author Tian.Dong
 */
public class InfoFrame extends JFrame {
  Point origin = new Point();  //全局的位置变量，用于表示鼠标在窗口上的位置


  JLabel globalLabel = new JLabel();
  JLabel infoLabel = new JLabel();
  JLabel windLabel = new JLabel();
  JLabel distanceLabel = new JLabel();
  JLabel originalDistanceLabel = new JLabel();


  public InfoFrame() {

    this.setUndecorated(true);//去掉JFrame的标题栏
    this.setAlwaysOnTop(true);
    this.setLayout(null);
    this.setBounds(1200, 100, 160, 500);
    this.setLayout(null);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setVisible(true);

    init();


    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
        origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
        origin.y = e.getY();
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        repaint();
      }
    });
    this.addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）

        Point p = getLocation();  //当鼠标拖动时获取窗口当前位置
        //设置窗口的位置
        //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
        setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
      }
    });
  }


  public void init() {
    WhitePanel panel = new WhitePanel();
    panel.setBounds(0, 0, 200, 500);
    panel.setLayout(null);
    this.add(panel);

    globalLabel.setText("0左1右:" + GlobalValue.leftOrRight);
    globalLabel.setBounds(0, 20, 200, 50);
    panel.add(globalLabel);


    infoLabel.setText("角度：" + GlobalValue.selfInfo.getAngle()
            + ",力量：" + Math.round(GlobalValue.selfInfo.getPower())
            + ",原力：" + Math.round(GlobalValue.selfInfo.getOriginalPower())
    );
    infoLabel.setBounds(0, 50, 200, 200);
    panel.add(infoLabel);

    windLabel.setText("风向左：" + GlobalValue.wind.isLeft() + ",风力：" + GlobalValue.wind.getValue());
    windLabel.setBounds(0, 150, 200, 200);
    panel.add(windLabel);

    distanceLabel.setText("w：" + Math.round(GlobalValue.distanceInfo.getWidth())
            + ",h：" + Math.round(GlobalValue.distanceInfo.getHeight())
    );
    distanceLabel.setBounds(0, 250, 200, 200);
    panel.add(distanceLabel);

    originalDistanceLabel.setText("ow：" + Math.round(GlobalValue.distanceInfo.getOriginalWidth())
            + ",oh：" + Math.round(GlobalValue.distanceInfo.getOriginalHeight())
    );
    originalDistanceLabel.setBounds(0, 300, 200, 200);
    panel.add(originalDistanceLabel);
  }

  public void refreshInfo() {
    globalLabel.setText("0左1右:" + GlobalValue.leftOrRight);


    infoLabel.setText("角度" + Math.round(GlobalValue.selfInfo.getAngle())
            + ",力量：" + Math.round(GlobalValue.selfInfo.getPower())
            + ",原力：" + Math.round(GlobalValue.selfInfo.getOriginalPower())
    );

    windLabel.setText("风向左：" + GlobalValue.wind.isLeft() + ",风力：" + GlobalValue.wind.getValue());

    distanceLabel.setText("w：" + Math.round(GlobalValue.distanceInfo.getWidth())
            + ",h：" + Math.round(GlobalValue.distanceInfo.getHeight())
    );

    originalDistanceLabel.setText("ow：" + Math.round(GlobalValue.distanceInfo.getOriginalWidth())
            + ",oh：" + Math.round(GlobalValue.distanceInfo.getOriginalHeight())
    );
  }


  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new InfoFrame().setVisible(true);
      }
    });
//    new InfoFrame();
  }
}
