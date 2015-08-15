package tk.gbl.game.dandantang.recognition;

import tk.gbl.game.dandantang.GlobalValue;
import tk.gbl.game.dandantang.bean.DistanceInfo;
import tk.gbl.game.dandantang.bean.SelfInfo;
import tk.gbl.util.FatArrayList;
import tk.gbl.util.ScreenUtil;
import tk.gbl.util.image.Binary;
import tk.gbl.util.image.Distance;
import tk.gbl.util.image.ImageFile;
import tk.gbl.util.image.Point;

import java.awt.image.BufferedImage;
import java.io.File;
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
   * 角度，方向
   */
  public static SelfInfo getSelfInfo() {
    //222 624     252 651
    BufferedImage angleImage = ScreenUtil.getScreenPart(221, 635, 24, 13);
    ImageFile.imageToFile(angleImage, new File(filePath + "\\angle", System.currentTimeMillis() + ".png"));
    int angle = RecognitionUtil.getAngle(angleImage);


    SelfInfo selfInfo = new SelfInfo();
    selfInfo.setAngle(angle);

    //233 625 left right
    BufferedImage powerLeftRightImage = ScreenUtil.getScreenPart(227, 632, 10, 10);
//    ImageFile.imageToFile(powerLeftRightImage, new File(filePath + "\\powerLeftRightImage", System.currentTimeMillis() + ".png"));


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
      DistanceRevise.distanceRevise(distanceImage, distanceInfo);
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
    DistanceRevise.distanceRevise(distanceImage, distanceInfo);
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
        if (RecognitionUtil.isRed(argb)) {
          redXList.add(w);
          redYList.add(h);
        } else if (RecognitionUtil.isBlue(argb)) {
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
    return distance < 250 || distance2 < 250;
  }


  public static boolean getOverInfo() {
    BufferedImage overInfo = ScreenUtil.getScreenPart(933, 108, 151, 38);
//    ImageFile.imageToFile(overInfo, new File(filePath + "\\over", System.currentTimeMillis() + ".png"));
    int[][] overImage = Binary.deal(overInfo);
    int distance = Distance.hamDistance(overImage, OverImageArrayInstance.overImage);
    int distance2 = Distance.hamDistance(overImage, OverImageArrayInstance.overImage2);
    return distance < 800 || distance2 < 800;
  }

  /**
   * 距离信息测试
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
}
