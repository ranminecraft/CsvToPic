package cn.ranmc;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String []args) throws IOException {

        //计时器
        Timing timing = new Timing();
        timing.start();

        //检查文件
        File file = new File(new File("").getCanonicalPath()+"/80万样本数据.csv");
        if(!file.exists()) {
            System.out.println("根目录找不到 80万样本数据.csv 文件");
            return;
        }

        //解析文件
        new Reader(file);

        timing.end();

    }


}
