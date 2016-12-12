package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.Status;

/**
 * Created by Asuka on 20/11/2559.
 */
public abstract class Enemy extends Sprite{
    protected World world;
    protected PlayScreen sceen;
    protected Status status;
    public Body b2body;
    public Enemy(PlayScreen screen ,float x , float y)
    {
        this.world = screen.getWorld();
        this.sceen = screen;
        setPosition(x , y);
        defineEnemy();

        this.status = new Status();
    }

    protected abstract void defineEnemy();
}
