package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import team.agile.campusnews.app.service.FileServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);



    @Autowired
    private FileServices fileService;


    @GetMapping("/photo/{name}.{type}")
    public void getFile(@PathVariable("name") String name, @PathVariable("type") String type,
                        HttpServletResponse response) {
        LOGGER.info("{}",name);
        //将图片输出给浏览器
       fileService.getFileToResponse("/photo/"+name+"."+type,type,response);
    }

    //下载
    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName){
       // fileService.downloadFile(request,response,filePath,fileName);
    }



}
