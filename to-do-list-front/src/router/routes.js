import * as path from "path";
import {createRouter} from "vue-router";

const routes = [
  {
    path: '/',
    component: () => import('layouts/LoginLayout.vue'),
  },
  {
    path: '/login',
    name:'login',
    component: () => import('layouts/LoginLayout.vue'),
  },
  {
    path: '/register',
    name:'register',
    component: () => import('layouts/RegisterLayout.vue'),
    children: [
      // { path: '', component: () => import('pages/IndexPage.vue') }
    ]
  },
  {
    path: '/todo',
    name:'todo',
    component: () => import('layouts/TodoLayout.vue'),
    children: [
      { path: '/todo/list', name:'todo-list', component: () => import('components/body/TodoBodyComponent.vue') },
      { path: '/todo/edit', name:'edit-user-info',component: () => import('components/body/UserInfoEditComponent.vue') }
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
