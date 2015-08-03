package tk.gbl.game.dandantang;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Date: 2015/8/2
 * Time: 10:27
 *
 * @author Tian.Dong
 */
public class GameControl {
  static Robot robot;


  static {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  public static void revise(SelfInfo selfInfo) {
    robot.keyPress(KeyEvent.VK_1);
    robot.keyPress(KeyEvent.VK_2);
    int count = 45 - selfInfo.getAngle();
    if (count > 0) {
      //up count次
      while (count-- > 0) {
        robot.keyPress(KeyEvent.VK_UP);
        robot.keyRelease(KeyEvent.VK_UP);
        System.out.println("up "+count);
      }
    } else {
      //down count次
      while (count++ < 0) {
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        System.out.println("down "+count);

      }
    }
    selfInfo.setAngle(45);
    selfInfo.setPower(50);
  }

  public static void keyPressSpace(){
    robot.keyPress(KeyEvent.VK_SPACE);
  }

  public static void keyReleaseSpace(){
    robot.keyRelease(KeyEvent.VK_SPACE);
  }
}
