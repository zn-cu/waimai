package com.sky.controller.admin;

import com.alibaba.druid.sql.visitor.functions.Isnull;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author 夙落陌上梦
 * @version 1.0
 * @ClassName commonController
 * @DateTime 2025/2/22 上午7:54
 * @Description:
 */
@Slf4j
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@RestController
public class commonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("上传文件接口")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传中{}",file);
        try {
            //获取文件原始名字
            String originalFilename = file.getOriginalFilename();
            //获取文件名后缀
            String exension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新的文件名
            String objectName = UUID.randomUUID() + exension;
            //获取文件在阿里云oos的网址
            String url = aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(url);
        } catch (IOException e) {
            log.info("文件上传失败,{}",e);
        }
        return Result.error("文件上传失败");
    }}
