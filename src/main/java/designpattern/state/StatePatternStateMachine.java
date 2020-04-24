package designpattern.state;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2020/4/24 18:01
 * @Version 1.0
 */
public class StatePatternStateMachine {
    public static void main(String[] args) {
        MarioStateMachine3 sm = new MarioStateMachine3();
        System.out.println(sm.toString());
        sm.obtainMushRoom();
        System.out.println(sm.toString());
        sm.obtainFireFlower();
        System.out.println(sm.toString());
        sm.meetMonster();
        System.out.println(sm.toString());

    }
}

class MarioStateMachine3 {
    private int score;
    private IMario currentState;

    public MarioStateMachine3() {
        this.score = 0;
        this.currentState = SmallMario.getInstance();
    }

    public void obtainMushRoom() {
        this.currentState.obtainMushRoom(this);
    }

    public void obtainCape() {
        this.currentState.obtainCape(this);
    }

    public void obtainFireFlower() {
        this.currentState.obtainFireFlower(this);
    }

    public void meetMonster() {
        this.currentState.meetMonster(this);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public IMario getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IMario currentState) {
        this.currentState = currentState;
    }


    @Override
    public String toString() {
        return "MarioStateMachine{" +
                "currentState=" + currentState.getName().name() +
                ", score=" + score +
                '}';
    }
}

/**
 * 动作接口
 */
interface IMario {
    State getName();

    void obtainMushRoom(MarioStateMachine3 sm);

    void obtainCape(MarioStateMachine3 sm);

    void obtainFireFlower(MarioStateMachine3 sm);

    void meetMonster(MarioStateMachine3 sm);
}

class SmallMario implements IMario {

    private static final SmallMario instance = new SmallMario();
    public static SmallMario getInstance() {
        return instance;
    }

    private SmallMario() {

    }

    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine3 sm) {
        sm.setCurrentState(SuperMario.getInstance());
        sm.setScore(sm.getScore() + 100);
    }

    @Override
    public void obtainCape(MarioStateMachine3 sm) {
        sm.setCurrentState(CapeMario.getInstance());
        sm.setScore(sm.getScore() + 200);
    }

    @Override
    public void obtainFireFlower(MarioStateMachine3 sm) {
        sm.setCurrentState(FireMario.getInstance());
        sm.setScore(sm.getScore() + 300);
    }

    @Override
    public void meetMonster(MarioStateMachine3 sm) {
        // do nothing
    }
}

class SuperMario implements IMario {

    private static final SuperMario instance = new SuperMario();

    public static SuperMario getInstance() {
        return instance;
    }

    private SuperMario() {

    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine3 sm) {
        // do nothing
    }

    @Override
    public void obtainCape(MarioStateMachine3 sm) {
        sm.setCurrentState(CapeMario.getInstance());
        sm.setScore(sm.getScore() + 200);
    }

    @Override
    public void obtainFireFlower(MarioStateMachine3 sm) {
        sm.setCurrentState(FireMario.getInstance());
        sm.setScore(sm.getScore() + 300);
    }

    @Override
    public void meetMonster(MarioStateMachine3 sm) {
        sm.setCurrentState(SmallMario.getInstance());
        sm.setScore(sm.getScore() - 100);
    }
}

class CapeMario implements IMario {

    private static final CapeMario instance = new CapeMario();

    public static CapeMario getInstance() {
        return instance;
    }

    private CapeMario() {

    }

    @Override
    public State getName() {
        return State.CAPE;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine3 sm) {
        // do nothing
    }

    @Override
    public void obtainCape(MarioStateMachine3 sm) {
        // do nothing
    }

    @Override
    public void obtainFireFlower(MarioStateMachine3 sm) {
        // do nothing
    }

    @Override
    public void meetMonster(MarioStateMachine3 sm) {
        sm.setCurrentState(SmallMario.getInstance());
        sm.setScore(sm.getScore() - 200);
    }
}

class FireMario implements IMario {

    private static final FireMario instance = new FireMario();

    public static FireMario getInstance() {
        return instance;
    }

    private FireMario() {

    }

    @Override
    public State getName() {
        return State.FIRE;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine3 sm) {
        // do nothing
    }

    @Override
    public void obtainCape(MarioStateMachine3 sm) {
        // do nothing
    }

    @Override
    public void obtainFireFlower(MarioStateMachine3 sm) {
        // do nothing
    }

    @Override
    public void meetMonster(MarioStateMachine3 sm) {
        sm.setCurrentState(SmallMario.getInstance());
        sm.setScore(sm.getScore() - 300);
    }
}