package com.yupi.yuaicodemother.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.yupi.yuaicodemother.ai.model.HtmlCodeResult;
import com.yupi.yuaicodemother.ai.model.MultiFileCodeResult;
import com.yupi.yuaicodemother.model.enums.CodeGenTypeEnum;

import java.io.File;

public class CodeFileSaver {
    //1.常量文件的根路径 项目路径/tmp/code_output
    private static final String FILE_SAVE_ROOT_PATH = System.getProperty("user.dir")+ "/tmp/code_output";

    //2.生成唯一文件路径 根路径 biz_type_snowflakeId
    private static String builderUniqueDirectory(String bizType) {
        String uniqueDirName = StrUtil.format("{}_{}",bizType, IdUtil.getSnowflakeNextId());
        String dirPath = FILE_SAVE_ROOT_PATH + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }
    //3.超路径写入文件 路径 文件名 编码格式
    private static void writeToFile(String filePath, String fileName, String content) {
        String fullPath = filePath + File.separator + fileName;
        FileUtil.writeUtf8String(content, fullPath);
    }
    //4.保存HtmlCodeResult
    public  static File saveHtmlCodeResult(HtmlCodeResult htmlCodeResult) {
        String baseDirPath = builderUniqueDirectory(CodeGenTypeEnum.HTML.getValue());
        writeToFile(baseDirPath, "index.html", htmlCodeResult.getHtmlCode());
        return new File(baseDirPath);
    }


    //5.保存MultiFileCodeResult
    public  static File saveMultiFileCodeResult(MultiFileCodeResult multiFileCodeResult) {
        String baseDirPath = builderUniqueDirectory(CodeGenTypeEnum.MULTI_FILE.getValue());
        writeToFile(baseDirPath, "index.html", multiFileCodeResult.getHtmlCode());
        writeToFile(baseDirPath, "style.css", multiFileCodeResult.getCssCode());
        writeToFile(baseDirPath, "script.js", multiFileCodeResult.getJsCode());
        return new File(baseDirPath);
    }

}
