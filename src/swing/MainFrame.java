package swing;

import controller.Command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private Map<String, Command> commands;

    public MainFrame(){
        setTitle("Image Viewer");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        commands = new HashMap();
        this.add(toolbar(),BorderLayout.SOUTH);
    }


    private JPanel toolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button("Next"));
        panel.add(button("Prev"));
        return panel;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(ae -> commands.get(name).execute());
        return button;
    }

    public void execute(){
        this.setVisible(true);
    }

    public void addCommand(String name ,Command command){
        commands.put(name, command);
    }

    public void addSwingImageDisplay(SwingImageDisplay swingImageDisplay){
        this.add(swingImageDisplay,BorderLayout.CENTER);
    }
}
