package tk.gbl.util;

import org.junit.Test;

import java.io.IOException;

/**
 * Date: 2015/8/1
 * Time: 17:30
 *
 * @author Tian.Dong
 */
public class FileUtilTest {
  @Test
  public void test() throws IOException {
    FileUtil fileUtil = new FileUtil("/worldInfo.properties");
    String aaa = fileUtil.getProperties("powerRatio");
    System.out.println(aaa);

    fileUtil.setProperties("powerRatio","333");
    aaa = fileUtil.getProperties("powerRatio");
    System.out.println(aaa);

    fileUtil.write();
  }
}
