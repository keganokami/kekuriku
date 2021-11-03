import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _4d3ef9c4 = () => interopDefault(import('../pages/login.vue' /* webpackChunkName: "pages/login" */))
const _b20ba866 = () => interopDefault(import('../pages/signup.vue' /* webpackChunkName: "pages/signup" */))
const _27c7f622 = () => interopDefault(import('../pages/auth/dashboard.vue' /* webpackChunkName: "pages/auth/dashboard" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/login",
    component: _4d3ef9c4,
    name: "login"
  }, {
    path: "/signup",
    component: _b20ba866,
    name: "signup"
  }, {
    path: "/auth/dashboard",
    component: _27c7f622,
    name: "auth-dashboard"
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
