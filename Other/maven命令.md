[TOC]

## mvn命令参数
| 命令参数 | 含义 |
| ---- | ---- |
| mvn -Pxxx | 激活id为xxx的profile（如有多个，用逗号隔开） |
| mvn -Dxxx=yyy | 指定Java全局属性 |
| mvn -N | --non-recursive：仅在当前项目模块执行命令,不构建子模块 |
| mvn -pl | --module_name：在指定模块上执行命令（如有多个，用逗号隔开）|
| mvn -pl xxx -am | 构建xxx模块及其依赖的子模块 |
| mvn -pl xxx -amd | 构建xxx模块及依赖xxx的父模块 |
| mvn -f | --file：强制使用备用的POM文件 |
| mvn -s | --settings：用户配置文件的备用路径 |
| mvn spring-boot:run | 通过springboot的maven插件启动 |

## spring-boot:run启动时，指定spring.profiles.active
指定maven的Profile：mvn spring-boot:run -Ptest

指定springboot配置文件的profile
- springboot1.x：mvn spring-boot:run -Drun.profiles=test
- springboot2.x：mvn spring-boot:run -Dspring-boot.run.profiles=test
- 直接运行jar包：java -jar -Dspring.profiles.active=test demo-0.0.1-SNAPSHOT.jar
- idea运行Application.java文件启动：增加参数--spring.profiles.active=test
