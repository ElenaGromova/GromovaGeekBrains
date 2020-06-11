package com.geekbrains.rpg.game.logic;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.geekbrains.rpg.game.logic.utils.MapElement;
import com.geekbrains.rpg.game.logic.utils.Poolable;

public class Messages implements MapElement, Poolable {
    private  GameController gc;
    private String amount;
    private Vector2 position;
    private boolean active;
    private Vector2 velocity;
    private float time;

    public void setup(String amount, float x, float y){
        this.setPosition(x, y);
        this.velocity.set(MathUtils.random(-5.0f, 5.0f), MathUtils.random(50.0f, 80.0f));
        this.amount = amount;
        time = 0.0f;
        this.active = true;
    }

    public Messages(GameController gc){
        this.gc = gc;
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(0, 0);
        time = 0.0f;
        this.amount = "";
    }

    @Override
    public void render(SpriteBatch batch, BitmapFont font) {
        batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(batch, amount, position.x , position.y + 20);
    }

    public void update(float dt) {
        time += dt;
        position.mulAdd(velocity, dt);
        if (time > 1.0f) {
            active = false;
        }
    }

    @Override
    public int getCellX() {
        return 0;
    }

    @Override
    public int getCellY() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

}
