package br.com.diegosilva.canyonbunny.game.objects;

import br.com.diegosilva.canyonbunny.game.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Diego on 20/01/2016.
 */
public class Clouds extends AbstractGameObject {

    private float length;

    private Array<TextureRegion> regClouds;
    private Array<Cloud> clouds;

    private class Cloud extends AbstractGameObject{

        private TextureRegion regCloud;

        public Cloud(){}

        @Override
        public void render(SpriteBatch batch) {
            TextureRegion reg = regCloud;
            batch.draw(reg.getTexture(), position.x+origin.x, position.y+origin.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
                    reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
        }

        public void setRegion(TextureRegion region){
            regCloud = region;
        }
    }

    public Clouds(float length){
        this.length = length;
        init();
    }

    private void init(){
        dimension.set(3.0f, 1.5f);
        regClouds = new Array<TextureRegion>();
        regClouds.add(Assets.instance.levelDecoration.cloud01);
        regClouds.add(Assets.instance.levelDecoration.cloud02);
        regClouds.add(Assets.instance.levelDecoration.cloud03);

        int distFac = 5;
        int numClouds = (int) (length/distFac);
        clouds = new Array<Cloud>(2*numClouds);
        for(int i = 0; i < numClouds; i++){
            Cloud cloud = spawnCloud();
            cloud.position.x = i* distFac;
            clouds.add(cloud);
        }
    }

    private Cloud spawnCloud(){
        Cloud cloud = new Cloud();
        cloud.dimension.set(dimension);
        cloud.setRegion(regClouds.random());
        Vector2 pos = new Vector2();
        pos.x = length+10;
        pos.y += 1.75;
        pos.y += MathUtils.random(0.0f, 0.2f) * (MathUtils.randomBoolean() ? 1 : -1);
        cloud.position.set(pos);
        return cloud;
    }


    @Override
    public void render(SpriteBatch batch) {
        for( Cloud cloud : clouds){
            cloud.render(batch);
        }
    }
}
