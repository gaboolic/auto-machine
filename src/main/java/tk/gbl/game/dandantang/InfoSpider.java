package tk.gbl.game.dandantang;

import tk.gbl.util.FatArrayList;
import tk.gbl.util.ScreenUtil;
import tk.gbl.util.image.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Date: 2015/8/1
 * Time: 18:32
 *
 * @author Tian.Dong
 */
public class InfoSpider {

  static String filePath = "F:\\workProject\\gaboolic\\auto-machine\\image";

  /**
   * 风
   */
  public static Wind getWind() {
    //675 89
    //729 132
    BufferedImage windImage = ScreenUtil.getScreenPart(637, 90, 80, 40);
    ImageFile.imageToFile(windImage, new File(filePath + "\\wind", System.currentTimeMillis() + ".png"));
    return null;
  }

  /**
   * 角度，方向
   */
  public static SelfInfo getSelfInfo() {
    //222 624     252 651
    BufferedImage angleImage = ScreenUtil.getScreenPart(221, 635, 24, 13);
    ImageFile.imageToFile(angleImage, new File(filePath + "\\angle", System.currentTimeMillis() + ".png"));
    int angle = getAngle(angleImage);


    SelfInfo selfInfo = new SelfInfo();
    selfInfo.setAngle(angle);

    //233 625 left right
    BufferedImage powerLeftRightImage = ScreenUtil.getScreenPart(227, 632, 10, 10);
    ImageFile.imageToFile(powerLeftRightImage, new File(filePath + "\\powerLeftRightImage", System.currentTimeMillis() + ".png"));


    //得到发射方向
    return selfInfo;
  }

