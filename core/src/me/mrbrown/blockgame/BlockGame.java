package me.mrbrown.blockgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class BlockGame extends Game {
	Screen screen;
	
	@Override
	public void create () {
		//create StartScreen
		//Show StartScreen
		screen = new ClassicGameScreen();
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
