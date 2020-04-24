package designpattern.state;


/**
 * @Descriptions 查表法状态机 避免难以维护的分支判断逻辑，适合简单动作的场景
 * @Company
 * @Author Junqson
 * @Date 2020/4/24 13:00
 * @Version 1.0
 */
public class TableStateMachine {
    public static void main(String[] args) {
        MarioStateMachine2 sm = new MarioStateMachine2();
        System.out.println(sm.toString());
        sm.obtainMushroom();
        System.out.println(sm.toString());
        sm.obtainFireFlower();
        System.out.println(sm.toString());
        sm.meetMonster();
        System.out.println(sm.toString());

    }
}

/**
 * 定义事件
 */
enum Event {

    GOT_MUSHROOM(0),
    GOT_CAPE(1),
    GOT_FIRE(2),
    MET_MONSTER(3)
    ;

    private int value;

    public int getValue() {
        return this.value;
    }

    private Event(int value) {
        this.value = value;
    }
}

class MarioStateMachine2 {
    private int score;
    private State currentState;

    /**
     * 状态转移表
     */
    private static final State[][] transitionTable = {
            {State.SUPER, State.CAPE, State.FIRE, State.SMALL},
            {State.SUPER, State.CAPE, State.FIRE, State.SMALL},
            {State.CAPE, State.CAPE, State.CAPE, State.SMALL},
            {State.FIRE, State.FIRE, State.FIRE, State.SMALL}
    };

    /**
     * 分数变更动作表
     */
    private static final int[][] actionTable = {
            {100, 200, 300, 0},
            {0, 200, 300, -100},
            {0, 0, 0, -200},
            {0, 0, 0, -300}
    };

    public MarioStateMachine2() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    /**
     * 吃蘑菇
     */
    public void obtainMushroom() {
        State originState = this.currentState;
        this.currentState = transitionTable[originState.getValue()][Event.GOT_MUSHROOM.getValue()];
        this.score += actionTable[originState.getValue()][Event.GOT_MUSHROOM.getValue()];
    }

    /**
     * 吃斗篷
     */
    public void obtainCape() {
        State originState = this.currentState;
        this.currentState = transitionTable[originState.getValue()][Event.GOT_CAPE.getValue()];
        this.score += actionTable[originState.getValue()][Event.GOT_CAPE.getValue()];

    }

    /**
     * 吃火花
     */
    public void obtainFireFlower() {
        State originState = this.currentState;
        this.currentState = transitionTable[originState.getValue()][Event.GOT_FIRE.getValue()];
        this.score += actionTable[originState.getValue()][Event.GOT_FIRE.getValue()];
    }

    /**
     * 遇到怪物
     */
    public void meetMonster() {
        State originState = this.currentState;
        this.currentState = transitionTable[originState.getValue()][Event.MET_MONSTER.getValue()];
        this.score += actionTable[originState.getValue()][Event.MET_MONSTER.getValue()];

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
