package tk.gbl.statemachine.instance.dandantang;

import org.junit.Test;

/**
 * Date: 2015/8/1
 * Time: 16:48
 *
 * @author Tian.Dong
 */
public class DandantangStateMachineTest {
  @Test
  public void test() throws Exception {
    DandanTangStateMachine dandanTangStateMachine = new DandanTangStateMachine();
    dandanTangStateMachine.setState(DandanTangState.SUF_SHOOT);
    System.out.println("当前状态："+dandanTangStateMachine.getCurrState());

//    dandanTangStateMachine.fire(DandanTangEvent.PRE_SHOOT);
//    System.out.println("当前状态："+dandanTangStateMachine.getCurrState());
//
//    dandanTangStateMachine.fire(DandanTangEvent.SHOOT);
//    System.out.println("当前状态："+dandanTangStateMachine.getCurrState());
//
//    dandanTangStateMachine.fire(DandanTangEvent.SHOOT_DONE);
//    System.out.println("当前状态："+dandanTangStateMachine.getCurrState());
//
    dandanTangStateMachine.fire(DandanTangEvent.GAME_OVER);
    System.out.println("当前状态："+dandanTangStateMachine.getCurrState());


    dandanTangStateMachine.fire(DandanTangEvent.READY);
    System.out.println("当前状态："+dandanTangStateMachine.getCurrState());
  }
}
