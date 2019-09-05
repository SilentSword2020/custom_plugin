# gradle自定义插件

 1. gradle 命令: gradlew -q writeToFile 
 
    测试自定义插件
    
 2. gradle 命令: gradlew -q writeToFile_simple
 
    测试使用java-gradle-plugin来实现自定义插件
    
 3. gradle 命令: gradlew -q writeToFile_lazy
    
    测试使自定义插件:延迟创建task，延迟配置扩展属性实现
    
    **需要gradle 版本: 5.6.1及以上**

## 参考
[Gradle理论与实践四：自定义Gradle插件](https://blog.csdn.net/u013700502/article/details/85232032)

[官方：自定义Gradle插件](https://docs.gradle.org/current/userguide/custom_plugins.html)

[官方：maven发布](https://docs.gradle.org/5.6.1/userguide/publishing_maven.html)

[官方：project api doc](https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html)

