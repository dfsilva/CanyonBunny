package br.com.diegosilva.canyonbunny.game;

import br.com.diegosilva.canyonbunny.game.objects.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
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

        rocks = new Array<Rock>();

        Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
        int lastPixel = -1;

        for(int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++){
            for(int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++){
                AbstractGameObject obj = null;

                float offsetHeight = 0;
                float baseHeight = pixmap.getHeight() - pixelY;
                int currentPixel = pixmap.getPixel(pixelX, pixelY);

                if(BlockType.EMPTY.sameColor(currentPixel)){
                    //do nothing
                }else if(BlockType.ROCK.sameColor(currentPixel)){
                    if(lastPixel != currentPixel){
                        obj = new Rock();
                        float heightIncreseFactor = 0.25f;
                        offsetHeight = -2.5f;
                        obj.position.set(pixelX, baseHeight * obj.dimension.y * heightIncreseFactor + offsetHeight);
                        rocks.add((Rock)obj);
                    }else{
                        rocks.get(rocks.size - 1).increaseLength(1);
                    }
                }else if(BlockType.PLAYER_SPAWNPOINT.sameColor(currentPixel)){

                }else if(BlockType.ITEM_FEATHER.sameColor(currentPixel)){

                }else if(BlockType.ITEM_GOLD_COIN.sameColor(currentPixel)){

                }else{
                    int r = 0xff & (currentPixel >>> 24);
                    int g = 0xff & (currentPixel >>> 16);
                    int b = 0xff & (currentPixel >>> 8);
                    int a = 0xff & currentPixel;
                    Gdx.app.error(TAG, "Objeto desconhecido em x < "+pixelX+"> y<"+pixelY+">: r<"+r+"> g<"+g+"> b<"+b+"> a<"+a+">");
                }
                lastPixel = currentPixel;
            }
        }

        clouds = new Clouds(pixmap.getWidth());
        clouds.position.set(0,2);
        mountains = new Mountains(pixmap.getWidth());
        mountains.position.set(-1,-1);
        waterOverlay = new WaterOverlay(pixmap.getWidth());
        waterOverlay.position.set(0,-3.75f);

        pixmap.dispose();
        Gdx.app.debug(TAG, "level '"+filename+"' carregado");
    }

    public void render(SpriteBatch batch){
        mountains.render(batch);
        for(Rock rock : rocks)
            rock.render(batch);
        waterOverlay.render(batch);
        clouds.render(batch);
    }

}
