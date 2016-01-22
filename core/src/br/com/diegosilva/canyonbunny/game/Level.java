package br.com.diegosilva.canyonbunny.game;

import br.com.diegosilva.canyonbunny.game.objects.Clouds;
import br.com.diegosilva.canyonbunny.game.objects.Mountains;
import br.com.diegosilva.canyonbunny.game.objects.Rock;
import br.com.diegosilva.canyonbunny.game.objects.WaterOverlay;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Diego on 21/01/2016.
 */
public class Level {

    public static final String TAG = Level.class.getName();

    public enum BlockType {
        EMPTY(0,0,0),
        ROCK(0,255,0),
        PLAYER_SPAWNPOINT(255,255,255),
        ITEM_FEATHER(255,0,255),
        ITEM_GOLD_COIN(255,255,0);

        private int color;

        private BlockType(int r, int g, int b){
            color = r << 24 | g << 16 | b << 8 | 0xff;
        }

        public boolean sameColor(int color){
            return this.color == color;
        }

        public int getColor(){
            return color;
        }
    }

    public Array<Rock> rocks;

    public Clouds clouds;
    public Mountains mountains;
    public WaterOverlay waterOverlay;

    public Level(String filename){
        init(filename);
    }

    private void init (String filename){

    }

    public void render(SpriteBatch batch){

    }

}
