package com.youxiquan.controller;

import com.youxiquan.exception.RestfulException;
import com.youxiquan.result.ResultInfo;
import com.youxiquan.util.QiniuUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author lzb
 */
@RestController
public class ImageController {

    @RequestMapping(value = "/image/imageUpload",method = RequestMethod.POST)
    public ResultInfo uploadFile(@RequestParam("file") MultipartFile files,
                                 HttpServletRequest request){

        String imagePath=null;
        String originalFilename = "blob.jpg";
        if(!"blob".equals(files.getOriginalFilename()))
        {
            originalFilename = files.getOriginalFilename();
        }
        // 文件保存路径
        String filePath = request.getSession().getServletContext().getRealPath("/upload")+"\\"
                + QiniuUtil.renamePic(originalFilename);
        // 转存文件
        try
        {
            //保存至服务器
            File file=new File((filePath));
            files.transferTo(file);
            //上传七牛云服务器
            imagePath= QiniuUtil.qiniuUpload(filePath);
            if(imagePath.contains("error"))
            {
               throw new RestfulException("上传图片失败");
            }
            // 路径为文件且不为空则进行删除
            if (file.isFile() && file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultInfo.success("上传成功","url",imagePath);
    }

    @RequestMapping(value = "/image/imageDelete",method = RequestMethod.POST)
    public ResultInfo imageDelete(@RequestParam("uuid") String uuid,
                                     HttpServletRequest request){


        String imagePath=uuid.split("com/")[1];
        try
        {

            //从七牛云服务器上删除图片
            imagePath= QiniuUtil.deleteFile(imagePath);
            if(imagePath.contains("error"))
            {
                throw new RestfulException("删除图片失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultInfo.success("");
    }

}
