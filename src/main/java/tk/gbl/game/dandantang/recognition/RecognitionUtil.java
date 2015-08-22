package tk.gbl.game.dandantang.recognition;

import tk.gbl.util.image.Binary;
import tk.gbl.util.image.Distance;

import java.awt.image.BufferedImage;

/**
 * Date: 2015/8/9
 * Time: 9:14
 *
 * @author Tian.Dong
 */
public class RecognitionUtil {
  public static int getAngle(BufferedImage angleImage) {
    int[][] left = Binary.deal(angleImage.getSubimage(0, 0, 12, 13));
    int[][] right = Binary.deal(angleImage.getSubimage(12, 0, 12, 13));


    int l = getSingleNumber(left);
    int r = getSingleNumber(right);
    return Integer.parseInt(l + "" + r);
  }

  public static int getSingleNumber(int[][] image) {
    int mixDistance = 1000;
    int num = -1;
    for (int i = 0; i < ImageArrayInstance.numberList.size(); i++) {
      int[][] instance = ImageArrayInstance.numberList.get(i);
      int distance = Distance.hamDistance(instance, image);
      if (mixDistance > distance) {
        mixDistance = distance;
        num = i;
      }
    }
    return num;
  }

  //蓝 0 51 204
  public static boolean isBlue(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r ==0 && g ==51 && b==204) {
      return true;
    }
    return false;
  }

  //蓝圈
  public static boolean isBlueRing(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r >= 100 && r <= 200 && g >= 100 && g <= 200 && b >= 200) {
      return true;
    }
    return false;
  }

  //红 255 0 0
  public static boolean isRed(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r ==255 && g ==0 && b ==0) {
      return true;
    }
    return false;
  }

  //红圈
  public static boolean isRedRing(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r >= 200 && g >= 100 && g <= 200 && b >= 100 && b <= 200) {
      return true;
    }
    return false;
  }

  public static boolean isBorderGreyLine(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r == 102 && g == 102 && b == 102) {
      return true;
    }
    return false;
  }

  public static boolean isGreyLine(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r == 153 && g == 153 && b == 153) {
      return true;
    }
    return false;
  }

  public static boolean isWindGrey(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r <= 100 && g <= 100 && b <= 100) {
      return true;
    }
    return false;
  }

  public static boolean isWindBlue(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r == 118 && g == 118 && b == 232) {
      return true;
    }
    return false;
  }

  public static boolean isBlack(int rgb) {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    if (r >= 200 && g >= 200 && b >= 200) {
      return true;
    }
    return false;
  }
}
