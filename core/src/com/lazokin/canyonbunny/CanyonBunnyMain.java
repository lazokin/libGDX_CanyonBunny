package com.lazokin.canyonbunny;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.lazokin.canyonbunny.game.Assets;
import com.lazokin.canyonbunny.game.WorldController;
import com.lazokin.canyonbunny.game.WorldRenderer;

public class CanyonBunnyMain implements ApplicationListener {

    private static final String TAG = CanyonBunnyMain.class.getName();

    private boolean paused;

    private WorldController worldController;
    private WorldRenderer worldRenderer;

    @Override public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Assets.instance.init(new AssetManager());
        worldController = new WorldController();
        worldRenderer = new WorldRenderer(worldController);
        paused = false;
    }

    @Override public void render() {
        if (!paused) {
            worldController.update(Gdx.graphics.getDeltaTime());
        }
        Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render();
    }

    @Override public void resize (int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override public void pause() {
        paused = true;
    }

    @Override public void resume() {
        paused = false;
    }

    @Override public void dispose() {
        worldRenderer.dispose();
        Assets.instance.dispose();
    }
}