package designpattern.memo;

import sun.management.snmp.util.SnmpNamedListTableCache;

import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

/**
 * @Descriptions
 *
 * 备忘录模式  用于将对象的状态保存起来以备恢复、撤销等操作
 * 1、存储副本以备恢复
 * 2、不能破坏封装性
 *
 *
 * 实现一个文本编辑器
 * 输入文本，追加到文本末
 * 输入:list 显示当前文本
 * 输入:undo 撤销回上次输入的文本
 *
 * @Company
 * @Author Junqson
 * @Date 2020/5/1 23:48
 * @Version 1.0
 */
public class Memo {
    public static void main(String[] args) {
        InputText it = new InputText();
        SnapShotHolder holder = new SnapShotHolder();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (":list".equals(input)) {
                System.out.println(it.getText());
            } else if (":undo".equals(input)) {
                if (holder.isEmpty()) {
                    System.out.println("sorry, no snapshot available");
                } else {
                    SnapShot st = holder.getLastSnapShot();
                    it.restoreSnapshot(st);
                }
            } else {
                SnapShot shot = new SnapShot(it.getText());
                holder.saveSnapShot(shot);
                it.append(input);
            }
        }


    }

}

class InputText {
    private StringBuilder text = new StringBuilder();

//    /**
//     * 会破坏封装性，被其他业务误用，用restoreSnapshot替换，通过命名而非强制手段防止误用
//     * @param s
//     */
//    public void setText(String s) {
//        text.replace(0, text.length(), s);
//    }

    /**
     * 用于恢复快照
     * @param snapshot
     */
    public void restoreSnapshot(SnapShot snapshot) {
        text.replace(0, text.length(), snapshot.getText());
        System.out.println(snapshot.getDate().toString() + " 快照恢复成功!");
    }

    /**
     * 追加文本到文本末
     * @param s
     */
    public void append(String s) {
        this.text.append(s);
    }

    public String getText() {
        return this.text.toString();
    }
}

/**
 * 保存文本快照，不可修改内部状态
 */
class SnapShot {
    private String text;

    Date date = new Date();

    public SnapShot(String t) {
        this.text = t;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return this.date;
    }
}

/**
 * 保存文本快照
 */
class SnapShotHolder {
    private Stack<SnapShot> stack = new Stack<>();

    /**
     * 保存快照
     */
    public void saveSnapShot(SnapShot shot) {
        stack.push(shot);
    }

    /**
     * 获取最新快照
     */
    public SnapShot getLastSnapShot() {
        return stack.pop();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}



