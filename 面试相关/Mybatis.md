[toc]
## 1、#{}和${}的区别是什么？
 ${}字符串替换，一般用于字段名，表名的传递
 #{}可以有效的防止SQL注入，因为#{}作了预编译处理。不管输入什么参数，都会先用占位符"?"替换变量，然后将sql发给数据库进行编译，再将变量值替换编译好的SQL中的占位符"?"，这样就可以防注入。因为SQL注入只对编译过程起作用。

## 2、实体类的属性名和表的字段名不一致？
 sql语句中使用别名；
 \<resultMap>进行映射

## 3、通常一个Xml映射文件，都会写一个Dao接口与之对应，请问，这个Dao接口的工作原理是什么？Dao接口里的方法，参数不同时，方法能重载吗？
 Dao接口的工作原理是JDK动态代理，Mybatis运行时会使用JDK动态代理为Dao接口生成代理proxy对象，代理对象proxy会拦截接口方法，转而执行MappedStatement所代表的sql，然后将sql执行结果返回。
 Dao接口里的方法，是不能重载的，因为是全限名+方法名的保存和寻找策略。
 Dao接口，就是人们常说的Mapper接口，接口的全限名，就是映射文件中的namespace的值，接口的方法名，就是映射文件中MappedStatement的id值，接口方法内的参数，就是传递给sql的参数。Mapper接口是没有实现类的，当调用接口方法时，接口全限名+方法名拼接字符串作为key值，可唯一定位一个MappedStatement。在Mybatis中，每一个\<select>、\<insert>、\<update>、\<delete>标签，都会被解析为一个MappedStatement对象。

## 4、Mybatis是如何将sql执行结果封装为目标对象并返回的？
 使用\<resultMap>标签，逐一定义列名和对象属性名之间的映射关系，有了列名与属性名的映射关系后，Mybatis通过反射创建对象，同时使用反射给对象的属性逐一赋值并返回，那些找不到映射关系的属性，是无法完成赋值的。

## 5、如何获取自动生成的（主）键值？
\<insert id=”insertname” usegeneratedkeys=”true” keyproperty=”id”>

## 6、在mapper中如何传递多个参数？
 在xml中使用#{0}、#{1}依次接收dao层的参数
 或dao层参数使用@param注解，在xml用变量名调用

## 7、Mybatis动态sql是做什么的？有哪些动态sql？原理是什么？
 Mybatis动态sql可以让我们在Xml映射文件内，以标签的形式编写动态sql，完成逻辑判断和动态拼接sql的功能。
 Mybatis提供了9种动态sql标签：trim|where|set|foreach|if|choose|when|otherwise|bind。
 其执行原理为，使用OGNL从sql参数对象中计算表达式的值，根据表达式的值动态拼接sql，以此来完成动态sql的功能。

## 8、为什么说Mybatis是半自动ORM映射工具？它与全自动的区别在哪里？
 Hibernate属于全自动ORM映射工具，使用Hibernate查询关联对象或者关联集合对象时，可以根据对象关系模型直接获取，所以它是全自动的。
 而Mybatis在查询关联对象或关联集合对象时，需要手动编写sql来完成，所以，称之为半自动ORM映射工具。

## 9、Mybatis是如何进行分页的？分页插件的原理是什么？
 Mybatis使用RowBounds对象进行分页，它是针对ResultSet结果集执行的内存分页（逻辑分页），而非物理分页，可以在sql内直接书写带有物理分页的参数来完成物理分页功能，也可以使用分页插件来完成物理分页。
 分页插件的基本原理是使用Mybatis提供的插件接口，实现自定义插件，在插件的拦截方法内拦截待执行的sql，然后重写sql，根据dialect方言，添加对应的物理分页语句和物理分页参数。
 PageHelper

## 10、缓存？
 MyBatis 同样提供了一级缓存和二级缓存的支持。
 一级缓存：基于PerpetualCache 的 HashMap本地缓存，其存储作用域为 Session，当 Session flush 或 close 之后，该Session中的所有 Cache 就将清空。
 二级缓存：默认也是采用 PerpetualCache，HashMap存储，不同在于其存储作用域为 Mapper(Namespace)，并且可自定义存储源，如 Ehcache。
 一级缓存的作用域是一个SqlSession。Mybatis默认开启一级缓存。在同一个namespace下的mapper文件中，执行相同的查询SQL，第一次会去查询数据库，并写到缓存中；第二次直接从缓存中取。SqlSession执行insert、update、delete等操作commit后会清空该SQLSession缓存。
 二级缓存是mapper级别的。Mybatis默认是没有开启二级缓存。第一次调用mapper下的SQL去查询用户信息。查询到的信息会存到该mapper对应的二级缓存区域内。第二次调用相同namespace下的mapper映射文件中相同的SQL去查询用户信息。会去对应的二级缓存内取结果。
 开启二级缓存：在核心配置文件SqlMapConfig.xml中加入\<setting name="cacheEnabled" value="true">（没有mybatis的配置文件时，在application.properties中加入mybatis.configuration.cache-enabled=true），然后在mapper中加入<cache />开启二级缓存。
 二级缓存不一定存在内存中，缓存对象需要实现序列化（serializable）。\<select useCache="false">禁用二级缓存，\<select flushCache="true">刷新





















