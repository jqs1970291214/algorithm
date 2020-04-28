package designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descriptions 一步一步重构出访问者模式 第二步
 *
 * 针对第一步的问题，将操作从具体的数据结构中解耦出来，设计成独立的类
 *
 * @Company
 * @Author Junqson
 * @Date 2020/4/28 21:20
 * @Version 1.0
 */
public class Second {

    /**
     * 资源文件抽象类
     */
    static abstract class ResourceFile {
        protected String filePath;

        public ResourceFile(String filePath) {
            this.filePath = filePath;
        }

    }

    static class PPTFile extends ResourceFile {
        public PPTFile(String filePath) {
            super(filePath);
        }
    }

    static class WordFile extends ResourceFile {
        public WordFile(String filePath) {
            super(filePath);
        }
    }

    static class PDFFile extends ResourceFile {
        public PDFFile(String filePath) {
            super(filePath);
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

            /**
             * 编译无法通过，java属于单分派语言，只能通过编译期声明的类型确定调用的重载方法，无法通过运行时参数类型决定调用哪个重载函数
              */
            // extractor.extract2txt(file);

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
