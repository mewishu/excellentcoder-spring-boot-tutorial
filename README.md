# excellent-spring-boot-tutorial
best practice of spring boot, using crud as example.

# 服务端(polling-app-server)
1. 使用 [Spring Initialzr](https://start.spring.io/) 工具
2. 运行 TutorialApplication

# 前端(polling-app-client)
## 使用[create-react-app](https://github.com/facebook/create-react-app)来进行: 

```bash
npx create-react-app my-app
cd my-app
npm start
```

然后，打开 http://localhost:3000/ 即可看到应用。

## [React Router](https://github.com/ReactTraining/react-router) 组件

### react-router、react-router-dom、react-router-native区别

`react-router`: 实现了路由的核心功能

`react-router-dom`: 基于`react-router`，加入了在浏览器运行环境下的一些功能，例如：`Link`组件，会渲染一个`a`标签，[Link组件源码`a`标签行](https://github.com/ReactTraining/react-router/blob/master/packages/react-router-dom/modules/Link.js#L63); `BrowserRouter`和`HashRouter `组件，前者使用`pushState`和`popState`事件构建路由，后者使用`window.location.hash`和`hashchange`事件构建路由。

`react-router-native`: 基于`react-router`，类似`react-router-dom`，加入了`react-native`运行环境下的一些功能。

基于浏览器环境的开发，直接使用`react-router-dom`就可以:

```bash
cd polling-app-client
npm install react-router-dom --save
```

## [And Design](https://ant.design/docs/react/introduce-cn)

[在 create-react-app 中使用and](https://ant.design/docs/react/use-with-create-react-app-cn): 包括and、react-app-rewired customize-cra、babel-plugin-import等插件等。


# 参考内容
- https://www.javaguides.net/2018/10/free-open-source-projects-using-spring-boot.html
  主要是其中的Plling App部分。