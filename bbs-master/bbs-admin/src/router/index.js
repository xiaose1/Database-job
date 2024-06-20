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
	  	title: '管理员登录'
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
		    path: '/user',
		    name: 'user',
		    component: () => import('../views/user.vue'),
			meta: {
				title: '用户管理'
			}
		  },
		  {
		    path: '/forum',
		    name: 'forum',
		    component: () => import('../views/forum.vue'),
			meta: {
				title: '帖子管理'
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
	let user = cookies.get('bbs-admin')
	if (to.name === 'login') {
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