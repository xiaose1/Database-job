<template>
	<div class="login">
		<el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" class="login-form">
		  <h2 class="title">用户登录</h2>
		  <el-form-item prop="account">
			<el-input v-model="loginForm.account" clearable prefix-icon="User" placeholder="账号"/>
		  </el-form-item>
		  <el-form-item prop="password">
			<el-input type="password" v-model="loginForm.password" clearable show-password prefix-icon="Lock" placeholder="密码"/>
		  </el-form-item>
		  <el-form-item prop="code">
				<el-input
				  v-model="loginForm.code"
				  auto-complete="off"
				  placeholder="验证码"
				  style="width: 66%"
				  prefix-icon="CircleCheck"
				  v-on:keyup.enter="doLogin"
				/>
				<div class="login-code">
				  <img :src="codeImage" @click="getCode" class="login-code-img"/>
				</div>
			</el-form-item>
		  <el-form-item style="width: 100%;">
			<el-button type="primary" @click="doLogin" style="width: 100%;">登录</el-button>
        <el-button type="primary" @click="register" style="width: 100%; margin-top: 10px;">注册</el-button>
		  </el-form-item>
		</el-form>
	</div>
</template>

<script setup>
import { getCurrentInstance, reactive, ref } from "vue";
import { useRouter } from "vue-router";
const { proxy } = getCurrentInstance();
//表单相关
const loginFormRef = ref(null);
const loginForm = reactive({});

const doLogin = () => {
  loginFormRef.value.validate(async (valid) => {
    if (!valid) {
      return
    }
    let params = Object.assign({}, loginForm);
    let result = await proxy.axios.post('login', params);
    if (!result) {
		getCode()
		return
    }
    proxy.cookies.set("bbs-web", result, 0);
	sessionStorage.removeItem('default-active')
    proxy.msg.success("登录成功");
    proxy.router.push('home')
  });
};
const register = () => {
  window.location.href = 'http://localhost:8092/register';
};
const codeImage = ref('')
const codeId = ref('')
const getCode = async () => {
	let result = await proxy.axios.get('login-code');
	if (!result) {
	  return;
	}
	codeImage.value = result.image
	codeId.value = result.codeId
	loginForm.codeId = result.codeId
}
getCode()

const loginRules = {
  account: [
    {
      required: true,
      message: "请输入账号",
    },
  ],
  password: [
    {
      required: true,
      message: "请输入密码",
    },
  ],
  code: [
    {
      required: true,
      message: "请输入验证码",
    },
  ]
}
</script>

<style lang="scss" scoped>
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 100%;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  padding: 30px 30px 10px 30px;
  .el-input {
	height: 38px;
	input {
	  height: 38px;
	}
  }
  .input-icon {
	height: 39px;
	width: 14px;
	margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
	cursor: pointer;
	vertical-align: middle;
  }
}
.login-code-img {
  height: 38px;
}
</style>