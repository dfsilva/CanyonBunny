package br.com.diegosilva.canyonbunny.game.objects;

import br.com.diegosilva.canyonbunny.game.Assets;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by diego on 20/01/16.
 */
public class Rock extends AbstractGameObject{


    private TextureRegion regEdge;
    private TextureRegion regMiddle;
    private int length;

    public Rock(){
        init();
    }

    private void init(){
        dimension.set(1,1.5f);

        regEdge = Assets.instance.rock.edge;
        regMiddle = Assets.instance.rock.middle;

        setLength(1);
    }

    @Override
    public void render(SpriteBatch batch) {

        TextureRegion reg = null;

        float relX = 0;
        float relY = 0;

        reg = regEdge;
        relX -= dimension.x/4;

        batch.draw(reg.getTexture(), position.x+relX,position.y+relY, origin.x, origin.y,dimension.x/4,dimension.y,scale.x, scale.y,rotation,reg.getRegionX(),
                reg.getRegionY(),reg.getRegionWidth(), reg.getRegionHeight(), false, false);

        relX = 0;
        reg = regMiddle;

        for(int i = 0; i< length; i++){
            batch.draw(reg.getTexture(), position.x+relX,position.y+relY, origin.x, origin.y,dimension.x,dimension.y,scale.x, scale.y,rotation,reg.getRegionX(),
                    reg.getRegionY(),reg.getRegionWidth(), reg.getRegionHeight(), false, false);
            relX+=dimension.x;
        }

        reg = regEdge;

        batch.draw(reg.getTexture(), position.x+relX,position.y+relY, origin.x+dimension.x/8, origin.y,dimension.x/4,dimension.y,scale.x, scale.y,rotation,reg.getRegionX(),
                reg.getRegionY(),reg.getRegionWidth(), reg.getRegionHeight(), true, false);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void increaseLength(int qtd){
        setLength(this.length+qtd);
    }
}
