package com.android.lazy.plugin.lib

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

import java.text.SimpleDateFormat

/**
 * 自定义插件
 *
 * TODO 需要gradle 版本: 5.6.1及以上
 * 参考：
 *
 * 1.配置数据：延迟配置 <a href="https://docs.gradle.org/current/userguide/lazy_configuration.html"/>
 * 2.Task延迟创建：<a href="https://docs.gradle.org/current/userguide/task_configuration_avoidance.html"/>
 */
class LazyPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        //获取项目的扩展属性: 名称为greeting_lazy
        //可以使用这种方式向插件：传递相关的配置数据
        def greetingExtension = project.extensions.create("greeting_lazy", GreetingExtensionLazy)

        //TODO 注册一个新的task：writeToFile_lazy，不会立即创建
        println("task:writeToFile_lazy register()")
        project.tasks.register('writeToFile_lazy', LazyPluginTask) {
            //当task被创建时，执行下面的代码

            println("task:writeToFile_lazy create()")
            config = greetingExtension.greeting
            doLast {
                //TODO 需要加上一些参数判断
                //打印文件的内容
                println project.file(config.get().destination).text
            }
        }

    }
}


class LazyPluginTask extends DefaultTask {
    //延迟初始化属性，使用时，才初始化
    @Input
    final Property<GreetingExtension> config = project.objects.property(GreetingExtension)
    @Internal
    final Provider<GreetingExtension> data = config.map { it }

    File getDestination() {
        //创建路径为destination的file
        project.file(data.get().destination)
    }

    @TaskAction
    def action() {
        //TODO 需要加上一些参数判断

        def file = getDestination()
        file.parentFile.mkdirs()
        //向文件中写入文本
        def extension = data.get()
        file.write(String.format("%s, %s, %s",
                extension.message, extension.greeter,
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())))
    }
}


/**
 * 配置数据
 */
class GreetingExtensionLazy {
    // A configurable greeting
    Property<GreetingExtension> greeting

    @javax.inject.Inject
    GreetingExtensionLazy(ObjectFactory objects) {
        greeting = objects.property(GreetingExtension)
    }

}
