package tk.gbl.game.dandantang;

import java.io.*;

/**
 * Date: 2015/8/1
 * Time: 16:56
 *
 * @author Tian.Dong
 */
public class WorldInfo {
  /**
   * 重力加速度
   */
  public static double gravity = 16;

  /**
   * 力量系数
   */
  public static double powerRatio = 1.0;

  /**
   * 风力系数
   */
  public static double windRatio = 1.0;

  public static double getGravity() {
    return gravity;
  }

  public static void setGravity(double gravity) {
    WorldInfo.gravity = gravity;
  }

  public static double getPowerRatio() {
    return powerRatio;
  }

  public static void setPowerRatio(double powerRatio) {
    WorldInfo.powerRatio = powerRatio;
  }

  public static double getWindRatio() {
    return windRatio;
  }

  public static void setWindRatio(double windRatio) {
    WorldInfo.windRatio = windRatio;
  }

  public void read() throws IOException {
    File file = new File("F:\\workProject\\gaboolic\\auto-machine", "world.txt");
    if (file.exists()) {
      BufferedReader fileReader = new BufferedReader(new FileReader(file));
      String line = fileReader.readLine();
      if (line != null) {
        String[] params = line.split("#");
        setGravity(Double.parseDouble(params[0]));
        setPowerRatio(Double.parseDouble(params[1]));
        setWindRatio(Double.parseDouble(params[2]));
      } else {
        initValue();
      }
      fileReader.close();
    } else {
      initValue();
    }
  }

  private void initValue() {
    setGravity(9.8);
    setPowerRatio(1.0);
    setWindRatio(1.0);
  }

  public void write() throws IOException {
    File file = new File("F:\\workProject\\gaboolic\\auto-machine", "world.txt");
    PrintWriter fileWriter = new PrintWriter(new FileWriter(file));
    fileWriter.println(toStoreString());
    fileWriter.close();
  }

  private String toStoreString() {
    return getGravity() + "#"
        + getPowerRatio() + "#"
        + getWindRatio();
  }

  public static void main(String[] args) throws IOException {
    WorldInfo worldInfo = new WorldInfo();
    worldInfo.read();
    System.out.println(worldInfo.toStoreString());

//    worldInfo.write();
  }
}
