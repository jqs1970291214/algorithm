package designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Descriptions 一步一步重构出访问者模式 第四步
 *
 * 通过抽象出访问者接口，进一步解耦，解决增加操作时需要修改所有类的弊端
 *
 * @Company
 * @Author Junqson
 * @Date 2020/4/28 21:35
 * @Version 1.0
 */
public class Forth {

    interface Visitor {
        void visit(PDFFile pdfFile);

        void visit(PPTFile pptFile);

        void visit(WordFile wordFile);
    }

    /**
     * 资源文件抽象类
     * 引入抽象方法，将extractor工具操作类作为参数传入，解决访问问题
     */
    static abstract class ResourceFile {
        protected String filePath;

        public ResourceFile(String filePath) {
            this.filePath = filePath;
        }

        public abstract void accept(Visitor visitor);
    }

    static class PPTFile extends ResourceFile {
        public PPTFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    static class WordFile extends ResourceFile {
        public WordFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    static class PDFFile extends ResourceFile {
        public PDFFile(String filePath) {
            super(filePath);
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }


    /**
     * 提取操作工具类 实现visitor接口
     */
    static class Extractor implements Visitor{

        @Override
        public void visit(PPTFile pptFile) {
            System.out.println("extract ppt to txt");
        }

        @Override
        public void visit(WordFile wordFile) {
            System.out.println("extract word to txt");
        }

        @Override
        public void visit(PDFFile pdfFile) {
            System.out.println("extract pdf to txt");
        }
    }

    /**
     * 压缩操作工具类 实现visitor接口
     */
    static class Compressor implements Visitor{

        @Override
        public void visit(PPTFile pptFile) {
            System.out.println("compress ppt to zip");
        }

        @Override
        public void visit(WordFile wordFile) {
            System.out.println("compress word to zip");
        }

        @Override
        public void visit(PDFFile pdfFile) {
            System.out.println("compress pdf to zip");
        }
    }

    public static void main(String[] args) {

        Visitor extractor = new Extractor();
        Visitor compressor = new Compressor();

        List<ResourceFile> files = listFiles();
        for (ResourceFile file : files) {
            file.accept(extractor);
            file.accept(compressor);
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
