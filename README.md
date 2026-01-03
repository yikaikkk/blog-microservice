# blog-microservice

一个基于Spring Cloud的微服务博客系统，采用现代化的技术栈和架构设计，支持高并发访问和分布式部署。

## 项目架构

本项目采用微服务架构，主要包括以下几个模块：

- **blog-api**: 核心业务逻辑模块，提供各种博客相关的API服务
- **blog-app**: Web应用模块，处理HTTP请求和响应
- **blog-consumer**: 消息消费者模块，处理RabbitMQ消息队列中的任务
- **blog-like**: 点赞服务模块，处理文章点赞相关功能
- **blog-common**: 公共组件模块，包含配置、工具类等公共功能
- **blog-model**: 数据模型模块，包含实体类、DTO、VO等数据结构
- **blog-ai**: AI服务模块，集成大语言模型提供智能功能

## 技术栈

### 后端技术
- **Spring Boot**: 快速开发框架
- **Spring Cloud**: 微服务架构解决方案
- **Spring Security**: 安全认证和授权
- **MyBatis-Plus**: ORM框架，增强MyBatis功能
- **Dubbo**: RPC框架，服务间通信
- **Nacos**: 服务注册与发现、配置中心
- **RabbitMQ**: 消息队列，异步处理
- **Redis**: 缓存和会话存储
- **Elasticsearch**: 搜索引擎，全文检索
- **MySQL**: 关系型数据库
- **MinIO/OSS**: 对象存储服务
- **langchain4j**: AI模型集成框架
- **Zhipu API**: 智谱AI模型接口

### 前端技术
- **Vue.js**: 前端框架
- **Element UI**: UI组件库
- **Axios**: HTTP客户端

## 功能特性

### 核心功能
- 文章管理：发布、编辑、删除文章
- 分类管理：文章分类的创建和管理
- 标签管理：文章标签的管理
- 评论系统：用户评论和回复功能
- 用户管理：用户注册、登录、权限管理
- 搜索功能：基于Elasticsearch的全文搜索
- 文件上传：支持多种存储方式（MinIO、OSS）

### 高级功能
- 文章点赞：支持文章点赞功能
- 邮件通知：评论、订阅等邮件通知
- 订阅功能：文章更新订阅通知
- 访问统计：网站访问量统计
- 异步处理：通过消息队列异步处理任务
- 安全防护：防止重复提交、验证码等安全措施
- AI智能功能：集成大语言模型提供智能对话等功能

## 项目配置

### 环境要求
- Java 8+
- Maven 3.6+
- MySQL 5.7+
- Redis 5.0+
- RabbitMQ 3.8+
- Elasticsearch 7.x
- Nacos 1.4+

### 配置文件说明

各模块的配置文件位于 `src/main/resources/application-prod.yml`:

#### 数据库配置
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/blog?serverTimezone=Asia/Shanghai
    username: root
    password: password
```

#### Redis配置
```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password: password
    database: 0
```

#### RabbitMQ配置
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
```

#### Elasticsearch配置
```yaml
spring:
  elasticsearch:
    rest:
      uris: http://localhost:9200
```

## 部署说明

### 本地开发环境

1. 克隆项目到本地
```bash
git clone
cd blog-microservice
```

2. 启动依赖服务
- 启动MySQL数据库
- 启动Redis缓存服务
- 启动RabbitMQ消息队列
- 启动Elasticsearch搜索引擎
- 启动Nacos服务注册中心

3. 导入数据库表结构
- 在MySQL中创建数据库
- 执行SQL脚本初始化表结构

4. 编译项目
```bash
mvn clean install
```

5. 按顺序启动各模块
- 启动 `blog-common` 模块
- 启动 `blog-model` 模块
- 启动 `blog-consumer` 模块
- 启动 `blog-like` 模块
- 启动 `blog-api` 模块
- 启动 `blog-app` 模块

### 生产环境部署

1. 构建Docker镜像
```bash
mvn package docker:build
```

2. 使用Docker Compose部署
```bash
docker-compose up -d
```

## 消息队列设计

项目使用RabbitMQ实现异步处理，主要包括：

- **maxwell_queue**: 用于监听数据库变更，同步到Elasticsearch
- **email_queue**: 用于处理邮件发送任务
- **subscribe_queue**: 用于处理文章订阅通知

## 项目特点

1. **高可用性**: 采用微服务架构，支持服务发现和负载均衡
2. **高性能**: 使用Redis缓存和Elasticsearch搜索引擎提升性能
3. **可扩展性**: 模块化设计，易于扩展新功能
4. **安全性**: 集成Spring Security，提供完善的权限控制
5. **异步处理**: 使用RabbitMQ处理耗时任务，提升用户体验
