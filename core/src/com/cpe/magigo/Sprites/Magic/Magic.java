package com.cpe.magigo.Sprites.Magic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.Sprites.Magician;
import com.cpe.magigo.System.ElementType;

/**
 * Created by ICQCQ on 15-Nov-16.
 */
public abstract class Magic  extends Sprite {
    protected ElementType element;
    protected PlayScreen screen;
    public Magic(ElementType e) {
        this.element = e;
    }
    public Magic(ElementType e,PlayScreen screen) {
        this.element = e;
        this.screen = screen;
    }

    public Magic() {
    }

    protected Body createMagic(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(screen.getPlayer().b2body.getPosition().x+0.2f,screen.getPlayer().b2body.getPosition().y);

        Body body = screen.getWorld().createBody(bodyDef);


        CircleShape circle = new CircleShape();
        circle.setRadius(6f/ MagiGO.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f;
        fixtureDef.filter.categoryBits = MagiGO.MAGIC_BIT;

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
       // circle.dispose();
        Gdx.app.log("MAGIC", "Magic Create");
        return body;
    }
    public abstract void excecute(PlayScreen screen);

}
