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
  double gravity;

  /**
   * 力量系数
   */
  double powerRatio;

  /**
   * 风力系数
   */
  double windRatio;

  public double getGravity() {
    return gravity;
  }

  public void setGravity(double gravity) {
    this.gravity = gravity;
  }

  public double getPowerRatio() {
    return powerRatio;
  }

  public void setPowerRatio(double powerRatio) {
    this.powerRatio = powerRatio;
  }

  public double getWindRatio() {
    return windRatio;
  }

  public void setWindRatio(double windRatio) {
    this.windRatio = windRatio;
  }

  public void read() throws IOException {
    File file = new File("F:\\workProject\\gaboolic\\auto-machine", "world.txt");
    if (file.exists()) {
      BufferedReader fileReader = new BufferedReader(new FileReader(file));
      String line = fileReader.readLine();
      if (line != null) {
        String[] params = line.split("#");
        this.setGravity(Double.parseDouble(params[0]));
        this.setPowerRatio(Double.parseDouble(params[1]));
        this.setWindRatio(Double.parseDouble(params[2]));
      } else {
        this.initValue();
      }
      fileReader.close();
    } else {
      this.initValue();
    }
  }

  private void initValue() {
    this.setGravity(9.8);
    this.setPowerRatio(1.0);
    this.setWindRatio(1.0);
  }

  public void write() throws IOException {
    File file = new File("F:\\workProject\\gaboolic\\auto-machine", "world.txt");
    PrintWriter fileWriter = new PrintWriter(new FileWriter(file));
    fileWriter.println(this.toStoreString());
    fileWriter.close();
  }

  private String toStoreString() {
    return this.getGravity() + "#"
        + this.getPowerRatio() + "#"
        + this.getWindRatio();
  }

  public static void main(String[] args) throws IOException {
    WorldInfo worldInfo = new WorldInfo();
    worldInfo.read();
    System.out.println(worldInfo.toStoreString());

//    worldInfo.write();
  }
}
