package br.com.diegosilva.canyonbunny;

import br.com.diegosilva.canyonbunny.game.WorldController;
import br.com.diegosilva.canyonbunny.game.WorldRenderer;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CanyonBunnyMain extends ApplicationAdapter {

	private static final String TAG = CanyonBunnyMain.class.getName();

	private WorldController worldController;
	private WorldRenderer worldRenderer;

	@Override public void create () { }
	@Override public void render () { }
	@Override public void resize (int width, int height) { }
	@Override public void pause () { }
	@Override public void resume () { }
	@Override public void dispose () { }
}
