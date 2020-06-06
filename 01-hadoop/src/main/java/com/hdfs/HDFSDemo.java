package com.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;

public class HDFSDemo {

    public static void main(String[] args) throws  Exception {

        InetAddress ia=null;
                 try {
                         ia=ia.getLocalHost();

                        String localname=ia.getHostName();
                        String localip=ia.getHostAddress();
                        System.out.println("本机名称是："+ localname);
                        System.out.println("本机的ip是 ："+localip);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
//        FileSystem fileSystem = getFileSystem();
//
//        String fileName = "/user/lpp/put-wc.input";
//        Path path = new Path(fileName);
//        FSDataOutputStream fsDataOutputStream = fileSystem.create(path);
//        FileInputStream fileInputStream = new FileInputStream("D:\\workinstall\\exercise\\01-hadoop\\src\\main\\resources\\wc");
//
//        IOUtils.copyBytes(fileInputStream, fsDataOutputStream, 4096, false);
//        IOUtils.closeStream(fileInputStream);
//        IOUtils.closeStream(fsDataOutputStream);
    }

    /**
     * 读取hdfs文件
      * @param fileName
     * @throws Exception
     */
    public static void read(String fileName)throws  Exception{
        FileSystem fileSystem  = getFileSystem();
        Path readPath = new Path(fileName);
        FSDataInputStream inStream = fileSystem.open(readPath);

        IOUtils.copyBytes(inStream, System.out, 4096, false);
        IOUtils.closeStream(inStream);
    }

    public static FileSystem getFileSystem(){

        //读取配置文件
        Configuration conf = new Configuration();
        FileSystem fileSystem =null;
        try {
             fileSystem  = FileSystem.get(conf);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return fileSystem;
        }
    }
}
