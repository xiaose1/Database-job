<template>
	<div>
		<el-form :model="queryForm" label-width="60px" :inline="true">
			<el-form-item label="账号">
						<el-input v-model="queryForm.account" clearable />
			</el-form-item>
		  <el-form-item>
			<el-button type="primary" @click="init()">查询</el-button>
		  </el-form-item>
		</el-form>
	</div>
	
	<div style="margin-top: 1%;">
	<el-table :data="formattedTableData" style="width: 100%;" stripe border highlight-current-row size="large">
	  <el-table-column prop="account" label="账号"  />
	  <el-table-column prop="name" label="姓名"  />
	  <el-table-column prop="mail" label="邮箱"  />
	  <el-table-column prop="createTime" label="注册时间"  />

	  <el-table-column label="操作" >
	    <template #default="scope">
	  	<el-button size="small" @click="handleDetail(scope.$index, scope.row)">详情</el-button>
	    </template>
	  </el-table-column>
	</el-table>
	<el-pagination background layout="total, sizes, prev, pager, next" style="margin-top: 2%;"
		:page-sizes="[10, 20, 50]"
		@size-change="init"
		@current-change="init"
		v-model:page-size="queryForm.pageSize"
		v-model:current-page="queryForm.page"
		:total="totalData"
	/>
	</div>

<el-dialog v-model="detailDialog" title="用户详情">
  <el-form :model="detailForm">
	<el-form-item label="账号" prop="account" :label-width="formLabelWidth">
	  <el-input v-model="detailForm.account" disabled/>
	</el-form-item>
	<el-form-item label="姓名" prop="name" :label-width="formLabelWidth">
	  <el-input v-model="detailForm.name" disabled/>
	</el-form-item>

	<el-form-item prop="head" label="头像" :label-width="formLabelWidth">
		<el-image style="height: 200px;" :src="imageBaseUrl + detailForm.head" fit="fill" :preview-src-list="[imageBaseUrl + detailForm.head]"/>
	</el-form-item>
	<el-form-item label="手机号码" prop="phoneNumber" :label-width="formLabelWidth">
	  <el-input v-model="detailForm.phoneNumber" disabled/>
	</el-form-item>
	<el-form-item label="邮箱" prop="mail" :label-width="formLabelWidth">
	  <el-input v-model="detailForm.mail" disabled/>
	</el-form-item>
	<el-form-item label="注册时间" prop="createTime" :label-width="formLabelWidth">
	  <el-input v-model="detailForm.createTime" disabled :formatter="formatterTime1"/>
	</el-form-item>
	<el-form-item label="修改时间" prop="updateTime" :label-width="formLabelWidth">
	  <el-input v-model="detailForm.updateTime" disabled :formatter="formatterTime1"/>
	</el-form-item>
  </el-form>
</el-dialog>

</template>

<script setup>
import {computed, getCurrentInstance, reactive, ref} from "vue";
import { useRouter } from "vue-router";
import { imageBaseUrl } from "../global/index.js"
const { proxy } = getCurrentInstance()

const queryForm = reactive({})
const totalData = ref(0)

//详情
const detailDialog = ref(false)
const detailForm = reactive({})

const formLabelWidth = ref(80);


let tableData = ref([]);
const formattedTableData = computed(() => {
  return tableData.value.map(item => ({
    ...item,
    // 在这里添加处理发布时间的逻辑，去除 'T' 字符
    createTime: item.createTime.replace('T', ' ')
  }));
});

const init = async () => {
	let params = Object.assign({}, queryForm);
	let result = await proxy.axios.post('user/list', params)
	if (!result) {
	  return;
	}
	tableData.value = result.list;
	totalData.value = result.total;
}
init();

const handleDetail = (idx, rowData) => {
	Object.assign(detailForm, rowData)
	detailDialog.value = true
}

</script>

<style>
</style>