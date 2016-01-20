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

    }

    public void setLength(int length) {
        this.length = length;
    }

    public void increaseLength(int qtd){
        setLength(this.length+qtd);
    }
}
