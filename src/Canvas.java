
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import org.opencv.core.Core;

public class Canvas extends JPanel{
    
    
    private BufferedImage currentImage;
    private String imagePath;
    
    
    @Override
    public void paintComponent(Graphics g) {        
        g.drawImage(currentImage, 0, 0, this.getWidth(), this.getHeight(), this);        
    }
    
    void setBufferImage(BufferedImage bI) {
        this.currentImage = bI;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
