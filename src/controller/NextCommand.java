package controller;

import view.ImageDisplay;

public class NextCommand implements Command {
    private ImageDisplay imageDisplay;

    public NextCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.display(imageDisplay.getCurrentImage().getNext());
    }
}
