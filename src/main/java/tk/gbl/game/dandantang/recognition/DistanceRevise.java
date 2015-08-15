package tk.gbl.game.dandantang.recognition;

import tk.gbl.game.dandantang.bean.DistanceInfo;
import tk.gbl.util.FatArrayList;

import java.awt.image.BufferedImage;

/**
 * 距离校正
 * <p>
 * Date: 2015/8/9
 * Time: 8:58
 *
 * @author Tian.Dong
 */
public class DistanceRevise {

  public static void distanceRevise(BufferedImage distanceImage, DistanceInfo distanceInfo) {
    FatArrayList<FatArrayList<Integer>> borderGreyLineList = new FatArrayList<>();
    FatArrayList<FatArrayList<Integer>> greyLineList = new FatArrayList<>();
    for (int w = 0; w < distanceImage.getWidth(); w++) {
      FatArrayList<Integer> borderGreyPointList = new FatArrayList<>();
      FatArrayList<Integer> greyPointList = new FatArrayList<>();

      for (int h = 0; h < distanceImage.getHeight(); h++) {
        int argb = distanceImage.getRGB(w, h);
        if (RecognitionUtil.isBorderGreyLine(argb)) {
          borderGreyPointList.add(h);
        } else if (RecognitionUtil.isGreyLine(argb)) {
          greyPointList.add(h);
        }
      }
      if (borderGreyPointList.size() > 30) {
        borderGreyPointList.set("w", w);
        borderGreyLineList.add(borderGreyPointList);
      }
      if (greyPointList.size() > 30) {
        greyPointList.set("w", w);
        greyLineList.add(greyPointList);
      }
    }

    FatArrayList<FatArrayList<Integer>> greyPointLineList = new FatArrayList<>();
    for (int h = 0; h < distanceImage.getHeight(); h++) {
      FatArrayList<Integer> greyPointLine = new FatArrayList<>();
      for (int w = 0; w < distanceImage.getWidth(); w++) {
        int argb = distanceImage.getRGB(w, h);
        if (RecognitionUtil.isGreyLine(argb)) {
          greyPointLine.add(w);
        }
      }
      if (greyPointLine.size() > 30) {
        greyPointLine.set("h", h);
        greyPointLineList.add(greyPointLine);
      }
    }
    int length = 1000;
    if (greyLineList.size() == 2) {
      int w1 = greyLineList.get(0).getCustom("w");
      int w2 = greyLineList.get(1).getCustom("w");
      length = Math.abs(w1 - w2);
    } else if (greyLineList.size() == 1) {

      int lineW = 3;
      if (greyPointLineList.size() > 0) {
        lineW = greyPointLineList.get(0).get(3);
      }

      int wl = borderGreyLineList.get(0).getCustom("w");
      int w = greyLineList.get(0).getCustom("w");
      int wr = borderGreyLineList.get(1).getCustom("w");
      int w1 = Math.abs(w - wl);
      int w2 = Math.abs(wr - w);

      if (wl < lineW && lineW < w) {
        length = w1;
      } else if (w < lineW && lineW < wr) {
        length = w2;
      }
    }
    System.out.println("length:" + length);

    if (distanceInfo == null) {
      return;
    }
    distanceInfo.setOriginalWidth(distanceInfo.getWidth());
    distanceInfo.setOriginalHeight(distanceInfo.getHeight());

    System.out.println("前" + distanceInfo.getWidth() + "_" + distanceInfo.getHeight());
    distanceInfo.setWidth(distanceInfo.getWidth() / length * 1000);
    distanceInfo.setHeight(distanceInfo.getHeight() / length * 1000);
    System.out.println("后" + distanceInfo.getWidth() + "_" + distanceInfo.getHeight());


  }

}
