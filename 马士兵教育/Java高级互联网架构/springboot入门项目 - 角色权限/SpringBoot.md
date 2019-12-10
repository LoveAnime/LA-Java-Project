[TOC]

# SpringBoot 2.x

## 整合Servlet

### 注解方式
**启动类上添加注解**
```java
@SpringBootApplication
public class Springboot011Application {
	public static void main(String[] args) {
		SpringApplication.run(Springboot011Application.class, args);
	}
}
```

**Servlet类**
``` java
@WebServlet(name = "myServlet",urlPatterns = "/srv",loadOnStartup = 1)
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("111");
        super.doGet(req, resp);
    }
}
```

### 编码方式
**启动类中添加**
``` java
	@Bean
	public ServletRegistrationBean<MyServlet2> getServletRegistrationBean(){
		ServletRegistrationBean<MyServlet2> bean = new ServletRegistrationBean<>(new MyServlet2(), "/s2");
		bean.setLoadOnStartup(1);
		return bean;
	}
```

这种方式在Servlet中无需注解

## 整合Filter
需要实现接口Filter：
```java
public class xxxFilter implements Filter{}
```

## 监听器
实现接口 ServletContextListener ：
```java 
public class MyListener implements ServletContextListener{}
```

## 访问静态资源
可以把静态文件放到以下工程目录下：
- src/main/resources/static
- src/main/webapp

## 常用表单数据接收方式

### PathVariable
```java
   @GetMapping(value = "/hello/{id}")
    public String hello(@PathVariable("id") Integer id){
        return "ID:" + id;
}
```
### 实体对象接受

#### JSON数据
```java
@PostMapping(value = "/user")
public User saveUser2(@RequestBody User user) {
    return user;
}
```

#### 普通实体对象
```java
@PostMapping(value = "/user")
public User saveUser2(User user) {
    return user;
}
```

#### 参数名取值
```java
@PostMapping(value = "/post")
public String post(@RequestParam(name = "name") String name,
                   @RequestParam(name = "age") Integer age) {
    String content = String.format("name = %s,age = %d", name, age);
    return content;
}
```

## 文件上传
**Html表单**
````html
<form action="fileUploadController" method="post" enctype="multipart/form-data">
		上传文件：<input type="file" name="filename"/><br/>
		<input type="submit"/>
	</form>
````

**application.properties配置上传文件的大小**
```properties
spring.http.multipart.maxFileSize=200MB
spring.http.multipart.maxRequestSize=200MB

spring.servlet.multipart.max-request-size = 200MB
spring.servlet.multipart.max-file-size = 200MB
```

**java代码**
```java
	@RequestMapping("/fileUploadController")
	public String fileUpload(MultipartFile filename) throws Exception{
		System.out.println(filename.getOriginalFilename());
		filename.transferTo(new File("e:/"+filename.getOriginalFilename()));
		return "ok";
	}
```

# SpringData Jpa进阶使用

## 官方文档：

https://docs.spring.io/spring-data/jpa/docs/2.1.8.RELEASE/reference/html/

## 接口继承

``` java
public interface AccountRepository extends JpaRepository<Account, Integer>
```

**JpaRepository**接口中已经实现了常用的增删改查分页查询等功能

### 控制台显示SQL

application.properties 中配置
```properties
spring.jpa.show-sql=true
```

## 自定义查询
| 关键字      | 意义                                             |
| ---------- | --------------------------------------------- |
| And         | 等价于 SQL 中的 and 关键字，比如 findByUsernameAndPassword(String user, Striang pwd)； |
| Or          | 等价于 SQL 中的 or 关键字，比如 findByUsernameOrAddress(String user, String addr)； |
| Between     | 等价于 SQL 中的 between 关键字，比如 findBySalaryBetween(int max, int min)； |
| LessThan    | 等价于 SQL 中的 "<"，比如 findBySalaryLessThan(int max)；    |
| GreaterThan | 等价于 SQL 中的">"，比如 findBySalaryGreaterThan(int min)；  |
| IsNull      | 等价于 SQL 中的 "is null"，比如 findByUsernameIsNull()；  |
| IsNotNull   | 等价于 SQL 中的 "is not null"，比如 findByUsernameIsNotNull()； |
| NotNull     | 与 IsNotNull 等价；                                      |
| Like        | 等价于 SQL 中的 "like"，比如 findByUsernameLike(String user)； |
| NotLike     | 等价于 SQL 中的 "not like"，比如 findByUsernameNotLike(String user)； |
| OrderBy     | 等价于 SQL 中的 "order by"，比如 findByUsernameOrderBySalaryAsc(String user)； |
| Not         | 等价于 SQL 中的 "！ ="，比如 findByUsernameNot(String user)； |
| In          | 等价于 SQL 中的 "in"，比如 findByUsernameIn(Collection\<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数； |
| NotIn       | 等价于 SQL 中的 "not in"，比如 findByUsernameNotIn(Collection\<String> userList) ，方法的参数可以是 Collection 类型，也可以是数组或者不定长参数； |

## 自定义SQL  @Query注解

### 占位符

``` java
public interface UserDao extends Repository<AccountInfo, Long> { 

@Query("select a from AccountInfo a where a.accountId = ?1") 
public AccountInfo findByAccountId(Long accountId); 

@Query("select a from AccountInfo a where a.balance > ?1") 
public Page<AccountInfo> findByBalanceGreaterThan(Integer balance,Pageable pageable); 
}
```

### 参数名

```java
public interface UserDao extends Repository<AccountInfo, Long> { 

    public AccountInfo save(AccountInfo accountInfo); 

    @Query("from AccountInfo a where a.accountId = :id") 
    public AccountInfo findByAccountId(@Param("id")Long accountId); 

    @Query("from AccountInfo a where a.balance > :balance") 
    public Page<AccountInfo> findByBalanceGreaterThan(@Param("balance")Integer balance,Pageable pageable); 
}
```

### 更新操作

``` java
@Modifying 
@Query("update AccountInfo a set a.salary = ?1 where a.salary < ?2") 
public int increaseSalary(int after, int before);
```

### 直接使用Native SQL

设置属性 **nativeQuery = true**

``` java
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
  User findByEmailAddress(String emailAddress);
}
```
