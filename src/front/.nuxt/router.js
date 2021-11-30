import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _b20ba866 = () => interopDefault(import('../pages/signup.vue' /* webpackChunkName: "pages/signup" */))
const _27c7f622 = () => interopDefault(import('../pages/auth/dashboard.vue' /* webpackChunkName: "pages/auth/dashboard" */))
const _11b22a8e = () => interopDefault(import('../pages/auth/compe/list.vue' /* webpackChunkName: "pages/auth/compe/list" */))
const _1921844d = () => interopDefault(import('../pages/auth/compe/_compeId.vue' /* webpackChunkName: "pages/auth/compe/_compeId" */))
const _f8d152a6 = () => interopDefault(import('../pages/index.vue' /* webpackChunkName: "pages/index" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/signup",
    component: _b20ba866,
    name: "signup"
  }, {
    path: "/auth/dashboard",
    component: _27c7f622,
    name: "auth-dashboard"
  }, {
    path: "/auth/compe/list",
    component: _11b22a8e,
    name: "auth-compe-list"
  }, {
    path: "/auth/compe/:compeId?",
    component: _1921844d,
    name: "auth-compe-compeId"
  }, {
    path: "/",
    component: _f8d152a6,
    name: "index"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config._app && config._app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
