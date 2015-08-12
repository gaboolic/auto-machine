package tk.gbl.game.dandantang.recognition;

import tk.gbl.game.dandantang.bean.Wind;
import tk.gbl.util.ScreenUtil;
import tk.gbl.util.image.Binary;
import tk.gbl.util.image.Distance;
import tk.gbl.util.image.ImageFile;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Date: 2015/8/10
 * Time: 16:57
 *
 * @author Tian.Dong
 */
public class WindInfoSpider {
  static String filePath = "F:\\workProject\\gaboolic\\auto-machine\\image";


  /**
   * 风
   */
  public static Wind getWind() {
    //675 89
    //729 132
    BufferedImage windImage = ScreenUtil.getScreenPart(637, 90, 80, 40);
    ImageFile.imageToFile(windImage, new File(filePath + "\\wind", System.currentTimeMillis() + ".png"));

    BufferedImage leftImage = windImage.getSubimage(26, 8, 16, 22);
    BufferedImage rightImage = windImage.getSubimage(46, 8, 16, 22);

    String left = makeOutLeftNumber(leftImage);
    String right = makeOutRightNumber(rightImage);
    boolean isLeft = isLeft(windImage);

    Wind wind = new Wind();
    wind.setLeft(isLeft);
    wind.setValue(Double.valueOf(left));
    return wind;
  }

  public static boolean isLeft(BufferedImage image) {
    //左风 13~56都是黑
    int[][] img = Binary.deal(image);
//    Output.output(img);
    int whiteCount = 0;
    int whiteCount2 = 0;
    for (int w = 13; w < 56; w++) {
      int argb = img[5][w];
      int argb2 = img[6][w];
      if (argb == 0) {
        whiteCount++;
      }
      if (argb2 == 0) {
        whiteCount2++;
      }
    }

    return whiteCount < 5 || whiteCount2 < 5;

    /*for (int w = 0; w < image.getWidth(); w++) {
      int argb = image.getRGB(w, 33);
      if (RecognitionUtil.isWindBlue(argb)) {
        //箭头在右边，风从左边吹，是左风
        System.out.println(w);
        return w > image.getWidth() / 2;
      }
    }
    return true;*/
  }


  public static String makeOutLeftNumber(BufferedImage image) {
    int[][] img = Binary.deal(image);
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind_l");
    String x = null;
    int distance = 10000;
    for (File file : path.listFiles()) {
      int[][] arr = Binary.deal(file);
      int dis = Distance.hamDistance(img, arr);
      if (distance > dis) {
        distance = dis;
        x = file.getName().substring(0, 1);
      }
    }
    return x;
  }

  public static String makeOutRightNumber(BufferedImage image) {
    int[][] img = Binary.deal(image);
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind_r");
    String x = null;
    int distance = 10000;
    for (File file : path.listFiles()) {
      int[][] arr = Binary.deal(file);
      int dis = Distance.hamDistance(img, arr);
      if (distance > dis) {
        distance = dis;
        x = file.getName().substring(0, 1);
      }
    }
    return x;
  }

  public static void main(String[] args) {
    File path = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\wind_");
    for (File file : path.listFiles()) {
      if (!file.getName().startsWith("r")) {
        continue;
      }
      BufferedImage image = ImageFile.fileToImage(file);
      String result = makeOutRightNumber(image);

      String fileName = "r-" + result + "-" + System.currentTimeMillis() + ".png";
      boolean flag = file.renameTo(new File(path, fileName));
      System.out.println(flag + " " + fileName);
    }

  }
}
