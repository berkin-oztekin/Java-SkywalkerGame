package com.realtusgml.neon.framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image=image;

    }
    public BufferedImage grabImage(int col , int row , int widht , int height){
    BufferedImage img = image.getSubimage((col*widht)- widht , (row*height)-height ,widht ,height);
    return img ;
    }

}
