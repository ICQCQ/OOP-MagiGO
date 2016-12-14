package com.cpe.magigo.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Screens.PlayScreen;
import com.cpe.magigo.System.Status;

/**
 * Created by Asuka on 20/11/2559.
 */
public class EnemyM extends Enemy {
    private float statetime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;
    public EnemyM(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for ( int i = 0 ; i < 3 ; i++)
            frames.add(new TextureRegion(screen.getAtlastMon().findRegion("monster"),i * 60 , 0 , 50 , 65   ));
        walkAnimation = new Animation(0.1f , frames);
        statetime = 0;
        setBounds(getX(),getY() ,60/MagiGO.PPM , 60/MagiGO.PPM);

        this.status = new Status();
    }

    public void draw(Batch batch)
    {
        super.draw(batch);
    }

    public TextureRegion getFrame() {
        TextureRegion region;
        region = walkAnimation.getKeyFrame(statetime,true);
        if(b2body.getLinearVelocity().x > 0 && region.isFlipX() == false){
            region.flip(false, false);
        }
        if(b2body.getLinearVelocity().x < 0 && region.isFlipX() == false){
            region.flip(true, false);
        }
        return region;
    }

    public void update(float dt)
    {
        statetime += dt;
        setRegion(getFrame());
        if(b2body.getPosition().x > 640/MagiGO.PPM){
            b2body.setLinearVelocity(velocity2);
        }
        else
            b2body.setLinearVelocity(velocity);
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight()/2);
        setRegion(walkAnimation.getKeyFrame(statetime , true));

    }

    @Override
    protected void defineEnemy()
    {
        Status status = new Status();
        BodyDef bodydef = new BodyDef();
        bodydef.position.set(getX(),getY());
        bodydef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodydef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / MagiGO.PPM);
        fdef.filter.categoryBits = MagiGO.ENEMY_BIT;
        fdef.filter.maskBits = MagiGO.DEFAULT_BIT | MagiGO.PLATFORM_BIT  | MagiGO.MAGIGO_BIT | MagiGO.CRYSTAL_BIT | MagiGO.OBJECT_BIT;


        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
}
