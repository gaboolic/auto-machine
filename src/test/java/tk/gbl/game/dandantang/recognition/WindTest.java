package tk.gbl.game.dandantang.recognition;

import org.junit.Test;
import tk.gbl.util.MD5Util;
import tk.gbl.util.image.ArrayToImage;
import tk.gbl.util.image.Binary;
import tk.gbl.util.image.ImageFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Date: 2015/8/10
 * Time: 14:41
 *
 * @author Tian.Dong
 */
public class WindTest {

  @Test
  public void test111(){
    File file = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind\\1439214733067.png");
    BufferedImage image = ImageFile.fileToImage(file);
    System.out.println(WindInfoSpider.makeOutLeftNumber(image));
  }

  @Test
  public void testLeftOrRight() {
    File file1 = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind\\l-l-l-l-r-1438434430717.png");
    BufferedImage image1= ImageFile.fileToImage(file1);

    System.out.println(WindInfoSpider.isLeft(image1));

    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind");
    for (File file : path.listFiles()) {
      BufferedImage image = ImageFile.fileToImage(file);

      boolean isLeft = WindInfoSpider.isLeft(image);
      if(isLeft) {
        boolean flag = file.renameTo(new File(path, "l-"+file.getName()));
      } else {
        boolean flag = file.renameTo(new File(path, "r-"+file.getName()));
      }

    }
  }

  @Test
  public void test() throws IOException {
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind_");
    File path2 = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind__");
    for (File file : path.listFiles()) {
      BufferedImage bufferedImage = ImageFile.fileToImage(file);
      int argb = bufferedImage.getRGB(5, 5);
      int r = (int) (((argb >> 16) & 0xFF));
      int g = (int) (((argb >> 8) & 0xFF));
      int b = (int) (((argb >> 0) & 0xFF));
      if (r >= 255) {
        r = 255;
      }
      if (g >= 255) {
        g = 255;
      }
      if (b >= 255) {
        b = 255;
      }
      int grey = (int) Math.pow((Math.pow(r, 2.2) * 0.2973 + Math.pow(g, 2.2) * 0.6274 + Math.pow(b, 2.2) * 0.0753), 1 / 2.2);

      int[][] arr = Binary.deal(bufferedImage, grey + 2);
      ArrayToImage.createImage(arr, new File(path2, System.currentTimeMillis() + ".png"));

    }
  }

  @Test
  public void testDealOrigin() throws IOException {
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind");
    File path2 = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind_");

    System.out.println(path.listFiles().length);
    Set<String> fileMd5s = new HashSet<>();
    Set<String> arrMd5s = new HashSet<>();
    for (File file : path.listFiles()) {
      if (!fileMd5s.contains(MD5Util.getMd5ByFile(file))) {
        fileMd5s.add(MD5Util.getMd5ByFile(file));

        BufferedImage bufferedImage = ImageFile.fileToImage(file);
        BufferedImage left = bufferedImage.getSubimage(26, 8, 16, 22);
        BufferedImage right = bufferedImage.getSubimage(46, 8, 16, 22);

        int[][] leftArray = Binary.deal(left);
        int[][] rightArray = Binary.deal(right);
        if (!arrMd5s.contains(MD5Util.getMd5(leftArray))) {
          arrMd5s.add(MD5Util.getMd5(leftArray));
          ArrayToImage.createImage(leftArray, new File(path2, "l-" + System.currentTimeMillis() + ".png"));
        }
        if (!arrMd5s.contains(MD5Util.getMd5(rightArray))) {
          arrMd5s.add(MD5Util.getMd5(rightArray));
          ArrayToImage.createImage(rightArray, new File(path2, "r-" + System.currentTimeMillis() + ".png"));
        }
      }

    }

    System.out.println(fileMd5s.size());
  }

  @Test
  public void testMd5() throws IOException {
    File file1 = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind_\\1439191830567.png");
    File file2 = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind_\\1439191830585.png");

    BufferedImage bufferedImage1 = ImageFile.fileToImage(file1);
    BufferedImage bufferedImage2 = ImageFile.fileToImage(file2);

    int[][] arr1 = Binary.deal(bufferedImage1);
    int[][] arr2 = Binary.deal(bufferedImage2);
    System.out.println(MD5Util.getMd5(arr1));
    System.out.println(MD5Util.getMd5(arr2));
  }
}
