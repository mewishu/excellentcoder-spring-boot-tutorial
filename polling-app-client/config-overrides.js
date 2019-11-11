// https://ant.design/docs/react/use-with-create-react-app-cn
const { override, fixBabelImports, addLessLoader } = require('customize-cra');

module.exports = override(
  fixBabelImports("import", {
    libraryName: "antd", 
    libraryDirectory: "es", 
    style: true // change importing css to less
  }),
  addLessLoader({
    javascriptEnabled: true,
    modifyVars: { "@primary-color": "#1DA57A" }
  })
);