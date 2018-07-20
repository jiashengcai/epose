# Epose - English Corner 团队
## 简介
Epose 项目以 posenet 为基础，实现动作识别与相似度分析功能。用户只需要使用浏览器登录，即可使用 Epose 的动作教学功能。动作教学课程会给出动作（如瑜伽）的标准姿态，以及最准确的各部位角度。系统会根据用户身体各部位角度来判断用户的动作是否标准，并给予评分。

## 运行环境
+ mysql 8.0.11 winx64
+ jdk 1.8.0_162

## 运行步骤
~~1. 安装正确版本的 mysql 与 jdk
2. 配置 mysql 的 root 账号，设置密码为 Di9Zy;2hCHQc
3. 在 mysql 中创建数据库 epose
4. 命令行输入 java -jar epose.jar 运行
5. 在浏览器中输入 http://localhost:8080~~

已经配置好数据库

spring.datasource.url = jdbc:mysql://35.232.39.13:3306/epose?useSSL=false&useUnicode=true&characterEncoding=utf-8
spring.datasource.username = epose
spring.datasource.password = WKFA*IfTP2l>

6. 注册账号并登录
7. 点击右方的继续学习按钮
8. 进入学习页面，做出相应的动作并得到评分

## 注意事项
在一些电脑中直接运行 epose.jar 文件可能会报错出现问题，这时候可以用 idea 导入源码，编译后即可完美运行。

## 开发环境
+ IntelliJ IDEA 2017.2.7
+ apache-maven-3.5.4
+ mysql 8.0.11 winx64
+ jdk 1.8.0_162
+ spring boot 2.0.3
