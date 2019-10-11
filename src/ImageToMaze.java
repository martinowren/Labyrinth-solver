import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class ImageToMaze {
    BufferedImage image;
    int width;
    int height;
    BufferedWriter filenSomSkrivesTil = new BufferedWriter(new FileWriter("src/perfekt4k.in"));

    public ImageToMaze() throws IOException {
        try {
            File input = new File("src/perfect4k.png");
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();



            int count = 0;
            String bigString = "";
            bigString += width + " ";
            bigString += height;
            filenSomSkrivesTil.write(bigString);
            filenSomSkrivesTil.newLine();
            bigString = "";

            for(int i=0; i<height; i++) {

                for(int j=0; j<width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    if (c.getBlue() == 0 && c.getGreen() == 0 && c.getRed() == 0) {
                        // Da må den være sort
                        bigString += "#";
                    } else {
                        // Da må den være hvit
                        bigString += ".";
                    }
                    /*
                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    System.out.println("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());*/
                }
                //System.out.println(bigString);
                filenSomSkrivesTil.write(bigString);
                filenSomSkrivesTil.newLine();
                bigString = "";
            }

            //filenSomSkrivesTil.write(fileContent);
            filenSomSkrivesTil.close();

        } catch (Exception e) {}
    }

    static public void main(String args[]) throws Exception {
        ImageToMaze obj = new ImageToMaze();
    }

}