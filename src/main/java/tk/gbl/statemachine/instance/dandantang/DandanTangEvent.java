package tk.gbl.statemachine.instance.dandantang;

/**
 * Date: 2015/7/31
 * Time: 13:44
 *
 * @author Tian.Dong
 */
public enum DandanTangEvent {
  READY_BY_MAN,
  READY,

  PRE_SHOOT, //按下空格

  SHOOT, //释放空格

  SHOOT_DONE_BY_MAN,
  SHOOT_DONE,

  GAME_OVER
}
