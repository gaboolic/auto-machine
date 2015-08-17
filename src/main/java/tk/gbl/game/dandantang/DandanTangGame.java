package tk.gbl.game.dandantang;

import tk.gbl.game.dandantang.bean.DistanceInfo;
import tk.gbl.game.dandantang.bean.SelfInfo;
import tk.gbl.game.dandantang.recognition.InfoSpider;
import tk.gbl.statemachine.instance.dandantang.DandanTangEvent;
import tk.gbl.statemachine.instance.dandantang.DandanTangStateMachine;
import tk.gbl.util.SpeakUtil;

/**
 * Date: 2015/8/1
 * Time: 9:10
 *
 * @author Tian.Dong
 */
public class DandanTangGame {
  DandanTangStateMachine stateMachine = new DandanTangStateMachine(this);

  public void start() {
    new Thread() {
      public void run() {
        boolean flag = true;
        while (flag) {
          boolean isReady = InfoSpider.getReadyInfo();
          if (isReady) {
            stateMachine.fire(DandanTangEvent.READY);
            flag = false;
          }
          boolean isOver = InfoSpider.getOverInfo();
          if (isOver) {
            stateMachine.fire(DandanTangEvent.GAME_OVER);
            flag = false;
          }

          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }.start();
  }


  public void ready() {
    DistanceInfo distanceInfo = null;
    try {
      distanceInfo = InfoSpider.getDistanceInfo();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e1) {
        e1.printStackTrace();
      }
      ready();
    }
    SelfInfo selfInfo = GameControl.revise(distanceInfo);

    GameControl.keyPressSpace();
    stateMachine.fire(DandanTangEvent.PRE_SHOOT);
    try {
      Thread.sleep((long) ((Math.round(selfInfo.getPower())) * 40 + 25));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    GameControl.keyReleaseSpace();
    stateMachine.fire(DandanTangEvent.SHOOT);

    try {
      Thread.sleep(8000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    stateMachine.fire(DandanTangEvent.SHOOT_DONE);
  }

  public void shutDone() {
    this.start();
  }

  /**
   * 游戏结束 清理
   */
  public void gameOver() {
    GlobalValue.leftOrRight = null;
    GlobalValue.redOrBlue = null;

    System.err.println("游戏结束，重新开始");
    SpeakUtil.speak("游戏结束，重新开始");
    try {
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.start();
  }
}
