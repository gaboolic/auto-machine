package tk.gbl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Date: 2015/8/1
 * Time: 17:21
 *
 * @author Tian.Dong
 */
public class FileUtil {

  Properties prop = new Properties();

  String fileName;

  public FileUtil(String fileName) {
    this.fileName = fileName;
    InputStream is = getClass().getResourceAsStream(fileName);

    try {
      prop.load(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void write() throws IOException {
    String path = getClass().getResource("").getPath();
    FileOutputStream oFile = new FileOutputStream(new File(path,fileName));
    prop.store(oFile, "comm");
    oFile.close();
  }

  public String getProperties(String propertyName) {
    return prop.getProperty(propertyName);
  }

  public void setProperties(String propertyName,String value) {
    prop.setProperty(propertyName,value);
  }
}
