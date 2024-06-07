
import java.awt.Graphics2D; 
import net.sourceforge.tess4j.*; 
import java.awt.Image; 
import java.awt.image.*; 
import java.io.*; 
import javax.imageio.ImageIO; 

public class Main { 
    private static String path = "C:\\Users\\wyatt\\OneDrive\\Desktop\\Coding\\Tess4J-3.4.8-src\\Tess4J\\tessdata"; 
    private static String image = "nickel1.jpg";
    public static void processImg(BufferedImage ipimage, float scaleFactor, float offset) throws IOException, TesseractException { 
        BufferedImage opimage = new BufferedImage(1050, 1024, ipimage.getType()); 

        Graphics2D graphic = opimage.createGraphics(); 
  
        graphic.drawImage(ipimage, 0, 0, 1050, 1024, null); 
        graphic.dispose(); 
  
        RescaleOp rescale = new RescaleOp(scaleFactor, offset, null); 
  
        BufferedImage fopimage  = rescale.filter(opimage, null); 
        ImageIO.write(fopimage, "jpg", new File(image)); 
  
        Tesseract it = new Tesseract(); 
  
        it.setDatapath(path); 
  
        String str = it.doOCR(fopimage); 
        System.out.println(str); 
    } 

    public static void main(String args[]) throws Exception { 
        File f = new File(image); 
  
        BufferedImage ipimage = ImageIO.read(f); 
  
        // getting RGB content of the whole image file 
        double d = ipimage.getRGB(ipimage.getTileWidth() / 2, ipimage.getTileHeight() / 2); 
  
        if (d >= -1.4211511E7 && d < -7254228) { 
            processImg(ipimage, 3f, -10f); 
        } else if (d >= -7254228 && d < -2171170) { 
            processImg(ipimage, 1.455f, -47f); 
        } else if (d >= -2171170 && d < -1907998) { 
            processImg(ipimage, 1.35f, -10f); 
        } else if (d >= -1907998 && d < -257) { 
            processImg(ipimage, 1.19f, 0.5f); 
        } else if (d >= -257 && d < -1) { 
            processImg(ipimage, 1f, 0.5f); 
        } else if (d >= -1 && d < 2) { 
            processImg(ipimage, 1f, 0.35f); 
        } 
    } 
}