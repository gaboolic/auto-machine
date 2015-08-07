package tk.gbl.game.dandantang;

import tk.gbl.util.image.Point;

/**
 * Date: 2015/8/6
 * Time: 9:14
 *
 * @author Tian.Dong
 */
public class DistanceInfo {
  Integer width;

  Integer height;

  /**
   * 0 red
   * 1 blue
   */
  Integer redOrBlue;

  Point red;
  Point blue;

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
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
    this.height = this.blue.getH() - this.red.getH();
    if (redOrBlue == 1) {
      this.width = -this.width;
      this.height = -this.height;
    }
  }
}
