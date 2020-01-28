package main;

import controller.NextCommand;
import controller.PrevCommand;
import loader.FileImageLoader;
import loader.ImageLoader;
import swing.MainFrame;
import swing.SwingImageDisplay;
import view.ImageDisplay;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ImageLoader imageLoader = new FileImageLoader(new File("Album"));

        MainFrame mainFrame = new MainFrame();

        ImageDisplay swingImageDisplay = new SwingImageDisplay();
        swingImageDisplay.display(imageLoader.load());
        mainFrame.addSwingImageDisplay((SwingImageDisplay)swingImageDisplay);

        mainFrame.addCommand("Next", new NextCommand(swingImageDisplay));
        mainFrame.addCommand("Prev", new PrevCommand(swingImageDisplay));
        mainFrame.execute();
    }
}
