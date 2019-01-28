const path = require('path')
const CompressionWebpackPlugin = require('compression-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const productionGzipExtensions = /\.(js|css|json|txt|html|ico|svg)(\?.*)?$/i;
const resolve = dir => {
  return path.join(__dirname, dir)
}
const IS_PROD = ['production', 'product', 'testing', 'development'].includes(process.env.NODE_ENV);

// 项目部署基础
// 默认情况下，我们假设你的应用将被部署在域的根目录下,
// 例如：https://www.my-app.com/
// 默认：'/'
// 如果您的应用程序部署在子路径中，则需要在这指定子路径
// 例如：https://www.foobar.com/my-app/
// 需要将它改为'/my-app/'
const BASE_URL = process.env.NODE_ENV === 'production'
  ? '/'
  : '/'

module.exports = {
  assetsDir: 'static', // 相对于outputDir的静态资源(js、css、img、fonts)目录
  // Project deployment base
  // By default we assume your app will be deployed at the root of a domain,
  // e.g. https://www.my-app.com/
  // If your app is deployed at a sub-path, you will need to specify that
  // sub-path here. For example, if your app is deployed at
  // https://www.foobar.com/my-app/
  // then change this to '/my-app/'
  baseUrl: BASE_URL,
  // tweak internal webpack configuration.
  // see https://github.com/vuejs/vue-cli/blob/dev/docs/webpack.md
  // 如果你不需要使用eslint，把lintOnSave设为false即可
  lintOnSave: true,
  chainWebpack: config => {
    config.resolve.symlinks(true)
    config.extensions = ['.js', '.vue', '.json']
    config.output.chunkFilename(`static/js/[name].[hash:8].js`)
    config.resolve.alias
      .set('@', resolve('src')) // key,value自行定义，比如.set('@@', resolve('src/components'))
      .set('_c', resolve('src/components'))
      .set('_conf', resolve('config'))
      .set('_static', resolve('static'))
  },
  configureWebpack: config => {
    config.optimization = {
      splitChunks: {
        chunks: 'all'
      }
    };
    const plugins = [];
    plugins.push(
      new UglifyJsPlugin({
        uglifyOptions: {
          compress: {
            warnings: false,
            drop_console: true,
            drop_debugger: false,
            pure_funcs: ['console.log']//移除console
          }
        },
        sourceMap: false,
        parallel: true
      })
    );
    plugins.push(
      new CompressionWebpackPlugin({
        filename: '[path].gz[query]',
        algorithm: 'gzip',
        test: productionGzipExtensions,
        threshold: 10240,
        minRatio: 0.8
      })
    );
    config.plugins = [
      ...config.plugins,
      ...plugins
    ];
  },
  css: {
    extract: {
      filename: 'static/css/[name].[hash:8].css',
      chunkFilename: 'static/css/[name].[hash:8].css'
    },
    sourceMap: false,
    loaderOptions: {
      // sass: {
      // 全局注入通用样式
      // data: fs.readFileSync('./src/assets/variables.scss', 'utf-8')
      // }
    }
  },
  parallel: require('os').cpus().length > 1,
  // 打包时不生成.map文件
  productionSourceMap: false
  // 这里写你调用接口的基础路径，来解决跨域，如果设置了代理，那你本地开发环境的axios的baseUrl要写为 '' ，即空字符串
  // devServer: {
  //   proxy: 'localhost:3000'
  // }
  // devServer: {
  //   // contentBase: "./static",
  //   port: 8080,
  //   inline: true,
  //   historyApiFallback: true,
  //   colors: true,
  //   stats: 'normal',
  // }
}
