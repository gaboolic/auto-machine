package tk.gbl.util.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Date: 2014/10/13
 * Time: 18:08
 *
 * @author Tian.Dong
 */
public class ArrayToImage {
  public static void createImage(int[][] t,String fileName) throws IOException {
    File file = new File(fileName);
    createImage(t,file);
  }

  public static void createImage(int[][] t,File file) throws IOException {
    BufferedImage image = new BufferedImage(t[0].length, t.length,
        BufferedImage.TYPE_INT_RGB);
    for (int h = 0; h < t.length; h++) {
      for (int w = 0; w < t[h].length; w++) {
        if (t[h][w] == 1) {
          image.setRGB(w, h, 0);
        } else {
          image.setRGB(w, h, 0xffffff);
        }
      }
    }

    if(!file.getParentFile().exists()){
      file.getParentFile().mkdir();
    }
    ImageIO.write(image, "PNG", file);
  }


}
