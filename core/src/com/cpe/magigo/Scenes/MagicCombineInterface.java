package com.cpe.magigo.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cpe.magigo.MagiGO;
import com.cpe.magigo.Sprites.Magician;
import com.sun.prism.Texture;

/**
 * Created by darunphop on 13-Nov-16.
 */
public class MagicCombineInterface implements Disposable {
    public Stage stage;
    private boolean flag;
    private SpriteBatch batch;
    private Image elementSet[];
    private Magician player;
    private Viewport viewport;

    public MagicCombineInterface(SpriteBatch sb, Magician player){
        this.batch = sb;
        viewport = new FitViewport(MagiGO.V_WIDTH,MagiGO.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,sb);
        this.player = player;
        flag = false;
        elementSet = new Image[5];
        for (int i = -2; i < 3; i++) {
            Image tmp = new Image(new com.badlogic.gdx.graphics.Texture("element/fire.png"));
            tmp.setPosition((MagiGO.V_WIDTH/2.08f) + (i * 100),(MagiGO.V_HEIGHT/1.3f));
            tmp.setSize(77,77);
            elementSet[i + 2] = tmp;
            stage.addActor(elementSet[i + 2]);
        }
    }
    public void standby(){
        flag = true;
    }
    public void sleep(){
        flag = false;
    }
    public void update(float dt) {
        if (flag){

        }
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public boolean isReady(){
        return flag;
    }
}