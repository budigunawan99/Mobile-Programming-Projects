package com.bnawan.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class CoinCollector extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture background;
	private Texture[] dude;
	private int dudeState, pause =0;
	private static float gravity = 0.2f;
	private float velocity = 0;
	private int dudeY = 0;

	ArrayList<Integer> coinX = new ArrayList<>();
	ArrayList<Integer> coinY = new ArrayList<>();
	private Texture coin;
	private Random number;
	private int coincount;

	ArrayList<Integer> bombX = new ArrayList<>();
	ArrayList<Integer> bombY = new ArrayList<>();
	private Texture bomb;
	private int bombcount;
	ArrayList<Rectangle> coinRect = new ArrayList<>();
	ArrayList<Rectangle> bombRect = new ArrayList<>();
	Rectangle manRect;

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
		dude = new Texture[4];
		dude[0] = new Texture("frame-1.png");
		dude[1] = new Texture("frame-2.png");
		dude[2] = new Texture("frame-3.png");
		dude[3] = new Texture("frame-4.png");
		coin = new Texture("coin.png");
		bomb = new Texture("bomb.png");
		dudeY = Gdx.graphics.getHeight() / 2;
		number = new Random();
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (coincount < 150) {
			coincount++;
		}else{
			coincount = 0;
			makeCoin();
		}

		coinRect.clear();
		for (int i = 0; i < coinX.size(); i++) {
			batch.draw(coin, coinX.get(i), coinY.get(i));
			coinX.set(i, coinX.get(i) - 4);
			coinRect.add(new Rectangle(coinX.get(i), coinY.get(i), coin.getWidth(), coin.getHeight()));
		}

		if (bombcount < 250) {
			bombcount++;
		}else{
			bombcount = 0;
			makeBomb();
		}

		bombRect.clear();
		for (int i = 0; i < bombX.size(); i++) {
			batch.draw(bomb, bombX.get(i), bombY.get(i));
			bombX.set(i, bombX.get(i) - 2);
			bombRect.add(new Rectangle(bombX.get(i), bombY.get(i), bomb.getWidth(), bomb.getHeight()));
		}

		if (Gdx.input.justTouched()) {
			velocity = -10;
		}

		if (pause < 8) {
			pause++;
		}else{
			pause = 0;
			if (dudeState < 3) {
				dudeState++;
			}else{
				dudeState = 0;
			}
		}

		if (dudeY <= 0) {
			dudeY = 0;
		}

		if (dudeY >= Gdx.graphics.getHeight() - dude[dudeState].getHeight()) {
			dudeY = Gdx.graphics.getHeight() - dude[dudeState].getHeight();
		}
		batch.draw(dude[dudeState], Gdx.graphics.getWidth() / 2 - dude[dudeState].getWidth() / 2, dudeY);
		velocity += gravity;
		dudeY -= velocity;
		manRect = new Rectangle(Gdx.graphics.getWidth() / 2 - dude[dudeState].getWidth() / 2, dudeY, dude[dudeState].getWidth(), dude[dudeState].getHeight());

		for (int i = 0; i < coinRect.size(); i++) {
			if (Intersector.overlaps(manRect, coinRect.get(i))) {
				Gdx.app.log("coin","collision");
			}
		}

		for (int i = 0; i < bombRect.size(); i++) {
			if (Intersector.overlaps(manRect, bombRect.get(i))) {
				Gdx.app.log("bomb","collision");
			}
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void makeCoin() {
		float height = number.nextFloat() * Gdx.graphics.getHeight();
		coinY.add((int)height);
		coinX.add(Gdx.graphics.getWidth());
	}

	public void makeBomb() {
		float height = number.nextFloat() * Gdx.graphics.getHeight();
		bombY.add((int)height);
		bombX.add(Gdx.graphics.getWidth());
	}
}
