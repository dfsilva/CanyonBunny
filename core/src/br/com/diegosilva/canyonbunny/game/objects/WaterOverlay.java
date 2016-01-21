package br.com.diegosilva.canyonbunny.game.objects;

import br.com.diegosilva.canyonbunny.game.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Diego on 20/01/2016.
 */
public class WaterOverlay extends AbstractGameObject{

    private TextureRegion regWaterOverlay;
    private float lenght;

    public WaterOverlay(float length){
        this.lenght = length;
        init();
    }

    private void init(){
        dimension.set(lenght*10, 3);
        regWaterOverlay = Assets.instance.levelDecoration.waterOverlay;
        origin.x = -dimension.x/2;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;

        reg = regWaterOverlay;
        batch.draw(reg.getTexture(), position.x+origin.x, position.y+origin.y,origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(), false, false);
    }
}
