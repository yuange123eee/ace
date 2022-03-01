package com.example.ace.controller;

import com.example.ace.mapper.UserMapper;
import com.example.ace.pojo.User;
import com.example.ace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/test")
    public void query(){
        List<User> users = userService.find();
        System.out.println(users);
    }
    //java
    @RequestMapping("javaCode")
    @ResponseBody
    public String insertCode(String javaCode,HttpServletResponse response, HttpSession session) throws IOException {

        //1.将代码更新source表

        //2.查询source表

        //3.创建一个文件
        String fileName = "Test.java";
        System.out.println("File.separator:" + File.separator);
        File testFile = new File(session.getServletContext().getRealPath("/java/") + fileName);
        File fileParent = testFile.getParentFile();//返回的是File类型,可以调用exsit()等方法
        String fileParentPath = testFile.getParent();//返回的是String类型
        System.out.println("fileParent:" + fileParent);
        System.out.println("fileParentPath:" + fileParentPath);
        if (!fileParent.exists()) {
            fileParent.mkdirs();// 能创建多级目录
        }
        if (!testFile.exists())
            testFile.createNewFile();//有路径才能创建文件


        //4.将代码写入文件

        FileOutputStream fos = new FileOutputStream(testFile);
        javaCode = URLDecoder.decode(javaCode,"UTF-8");
        byte[] bytes = javaCode.getBytes(StandardCharsets.UTF_8);
        fos.write(bytes);

        //5.编译
        Runtime run = Runtime.getRuntime();
        try {
            Process process1 = run.exec("javac Test.java", null, new File(fileParentPath));//编译
            Process process2 = run.exec(new String[]{"cmd.exe","/C","java Test.java > output.txt"}, null, new File(fileParentPath));//运行并重定向
            process1.waitFor();
            process2.waitFor();
            process1.destroy();
            process2.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        /*InputStream is = process2.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process2.getInputStream()));
        String line=null;
        while((line=reader.readLine())!=null){
            System.out.println(line);
        }
        int result=process2.waitFor();
        System.out.println(result);*/

        //6.读取重定向output文件内容
        String str="";
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            InputStream is = new FileInputStream(fileParentPath + "\\output.txt");
            byte[] buffer = new byte[is.available()];
            /*int tempbyte = 0;
            while ((tempbyte = is.read()) != -1) {
                System.out.write(tempbyte);
                bos.write(buffer,0,tempbyte);

            }*/
            is.read(buffer);
            is.close();
            str = new String(buffer, StandardCharsets.UTF_8);
            //7.响应客户端
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }


    @GetMapping("/cCode")
    @ResponseBody
    public void insertCCode(String code, HttpServletResponse response, HttpSession session) throws IOException {
        String code1 = "#include <stdio.h>\n" +
                "\n" +
                "int main() {\n" +
                "  printf(\"hello world\\n\");\n" +
                "  return 0;\n" +
                "}\n";
        /*Gson gson = new Gson();
        String code2 = gson.fromJson(code, String.class);*/
        //1.将代码更新source表

        //2.查询source表

        //3.创建一个文件
        String fileName = "Test.c";
        System.out.println("File.separator:" + File.separator);
        File testFile = new File(session.getServletContext().getRealPath("/c/") + fileName);
        File fileParent = testFile.getParentFile();//返回的是File类型,可以调用exsit()等方法
        String fileParentPath = testFile.getParent();//返回的是String类型
        System.out.println("fileParent:" + fileParent);
        System.out.println("fileParentPath:" + fileParentPath);
        if (!fileParent.exists()) {
            fileParent.mkdirs();// 能创建多级目录
        }
        if (!testFile.exists())
            testFile.createNewFile();//有路径才能创建文件


        //4.将代码写入文件

        FileOutputStream fos = new FileOutputStream(testFile);
        byte[] bytes = code1.getBytes(StandardCharsets.UTF_8);
        fos.write(bytes);

        //5.编译
        Runtime run = Runtime.getRuntime();
        try {
            //win10
            /*Process process1 = run.exec("javac Test.java", null, new File(fileParentPath));//编译
            Process process2 = run.exec(new String[]{"cmd.exe","/C","java Test.java > output.txt"}, null, new File(fileParentPath));//运行并重定向*/

            //ubuntu
            //Process process1 = run.exec("javac Test.java", null, new File(fileParentPath));//编译
            //Process process2 = run.exec(new String[]{"/bin/bash","/c","java "+"Test.java > output.txt"}, null, new File(fileParentPath));//运行并重定向
            //Process process = run.exec("java Test.java > output.txt",null,new File(fileParentPath));

            Process process5 = run.exec(new String[]{"/bin/sh","-c","gcc Test.c -o Test"}, null,new File(fileParentPath));
            Process process6 = run.exec(new String[]{"/bin/sh","-c","./Test > output.txt"}, null,new File(fileParentPath));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    
}
