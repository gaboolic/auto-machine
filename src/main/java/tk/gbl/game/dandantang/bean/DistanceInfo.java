package tk.gbl.game.dandantang.bean;

import tk.gbl.util.image.Point;

/**
 * Date: 2015/8/6
 * Time: 9:14
 *
 * @author Tian.Dong
 */
public class DistanceInfo {
  double originalWidth;
  double originalHeight;

  double width;

  double height;

  /**
   * 0 red
   * 1 blue
   */
  Integer redOrBlue;

  Point red;
  Point blue;

  public double getWidth() {
    return width;
  }

  public void setWidth(double width) {
    this.width = width;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public Point getRed() {
    return red;
  }

  public void setRed(Point red) {
    this.red = red;
  }

  public Point getBlue() {
    return blue;
  }

  public void setBlue(Point blue) {
    this.blue = blue;
  }

  @Override
  public String toString() {
    return "DistanceInfo{" +
        "width=" + width +
        ", height=" + height +
        ", red=" + red +
        ", blue=" + blue +
        '}';
  }

  public Integer getRedOrBlue() {
    return redOrBlue;
  }

  public void setRedOrBlue(Integer redOrBlue) {
    this.redOrBlue = redOrBlue;
  }

  public void executeDistance() {
    this.width = this.blue.getW() - this.red.getW();
    this.height = this.red.getH() -this.blue.getH();
    if (redOrBlue == 1) {
      //1是blue
      this.width = -this.width;
      this.height = -this.height;
    }
  }

  public double getOriginalWidth() {
    return originalWidth;
  }

  public void setOriginalWidth(double originalWidth) {
    this.originalWidth = originalWidth;
  }

  public double getOriginalHeight() {
    return originalHeight;
  }

  public void setOriginalHeight(double originalHeight) {
    this.originalHeight = originalHeight;
  }
}
