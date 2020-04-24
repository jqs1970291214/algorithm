package designpattern.state;


/**
 * @Descriptions if else switch 分支逻辑实现状态机。 实现简单，逻辑复杂，可维护性不高.
 * @Company
 * @Author Junqson
 * @Date 2020/4/24 11:15
 * @Version 1.0
 */
public class IfElseStateMachine {

    public static void main(String[] args) {
        MarioStateMachine1 sm = new MarioStateMachine1();
        System.out.println(sm.toString());
        sm.obtainMushroom();
        System.out.println(sm.toString());
        sm.obtainFireFlower();
        System.out.println(sm.toString());
        sm.meetMonster();
        System.out.println(sm.toString());

    }

}

enum State {
    SMALL(0),
    SUPER(1),
    CAPE(2),
    FIRE(3);

    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

class MarioStateMachine1 {
    private State currentState;
    private int score;

    public MarioStateMachine1() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    /**
     * 吃蘑菇
     */
    public void obtainMushroom() {
        if (this.currentState == State.SMALL) {
            this.currentState = State.SUPER;
            this.score += 100;
        }
    }

    /**
     * 吃斗篷
      */
    public void obtainCape() {
        if (this.currentState == State.SMALL) {
            this.currentState = State.CAPE;
            this.score += 200;
        }

        if (this.currentState == State.SUPER) {
            this.currentState = State.CAPE;
            this.score += 200;
        }

    }

    /**
     * 吃火花
     */
    public void obtainFireFlower() {
        if (this.currentState == State.SMALL) {
            this.currentState = State.FIRE;
            this.score += 300;
        }
        if (this.currentState == State.SUPER) {
            this.currentState = State.FIRE;
            this.score += 300;
        }
    }

    /**
     * 遇到怪物
     */
    public void meetMonster() {
        if (this.currentState == State.FIRE) {
            this.currentState = State.SMALL;
            this.score -= 300;
        }

        if (this.currentState == State.SUPER) {
            this.currentState = State.SMALL;
            this.score -= 100;

        }

        if (this.currentState == State.CAPE) {
            this.currentState = State.SMALL;
            this.score -= 200;
        }
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    @Override
    public String toString() {
        return "MarioStateMachine{" +
                "currentState=" + currentState +
                ", score=" + score +
                '}';
    }


}