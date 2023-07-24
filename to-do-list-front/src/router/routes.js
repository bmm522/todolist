
const routes = [

  {
    path: '/',
    component: () => import('layouts/LoginLayout.vue'),
    children: [
      // { path: '', component: () => import('pages/IndexPage.vue') }
    ]
  },
  {
    path: '/register',
    component: () => import('layouts/RegisterLayout.vue'),
    children: [
      // { path: '', component: () => import('pages/IndexPage.vue') }
    ]
  },
  {
    path: '/main',
    component: () => import('layouts/AppLayout.vue'),
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
