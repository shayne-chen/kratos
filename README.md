# kratos

模块说明:  
kratos-common  : 通用模块，包括 常量、异常定义、response定义、utils、全局异常处理  
kratos-core    : 核心模块，包括 动态数据源、数据源动态切换、sharding分表、线程池、请求责任链  
kratos-dao     : dao层，包括 mybatis拦截器、数据源原始配置  
kratos-dto     : DO/VO等  
kratos-service : service层  
kratos-web     : controller、请求拦截器  


###基础业务功能描述: 用户登录注册等简单功能  

1.使用拦截器校验请求cookie中的sid, sid及用户信息保存在redis    

2.用户表及session表根据用户uid做分表处理，使用sharding-jdbc  

3.支持动态数据源，通过aop动态切换数据源  

4.使用mybatis拦截器自动更新数据表中的创建/修改时间字段    

5.通过全局异常处理，统一处理controller中的异常  

