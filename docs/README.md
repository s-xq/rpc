[TOC]

### 框架设计

* 添加Transport层，支持Netty、SOFA-RPC、gRPC、DubboRPC等
* 构建Serialize模块：分为RPC消息头协议，RPC消息体(即用户协议，支持多种，比如Json、PB)
* 在Netty上层构建同步阻塞模块Remoting
* 通过CGlib或者Jdk动态代理实现Proxy：ServiceApiStub -> Proxy -> Invoker -> Transport
* 通过Javassist或ASM实现SPI，加载Client端的用户的ServiceApi接口，生成Stub代码
* 构建Registry模块，支持ZK




### 部署
1. Provider层：使用DockerCompose打包部署，同时启动注册中心Docker
2. Consumer层：服务发现，服务调用
