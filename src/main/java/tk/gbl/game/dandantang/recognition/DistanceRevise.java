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

    int length = 0;
    if (greyLineList.size() == 2) {
      int w1 = greyLineList.get(0).getCustom("w");
      int w2 = greyLineList.get(1).getCustom("w");
      length = Math.abs(w1 - w2);
    } else if (greyLineList.size() == 1) {
      int wl = borderGreyLineList.get(0).getCustom("w");
      int w = greyLineList.get(0).getCustom("w");
      int wr = borderGreyLineList.get(1).getCustom("w");
      int w1 = Math.abs(w-wl);
      int w2 = Math.abs(wr-w);
      length = Math.max(w1,w2);
    }
    distanceInfo.setWidth(distanceInfo.getWidth()*130/length);
    distanceInfo.setHeight(distanceInfo.getHeight()*130/length);

  }

}
