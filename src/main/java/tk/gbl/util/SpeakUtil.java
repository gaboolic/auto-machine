package tk.gbl.util;

/**
 * Date: 2015/8/11
 * Time: 22:01
 *
 * @author Tian.Dong
 */
public class SpeakUtil {
  public static void speak(String str) {
    String fileName = SpeakUtil.class.getClassLoader().getResource("speak.vbs").getPath();
    fileName = fileName.substring(1);
    String[] cpCmd  = new String[]{"wscript", fileName,str};
    try {
      Process process = Runtime.getRuntime().exec(cpCmd);
      int val = process.waitFor();
      process.destroy();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("Speak:"+str);
  }

}
