package com.stuffmanage;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.hibernate.result.Output;

import java.nio.file.Paths;
import java.util.Collections;

public class codeGenerator {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/ry?characterEnconding=utf-8";
        String username="root";
        String password="lxjlxt123";
        String author="damingass";
        String moduleName="sys";
        String mapperLocation="E:\\JAVA_Proj\\stuffManage\\src\\main\\resources\\mapper"+moduleName;
        String table="sys_user,sys_role,sys_menu,sys_user_role,sys_role_menu";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> builder
                        .author(author)
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("com.stuffmanage")
                        .moduleName(moduleName)
                        .pathInfo(Collections.singletonMap(OutputFile.xml,mapperLocation))
                )
                .strategyConfig(builder -> {
                    builder.addInclude(table)
                            .addTablePrefix("sys_");

                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
