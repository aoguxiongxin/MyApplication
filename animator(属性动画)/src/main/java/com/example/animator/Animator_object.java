package com.example.animator;

/**
 * 用来保存x和y轴坐标
 */

public class Animator_object {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Animator_object(float x, float y) {

        this.x = x;
        this.y = y;
    }
}
