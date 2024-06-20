<template>
	<!-- <div class="common-layout"> -->
	  <el-container>
	    <el-header class="header">
			  <el-menu
			      class="el-menu-demo"
			      mode="horizontal"
			      :ellipsis="false"
			      @select="handleSelect"
				  router
				  :default-active="defaultActive"
			    >
			      <el-menu-item index="/home">
			  	  <template #title>
					  <span>bbs论坛系统</span>
				  </template>
			      </el-menu-item>
			      <div class="flex-grow" />

			      <el-sub-menu index="">
			        <template #title>{{userInfo.name}}</template>
			        <el-menu-item index="logout">退出登录</el-menu-item>
			      </el-sub-menu>
			    </el-menu>
		  </el-header>
	      <el-main style="padding: 5%;">
				<router-view></router-view>
			</el-main>
	  </el-container>
	<!-- </div> -->
</template>

<script setup>
import { getCurrentInstance, reactive, ref } from "vue";
import { useRouter } from "vue-router";

const { proxy } = getCurrentInstance();

const userInfo = {
	name: '',
	account: ''
}
const defaultActive = ref('home')
const init = () => {
	let loginUser = proxy.cookies.get("bbs-web");
	userInfo.name = loginUser.name;
	userInfo.account = loginUser.account;
	
	let da = proxy.router.currentRoute.value.path
	if (da) {
		defaultActive.value = da
	}
}

init();

const handleOpen = () => {
	
}
const handleClose = () => {
	
}

const handleSelect = (key, keyPath) => {
	if (key === 'logout') {
		proxy.cookies.remove('bbs-web')
		proxy.axios.get('logout')
		proxy.router.push('/login')
	}
}

</script>

<style scoped>
	.flex-grow {
	  flex-grow: 1;
	}
	.item {
	  vertical-align: text-top;
	}
	.header{
		position: fixed;
		left: 0;
		right: 0;
		z-index: 10;
	}
</style>