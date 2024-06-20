import { createRouter, createWebHistory } from 'vue-router'
import cookies from 'vue-cookies'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
	{
	  path: '/login',
	  name: 'login',
	  component: () => import('../views/login.vue'),
	  meta: {
	  	title: '用户登录'
	  }
	},
	{
	  path: '/register',
	  name: 'register',
	  component: () => import('../views/register.vue'),
	  meta: {
	  	title: '用户注册'
	  }
	},
	{
	  path: '/',
	  name: 'index',
	  component: () => import('../views/index.vue'),
	  children: [
		  {
		    path: '/home',
		    name: 'home',
		    component: () => import('../views/home.vue'),
			meta: {
				title: '首页'
			}
		  },
		  {
		    path: '/publish',
		    name: 'publish',
		    component: () => import('../views/publish.vue'),
			meta: {
				title: '帖子发布'
			}
		  }
	  ]
	}
  ]
})

// 路由拦截器
router.beforeEach((to, from, next) => {
	if (to.meta.title) { // 判断是否有标题
		document.title = to.meta.title;
	  } else {
		  document.title = 'bbs论坛管理系统';
	  }
	let user = cookies.get('bbs-web')
	if (to.name === 'login' || to.name === 'register') {
		next();
	} else if (user) {
	  // 如果已经登录
	  next();
	} else {
	  // 否则跳转到登录页
	  next({
		path: '/login'
	  });
	}
});

export default router