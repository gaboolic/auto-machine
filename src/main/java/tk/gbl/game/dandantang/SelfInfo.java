package tk.gbl.game.dandantang;

/**
 * Date: 2015/8/1
 * Time: 16:51
 *
 * @author Tian.Dong
 */
public class SelfInfo {

  /**
   * 角度
   */
  Integer angle;

  /**
   * 力量
   */
  Long power;

  Integer leftOrRight;


  public Integer getAngle() {
    return angle;
  }

  public void setAngle(Integer angle) {
    this.angle = angle;
  }

  public Long getPower() {
    return power;
  }

  public void setPower(Long power) {
    this.power = power;
  }

  public Integer getLeftOrRight() {
    return leftOrRight;
  }

  public void setLeftOrRight(Integer leftOrRight) {
    this.leftOrRight = leftOrRight;
  }

  @Override
  public String toString() {
    return "SelfInfo{" +
        "angle=" + angle +
        ", power=" + power +
        ", leftOrRight=" + leftOrRight +
        '}';
  }
}
