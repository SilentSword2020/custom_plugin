package com.android.simple.plugin.lib

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

import java.text.SimpleDateFormat

/**
 * 自定义插件
 */
class SimplePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        //获取项目的扩展属性: 名称为greeting
        //可以使用这种方式向插件：传递相关的配置数据
        def greetingExtension = project.extensions.create("greeting_simple", GreetingExtension)

        //创建一个新的task：writeToFile
        project.tasks.create('writeToFile_simple', SimplePluginTask) {
            config = greetingExtension
            doLast {
                //TODO 需要加上一些参数判断
                //打印文件的内容
                println project.file(config.destination).text
            }
        }
    }
}


class SimplePluginTask extends DefaultTask {
    def config

    File getDestination() {
        //创建路径为destination的file
        project.file(config.destination)
    }

    @TaskAction
    def action() {
        //TODO 需要加上一些参数判断

        def file = getDestination()
        file.parentFile.mkdirs()
        //向文件中写入文本
        file.write(String.format("%s, %s, %s",
                config.message, config.greeter,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())))
    }
}

/**
 * 配置数据
 */
class GreetingExtension {
    String destination
    String message
    String greeter
}
