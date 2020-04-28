package designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descriptions 一步一步重构出访问者模式 第三步
 *
 * 解决单分派的问题
 *
 * 缺点：当增加操作时，所有的类依然需要修改，增加accept方法，解耦不彻底
 *
 * @Company
 * @Author Junqson
 * @Date 2020/4/28 21:29
 * @Version 1.0
 */
public class Third {

    /**
     * 资源文件抽象类
     * 引入抽象方法，将extractor工具操作类作为参数传入，解决访问问题
     */
    static abstract class ResourceFile {
        protected String filePath;

        public ResourceFile(String filePath) {
            this.filePath = filePath;
        }

        public abstract void accept(Extractor extractor);
    }

    static class PPTFile extends ResourceFile {
        public PPTFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Extractor extractor) {
            extractor.extract2txt(this);
        }
    }

    static class WordFile extends ResourceFile {
        public WordFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Extractor extractor) {
            extractor.extract2txt(this);
        }
    }

    static class PDFFile extends ResourceFile {
        public PDFFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Extractor extractor) {
            extractor.extract2txt(this);
        }
    }

    /**
     * 具体的提取操作类，从数据结构中解耦出来，不违反面向对象的原则，因为这个操作可以看做对象
     * 利用方法重载
     */
    static class Extractor {

        public void extract2txt(PPTFile pptFile) {
            System.out.println("extract ppt to txt");
        }

        public void extract2txt(WordFile wordFile) {
            System.out.println("extract word to txt");
        }

        public void extract2txt(PDFFile pdfFile) {
            System.out.println("extract pdf to txt");
        }
    }

    public static void main(String[] args) {
        Extractor extractor = new Extractor();

        List<ResourceFile> files = listFiles();
        for (ResourceFile file : files) {
            file.accept(extractor);
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
