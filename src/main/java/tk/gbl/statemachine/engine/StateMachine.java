package tk.gbl.statemachine.engine;

/**
 * Date: 2015/7/31
 * Time: 13:44
 *
 * @author Tian.Dong
 */
public interface StateMachine<E,S,C> {
  public void fire(E event);

  public void setState(S state);

  public S getCurrState();
}
