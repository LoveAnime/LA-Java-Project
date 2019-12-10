[TOC]

# Thymeleaf

## 官方文档
https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#base-objects

### 安装地址

http://www.thymeleaf.org/eclipse-plugin-update-site/

### Html中添加约束

```html
<html xmlns:th="http://www.thymeleaf.org">
```

## 常用语法

### th:href=‘’@{...}‘’

URL地址处理。假设当前路径为：http://localhost/thymeleaf/user

1）@{userList} 、@{./userList}相对当前路径：http://localhost/thymeleaf/user/userList

2）@{../tiger/home} 相对当前路径的上一级路径：http://localhost/thymeleaf/tiger/home

3）@{/tiger/home} 相对项目根目录：http://localhost/tiger/home

4）@{https://www.baidu.com/} 绝对路径：https://www.baidu.com

5）静态资源： 以 "/" 开头定位到**项目根路径**，否则使用相对路径

``` html
<body>

<link type="text/css" rel="stylesheet" th:href="@{/css/home.css}">

<a th:href="@{userList(id=1)}">1、@{userList(id=9527)}</a>
<a th:href="@{userList(id=1,name=华安)}">2、@{userList(id=1,name=yoyo)}</a>
<a th:href="@{userList(id=1,name=${userName})}">3、@{userList(id=1,name=${userName})}</a>
</body>
```

### th:class
设置类名
``` html
<p class="css1 css2" th:class="'css1 css2'">样式</p>
```

### th:text

**简单设值**
``` html
<!-- 设置常量 -->
<p th:text="Hello">中国</p>
<!-- 空格属于特殊字符，必须使用单引号包含整个字符串 -->
<p th:text="'Big China'">中国</p>
<!-- 设置变量 -->
<p th:text="${userName}">userName</p>
```

**四则运算**
``` html
<p th:text="8+8">8 + 8</p>
<p th:text="100-${age}"></p>
<p th:text="15 * 4-100/10">值为 50 </p>
<!-- 字符串拼接 -->
<p th:text="hello + ${str}"></p>
```

### th:if
** Boolean判断**
``` html
<p th:text="true">布尔</p>
<p th:text="true and false">true and true</p>
<p th:if="${age}&gt;18">已成年</p>
<p th:if="10>=8 and 7 !=8">10大于等于8，且 7 不等于 8 </p>
```

### 三元运算符
```html
<p th:text="7&gt;5?'7大':'5大'">三元运算符</p>
<p th:text="${age}>=18?'成年':'未成年'"></p>
<p th:class="${isMarry}?'css2':'css3'">已婚</p>
```

### th:utext转义
java后台传值 
```java
map.addAttribute("china", "<b>Chian</b>,USA,UK");
```
html取值
```html
<p th:text="${china}">默认转义</p>
<p th:utext="${china}">不会转义</p>
```

### th:attr 设置属性
HTML5 所有的属性，都可以使用 th:\* 的形式进行设置值，也可以自定义属性
```html
<a href="http://baidu.com" th:attr="title='百度'">百度</a>
```
html属性设置
```html
<!-- 设置 href 属性 -->
<a href="" th:attr="title='前往百度',href='http://baidu.com'">前往百度</a>
<!-- data-target 属性 Html 本身是没有的，但允许用户自定义 -->
<a href="#" th:attr="id='9527',data-target='user'">归海一刀</a>
<p th:abc="9527">th:abc="9527"</p>
```

### th:checked、th:selected、th:autofocus
java后台传值 ：
```java
model.addAttribute("isMarry", true);
```
html取值
```html
<!--option3、option4 会选中；option5 不会选中-->
<input type="checkbox" name="option3" th:checked="${isMarry}"/><span>是否已婚？</span>
<input type="radio" name="option4" th:checked="${isMarry}"/><span>是否已婚？</span>
<input type="radio" name="option5" th:checked="!${isMarry}"/><span>是否已婚？</span>

<select>
	<option>a</option>
	<option th:selected="${isMarry}">已婚</option>
	<option th:selected="${!isMarry}">未婚</option>
</select>

<input type="text" th:autofocus="false">
<input type="text" th:autofocus="true">
<input type="text" th:autofocus="false">
```

### 日期格式化
```html
<span th:text="${#dates.format(date, 'yyyy-MM-dd HH:mm')}"></span>
```

### th:each

JSTL 有一个 **c:foreach**。 Thymeleaf 也有一个 th:each，用于遍历数组、List、Set、Map 等数据。

在Select上循环
```html
 <option th:each="city: ${list}" th:text="${city.name}" th:selected="${cityName} eq ${city.name}">Peking</option>
```

#### 状态变量 loopStatus

- index: 当前迭代对象的index（从0开始计算）
- count:  当前迭代对象的index(从1开始计算)   
- size: 被迭代对象的大小     current:当前迭代变量 
- even/odd: 布尔值，当前循环是否是偶数/奇数（从0开始计算）
- first: 布尔值，当前循环是否是第一个   
- last: 布尔值，当前循环是否是最后一个

```html
<tr th:each="city,status : ${list}" th:style="${status.odd}?'background-color:#c2c2c2'">
	<!-- EL JSTL-->
	<td th:text = "${status.count}"></td>
	<td th:text = "${city.id}"></td>
	<td th:text = "${city.name}"></td>
/tr>
```
如上例所示，status就是状态变量。可以不指定，默认为`迭代变量+Stat`，如`cityStat`。

### If/else

```html
<p th:if="${isMarry}">已婚1</p>
<p th:unless="${isMarry}">未婚</p>
```

### Switch/case
``` html
<div th:switch="1">
    <p th:case="0">管理员</p>
    <p th:case="1">操作员</p>
    <p th:case="*">未知用户</p>
</div>

<div th:switch="-1">
    <p th:case="0">管理员</p>
    <p th:case="*">操作员</p>
    <p th:case="*">未知用户</p>
</div>

<div th:switch="${isMarry}">
    <p th:case="true">已婚</p>
    <p th:case="true">已成年</p>
    <p th:case="false">未婚</p>
</div>

<div th:switch="'For China'">
    <p th:case="'For USA'">美国</p>
    <p th:case="'For UK'">英国</p>
    <p th:case="'For China'">中国</p>
    <p th:case="*">未知国籍</p>
</div>
```
## 内联表达式
[[...]] 等价于 th:text（结果将被 HTML 转义），[(...)] 等价于 th:utext（结果不被HTML转义）
```html
<p>[[${china}]]</p>
<p>[(${china})]</p>
<p>[[Lo1ve]]</p>
<p>[['I Love You Baby']]</p>
```
禁用内联th:inline ="none"

### 内联 JavaScript
用于前后端分离开发
```html
<script type="text/javascript" th:inline="javascript">
     var info = [[${info}]];
     var age = [[${age}]];
     var id = [[${id}]];
     var name = [[${name}]];
     /**
      * Thymeleaf 将自动忽略掉注释之后 和 分号之前的所有内容,如下为 "前端测试"
      */
     var info = /*[[${info}]]*/ "前端测试";
     console.log(id, name, age, info);
</script>
```

## Servlet作用域中的对象属性
### URL/request
```html
<p>${param.size()}=[[${param.size()}]]</p>
<p>${param.id}=[[${param.id}]]</p>
```

### Session
```html
<p>${session.size()}=[[${session.size()}]]</p>
<p>${session.user.id}=[[${session.user.id}]]</p>
```

