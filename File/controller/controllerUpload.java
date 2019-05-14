package com.qhit.File.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author name
 * @date 2019/5/13 14:48
 */
@RestController
@RequestMapping("/shopInfo")
public class controllerUpload {

    @Resource
    Upload upload;

    @RequestMapping("/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response){
        try {
            String s = upload.handleFileUpload(request, response);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
            String s = upload.downloadFile(request,response);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
