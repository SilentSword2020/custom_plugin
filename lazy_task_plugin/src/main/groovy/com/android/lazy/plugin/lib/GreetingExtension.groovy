package com.android.lazy.plugin.lib;

/**
 * 配置数据
 */
class GreetingExtension {
    String destination
    String message
    String greeter

    GreetingExtension() {
    }

    GreetingExtension(String destination, String message, String greeter) {
        this.destination = destination
        this.message = message
        this.greeter = greeter
    }


    def destination(String destination) {
        this.destination = destination
    }

    def message(String message) {
        this.message = message
    }

    def greeter(String greeter) {
        this.greeter = greeter
    }

    //groovy DSLS: https://www.w3cschool.cn/groovy/groovy_dsls.html
    def static GreetingExtension create(closure) {
        GreetingExtension extension = new GreetingExtension()
        // any method called in closure will be delegated to the EmailDsl class
        closure.delegate = extension
        closure()
        return extension
    }
}

