package tk.gbl.game.dandantang;

import org.junit.Test;
import tk.gbl.game.dandantang.recognition.DistanceRevise;
import tk.gbl.util.image.ImageFile;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Date: 2015/8/9
 * Time: 9:39
 *
 * @author Tian.Dong
 */
public class DistanceReviseTest {
  @Test
  public void test() {
    File file = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\distance\\test5.png");
    BufferedImage bufferedImage = ImageFile.fileToImage(file);
    DistanceRevise.distanceRevise(bufferedImage,null);
  }
}
