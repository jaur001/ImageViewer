package loader;

import model.Image;

import java.io.*;

public class FileImageLoader implements ImageLoader{

    private File[] fileList;

    public FileImageLoader(File root){
        fileList = root.listFiles(isImageDomain());
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private FileFilter isImageDomain(){
        return pathname -> (pathname.getName().endsWith(".png") || pathname.getName().endsWith(".jpg"));
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String getName() {
                return fileList[i].getName();
            }

            @Override
            public byte[] getData() throws IOException {
                return dataOf(file());
            }

            private File file(){
                return fileList[(i+ fileList.length)% fileList.length];
            }

            private byte[] dataOf(File file) throws IOException {
                byte[] buffer = new byte[4096];
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                    BufferedOutputStream outputStream = new BufferedOutputStream(result)) {
                    while(true){
                        int read = inputStream.read(buffer);
                        if(read < 0) break;
                        outputStream.write(buffer, 0, read);
                    }
                    outputStream.flush();
                }
                return result.toByteArray();
            }

            @Override
            public Image getPrev() {
                return imageAt((i-1+ fileList.length)% fileList.length);
            }

            @Override
            public Image getNext() {
                return imageAt((i+1)% fileList.length);
            }
        };
    }
}
