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

  /**
   * S = Vx * t
   * H = (1/2)(Vy * t1 - Vy * g * t1)
   * t = 2* t0 + t1
   * Vy = g * t0
   * tan(radian) = Vy / Vx
   * ->
   * <p>
   * V = Vx / cos(radian)
   */
  public static void revise(SelfInfo selfInfo, DistanceInfo distanceInfo) {
    double radian = selfInfo.getAngle() * 2 * Math.PI / 360;
    double tan = Math.tan(radian);
    double up = distanceInfo.getWidth() * (1 - WorldInfo.getGravity()) * tan - 2 * distanceInfo.getHeight();
    double down = (2 / WorldInfo.getGravity() - 2) * tan * tan;
    double v = Math.sqrt(up / down * (1 + tan * tan));

    System.out.println("速度" + v);

    selfInfo.setPower((int) (v / WorldInfo.getPowerRatio()));

    System.out.println(distanceInfo);
    System.out.println(selfInfo);

    robot.keyPress(KeyEvent.VK_1);
    robot.keyPress(KeyEvent.VK_2);
    /*
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
    }*/
  }

  public static void keyPressSpace() {
    robot.keyPress(KeyEvent.VK_SPACE);
  }

  public static void keyReleaseSpace() {
    robot.keyRelease(KeyEvent.VK_SPACE);
  }
}
