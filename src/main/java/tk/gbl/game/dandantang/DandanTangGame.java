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
      new Thread(){
        public void run(){
          while (true) {
            try {
              Thread.sleep(3000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            SelfInfo selfInfo = InfoSpider.getSelfInfo();
            System.out.println(selfInfo);
            boolean isReady = InfoSpider.getReadyInfo();
            if(isReady) {
              stateMachine.fire(DandanTangEvent.READY);
            }
          }
        }
      }.start();
  }



  public static void main(String[] args){
    new DandanTangGame().start();
  }

  public void ready() {
    SelfInfo selfInfo = InfoSpider.getSelfInfo();
    GameControl.revise(selfInfo);

    GameControl.keyPressSpace();
    stateMachine.fire(DandanTangEvent.PRE_SHOOT);
    try {
      Thread.sleep(selfInfo.getPower()*40+25);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    GameControl.keyReleaseSpace();
    stateMachine.fire(DandanTangEvent.SHOOT);

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    stateMachine.fire(DandanTangEvent.SHOOT_DONE);

  }
}
