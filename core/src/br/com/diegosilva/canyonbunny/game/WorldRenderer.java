package br.com.diegosilva.canyonbunny.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by 98379720172 on 12/01/2016.
 */
public class WorldRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;
    public WorldRenderer (WorldController worldController) { }
    private void init () { }
    public void render () { }
    public void resize (int width, int height) { }
    @Override public void dispose () { }
}