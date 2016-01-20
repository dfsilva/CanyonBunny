package br.com.diegosilva.canyonbunny;

import br.com.diegosilva.canyonbunny.game.Assets;
import br.com.diegosilva.canyonbunny.game.WorldController;
import br.com.diegosilva.canyonbunny.game.WorldRenderer;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CanyonBunnyMain extends ApplicationAdapter {

    private static final String TAG = CanyonBunnyMain.class.getName();

    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private boolean paused;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Assets.instance.init(new AssetManager());
        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);
        paused = false;
    }

    @Override
    public void render() {
        if(!paused){
            worldController.update(Gdx.graphics.getDeltaTime());
        }
        Gdx.gl.glClearColor(100/255.0f, 149/255.0f, 237/255.0f, 255/255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void dispose() {
        worldRenderer.dispose();
        Assets.instance.dispose();
    }
}
