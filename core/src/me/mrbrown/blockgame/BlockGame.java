package me.mrbrown.blockgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class BlockGame extends Game {
	Screen screen;
	Texture img;
	MyColors colors;
	GameModel model;
	
	@Override
	public void create () {
		img = new Texture("transparent.png");
		colors = new MyColors();
		model = new GameModel();
		//create StartScreen
		//Show StartScreen
		screen = new GameScreen(img, colors, model);
		setScreen(screen);
	}

	@Override
	public void dispose () {
		img.dispose();		
	}

    @Override
    public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
        super.render();
		// screen.render(Gdx.graphics.getDeltaTime());
    }
	
}
