package me.mrbrown.blockgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

public class BlockGame extends Game {
	Screen screen;
	Texture img;
	MyColors colors;
	
	@Override
	public void create () {
		img = new Texture("transparent.png");
		colors = new MyColors();
		//create StartScreen
		//Show StartScreen
		screen = new GameScreen(img);
		setScreen(screen);
	}

	@Override
	public void dispose () {
		
	}

    @Override
    public void render() {
		ScreenUtils.clear(1, 0, 0, 1);
        super.render();
		// screen.render(Gdx.graphics.getDeltaTime());
    }
	
}
