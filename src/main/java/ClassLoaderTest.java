import java.io.IOException;
import java.io.InputStream;

/**
 * @Descriptions
 * @Company
 * @Author Junqson
 * @Date 2019/3/25 14:38
 * @Version 1.0
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader cll = new MyClassLoader();
        Class<?> clazz = Class.forName("Dijkstra", true, cll);
        assert clazz != null;
        Dijkstra d = (Dijkstra) clazz.newInstance();

        d.initGraph().dijkstra();

        System.out.println(new Dijkstra().getClass() == clazz);
    }

}

class MyClassLoader extends ClassLoader {

    public MyClassLoader() {
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classBytes = getClassBytes(name);
            Class<?> myclass = this.defineClass(name, classBytes, 0, classBytes.length);
            return myclass;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private byte[] getClassBytes(String name) throws IOException {
        InputStream inputStream = MyClassLoader.class.getClassLoader().getResourceAsStream(name + ".class");
        byte[] classBytes = new byte[inputStream.available()];
        inputStream.read(classBytes);
        return classBytes;
    }

}
