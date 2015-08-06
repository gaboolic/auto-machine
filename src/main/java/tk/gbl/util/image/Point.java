package tk.gbl.util.image;

/**
 * Date: 2014/9/22
 * Time: 14:37
 *
 * @author Tian.Dong
 */
public class Point {

  int h = 0;
  int w = 0;

  public Point(int h, int w) {
    if (h > 0) {
      this.h = h;
    }
    if (w > 0) {
      this.w = w;
    }
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(h) * 37 + w;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Point))
      return false;
    Point p = (Point) o;
    return this.h == p.h && this.w == p.w;
  }

  public int getH() {
    return h;
  }

  public void setH(int h) {
    this.h = h;
  }

  public int getW() {
    return w;
  }

  public void setW(int w) {
    this.w = w;
  }

  public void up() {
    this.h--;
  }

  public void down() {
    this.h++;
  }

  public void left() {
    this.w--;
  }

  public void right() {
    this.w++;
  }

  @Override
  public String toString() {
    return "Point{" +
        "h=" + h +
        ", w=" + w +
        '}';
  }
}
