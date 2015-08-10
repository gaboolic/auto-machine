package tk.gbl.game.dandantang.recognition;

/**
 * Date: 2015/8/10
 * Time: 18:13
 *
 * @author Tian.Dong
 */
public class WidthAndRgb implements Comparable<WidthAndRgb> {
  int width;

  int rgb;

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getRgb() {
    return rgb;
  }

  public void setRgb(int rgb) {
    this.rgb = rgb;
  }

  @Override
  public int compareTo(WidthAndRgb other) {
    return this.rgb - other.rgb;
  }

  @Override
  public String toString() {
    int r = (int) (((rgb >> 16) & 0xFF));
    int g = (int) (((rgb >> 8) & 0xFF));
    int b = (int) (((rgb >> 0) & 0xFF));
    return "WidthAndRgb{" +
        "width=" + width +
        ", rgb=" + r + "," + g + "," + b +
        '}';
  }
}
