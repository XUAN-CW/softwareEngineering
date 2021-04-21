# Idea Maven项目编译后target下classes中没有.xml问题解决方案

```xml
<build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
</build>
```

 [Idea Maven项目编译后target下classes中没有.xml问题解决方案_进修的CODER-CSDN博客_target中没有xml.html](references\Idea Maven项目编译后target下classes中没有.xml问题解决方案_进修的CODER-CSDN博客_target中没有xml.html) 





# MyBatis-Plus 自定义sql语句

 [MyBatis-Plus 自定义sql语句_贱男-CSDN博客.html](references\MyBatis-Plus 自定义sql语句_贱男-CSDN博客.html) 

```properties
mybatis-plus.mapper-locations=classpath:/edu/guet/mapper/xml/**.xml
```

