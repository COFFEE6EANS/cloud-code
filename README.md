# cloud-code

###  Spring Cloud Zuul Filter链
Zuul中不同类型Filter的执行逻辑核心在com.netflix.zuul.http.ZuulServlet类

@EnableZuulProxy 搭配 Actuator会多两个接口
/routes
/filterss

|服务名|端口号|
|eureka-server-1001|1001|
|client-a|7000|
|zuul-server-middle|5100|
|auth-server|7777|
|client-b|7001|
