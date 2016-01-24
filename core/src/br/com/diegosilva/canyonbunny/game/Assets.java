package br.com.diegosilva.canyonbunny.game;

import br.com.diegosilva.canyonbunny.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by 98379720172 on 19/01/2016.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;

    public AssetBunny bunny;
    public AssetRock rock;
    public AssetGoldCoin goldCoin;
    public AssetFeather feather;
    public AssetLevelDecoration levelDecoration;

    public AssetFonts fonts;

    private Assets(){}

    public void init (AssetManager assetManager){
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();
        Gdx.app.debug(TAG, "Quantidade de recursos carregados: "+assetManager.getAssetNames().size);

        for(String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: "+a);

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

        //ativar filtro de textura para pixel smoothing
        for(Texture t : atlas.getTextures()){
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        fonts = new AssetFonts();
        bunny = new AssetBunny(atlas);
        rock = new AssetRock(atlas);
        goldCoin = new AssetGoldCoin(atlas);
        feather = new AssetFeather(atlas);
        levelDecoration = new AssetLevelDecoration(atlas);

    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Nao foi possivel carregar o recurso "+asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        fonts.defaultSmall.dispose();
        fonts.defaultNormal.dispose();
        fonts.defaultBig.dispose();
    }


    public class AssetBunny{
        public final TextureAtlas.AtlasRegion head;

        public AssetBunny(TextureAtlas atlas){
            head = atlas.findRegion("bunny_head");
        }
    }

    public class AssetRock {
        public final TextureAtlas.AtlasRegion edge;
        public final TextureAtlas.AtlasRegion middle;

        public AssetRock(TextureAtlas atlas){
            edge = atlas.findRegion("rock_edge");
            middle = atlas.findRegion("rock_middle");
        }
    }

    public class AssetGoldCoin {
        public final TextureAtlas.AtlasRegion goldCoin;
        public AssetGoldCoin(TextureAtlas atlas) {
            goldCoin = atlas.findRegion("item_gold_coin");
        }
    }

    public class AssetFeather {
        public final TextureAtlas.AtlasRegion feather;
        public AssetFeather(TextureAtlas atlas) {
            feather = atlas.findRegion("item_feather");
        }
    }

    public class AssetLevelDecoration {

        public final TextureAtlas.AtlasRegion cloud01;
        public final TextureAtlas.AtlasRegion cloud02;
        public final TextureAtlas.AtlasRegion cloud03;
        public final TextureAtlas.AtlasRegion mountainLeft;
        public final TextureAtlas.AtlasRegion mountainRight;
        public final TextureAtlas.AtlasRegion waterOverlay;

        public AssetLevelDecoration(TextureAtlas atlas) {
            cloud01 = atlas.findRegion("cloud01");
            cloud02 = atlas.findRegion("cloud02");
            cloud03 = atlas.findRegion("cloud03");
            mountainLeft = atlas.findRegion("mountain_left");
            mountainRight = atlas.findRegion("mountain_right");
            waterOverlay = atlas.findRegion("water_overlay");
        }
    }


    public class AssetFonts{
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        public AssetFonts(){
            defaultSmall = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
            defaultNormal = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
            defaultBig = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);

            defaultSmall.getData().setScale(0.75f);
            defaultNormal.getData().setScale(1.0f);
            defaultBig.getData().setScale(2.0f);

            defaultSmall.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }


}
