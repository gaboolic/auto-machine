package tk.gbl.util.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Date: 2015/8/1
 * Time: 18:45
 *
 * @author Tian.Dong
 */
public class ImageFile {
  public static void imageToFile(BufferedImage image,File file){
    try {
      ImageIO.write(image, "png", file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static BufferedImage fileToImage(File file) {
    try {
      BufferedImage image = ImageIO.read(file);
      return image;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
