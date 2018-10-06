module.exports = {
  root: true,
  extends: [
    'plugin:vue/essential',
    '@vue/standard'
  ],
  env: {
    node: true,
    'browser': true,
    'es6': true
  },
  rules: {
    // allow async-await
    'indent': 'off',
    'generator-star-spacing': 'off',
    // allow debugger during development
    // 'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'vue/no-parsing-error': [2, {'x-invalid-end-tag': false}],
    'no-undef': 'off',
    'no-console': 'off',
    'eol-last': 0,//这句话表示在文件末尾可以不加回车，贴个链接  https://eslint.org/docs/rules/eol-last
    'space-before-function-paren': 0, //这句话表示在函数后可以不加空格
    'object-curly-spacing': 'off',
    'semi': 0, // 分号
    'no-unused-vars': 'off',
    'eqeqeq': 'off',
    'spaced-comment': 'off',
    'handle-callback-err': 'off'
  }
}
