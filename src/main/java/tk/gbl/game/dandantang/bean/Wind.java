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
  boolean isLeft = true;

  /**
   * 风力值
   */
  double value = 0;

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

  @Override
  public String toString() {
    return "Wind{" +
        "isLeft=" + isLeft +
        ", value=" + value +
        '}';
  }
}
