package tk.gbl.game.dandantang;

import tk.gbl.statemachine.instance.dandantang.DandanTangEvent;
import tk.gbl.statemachine.instance.dandantang.DandanTangStateMachine;

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
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          boolean isReady = InfoSpider.getReadyInfo();
          if (isReady) {
            stateMachine.fire(DandanTangEvent.READY);
            flag = false;
          }
        }
      }
    }.start();
  }


  public static void main(String[] args) {
    new DandanTangGame().start();
  }

  public void ready() {
    DistanceInfo distanceInfo = InfoSpider.getDistanceInfo();
    SelfInfo selfInfo = GameControl.revise(distanceInfo);

    GameControl.keyPressSpace();
    stateMachine.fire(DandanTangEvent.PRE_SHOOT);
    try {
      Thread.sleep(selfInfo.getPower() * 40 + 25);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    GameControl.keyReleaseSpace();
    stateMachine.fire(DandanTangEvent.SHOOT);

    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    stateMachine.fire(DandanTangEvent.SHOOT_DONE);
  }

  public void shutDone(){
    this.start();
  }
}
