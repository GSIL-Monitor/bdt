import Main from '@/components/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面不会缓存
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 *  beforeCloseName: (-) 设置该字段，则在关闭当前tab页时会去'@/router/before-close.js'里寻找该字段名对应的方法，作为关闭前的钩子函数
 * }
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {title: 'Login - 登录', hideInMenu: true},
    // component: () => import('@/view/login/login.vue')
    component: resolve => require(['@/view/login/login.vue'], resolve)
  },
  {
    path: '/',
    name: '投注管理',
    redirect: '/home',
    component: Main,
    meta: {hideInMenu: true},
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {hideInMenu: true, title: '投注管理'},
        // component: () => import('@/view/overview/overview.vue')
        component: resolve => require(['@/view/overview/overview.vue'], resolve)
      },
      {
        path: '/super',
        name: 'super',
        meta: {hideInMenu: true, title: '超级管理员'},
        // component: () => import('@/view/overview/super.vue')
        component: resolve => require(['@/view/overview/super.vue'], resolve)
      },
      {
        path: '/setting',
        name: 'setting',
        meta: {hideInMenu: true, title: '设置'},
        // component: () => import('@/view/setting/setting.vue'),
        component: resolve => require(['@/view/setting/setting.vue'], resolve)
      }
    ]
  },
  {
    path: '/overview',
    name: '投注管理',
    component: Main,
    meta: {icon: 'ios-keypad', title: '投注管理'},
    children: [
      {
        path: '/overview',
        name: 'overview',
        meta: {icon: 'ios-home', title: '投注', beforeCloseName: 'before_close_normal'},
        // component: () => import('@/view/overview/overview.vue')
        component: resolve => require(['@/view/overview/overview.vue'], resolve)
      },
      {
        path: '/read',
        name: 'read',
        meta: {icon: 'ios-cloud', title: '读牌', beforeCloseName: 'before_close_normal'},
        // component: () => import('@/view/overview/read.vue')
        component: resolve => require(['@/view/overview/read.vue'], resolve)
      },
      {
        path: '/control',
        name: 'control',
        meta: {icon: 'ios-build', title: '图形监控', beforeCloseName: 'before_close_normal'},
        component: resolve => require(['@/view/overview/control/components/c_lz_all.vue'], resolve),
      }
    ]
  },
  {
    path: '/control/lz',
    name: 'control-all',
    component: Main,
    redirect: '/control/lz',
    meta: {icon: 'ios-build', title: '图形监控', beforeCloseName: 'before_close_normal'},
    children: [
      {
        path: '/control/lz',
        name: 'control-lz',
        meta: {
          hideInMenu: false,
          icon: 'ios-build',
          title: '图形-总览'
        },
        component: resolve => require(['@/view/overview/control/components/c_lz.vue'], resolve),
      },
      {
        path: '/control/la',
        name: 'control-la',
        meta: {
          hideInMenu: false,
          icon: 'ios-build',
          title: '图形-LA'
        },
        component: resolve => require(['@/view/overview/control/components/c_la.vue'], resolve),
      },
      {
        path: '/control/lb',
        name: 'control-lb',
        meta: {
          hideInMenu: false,
          icon: 'ios-build',
          title: '图形-LB'
        },
        component: resolve => require(['@/view/overview/control/components/c_lb.vue'], resolve),
      },
      {
        path: '/control/lc',
        name: 'control-lc',
        meta: {
          hideInMenu: false,
          icon: 'ios-build',
          title: '图形-LC'
        },
        component: resolve => require(['@/view/overview/control/components/c_lc.vue'], resolve),
      },
      {
        path: '/control/ld',
        name: 'control-ld',
        meta: {
          hideInMenu: false,
          icon: 'ios-build',
          title: '图形-LD'
        },
        component: resolve => require(['@/view/overview/control/components/c_ld.vue'], resolve),
      },
      {
        path: '/control/le',
        name: 'control-le',
        meta: {
          hideInMenu: false,
          icon: 'ios-build',
          title: '图形-LE'
        },
        component: resolve => require(['@/view/overview/control/components/c_le.vue'], resolve),
      },
      {
        path: '/control/lf',
        name: 'control-lf',
        meta: {
          hideInMenu: false,
          icon: 'ios-build',
          title: '图形-LF'
        },
        component: resolve => require(['@/view/overview/control/components/c_lf.vue'], resolve),
      },
      {
        path: '/control/lg',
        name: 'control-lg',
        meta: {
          hideInMenu: false,
          icon: 'ios-build',
          title: '图形-LG'
        },
        component: resolve => require(['@/view/overview/control/components/c_lg.vue'], resolve),
      },
    ]
  },
  {
    path: '/desktop',
    name: '数据管理',
    component: Main,
    meta: {icon: 'ios-keypad', title: '数据管理', beforeCloseName: 'before_close_normal'},
    children: [
      {
        path: '/desktop',
        name: 'desktop',
        meta: {icon: 'ios-browsers', title: '桌面管理', beforeCloseName: 'before_close_normal'},
        // component: () => import('@/view/overview/desktop.vue')
        component: resolve => require(['@/view/overview/desktop.vue'], resolve)
      },
      {
        path: '/betting',
        name: 'betting',
        meta: {icon: 'ios-analytics', title: '投注数据', beforeCloseName: 'before_close_normal'},
        // component: () => import('@/view/overview/betting.vue')
        component: resolve => require(['@/view/overview/betting.vue'], resolve)
      }
    ]
  }
  // {
  //   path: '/components',
  //   name: 'components',
  //   meta: {icon: 'logo-buffer', title: '组件'},
  //   component: Main,
  //   children: [
  //     {
  //       path: 'count_to_page',
  //       name: 'count_to_page',
  //       meta: {
  //         icon: 'md-trending-up',
  //         title: '数字渐变'
  //       },
  //       component: () => import('@/view/components/count-to/count-to.vue')
  //     },
  //     {
  //       path: 'drag_list_page',
  //       name: 'drag_list_page',
  //       meta: {
  //         icon: 'ios-infinite',
  //         title: '拖拽列表'
  //       },
  //       component: () => import('@/view/components/drag-list/drag-list.vue')
  //     },
  //     {
  //       path: 'tables_page',
  //       name: 'tables_page',
  //       meta: {
  //         icon: 'md-grid',
  //         title: '多功能表格'
  //       },
  //       component: () => import('@/view/components/tables/tables.vue')
  //     },
  //     {
  //       path: 'split_pane_page',
  //       name: 'split_pane_page',
  //       meta: {
  //         icon: 'md-pause',
  //         title: '分割窗口'
  //       },
  //       component: () => import('@/view/components/split-pane/split-pane.vue')
  //     },
  //     {
  //       path: 'markdown_page',
  //       name: 'markdown_page',
  //       meta: {
  //         icon: 'logo-markdown',
  //         title: 'Markdown编辑器'
  //       },
  //       component: () => import('@/view/components/markdown/markdown.vue')
  //     },
  //     {
  //       path: 'editor_page',
  //       name: 'editor_page',
  //       meta: {
  //         icon: 'ios-create',
  //         title: '富文本编辑器'
  //       },
  //       component: () => import('@/view/components/editor/editor.vue')
  //     },
  //     {
  //       path: 'icons_page',
  //       name: 'icons_page',
  //       meta: {
  //         icon: '_bear',
  //         title: '自定义图标'
  //       },
  //       component: () => import('@/view/components/icons/icons.vue')
  //     }
  //   ]
  // },
  // {
  //   path: '/update',
  //   name: 'update',
  //   meta: {
  //     icon: 'md-cloud-upload',
  //     title: '数据上传'
  //   },
  //   component: Main,
  //   children: [
  //     {
  //       path: 'update_table_page',
  //       name: 'update_table_page',
  //       meta: {
  //         icon: 'ios-document',
  //         title: '上传Csv'
  //       },
  //       component: () => import('@/view/update/update-table.vue')
  //     },
  //     {
  //       path: 'update_paste_page',
  //       name: 'update_paste_page',
  //       meta: {
  //         icon: 'md-clipboard',
  //         title: '粘贴表格数据'
  //       },
  //       component: () => import('@/view/update/update-paste.vue')
  //     }
  //   ]
  // },
  // {
  //   path: '/excel',
  //   name: 'excel',
  //   meta: {
  //     icon: 'ios-stats',
  //     title: '导入导出'
  //   },
  //   component: Main,
  //   children: [
  //     {
  //       path: 'upload-excel',
  //       name: 'upload-excel',
  //       meta: {
  //         icon: 'md-add',
  //         title: '导入EXCEL'
  //       },
  //       component: () => import('@/view/excel/upload-excel.vue')
  //     },
  //     {
  //       path: 'export-excel',
  //       name: 'export-excel',
  //       meta: {
  //         icon: 'md-download',
  //         title: '导出EXCEL'
  //       },
  //       component: () => import('@/view/excel/export-excel.vue')
  //     }
  //   ]
  // },
  // {
  //   path: '/tools_methods',
  //   name: 'tools_methods',
  //   meta: {
  //     hide: true
  //   },
  //   component: Main,
  //   children: [
  //     {
  //       path: 'tools_methods_page',
  //       name: 'tools_methods_page',
  //       meta: {
  //         icon: 'ios-hammer',
  //         title: '工具方法',
  //         beforeCloseName: 'before_close_normal'
  //       },
  //       component: () => import('@/view/tools-methods/tools-methods.vue')
  //     }
  //   ]
  // },
  // {
  //   path: '/directive',
  //   name: 'directive',
  //   meta: {
  //     hide: true
  //   },
  //   component: Main,
  //   children: [
  //     {
  //       path: 'directive_page',
  //       name: 'directive_page',
  //       meta: {
  //         icon: 'ios-navigate',
  //         title: '指令'
  //       },
  //       component: () => import('@/view/directive/directive.vue')
  //     }
  //   ]
  // },
  // {
  //   path: '/multilevel',
  //   name: 'multilevel',
  //   meta: {
  //     icon: 'md-menu',
  //     title: '多级菜单'
  //   },
  //   component: Main,
  //   children: [
  //     {
  //       path: 'level_2_1',
  //       name: 'level_2_1',
  //       meta: {
  //         icon: 'md-funnel',
  //         title: '二级-1'
  //       },
  //       component: () => import('@/view/multilevel/level-2-1.vue')
  //     },
  //     {
  //       path: 'level_2_2',
  //       name: 'level_2_2',
  //       meta: {
  //         access: ['super_admin'],
  //         icon: 'md-funnel',
  //         showAlways: true,
  //         title: '二级-2'
  //       },
  //       component: parentView,
  //       children: [
  //         {
  //           path: 'level_2_2_1',
  //           name: 'level_2_2_1',
  //           meta: {
  //             icon: 'md-funnel',
  //             title: '三级'
  //           },
  //           component: () => import('@/view/multilevel/level-2-2/level-3-1.vue')
  //         }
  //       ]
  //     },
  //     {
  //       path: 'level_2_3',
  //       name: 'level_2_3',
  //       meta: {
  //         icon: 'md-funnel',
  //         title: '二级-3'
  //       },
  //       component: () => import('@/view/multilevel/level-2-3.vue')
  //     },
  //   ]
  // },
  // {
  //   path: '/argu',
  //   name: 'argu',
  //   meta: {
  //     hideInMenu: true
  //   },
  //   component: Main,
  //   children: [
  //     {
  //       path: 'params/:id',
  //       name: 'params',
  //       meta: {
  //         icon: 'md-flower',
  //         title: route => `动态路由-${route.params.id}`,
  //         notCache: true,
  //         beforeCloseName: 'before_close_normal'
  //       },
  //       component: () => import('@/view/argu-page/params.vue')
  //     },
  //     {
  //       path: 'query',
  //       name: 'query',
  //       meta: {
  //         icon: 'md-flower',
  //         title: route => `带参路由-${route.query.id}`,
  //         notCache: true
  //       },
  //       component: () => import('@/view/argu-page/query.vue')
  //     }
  //   ]
  // },
  // {
  //   path: '/401',
  //   name: 'error_401',
  //   meta: {
  //     hideInMenu: true
  //   },
  //   component: () => import('@/view/error-page/401.vue')
  // },
  // {
  //   path: '/500',
  //   name: 'error_500',
  //   meta: {
  //     hideInMenu: true
  //   },
  //   component: () => import('@/view/error-page/500.vue')
  // },
  // {
  //   path: '*',
  //   name: 'error_404',
  //   meta: {
  //     hideInMenu: true
  //   },
  //   component: () => import('@/view/error-page/404.vue')
  // }
]
