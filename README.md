# excellent-spring-boot-multi-module-project
best practice of multi module spring boot, Spring Security, JWT, React, and Ant Design, using poll as example.

# 服务端(polling-app-server)
## 工程创建

参见[Spring Initialzr](https://start.spring.io/) 

## 数据库及数据初始化

- 创建MySQL数据库: polling_app
- 打开 app/bootstrap/src/main/resources/application.properties，并设置spring.datasource.username` 和 `spring.datasource.password的属性值
- 按照 app/common/dal/src/main/resources/db/migration 中的内容分别建立对应的表，并初始化对应的数据。

## 运行

1. 使用IDE，如intellij idea，运行 TutorialApplication；

2. 在command line中执行: 

   ```bash
   mvn clean install
   
   cd app/bootstrap
   mvn spring-boot:run
   
   直到发现: 
   2019-11-12 16:54:56.254  INFO 61521 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 5000 (http) with context path 
   ```

   也可使用Maven Wrapper: https://www.baeldung.com/maven-wrapper

# 前端(polling-app-client)
## 工程创建

### 使用[create-react-app](https://github.com/facebook/create-react-app)来进行

```bash
npx create-react-app my-app
cd my-app
npm start
```

然后，打开 http://localhost:3000/ 即可看到应用。

### [React Router](https://github.com/ReactTraining/react-router) 组件

#### react-router、react-router-dom、react-router-native区别

`react-router`: 实现了路由的核心功能

`react-router-dom`: 基于`react-router`，加入了在浏览器运行环境下的一些功能，例如：`Link`组件，会渲染一个`a`标签，[Link组件源码`a`标签行](https://github.com/ReactTraining/react-router/blob/master/packages/react-router-dom/modules/Link.js#L63); `BrowserRouter`和`HashRouter `组件，前者使用`pushState`和`popState`事件构建路由，后者使用`window.location.hash`和`hashchange`事件构建路由。

`react-router-native`: 基于`react-router`，类似`react-router-dom`，加入了`react-native`运行环境下的一些功能。

基于浏览器环境的开发，直接使用`react-router-dom`就可以:

```bash
cd polling-app-client
npm install react-router-dom --save
```

### [And Design](https://ant.design/docs/react/introduce-cn)

[在 create-react-app 中使用and](https://ant.design/docs/react/use-with-create-react-app-cn): 包括and、react-app-rewired customize-cra、babel-plugin-import等插件等。



## 运行

First go to the `polling-app-client` folder -

```
cd polling-app-client
```

Then type the following command to install the dependencies and start the application -

```
npm install && npm start
```

The front-end server will start on port `3000`.



# Live Demo

1. 首页

   ![image](https://github.com/mewishu/excellentcoder-spring-boot-tutorial/raw/master/live_demo/main_page.png)

2. 用户登陆

   ![image](https://github.com/mewishu/excellentcoder-spring-boot-tutorial/raw/master/live_demo/user_login.png)

3. 用户注册

   ![image](https://github.com/mewishu/excellentcoder-spring-boot-tutorial/raw/master/live_demo/user_signup.png)

4. 创建投票

   ![image](https://github.com/mewishu/excellentcoder-spring-boot-tutorial/raw/master/live_demo/create_poll.png)

5. 我的polls

   ![image](https://github.com/mewishu/excellentcoder-spring-boot-tutorial/raw/master/live_demo/profile_polls.png)

6. 我的votes

   ![image](https://github.com/mewishu/excellentcoder-spring-boot-tutorial/raw/master/live_demo/profile_votes.png)

# 参考内容

- https://www.javaguides.net/2018/10/free-open-source-projects-using-spring-boot.html
  主要是其中的Plling App部分。
