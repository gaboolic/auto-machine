package tk.gbl.util;

import tk.gbl.util.image.Output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * Date: 2015/8/10
 * Time: 15:08
 *
 * @author Tian.Dong
 */
public class MD5Util {
  public static String getMd5(int[][] arr) {
    String value = null;
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(Output.arrToString(arr).getBytes());
      BigInteger bi = new BigInteger(1, md5.digest());
      value = bi.toString(16);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return value;
  }

  public static String getMd5ByFile(File file) throws FileNotFoundException {
    String value = null;
    FileInputStream in = new FileInputStream(file);
    try {
      MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(byteBuffer);
      BigInteger bi = new BigInteger(1, md5.digest());
      value = bi.toString(16);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if(null != in) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return value;
  }
}
