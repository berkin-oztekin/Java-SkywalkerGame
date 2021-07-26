package com.realtus.gml.neon.objects;
import com.realtusgml.neon.framework.GameObject;
import com.realtusgml.neon.framework.ObjectId;
import com.realtusgml.neon.framework.Texture;
import com.realtutsgml.neon.window.Game;

import java.awt.*;
import java.util.LinkedList;

public class Block extends GameObject {
    Texture tex = Game.getInstance();
    private int type;


    public Block(float x, float y,int type , ObjectId id)
    {
        super(x, y, id);
        this.type=type;
    }


    public void tick(LinkedList<GameObject> object) { // have worked block to block

    }

    public void render(Graphics g) { // to show the block
    if(type== 0 )//dirt block
        g.drawImage(tex.block[0] , (int)x , (int) y ,null);
        if(type== 1 )//grass block
            g.drawImage(tex.block[1] , (int)x , (int) y ,null);


    }

    public Rectangle getBounds() { //measurment of block

        return new Rectangle((int)x , (int)y , 32, 32);
    }


}
