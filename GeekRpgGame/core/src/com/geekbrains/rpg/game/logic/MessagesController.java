package com.geekbrains.rpg.game.logic;

import com.geekbrains.rpg.game.logic.utils.ObjectPool;

public class MessagesController extends ObjectPool<Messages> {
    private GameController gc;
    @Override
    protected Messages newObject() {
        return new Messages(gc);
    }

    public MessagesController(GameController gc) {
        this.gc = gc;
        }

    public void setup(String amount, float x, float y){
        Messages m = getActiveElement();
        m.setup(amount, x, y);
    }

    public void update(float dt) {
        for (int i = 0; i < getActiveList().size(); i++) {
            getActiveList().get(i).update(dt);
        }
        checkPool();
    }
}
