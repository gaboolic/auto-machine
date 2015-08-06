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
  public void test(){
    File distanceFile = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\distance\\distance.png");
    BufferedImage distanceImage = ImageFile.fileToImage(distanceFile);

    System.out.println(InfoSpider.getDistanceInfo(distanceImage));
  }

  @Test
  public void test123() {
    File distanceFile = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\distance\\111.png");
    BufferedImage distanceImage = ImageFile.fileToImage(distanceFile);
    for (int w = 0; w < distanceImage.getWidth(); w++) {
      for (int h = 0; h < distanceImage.getHeight(); h++) {
        int argb = distanceImage.getRGB(w, h);
        int r = (int) (((argb >> 16) & 0xFF));
        int g = (int) (((argb >> 8) & 0xFF));
        int b = (int) (((argb >> 0) & 0xFF));

        //45 24 红
        if (w == 45 && h == 24) {
          System.out.println("红点：");

          System.out.println(r);
          System.out.println(g);
          System.out.println(b);
        }

        //红圈
        if (w == 43 && h == 18) {
          System.out.println("红圈：");

          System.out.println(r);
          System.out.println(g);
          System.out.println(b);
        }

        //蓝 0 51 204
        if (w == 124 && h == 41) {
          System.out.println("蓝");

          System.out.println(r);
          System.out.println(g);
          System.out.println(b);
        }
      }
    }
  }

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
