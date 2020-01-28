package swing;

import model.Image;
import view.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private Image image;

    @Override
    public Image getCurrentImage() {
        return this.image;
    }

    @Override
    public void display(Image image) {
        this.image = image;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        try {
            java.awt.Image awtImage = getAWTImage();
            Dimension dimension = getDimension(awtImage);
            g.drawImage(awtImage, (int)(this.getWidth()/2-dimension.getWidth()/2), (int)(this.getHeight()/2-dimension.getHeight()/2), (int)dimension.getWidth(), (int)dimension.getHeight(),null);
        } catch (IOException ex) {
            System.out.println("Error Drawing the Image!");
        }
    }

    private Dimension getDimension(java.awt.Image image) {
        double parameter = Math.min((double)this.getHeight()/(double)image.getHeight(null),(double)this.getWidth()/(double)image.getWidth(null));
        if(parameter<1){
            return new Dimension((int)(image.getWidth(null)*parameter),(int)(image.getHeight(null)*parameter));
        }
        return new Dimension(image.getWidth(null),image.getHeight(null));
    }

    private java.awt.Image getAWTImage() throws IOException {
        return ImageIO.read(new ByteArrayInputStream(this.image.getData()));
    }
}
