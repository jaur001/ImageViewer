package model;

import java.io.IOException;

public interface Image {
    public String getName();
    public byte[] getData() throws IOException;
    public Image getPrev();
    public Image getNext();
}
