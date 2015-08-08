package tk.gbl.statemachine.instance.dandantang;

import tk.gbl.game.dandantang.DandanTangGame;
import tk.gbl.statemachine.anno.*;
import tk.gbl.statemachine.engine.AbstractStateMachine;
import tk.gbl.statemachine.engine.Context;

/**
 * Date: 2015/7/31
 * Time: 13:44
 *
 * @author Tian.Dong
 */
@States(
    {
        @State(name = "READY"),
        @State(name = "PRE_SHOOT"),
        @State(name = "SHOOTING"),
        @State(name = "SUF_SHOOT")
    })
@Transitions({
    //就绪 -> 准备发射 on 按下空格
    @Transit(from = "READY", to = "PRE_SHOOT", on = "PRE_SHOOT", callMethod = "fromReadyToPreShootOnPreShoot"),
    @Transit(from = "READY", to = "READY", on = "READY", callMethod = "fromReadyToReadyOnReady"),

    //准备发射 -> 发射中 on 释放空格
    @Transit(from = "PRE_SHOOT", to = "SHOOTING", on = "SHOOT", callMethod = "fromPreShootToShootingOnShoot"),

    //发射中 -> 发射完成
    @Transit(from = "SHOOTING", to = "SUF_SHOOT", on = "SHOOT_DONE_BY_MAN", callMethod = "fromShootingToSufShootOnShootDoneByMan"),
    @Transit(from = "SHOOTING", to = "SUF_SHOOT", on = "SHOOT_DONE", callMethod = "fromShootingToSufShootOnShootDone"),

    //发射完成 -> 就绪
    @Transit(from = "SUF_SHOOT", to = "READY", on = "READY_BY_MAN", callMethod = "fromSufShootToReadyOnReadyByMan"),
    @Transit(from = "SUF_SHOOT", to = "READY", on = "READY", callMethod = "fromSufShootToReadyOnReady")
})
@StateMachineParameters(stateType = DandanTangState.class, eventType = DandanTangEvent.class, contextType = Context.class)
public class DandanTangStateMachine extends AbstractStateMachine<DandanTangEvent, DandanTangState, Context> {

  DandanTangGame dandanTangGame;

  public DandanTangStateMachine() {

  }

  public DandanTangStateMachine(DandanTangGame dandanTangGame) {
    this.dandanTangGame = dandanTangGame;
  }

  public void fromReadyToPreShootOnPreShoot(Context context) throws Exception {
    System.out.println("fromReadyToPreShootOnPreShoot");
  }

  public void fromPreShootToShootingOnShoot(Context context) throws Exception {
    System.out.println("fromPreShootToShootingOnShoot");

  }

  public void fromShootingToSufShootOnShootDoneByMan(Context context) throws Exception {
    System.out.println("fromShootingToSufShootOnShootDoneByMan");

  }


  public void fromShootingToSufShootOnShootDone(Context context) throws Exception {
    dandanTangGame.shutDone();
  }

  public void fromSufShootToReadyOnReadyByMan(Context context) throws Exception {
    dandanTangGame.ready();
  }

  public void fromSufShootToReadyOnReady(Context context) throws Exception {
    dandanTangGame.ready();
  }

  public void fromReadyToReadyOnReady(Context context) throws Exception {
    dandanTangGame.ready();
  }
}
