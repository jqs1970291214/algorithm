package designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descriptions 一步一步重构出访问者模式 第一步
 * 缺点：
 * 1、违反开闭原则，添加一个新功能，所有的类都要改（抽象类和实现类）
 * 2、功能增多，每个类的代码都在膨胀，可维护性和可读性变差
 * 3、很多比较上层的业务逻辑都耦合到了实现类中，各实现类职责不够单一，变成大杂烩
 *
 *
 * @Company
 * @Author Junqson
 * @Date 2020/4/28 21:08
 * @Version 1.0
 */
public class First {

    /**
     * 资源文件抽象类
     */
    static abstract class ResourceFile {
        protected String filePath;

        public ResourceFile(String filePath) {
            this.filePath = filePath;
        }

        /**
         * 定义提取抽象方法
         */
        public abstract void extract2txt();
    }

    /**
     * PPT实现类
     */
    static class PPTFile extends ResourceFile {
        public PPTFile(String filePath) {
            super(filePath);
        }

        @Override
        public void extract2txt() {
            System.out.println("extract ppt to txt");
        }
    }

    static class WordFile extends ResourceFile {
        public WordFile(String filePath) {
            super(filePath);
        }

        @Override
        public void extract2txt() {
            System.out.println("extract word to txt");
        }
    }

    static class PDFFile extends ResourceFile {
        public PDFFile(String filePath) {
            super(filePath);
        }

        @Override
        public void extract2txt() {
            System.out.println("extract pdf to txt");
        }
    }

    public static void main(String[] args) {
        List<ResourceFile> files = listFiles();
        for (ResourceFile file : files) {
            file.extract2txt();
        }
    }

    public static List<ResourceFile> listFiles() {
        List<ResourceFile> files = new ArrayList<>();
        files.add(new PPTFile("a.ppt"));
        files.add(new WordFile("b.doc"));
        files.add(new PDFFile("c.pdf"));
        return files;
    }
}
