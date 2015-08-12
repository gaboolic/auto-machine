package tk.gbl.game.dandantang;

import tk.gbl.game.dandantang.bean.DistanceInfo;
import tk.gbl.game.dandantang.bean.SelfInfo;
import tk.gbl.game.dandantang.bean.Wind;
import tk.gbl.game.dandantang.frame.InfoFrame;

/**
 * Date: 2015/8/7
 * Time: 10:49
 *
 * @author Tian.Dong
 */
public class GlobalValue {
  public static Integer redOrBlue;
  public static Integer leftOrRight;
  public static Wind wind = new Wind();
  public static SelfInfo selfInfo = new SelfInfo();
  public static DistanceInfo distanceInfo = new DistanceInfo();

  public static InfoFrame infoFrame;
}
