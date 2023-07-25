import * as path from "path";
import {createRouter} from "vue-router";

const routes = [
  {
    path: '/',
    name:'login',
    component: () => import('layouts/LoginLayout.vue'),
  },
  {
    path: '/register',
    component: () => import('layouts/RegisterLayout.vue'),
    children: [
      // { path: '', component: () => import('pages/IndexPage.vue') }
    ]
  },
  {
    path: '/todo',
    component: () => import('layouts/TodoLayout.vue'),
    children: [
      // { path: '', component: () => import('pages/IndexPage.vue') }
    ]
  },

  {
    path: '/error',
    name: 'error',
    component: () => import('pages/ErrorNotFound.vue'),
    children: [
      // { path: '', component: () => import('pages/IndexPage.vue') }
    ]
  },



  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]


export default routes
