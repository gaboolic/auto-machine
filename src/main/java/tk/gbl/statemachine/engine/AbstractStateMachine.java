package tk.gbl.statemachine.engine;

import tk.gbl.statemachine.anno.*;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2015/8/1
 * Time: 9:18
 *
 * @author Tian.Dong
 */
public class AbstractStateMachine<E, S, C> implements StateMachine<E, S, C> {

  Map<S, Map<E, Transit>> state_eventMap = new HashMap<>();
  S currState;
  C context;

  Class stateType;
  Class eventType;

  public AbstractStateMachine() {
    StateMachineParameters stateMachineParameters = this.getClass().getAnnotation(StateMachineParameters.class);
    stateType = stateMachineParameters.stateType();
    eventType = stateMachineParameters.eventType();

    Transitions transitions = this.getClass().getAnnotation(Transitions.class);
    Transit[] transitionList = transitions.value();


    States states = this.getClass().getAnnotation(States.class);
    State[] stateList = states.value();
    for (State state : stateList) {
      S realState = getRealState(state.name());
      Map<E, Transit> event_TransitMap = new HashMap<>();

      for (Transit transit : transitionList) {
        if (transit.from().equals(state.name())) {
          E realEvent = getRealEvent(transit.on());
          event_TransitMap.put(realEvent, transit);
        }
      }
      state_eventMap.put((S) realState, event_TransitMap);
    }

    currState = getRealState(stateList[0].name());
  }

  public AbstractStateMachine(S initState) {
    this();
    currState = initState;
  }


  @Override
  public void fire(E event) {
    System.out.print(event);
    try {
      //根据当前状态找到event map
      Map<E, Transit> event_TransitMap = state_eventMap.get(currState);
      Transit transit = event_TransitMap.get(event);
      String methodName = transit.callMethod();
      Method callMethod = null;
      callMethod = this.getClass().getMethod(methodName, getContextClass());
      callMethod.invoke(this, context);
      currState = getRealState(transit.to());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setState(S state) {
    this.currState = state;
  }

  @Override
  public S getCurrState() {
    return currState;
  }


  private S getRealState(String stateName) {
    Object[] realStates = stateType.getEnumConstants();
    for (Object state : realStates) {
      String name = state.toString();
      if (stateName.equals(name)) {
        return (S) state;
      }
    }
    return null;
  }

  private E getRealEvent(String eventName) {
    Object[] realEvents = eventType.getEnumConstants();
    for (Object event : realEvents) {
      String name = event.toString();
      if (eventName.equals(name)) {
        return (E) event;
      }
    }
    return null;
  }

  protected Class getContextClass() {
    Type genType = getClass().getGenericSuperclass();
    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
    Class<?> entityClass = (Class<?>) params[2];
    return entityClass;
  }
}
