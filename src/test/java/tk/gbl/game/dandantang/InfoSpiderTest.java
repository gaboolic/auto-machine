package tk.gbl.game.dandantang;

import org.junit.Test;
import tk.gbl.util.image.ArrayToImage;
import tk.gbl.util.image.Binary;
import tk.gbl.util.image.ImageFile;
import tk.gbl.util.image.Output;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Date: 2015/8/2
 * Time: 17:17
 *
 * @author Tian.Dong
 */
public class InfoSpiderTest {

  @Test
  public void testMake() {
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\angle");
    for (File file : path.listFiles()) {
      //file = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\angle\\1438504034790.png");
      BufferedImage image = ImageFile.fileToImage(file);
      int angle = InfoSpider.getAngle(image);

      file.renameTo(new File("F:\\workProject\\gaboolic\\auto-machine\\image\\angle\\" + angle + "_" + System.currentTimeMillis() + ".png"));
    }
  }

  public static void main(String[] args) throws IOException {
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\angleArray");
    for (File file : path.listFiles()) {
      int[][] image = Binary.deal(file);
      Output.outputS(image, file.getName().substring(0, 1));
    }
  }

  public static void main222(String[] args) throws IOException {
//    BufferedImage ready = ImageFile.fileToImage(new File("F:\\workProject\\gaboolic\\auto-machine\\image\\passTest.png"));
//    int[][] image = Binary.deal(ready);
//    Output.output(image);
//    ArrayToImage.createImage(image, new File("F:\\workProject\\gaboolic\\auto-machine\\image\\passTestArray.png"));
//    System.out.println(Distance.hamDistance(image, ImageArrayInstance.readyImage));

    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\angle");
    String arrayPath = "F:\\workProject\\gaboolic\\auto-machine\\image\\angleArray";
    for (File file : path.listFiles()) {
      if (!file.isFile()) {
        continue;
      }
      BufferedImage image = ImageFile.fileToImage(file);
      BufferedImage left = image.getSubimage(0, 0, 11, 13);
      BufferedImage right = image.getSubimage(13, 0, 11, 13);

      int[][] leftArray = Binary.deal(left);
      int[][] rightArray = Binary.deal(right);

      ArrayToImage.createImage(leftArray, new File(arrayPath, "a_" + System.currentTimeMillis() + ".png"));
      ArrayToImage.createImage(rightArray, new File(arrayPath, "a_" + System.currentTimeMillis() + ".png"));

    }
  }


  public static void main4(String[] args) throws IOException {
    File file = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\angle\\wrong\\27_1438517735172.png");
    int[][] image = Binary.deal(file);
    Output.output(image);
  }
}
