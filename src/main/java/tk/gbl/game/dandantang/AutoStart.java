package tk.gbl.game.dandantang;

import tk.gbl.game.dandantang.frame.InfoFrame;

import java.awt.*;

/**
 * Date: 2015/8/9
 * Time: 10:43
 *
 * @author Tian.Dong
 */
public class AutoStart {
  public static void main(String[] args) {
    new DandanTangGame().start();
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        GlobalValue.infoFrame = new InfoFrame();
        GlobalValue.infoFrame.setVisible(true);
      }
    });
  }
}
