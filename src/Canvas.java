import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel{
    
    private File currentImage;
    
    @Override
    public void paintComponent(Graphics g) {        
        g.drawImage(getBufferedImage(currentImage), 0, 0, this.getWidth(), this.getHeight(), this);        
    }
    
    void setImage(File image) {
        this.currentImage = image;
    }
    
    private BufferedImage getBufferedImage(File file) {
        BufferedImage bI = null;
        try {
            bI = ImageIO.read(file);
        } catch(IllegalArgumentException | IOException e) {}
        return bI;
    }
}
