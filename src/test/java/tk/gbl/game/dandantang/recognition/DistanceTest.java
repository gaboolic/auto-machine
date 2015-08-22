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
  public void testSingle(){
    File file = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\1439215073741.png");
    BufferedImage distanceImage = ImageFile.fileToImage(file);
    DistanceInfo distanceInfo = InfoSpider.getPointInfo(distanceImage);
    distanceImage.setRGB(distanceInfo.getRed().getW(), distanceInfo.getRed().getH(), 0);
    distanceImage.setRGB(distanceInfo.getBlue().getW(), distanceInfo.getBlue().getH(), 0);

    ImageFile.imageToFile(distanceImage, new File("F:\\workProject\\gaboolic\\auto-machine\\image\\1439215073741__.png"));
  }

  @Test
  public void test() {
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\distance");
    String page_ = "F:\\workProject\\gaboolic\\auto-machine\\image\\distance_";
    for (File file : path.listFiles()) {
      BufferedImage distanceImage = ImageFile.fileToImage(file);
      try {
        DistanceInfo distanceInfo = InfoSpider.getPointInfo(distanceImage);
        distanceImage.setRGB(distanceInfo.getRed().getW(), distanceInfo.getRed().getH(), 0);
        distanceImage.setRGB(distanceInfo.getBlue().getW(), distanceInfo.getBlue().getH(), 0);

        ImageFile.imageToFile(distanceImage, new File(page_, file.getName()));
        //System.out.println(distanceInfo);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(file.getName());
      }
      //System.exit(0);
    }
  }
}
