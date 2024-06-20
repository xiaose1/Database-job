<template>
	<div class="common-layout">
	  <el-container>
	    <el-header style="padding: 0;">
			  <el-menu
			      :default-active="defaultActive"
			      class="el-menu-demo"
			      mode="horizontal"
			      :ellipsis="false"
			      @select="handleSelect"
				  background-color="#545c64"
				      text-color="#fff"
				      active-text-color="#ffd04b"
			    >
			      <el-menu-item index="home">
			  	  <template #title>
					  <span>bbs论坛管理系统</span>
					</template>
			      </el-menu-item>
			      <div class="flex-grow" />
			      <el-sub-menu index="home">
			        <template #title>{{userInfo.name}}</template>
			        <el-menu-item index="logout">退出登录</el-menu-item>
			      </el-sub-menu>
			    </el-menu>
		  </el-header>
			<el-container>
	      <el-aside width="200px" height="100vh">
			  <el-menu
			      :default-active="defaultActive"
			      class="el-menu-vertical-demo"
			      @open="handleOpen"
			      @close="handleClose"
				  router
				  @select="asideHandleSelect"
			    >
					<el-menu-item index="user">用户管理</el-menu-item>
					<el-menu-item index="forum">帖子管理</el-menu-item>
			    </el-menu>
		  </el-aside>
		  
			<el-main>
				<router-view></router-view>
			</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script setup>
import { getCurrentInstance, reactive, ref } from "vue";
import { useRouter } from "vue-router";

const { proxy } = getCurrentInstance();

const userInfo = {
	name: '',
	account: ''
}

const init = () => {
	let loginUser = proxy.cookies.get("bbs-admin")
	userInfo.name = loginUser.name;
	userInfo.account = loginUser.account;
}

init();

const handleOpen = () => {
	
}
const handleClose = () => {
	
}
const handleSelect = (key, keyPath) => {
	defaultActive.value = key
	sessionStorage.setItem('default-active', key)
	if (key === 'logout') {
		proxy.cookies.remove('bbs-admin')
		proxy.router.push('/login')
	}
	if (key === 'home') {
		proxy.router.push('/home')
	}
}

const defaultActive = ref('/home')
const asideHandleSelect = (key, keyPath) => {
	defaultActive.value = key
	sessionStorage.setItem('default-active', key)
}
const asideInit = () => {
	let da = proxy.router.currentRoute.value.path
	if (da) {
		defaultActive.value = da
	}
}
asideInit()
</script>

<style scoped>
	.flex-grow {
	  flex-grow: 1;
	}
	.header{
		position: fixed;
		left: 0;
		right: 0;
		z-index: 10;
	}
	.aside-menu{
		position: fixed;
		left: 0;
		right: 0;
		z-index: 10;
	}
</style>