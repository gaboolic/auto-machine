package tk.gbl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 2015/8/6
 * Time: 10:18
 *
 * @author Tian.Dong
 */
public class FatArrayList<E> extends ArrayList<E> {

  Map<String,E> map = new HashMap<>();

  public E getFirst() {
    return get(0);
  }

  public E getMedian() {
    int length = this.size();
    return length == 0 ? null : get(length / 2);
  }

  public E getEnd() {
    int length = this.size();
    return length == 0 ? null : get(length - 1);
  }

  public void set(String key,E value) {
    map.put(key,value);
  }

  public E getCustom(String key) {
    return map.get(key);
  }
}
