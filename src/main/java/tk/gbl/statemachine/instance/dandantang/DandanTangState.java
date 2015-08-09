package tk.gbl.statemachine.instance.dandantang;

/**
 * Date: 2015/7/31
 * Time: 13:44
 *
 * @author Tian.Dong
 */
public enum DandanTangState {
  READY(0),
  PRE_SHOOT(1),
  SHOOTING(2),
  SUF_SHOOT(3),
  GAME_OVER(4);

  DandanTangState(Integer state) {
    this.state = state;
  }

  Integer state;

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }
}
