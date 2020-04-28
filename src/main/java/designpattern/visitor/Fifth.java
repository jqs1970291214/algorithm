package designpattern.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Descriptions 利用工厂模式解决前面的需求
 * @Company
 * @Author Junqson
 * @Date 2020/4/28 21:48
 * @Version 1.0
 */
public class Fifth {

    static abstract class ResourceFile {
        protected String filePath;

        public ResourceFile(String filePath) {
            this.filePath = filePath;
        }
    }

    static class PDFFile extends ResourceFile {
        public PDFFile(String filePath) {
            super(filePath);
        }
    }

    static class WordFile extends ResourceFile {
        public WordFile(String filePath) {
            super(filePath);
        }
    }

    static class PPTFile extends ResourceFile {
        public PPTFile(String filePath) {
            super(filePath);
        }
    }


    /**
     * 每次增加操作需要增加操作接口，和数据结构个数相同的实现类，一个工厂类，类的膨胀比较快
     * 相比访问者模式增加一种操作只需要增加一个Visitor实现类即可
     *
     * 但是轻易不要使用访问者模式，非常不常用，会导致代码可读性变差
     *
     * 提取操作接口
     */
    interface Extractor {
        void extract(ResourceFile file);
    }

    /**
     * 提取接口实现类
     */
    static class PDFExtractor implements Extractor {
        @Override
        public void extract(ResourceFile file) {
            PDFFile pdfFile = (PDFFile) file;
            System.out.println("extract pdf to txt");
        }
    }

    static class PPTExtractor implements Extractor {
        @Override
        public void extract(ResourceFile file) {
            PPTFile pptFile = (PPTFile) file;
            System.out.println("extract ppt to txt");
        }
    }

    static class WordExtractor implements Extractor {
        @Override
        public void extract(ResourceFile file) {
            WordFile wordFile = (WordFile) file;
            System.out.println("extract word to txt");
        }
    }



    static class ExtractorFactory {
        private static Map<Class<?>, Extractor> cached = new HashMap<>();

        static  {
            cached.put(PDFFile.class, new PDFExtractor());
            cached.put(PPTFile.class, new PPTExtractor());
            cached.put(WordFile.class, new WordExtractor());
        }

        public static Extractor getExtractor(ResourceFile file) {
            return cached.get(file.getClass());
        }
    }


    public static void main(String[] args) {
        List<ResourceFile> files = listFiles();
        for (ResourceFile file : files) {
            Extractor extractor = ExtractorFactory.getExtractor(file);
            extractor.extract(file);
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
