<template>
	<div class="register">
		<el-form ref="registerFormRef" :model="registerForm" :rules="registerRules" class="register-form">
		  <h2 class="title">用户注册</h2>
		  <el-form-item prop="account" label="账号" :label-width="labelWidth">
			<el-input v-model="registerForm.account" clearable/>
		  </el-form-item>
		  <el-form-item prop="password" label="密码" :label-width="labelWidth">
			<el-input type="password" v-model="registerForm.password" clearable show-password />
		  </el-form-item>
		  <el-form-item prop="rePassword" label="确认密码" :label-width="labelWidth">
		  		<el-input type="password" v-model="registerForm.rePassword" clearable show-password />
		  </el-form-item>
		  <el-form-item prop="name" label="姓名" :label-width="labelWidth">
		  		<el-input v-model="registerForm.name" clearable />
		  </el-form-item>
		  <el-form-item prop="mail" label="邮箱" :label-width="labelWidth">
		  		<el-input v-model="registerForm.mail" clearable />
		  </el-form-item>
		  <el-form-item style="width: 100%;">
			<el-button type="primary" @click="doRegister" style="width: 100%;">注册</el-button>
		  </el-form-item>
		</el-form>
	</div>
</template>

<script setup>
import { getCurrentInstance, reactive, ref } from "vue"
import { useRouter } from "vue-router"
const { proxy } = getCurrentInstance()
//表单相关
const registerFormRef = ref(null);
const registerForm = reactive({});
let labelWidth = 80;

const doRegister = () => {
  registerFormRef.value.validate(async (valid) => {
    if (!valid) {
      return
    }
    let params = Object.assign({}, registerForm);
    let result = await proxy.axios.post('register', params)
    if (!result) {
		getCode()
		return
    }
    proxy.msg.success("注册成功")
    proxy.router.push('login')
  });
};

const registerRules = {
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
  rePassword: [
    {
      required: true,
      message: "请输入确认密码",
    },
  ],
  name: [
    {
      required: true,
      message: "请输入姓名",
    },
  ],
  mail: [
    {
      required: true,
      message: "请输入邮箱",
    },
  ]
}
</script>

<style lang="scss" scoped>
.register {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.register-form {
  border-radius: 6px;
  background: #ffffff;
  padding: 30px 30px 10px 30px;
  width: 500px;
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
.register-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.register-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
	cursor: pointer;
	vertical-align: middle;
  }
}
.register-code-img {
  height: 38px;
}
</style>