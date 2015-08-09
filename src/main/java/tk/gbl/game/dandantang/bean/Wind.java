package tk.gbl.game.dandantang.bean;

/**
 * Date: 2015/8/1
 * Time: 16:46
 *
 * @author Tian.Dong
 */
public class Wind {
  /**
   * 左右
   */
  boolean isLeft;

  /**
   * 风力值
   */
  double value;

  public boolean isLeft() {
    return isLeft;
  }

  public void setLeft(boolean isLeft) {
    this.isLeft = isLeft;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
