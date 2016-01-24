package br.com.diegosilva.canyonbunny.game;

import br.com.diegosilva.canyonbunny.util.CameraHelper;
import br.com.diegosilva.canyonbunny.util.Constants;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;

/**
 * Created by 98379720172 on 12/01/2016.
 */
public class WorldController extends InputAdapter {

    private static final String TAG = WorldController.class.getName();

    public CameraHelper cameraHelper;
    public Level level;
    public int lives;
    public int score;

    public WorldController() {
        init();
    }

    private void initLevel(){
        score = 0;
        level = new Level(Constants.LEVEL_01);
    }

    private void init() {
        Gdx.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
        lives = Constants.LIVES_START;
        initLevel();
    }

    public void update(float deltaTime) {
        handleDebugInput(deltaTime);
        cameraHelper.update(deltaTime);
    }

    private void handleDebugInput(float deltaTime) {
        if (Gdx.app.getType() != Application.ApplicationType.Desktop)
            return;

        float sprMoveSpeed = 5 * deltaTime;

        // Camera Controls (move)
        float camMoveSpeed = 5 * deltaTime;
        float camMoveSpeedAccelerationFactor = 5;
        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
            camMoveSpeed *= camMoveSpeedAccelerationFactor;
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            moveCamera(-camMoveSpeed,0);
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            moveCamera(camMoveSpeed,0);
        if (Gdx.input.isKeyPressed(Keys.UP))
            moveCamera(0, camMoveSpeed);
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            moveCamera(0,-camMoveSpeed);
        if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
            cameraHelper.setPosition(0, 0);

        // Camera Controls (zoom)
        float camZoomSpeed = 1 * deltaTime;
        float camZoomSpeedAccelerationFactor = 5;

        if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
            camZoomSpeed *= camZoomSpeedAccelerationFactor;
        if (Gdx.input.isKeyPressed(Keys.COMMA))
            cameraHelper.addZoom(camZoomSpeed);
        if (Gdx.input.isKeyPressed(Keys.PERIOD))
            cameraHelper.addZoom(-camZoomSpeed);
        if (Gdx.input.isKeyPressed(Keys.SLASH))
            cameraHelper.setZoom(1);
    }

    private void moveCamera (float x, float y) {
        x += cameraHelper.getPosition().x;
        y += cameraHelper.getPosition().y;
        cameraHelper.setPosition(x, y);
    }


    @Override
    public boolean keyUp(int keycode) {
        // Reset game world
        if (keycode == Keys.R) {
            init();
            Gdx.app.debug(TAG, "Game world resetted");
        }

        return false;
    }
}
