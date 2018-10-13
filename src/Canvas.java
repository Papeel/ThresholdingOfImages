import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel{
    
    private BufferedImage currentImage;
    
    @Override
    public void paintComponent(Graphics g) {        
        g.drawImage(currentImage, 0, 0, this.getWidth(), this.getHeight(), this);        
    }
    
    protected void showImage(BufferedImage image) {
        this.currentImage = image;
        repaint();
    }
    
    protected BufferedImage getBufferedImage(File file) {
        BufferedImage bI = null;
        try {
            bI = ImageIO.read(file);
        } catch(IOException e) {}
        return bI;
    }
}
