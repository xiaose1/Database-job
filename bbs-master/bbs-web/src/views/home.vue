<template>
	
<div style="float: right;">
	<el-form :model="queryForm" :inline="true">
	  <el-form-item>
		<el-input v-model="queryForm.title" clearable placeholder="输入标题搜索">
			<template #append>
				<el-button type="primary" @click="forumInit()">查询</el-button>
			</template>
		</el-input>
	  </el-form-item>
	  <el-form-item>
		<el-button type="primary" @click="toMyList">我的发布</el-button>
		<el-button type="primary" @click="toPublish">发布一个</el-button>
	  </el-form-item>
	</el-form>
</div>
<div style="margin-top: 5%;">
	
	<el-row :gutter="20" v-for="list in forumList" :key="list" style="margin-top: 2%;">
		<el-col :span="6" v-for="forum in list" :key="forum">
			<el-card shadow="hover" style="max-height: 250px;" class="box-card" @click="handleDetail(forum)">
				<el-image :src="imageBaseUrl + forum.coverImage" class="" style="width: 100%; height: 150px;" fit="contain"/>
				<div style="padding: 5px">
				  <p style="width: 70%; white-space: nowrap;overflow: hidden; text-overflow: ellipsis;">{{forum.title}}</p>
				  <div class="bottom">
					<time class="time">{{ forum.createTime +'    pm' }}</time>
				  </div>
				</div>
			</el-card>
		</el-col>
	</el-row>
</div>
	
<el-dialog v-model="detailDialog" :title="'标题:&nbsp;&nbsp;&nbsp; ' + detailForm.title" width="70%" style="height: 700px;">
	<div style="width: 48%; float: left; height: 500px;">
		<el-image style="width: 100%;height: 300px;" :src="imageBaseUrl + detailForm.coverImage" fit="contain" :preview-src-list="[imageBaseUrl + detailForm.coverImage]"/>
		<div style="text-indent: 0em; word-break: break-all; max-height: 200px; height: 200px; overflow-y: auto;font-size: 18px;">{{'内容：'+detailForm.content}}</div>
	</div>
	<div style="width: 48%; float: left; height: 500px; min-height: 600px; padding: 0 1%;">
		<div style="height: 400px; overflow-y: auto;" id="comment-list">
			<ul style="list-style: none;">
				<li style="width: 100%;" v-for="item in commentList">
					<p style="font-size: 16px; color: #afa9a9;margin-bottom: 0.1px;">{{item.userName}}  {{new Date(item.createTime).toLocaleString('zh-CN', { timeZone: 'Asia/Shanghai' }).replace(/,/g, '')}}</p>
					<p style="font-size: 16px; text-indent: 1em;margin-top: 11px;">{{item.content}}</p>
				</li>
			</ul>
		</div>
		<el-input v-model="content" v-on:keyup.enter="commentSend" clearable maxlength="50" show-word-limit style="margin-top: 70px;">
			<template #append>
				<el-button type="primary" @click="commentSend">发送</el-button>
			</template>
		</el-input>
	</div>
</el-dialog>
	
</template>

<script setup>
import { getCurrentInstance, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { ElMessage, ElMessageBox } from 'element-plus'
import { imageBaseUrl } from "../global/index.js"
const { proxy } = getCurrentInstance()

let formLabelWidth = ref(80)

let forumList = ref([])
let queryForm = reactive({})
const forumInit = async () => {
	let param = Object.assign({}, queryForm)
	let result = await proxy.axios.post('forum/list', param)
	if (!result) {
	  return;
	}
	//Object.assign(forumList, result)
	forumList.value = result
}
forumInit()

const toMyList = async () => {
	let loginUser = proxy.cookies.get("bbs-web")
	let param = {
		userId: loginUser.id
	}
	let result = await proxy.axios.post('forum/list', param)
	if (!result) {
	  return;
	}
	//Object.assign(forumList, result)
	forumList.value = result
}

const toPublish = () => {
	proxy.router.push('publish')
}

let detailDialog = ref(false)
let detailForm = reactive({})
const handleDetail = (forum) => {
	Object.assign(detailForm, forum)
	
	//加载评论
	commentInit(forum.id)
	
	detailDialog.value = true
}

let commentList = ref([])
const commentInit = async (forumId) => {
	let result = await proxy.axios.get('forumComment/list?forumId=' + forumId)
	if (!result) {
	  return;
	}
	commentList.value = result
}

let content = ref("")
const commentSend = async () => {
	let param = {
		  forumId: detailForm.id,
		  content: content.value
	}
	let result = await proxy.axios.post('forumComment/save', param)
	if (!result) {
		return
	}
	content.value = ""
	commentInit(detailForm.id)
}

</script>

<style scoped>
	.time {
	  font-size: 12px;
	  color: #999;
	}
	
	.bottom {
	  margin-top: 13px;
	  line-height: 12px;
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	}
	
	.button {
	  padding: 0;
	  min-height: auto;
	}
	
	.image {
	  width: 100%;
	  display: block;
	}
</style>