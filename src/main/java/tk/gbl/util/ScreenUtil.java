package tk.gbl.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Date: 2015/8/1
 * Time: 18:20
 *
 * @author Tian.Dong
 */
public class ScreenUtil {

  static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

  static Robot robot;


  static {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  public static BufferedImage getScreen() {
    BufferedImage screenImage = robot.createScreenCapture(new
        Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
    return screenImage;
  }

  public static BufferedImage getScreenPart(int x, int y, int w, int h) {
    BufferedImage screenImage = robot.createScreenCapture(new
        Rectangle(x, y, w, h));
    return screenImage;
  }

  public static void main(String[] args) throws IOException {
    BufferedImage bufferedImage = ScreenUtil.getScreenPart(100, 100, 50, 50);
    File f = new File("F:/test.png");
    //将screenshot对象写入图像文件
    ImageIO.write(bufferedImage, "png", f);
  }
}
