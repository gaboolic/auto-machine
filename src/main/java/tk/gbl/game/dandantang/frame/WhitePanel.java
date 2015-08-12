package tk.gbl.game.dandantang.frame;

import tk.gbl.util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Date: 2015/8/12
 * Time: 21:44
 *
 * @author Tian.Dong
 */
public class WhitePanel extends JPanel {

  public WhitePanel(){
    this.setOpaque(false);
  }

  public void paintComponent(Graphics g) {
//    BufferedImage bkg = getBackgroundImg();
//    g.drawImage(bkg, 0, 0, this.getWidth(), this.getHeight(), null);
  }

  private BufferedImage getBackgroundImg() {//截屏代码
    try {
      Point p = this.getLocation();//移動方式,用p

      //截圖
      BufferedImage background = ScreenUtil.getScreenPart((int) p.getX() + 1, (int) p.getY() + 1, this.getWidth() - 2, this.getHeight() - 2);


      float[] data = {
          //0.0625f,0.125f,0.0625f,
          //0.125f,0.125f,0.125f,
          //0.0625f,0.125f,0.0625f,

          0.0625f, 0.125f, 0.0625f,
          0.125f, 0.125f, 0.125f,
          0.0625f, 0.125f, 0.0625f,

          //0.170f,0.06f,0.170f,
          //0.06f,0.06f,0.06f,
          //0.170f,0.06f,0.170f,
      };
      Kernel kernel = new Kernel(3, 3, data);
      ConvolveOp co = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
      BufferedImage background2 = null;
      background2 = co.filter(background, background2);
      return background2;
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
