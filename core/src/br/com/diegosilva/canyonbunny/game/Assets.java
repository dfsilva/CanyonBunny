package br.com.diegosilva.canyonbunny.game;

import br.com.diegosilva.canyonbunny.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by 98379720172 on 19/01/2016.
 */
public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;

    private Assets(){}

    public void init (AssetManager assetManager){
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();
        Gdx.app.debug(TAG, "Quantidade de recursos carregados: "+assetManager.getAssetNames().size);

        for(String a : assetManager.getAssetNames())
            Gdx.app.debug(TAG, "asset: "+a);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Nao foi possivel carregar o recurso "+asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }


    public class AssetBunny{
        public final TextureAtlas.AtlasRegion head;

        public AssetBunny(TextureAtlas atlas){
            head = atlas.findRegion("bunny_head");
        }
    }
}
