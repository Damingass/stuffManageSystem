import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../views/login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/index',
    name:'login',
    component: login
  },
  {
    path: '/',
    name: 'login',
    component: login
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/about.vue')
  },
  {
    path: '/user',
    name: 'User',
    component: () => import('../views/user.vue'),
    children:[{
      path:'/course/choose',
      name:'chooseCourse',
      component: () => import('../views/chooseCourse.vue')
    }]
  },
  {
    path: '/403',
    name: '403',
    component: () => import('../views/403.vue')
  },
  {
    path: '/user/test',
    name: 'test',
    component: () => import('../views/test.vue')
  },
  {
    path: '/user/test2',
    name: 'test2',
    component: () => import('../views/test2.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
