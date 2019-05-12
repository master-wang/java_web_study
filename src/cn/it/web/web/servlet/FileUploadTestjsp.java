package cn.it.web.web.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/fileUploadTestjsp")
public class FileUploadTestjsp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("上传文件、。。。。。");
        System.out.println(FileUploadTestjsp.class.getClassLoader().getResource("/").getPath());
        //使用fileupload组件完成文件上传
        //上传的位置
        String path = req.getSession().getServletContext().getRealPath("/uploads/");
        //String path = req.getContextPath()+"/uploads/";
        File file = new File(path);
        if(!file.exists()){
            //创建文件夹
            file.mkdir();
        }

        //解析　　ｒｅｑ对象　获取上传的文件项
        //创建文件磁盘工程　
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析　req　 返回文件项　　需要遍历
        List<FileItem> fileItems = null;
        try {
            fileItems = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for (FileItem item:fileItems) {
            //进行判断当前的　　item　是都是一个上传文件项
            if (item.isFormField()){
                //是一个普通的表单项
            }else{
                //说明文件上传
                //获取上传文件的名称
                String fileName = item.getName();
                System.out.println("上传的文件名为："+fileName);
                //完成文件上传
                try {
                    item.write(new File(path,fileName));
                    System.out.println("上传的路径为：");
                    System.out.println(path+fileName);
                    //删除零食文件
                    item.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
