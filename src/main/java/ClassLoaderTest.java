import Test.OptionalTest;

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


class choushu {

    public static int nthUglyNumber(int n) {
        int[] table = new int[]{0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12};
        int count = 10;
        int cur = 13;
        if (n < 11) return table[n];
        while (count < n) {
            if (cur % 2 != 0 && cur % 5 != 0 && cur % 3 != 0) {
                cur++;
                continue;
            }
            boolean find = false;
            int li = (int) Math.sqrt(cur) + 1;
            for (int i = 3; i <= li; i++) {
                if (cur % i == 0 && i != 3 && i != 5) {
                    find = true;
                    break;
                }
            }
            if (!find) count++;
            cur++;
        }
        return cur - 1;
    }


    public static void main(String[] args) {
        System.out.println(nthUglyNumber(11));
    }

}
