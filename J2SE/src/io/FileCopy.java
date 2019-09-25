package io;

import java.io.*;

/**
 * @author 陌上丶天琊
 * @date 2019-9-24 20:26
 * @description
 */
public class FileCopy {
    public static void main(String[] args) {
        int b = 0;
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("src/io/fileCopy.txt");
            out = new FileOutputStream("src/io/fileCopy_bak.txt");
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
            System.exit(-1);
        }
        try {
            System.out.println("单字节读出文本");
            while ((b = in.read()) != -1) {
                System.out.print((char) b);
                out.write(b);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("文件复制出错");
            System.exit(-1);
        }

        FileReader fr = null;
        try {
            fr = new FileReader("src/io/fileCopy.txt");
            System.out.println("");
            System.out.println("单字符读出文本");
            while ((b = fr.read()) != -1) {
                System.out.print((char) b);
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("文件复制出错");
            System.exit(-1);
        }

        System.out.println("文件读取完毕");
    }
}
