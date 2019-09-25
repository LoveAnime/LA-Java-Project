package io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * author 帝歌恋雪
 * date 2018-12-04
 * 描述:
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        File path = new File("src/io");
        if (!path.exists()) {
            path.mkdir();
        }
        File file = new File(path, "fileDemo.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        System.out.println("文件指针位置：" + raf.getFilePointer());

        // write一次之写入一个字节或一个字节数组
        raf.write('A');
        raf.write('\n');

        // 想要写入一个int型，需要分四次
        int i = 0x7ffffff;
        raf.write(i >>> 24);
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i);
        raf.write('\n');

        // 或者
        raf.writeInt(i); // 底层也是分四个字节实现

        String s = "慕课";
        byte[] bs = s.getBytes("utf-8");
        raf.write(bs);
        System.out.println("文件长度：" + raf.length());

        // 将文件指针移动到头部
        raf.seek(0);
        byte[] buf = new byte[(int) raf.length()];

        // 一次读取所有字节
        raf.read(buf);

        raf.close();
    }
}
