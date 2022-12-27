##这是开发中遇到的问题以及一些注意事项
###问题
###注意
1、使用@Transient注解可以忽略字段,添加该注解的字段不会作为表字段使用。
2、如果是MySQL的自增字段，加上@GeneratedValue(generator = “JDBC”)即可。
3、@Select("sql语句")
List<User> queryBrandByCid(@Param("where条件") String userId);
4、目前用户表userid是长整型，球队成员memberid是整型，员工staffid是整型，后期需要酌情调整一下