package tk.gbl.game.dandantang.recognition;

import org.junit.Test;
import tk.gbl.game.dandantang.bean.DistanceInfo;
import tk.gbl.util.image.ImageFile;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Date: 2015/8/20
 * Time: 14:13
 *
 * @author Tian.Dong
 */
public class DistanceTest {
  @Test
  public void test() {
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\distance_");
    for (File file : path.listFiles()) {
      BufferedImage distanceImage = ImageFile.fileToImage(file);
      DistanceInfo distanceInfo = InfoSpider.getPointInfo(distanceImage);

      System.out.println(distanceInfo);
      System.exit(0);
    }
  }
}
