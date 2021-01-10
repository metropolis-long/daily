package com.daily.controller;

import com.daily.component.MyConfig;
import com.daily.msg.ResultBody;
import com.daily.pojo.DailyFiles;
import com.daily.service.DailyFilesService;
import com.daily.tool.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

@RestController
@RequestMapping(value = {"/api/file"})
public class FileCtrl {

    @Resource
    private DailyFilesService filesService;

    @ResponseBody
    @RequestMapping("/image")
    public Object upload(@RequestParam("file") MultipartFile file,Long fileId,Long userId) throws IOException {
        String fileName = file.getOriginalFilename();
        String extent = fileName.split("\\.")[1];
        fileName = System.currentTimeMillis() + "." + extent;
        String filePath = MyConfig.getProfile() + "image" + File.separator;
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        filePath = filePath + fileName;
        File dest = new File(filePath);
        if (!dest.exists()) {
            dest.createNewFile();

        }
        ResultBody resultBody = new ResultBody();
        try {
            file.transferTo(dest);
            String domain = MyConfig.getDomain();
            String url = domain + File.separator + "image" + File.separator + fileName;
            DailyFiles df = new DailyFiles();
            df.setFilePath(filePath);
            df.setFileSize(file.getSize());
            df.setFileUrl(url);
            df.setUserId(userId);
            df.setFileId(fileId);
            resultBody = filesService.save(df);
            resultBody.setData(df);
            HashMap n = new HashMap();
            n.put("uploaded",true);
            n.put("url",df.getFileUrl());
            n.put("fileId",df.getFileId());
            return n;
        } catch (IOException e) {
            e.printStackTrace();
            resultBody.setCode(-100);
            resultBody.setMsg("上传失败");
        }
        return resultBody;

    }

    @RequestMapping(value = "/excel/one")
    public ResponseEntity<byte[]> downForm(HttpServletRequest request, String matchId) throws InterruptedException {
        String path = "E:\\WXWork\\1688852666122974\\Cache\\File\\2020-09\\221.xlsx";
        System.out.println(matchId);
        File file = new File(path);
        Thread.sleep(1000 * 60 * 2);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", file.getName());
        httpHeaders.set("code", "200");
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/excel/two")
    public void down(HttpServletRequest request, HttpServletResponse response) {
        String path = "E:\\WXWork\\1688852666122974\\Cache\\File\\2020-09\\11.xlsx";

        File file = new File(path);
        OutputStream out = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