  /**
   * 距离信息
   */
  public static DistanceInfo getDistanceInfo() {
    BufferedImage distanceImage = ScreenUtil.getScreenPart(940, 100, 255, 105);
    ImageFile.imageToFile(distanceImage, new File(filePath + "\\distance", System.currentTimeMillis() + ".png"));

    //坐标
    DistanceInfo distanceInfo = getPointInfo(distanceImage);
    if (GlobalValue.redOrBlue != null) {
      distanceInfo.setRedOrBlue(GlobalValue.redOrBlue);
      distanceInfo.executeDistance();
      return distanceInfo;
    }

    int rw = distanceInfo.getRed().getW();
    int rh = distanceInfo.getRed().getH();
    FatArrayList<Integer> redRgbList = new FatArrayList<>();

    int bw = distanceInfo.getBlue().getW();
    int bh = distanceInfo.getBlue().getH();
    FatArrayList<Integer> blueRgbList = new FatArrayList<>();

    for (int i = rh; i < rh + 15; i++) {
      redRgbList.add(distanceImage.getRGB(rw, i));
    }
    for (int i = bh; i < bh + 15; i++) {
      blueRgbList.add(distanceImage.getRGB(bw, i));
    }

    int retry = 4;
    while (retry-- > 0) {
      try {
        Thread.sleep(30);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      BufferedImage image = ScreenUtil.getScreenPart(940, 100, 255, 105);
      for (int i = rh; i < rh + 15; i++) {
        int newRgb = image.getRGB(rw, i);
        if (!redRgbList.get(i - rh).equals(newRgb)) {
          distanceInfo.setRedOrBlue(0);
          break;
        }
      }
      for (int i = bh; i < bh + 15; i++) {
        int newRgb = image.getRGB(bw, i);
        if (!blueRgbList.get(i - bh).equals(newRgb)) {
          distanceInfo.setRedOrBlue(1);
          break;
        }
      }
    }
    GlobalValue.redOrBlue = distanceInfo.getRedOrBlue();
    distanceInfo.executeDistance();
    return distanceInfo;
  }

  /**
   * 距离信息
   */
  public static DistanceInfo getDistanceInfoTest() {
    BufferedImage distanceImage = ImageFile.fileToImage(new File("F:\\workProject\\gaboolic\\auto-machine\\image\\distance\\1438959189485.png"));
    ImageFile.imageToFile(distanceImage, new File(filePath + "\\distance", System.currentTimeMillis() + ".png"));

    //坐标
    DistanceInfo distanceInfo = getPointInfo(distanceImage);
    if (GlobalValue.redOrBlue != null) {
      distanceInfo.setRedOrBlue(GlobalValue.redOrBlue);
      distanceInfo.executeDistance();
      return distanceInfo;
    }

    int rw = distanceInfo.getRed().getW();
    int rh = distanceInfo.getRed().getH();
    FatArrayList<Integer> redRgbList = new FatArrayList<>();

    int bw = distanceInfo.getBlue().getW();
    int bh = distanceInfo.getBlue().getH();
    FatArrayList<Integer> blueRgbList = new FatArrayList<>();

    System.out.println("高度" + distanceImage.getHeight());
    System.out.println("宽度" + distanceImage.getWidth());

    for (int i = rh; i < rh + 15; i++) {
      redRgbList.add(distanceImage.getRGB(rw, i));
    }
    for (int i = bh; i < bh + 15; i++) {
      blueRgbList.add(distanceImage.getRGB(bw, i));
    }

    int retry = 1;
    while (retry-- > 0) {
      try {
        Thread.sleep(30);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      BufferedImage image = ImageFile.fileToImage(new File("F:\\workProject\\gaboolic\\auto-machine\\image\\distance\\1438959195807.png"));
      ;
      for (int i = rh; i < rh + 15; i++) {
        int newRgb = image.getRGB(rw, i);
        if (!redRgbList.get(i - rh).equals(newRgb)) {
          distanceInfo.setRedOrBlue(0);
          break;
        }
      }
      for (int i = bh; i < bh + 15; i++) {
        int newRgb = image.getRGB(bw, i);
        if (!blueRgbList.get(i - bh).equals(newRgb)) {
          distanceInfo.setRedOrBlue(1);
          break;
        }
      }
    }
    GlobalValue.redOrBlue = distanceInfo.getRedOrBlue();
    distanceInfo.executeDistance();
    return distanceInfo;
  }


  public static DistanceInfo getPointInfo(BufferedImage distanceImage) {
    FatArrayList<Integer> redXList = new FatArrayList<>();
    FatArrayList<Integer> redYList = new FatArrayList<>();
    FatArrayList<Integer> blueXList = new FatArrayList<>();
    FatArrayList<Integer> blueYList = new FatArrayList<>();

    for (int w = 0; w < distanceImage.getWidth(); w++) {
      for (int h = 0; h < distanceImage.getHeight(); h++) {
        int argb = distanceImage.getRGB(w, h);
        if (isRed(argb)) {
          redXList.add(w);
          redYList.add(h);
        } else if (isBlue(argb)) {
          blueXList.add(w);
          blueYList.add(h);
        }
      }
    }
    Collections.sort(redXList);
    Collections.sort(redYList);
    Collections.sort(blueXList);
    Collections.sort(blueYList);

    int redX = redXList.getMedian();
    int redY = redYList.getMedian();

    int blueX = blueXList.getMedian();
    int blueY = blueYList.getMedian();

    DistanceInfo distanceInfo = new DistanceInfo();
    distanceInfo.setRed(new Point(redX, redY));
    distanceInfo.setBlue(new Point(blueX, blueY));
    return distanceInfo;
  }

  public static boolean getReadyInfo() {
    //332 655
    BufferedImage readyInfo = ScreenUtil.getScreenPart(658, 234, 50, 20);
    ImageFile.imageToFile(readyInfo, new File(filePath + "\\ready", System.currentTimeMillis() + ".png"));
    int[][] readyImage = Binary.deal(readyInfo);
    int distance = Distance.hamDistance(readyImage, ImageArrayInstance.readyImage);
    int distance2 = Distance.hamDistance(readyImage, ImageArrayInstance.readyImage2);
    System.out.println("ready:"+distance + "|"+distance2);
    return distance < 180 || distance2 < 180;
  }

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
    if (r <= 55 && g <= 55 && b >= 200) {
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
    if (r >= 200 && g <= 55 && b <= 55) {
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

  public static void main(String[] args) throws IOException {
    File file = new File("F:\\workProject\\gaboolic\\auto-machine\\image\\ready.png");
    int[][] aaa = Binary.deal(file);

    ArrayToImage.createImage(aaa, new File("F:\\workProject\\gaboolic\\auto-machine\\image\\readyNew.png"));
  }

}
