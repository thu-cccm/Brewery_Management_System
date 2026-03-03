import auth from '@/plugins/auth'
import router, { constantRoutes, dynamicRoutes } from '@/router'
import { getRouters } from '@/api/menu'
import Layout from '@/layout/index'
import ParentView from '@/components/ParentView'
import InnerLink from '@/layout/components/InnerLink'

// 使用require.context预加载所有views目录下的组件
// 这样webpack就能在编译时知道所有组件，避免运行时找不到模块
// 使用 'lazy' 模式进行代码分割，提高性能
const viewsContext = require.context('@/views', true, /\.vue$/, 'lazy')

// 创建路径映射表：将标准路径映射到require.context的key
const viewsMap = {}
viewsContext.keys().forEach(key => {
  // require.context返回的key格式：'./system/post/index.vue'
  // 转换为标准格式：'system/post/index'
  const normalizedKey = key.replace(/^\.\//, '').replace(/\.vue$/, '')
  viewsMap[normalizedKey] = key
  
  // 同时支持不带index的路径：system/post -> system/post/index
  if (normalizedKey.endsWith('/index')) {
    const withoutIndex = normalizedKey.replace(/\/index$/, '')
    if (!viewsMap[withoutIndex]) {
      viewsMap[withoutIndex] = key
    }
  }
})

// 调试：检查用户管理组件是否被正确扫描
if (process.env.NODE_ENV === 'development') {
  const userKeys = Object.keys(viewsMap).filter(k => k.includes('user'))
  if (userKeys.length > 0) {
    console.log('User components found in viewsMap:', userKeys)
  } else {
    console.warn('No user components found in viewsMap!')
  }
}

const permission = {
  state: {
    routes: [],
    addRoutes: [],
    defaultRoutes: [],
    topbarRouters: [],
    sidebarRouters: []
  },
  mutations: {
    SET_ROUTES: (state, routes) => {
      state.addRoutes = routes
      state.routes = constantRoutes.concat(routes)
    },
    SET_DEFAULT_ROUTES: (state, routes) => {
      state.defaultRoutes = constantRoutes.concat(routes)
    },
    SET_TOPBAR_ROUTES: (state, routes) => {
      state.topbarRouters = routes
    },
    SET_SIDEBAR_ROUTERS: (state, routes) => {
      state.sidebarRouters = routes
    },
  },
  actions: {
    // 生成路由
    GenerateRoutes({ commit }) {
      return new Promise(resolve => {
        // 向后端请求路由数据
        getRouters().then(res => {
          const sdata = JSON.parse(JSON.stringify(res.data))
          const rdata = JSON.parse(JSON.stringify(res.data))
          const sidebarRoutes = filterAsyncRouter(sdata)
          const rewriteRoutes = filterAsyncRouter(rdata, false, true)
          const asyncRoutes = filterDynamicRoutes(dynamicRoutes)
          rewriteRoutes.push({ path: '*', redirect: '/404', hidden: true })
          router.addRoutes(asyncRoutes)
          commit('SET_ROUTES', rewriteRoutes)
          commit('SET_SIDEBAR_ROUTERS', constantRoutes.concat(sidebarRoutes))
          commit('SET_DEFAULT_ROUTES', sidebarRoutes)
          commit('SET_TOPBAR_ROUTES', sidebarRoutes)
          resolve(rewriteRoutes)
        })
      })
    }
  }
}

// 遍历后台传来的路由字符串，转换为组件对象
function filterAsyncRouter(asyncRouterMap, lastRouter = false, type = false) {
  return asyncRouterMap.filter(route => {
    if (type && route.children) {
      route.children = filterChildren(route.children)
    }
    if (route.component) {
      // Layout ParentView 组件特殊处理
      if (route.component === 'Layout') {
        route.component = Layout
      } else if (route.component === 'ParentView') {
        route.component = ParentView
      } else if (route.component === 'InnerLink') {
        route.component = InnerLink
      } else {
        route.component = loadView(route.component)
      }
    }
    if (route.children != null && route.children && route.children.length) {
      route.children = filterAsyncRouter(route.children, route, type)
    } else {
      delete route['children']
      delete route['redirect']
    }
    return true
  })
}

function filterChildren(childrenMap, lastRouter = false) {
  var children = []
  childrenMap.forEach(el => {
    el.path = lastRouter ? lastRouter.path + '/' + el.path : el.path
    if (el.children && el.children.length && el.component === 'ParentView') {
      children = children.concat(filterChildren(el.children, el))
    } else {
      children.push(el)
    }
  })
  return children
}

// 动态路由遍历，验证是否具备权限
export function filterDynamicRoutes(routes) {
  const res = []
  routes.forEach(route => {
    if (route.permissions) {
      if (auth.hasPermiOr(route.permissions)) {
        res.push(route)
      }
    } else if (route.roles) {
      if (auth.hasRoleOr(route.roles)) {
        res.push(route)
      }
    }
  })
  return res
}

// 组件加载缓存
const componentCache = {}

// 路径映射表：将后端返回的component路径映射到实际的文件路径
const componentPathMap = {
  'system/post/index': 'system/post/index',
  'system/post': 'system/post/index',
  'system/menu/index': 'system/menu/index',
  'system/menu': 'system/menu/index',
  'system/dept/index': 'system/dept/index',
  'system/dept': 'system/dept/index',
  'system/role/index': 'system/role/index',
  'system/role': 'system/role/index',
  'system/user/index': 'system/user/index',
  'system/user': 'system/user/index',
  'system/notice/index': 'system/notice/index',
  'system/notice': 'system/notice/index',
  'system/log/operlog': 'monitor/operlog/index',
  'system/log/logininfor': 'monitor/logininfor/index'
}

export const loadView = (view) => {
  // 缓存检查
  if (componentCache[view]) {
    return componentCache[view]
  }
  
  // 规范化路径：使用映射表或直接使用view
  let normalizedView = componentPathMap[view] || view
  
  // 移除可能的.vue扩展名
  normalizedView = normalizedView.replace(/\.vue$/, '')
  
  // 确保路径不以/开头
  if (normalizedView.startsWith('/')) {
    normalizedView = normalizedView.substring(1)
  }
  
  // 从viewsMap中查找对应的require.context key
  // 尝试多种匹配方式
  let contextKey = viewsMap[normalizedView] || 
                   viewsMap[normalizedView + '/index'] ||
                   viewsMap[normalizedView.replace(/\/$/, '')] ||
                   viewsMap[normalizedView.replace(/\/$/, '') + '/index']
  
  // 调试信息：只在开发环境且是用户管理相关路径时输出
  if (process.env.NODE_ENV === 'development' && normalizedView.includes('user')) {
    console.log(`[loadView] Loading component:`, {
      originalView: view,
      normalizedView: normalizedView,
      contextKeyFound: !!contextKey,
      contextKey: contextKey,
      viewsMapHasKey: viewsMap.hasOwnProperty(normalizedView),
      viewsMapHasKeyWithIndex: viewsMap.hasOwnProperty(normalizedView + '/index')
    })
  }
  
  if (contextKey) {
    // 使用require.context加载组件（webpack已预知所有路径）
    // require.context在lazy模式下返回一个函数，调用后返回Promise
    const component = () => {
      try {
        // viewsContext(contextKey) 返回一个Promise
        // 注意：require.context在lazy模式下，contextKey应该是相对路径（如'./system/user/index.vue'）
        return viewsContext(contextKey)
          .then(module => {
            // 确保返回默认导出，Vue组件通常在default中
            const component = module.default || module.__esModule && module.default || module
            if (!component) {
              console.error(`Component module has no default export:`, module)
              throw new Error(`Component ${normalizedView} has no default export`)
            }
            return component
          })
          .catch(error => {
            // 如果require.context失败，尝试动态import
            console.error(`Failed to load via require.context: ${normalizedView}`, error)
            console.error('Context key:', contextKey)
            return import(/* webpackChunkName: "[request]" */ `@/views/${normalizedView}`)
              .then(module => module.default || module)
              .catch(() => import('@/views/error/404').then(m => m.default || m))
          })
      } catch (error) {
        // 如果调用viewsContext时出错，使用动态import
        console.error(`Error calling viewsContext for ${normalizedView}:`, error)
        return import(/* webpackChunkName: "[request]" */ `@/views/${normalizedView}`)
          .then(module => module.default || module)
          .catch(() => import('@/views/error/404').then(m => m.default || m))
      }
    }
    componentCache[view] = component
    return component
  }
  
  // 如果找不到，使用动态import作为后备方案
  // 对于用户管理等标准页面，直接使用动态import应该可以工作
  const component = () => {
    return import(/* webpackChunkName: "[request]" */ `@/views/${normalizedView}`)
      .catch(error => {
        // 如果加载失败，返回404页面
        console.error(`Failed to load component: @/views/${normalizedView}`, error)
        console.error('Original view path:', view)
        console.error('Normalized view:', normalizedView)
        console.error('Available viewsMap keys:', Object.keys(viewsMap).filter(k => k.includes('user') || k.includes(normalizedView.split('/')[0])))
        return import('@/views/error/404')
      })
  }
  
  componentCache[view] = component
  return component
}

export default permission
