package io;

import java.io.*;

/**
 * @author 陌上丶天琊
 * @date 2019-9-24 20:31
 * @description
 */
public class FileObject {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        T t = new T();

        FileOutputStream fo = new FileOutputStream("src/io/fileObject.txt", true);
        ObjectOutputStream oo = new ObjectOutputStream(fo);

        oo.writeObject(t);
        oo.flush();
        oo.close();

        FileInputStream fi = new FileInputStream("src/io/fileObject.txt");
        ObjectInputStream oi = new ObjectInputStream(fi);
        T tt = (T) oi.readObject();
        System.out.println(tt);
    }
}

// 序列化接口是一个空接口，里面没有方法
class T implements Serializable {
    public int x = 13;
    public double y = 3.14;
    public String z = "java";
}
