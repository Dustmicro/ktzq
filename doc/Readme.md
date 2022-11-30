##这是开发中遇到的问题以及一些注意事项
###问题
###注意
1、使用@Transient注解可以忽略字段,添加该注解的字段不会作为表字段使用。
2、如果是MySQL的自增字段，加上@GeneratedValue(generator = “JDBC”)即可。
3、@Select("sql语句")
List<User> queryBrandByCid(@Param("where条件") String userId);
