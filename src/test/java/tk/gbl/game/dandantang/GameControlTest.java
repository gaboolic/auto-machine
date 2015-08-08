package tk.gbl.game.dandantang;

import org.junit.Test;

/**
 * Date: 2015/8/7
 * Time: 19:14
 *
 * @author Tian.Dong
 */
public class GameControlTest {
  @Test
  public void test(){
    System.out.println(Math.tan(Math.PI / 4));
  }

  @Test
  public void test111(){
    double sg = 1600;
    for(int i=0;i<7;i++) {
      double radian = i * 10 * 2 * Math.PI / 360;
      double tan = Math.tan(radian);
      double cos = Math.cos(radian);
      double v = Math.sqrt(sg / (2*tan))/cos;
      System.out.println((i*10)+"度"+",速度"+v);
    }

  }

  @Test
  public void test15_60(){

    double radian = 15 * 2 * Math.PI / 360;
    double tan = Math.tan(radian);
    double cos = Math.cos(radian);
    double sg = 2* 57 * 57 * cos * cos * tan;
    System.out.println(sg);
  }

  @Test
  public void test25_48(){

    double radian = 25 * 2 * Math.PI / 360;
    double tan = Math.tan(radian);
    double cos = Math.cos(radian);
    double sg = 2* 47 * 47 * cos * cos * tan;
    System.out.println(sg);
  }

  @Test
  public void test35_41(){

    double radian = 35 * 2 * Math.PI / 360;
    double tan = Math.tan(radian);
    double cos = Math.cos(radian);
    double sg = 2* 41 * 41 * cos * cos * tan;
    System.out.println(sg);
  }

  @Test
  public void test50_41(){

    double radian = 50 * 2 * Math.PI / 360;
    double tan = Math.tan(radian);
    double cos = Math.cos(radian);
    double sg = 2* 41 * 41 * cos * cos * tan;
    System.out.println(sg);
  }
}
