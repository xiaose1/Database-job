<template>
  <div>
    <el-form :model="queryForm" label-width="60px" :inline="true">
      <el-form-item label="标题">
        <el-input v-model="queryForm.title" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="init()">查询</el-button>
      </el-form-item>
    </el-form>
  </div>

  <div style="margin-top: 1%;">
    <el-table :data="formattedTableData" style="width: 100%;" stripe border highlight-current-row size="large">
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="content" label="内容" />
      <el-table-column prop="createTime" label="发布时间" />

      <el-table-column label="操作" width="300">
        <template #default="scope">
          <el-button size="small" @click="handleDetail(scope.$index, scope.row)">详情</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          <el-button size="small" @click="handleComment(scope.$index, scope.row)">查看评论</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background layout="total, sizes, prev, pager, next" style="margin-top: 2%;"
                   :page-sizes="[10, 20, 50]" @size-change="init" @current-change="init" v-model:page-size="queryForm.pageSize"
                   v-model:current-page="queryForm.page" :total="totalData" />
  </div>

  <el-dialog v-model="detailDialog" title="帖子详情">
    <el-form :model="detailForm">
      <el-form-item label="标题" prop="title" :label-width="formLabelWidth">
        <el-input v-model="detailForm.title" disabled />
      </el-form-item>
      <el-form-item prop="coverImage" label="图片" :label-width="formLabelWidth">
        <el-image style="height: 200px;" :src="imageBaseUrl + detailForm.coverImage" fit="fill"
                  :preview-src-list="[imageBaseUrl + detailForm.coverImage]" />
      </el-form-item>
      <el-form-item label="内容" prop="content" :label-width="formLabelWidth">
        <el-input type="textarea" rows="15" v-model="detailForm.content" disabled />
      </el-form-item>
      <el-form-item label="发布时间" prop="createTime" :label-width="formLabelWidth">
        <el-input v-model="detailForm.createTime" disabled />
      </el-form-item>

    </el-form>
  </el-dialog>

  <el-dialog v-model="commentDialog" title="帖子评论">
    <el-table :data="commentData" style="width: 100%;" max-height="600" stripe border highlight-current-row
              size="large">
      <el-table-column prop="userName" label="姓名" />
      <el-table-column prop="content" label="评论内容" />
      <el-table-column prop="createTime" label="评论时间" />
    </el-table>
  </el-dialog>

</template>

<script setup>
import {getCurrentInstance, reactive, ref, computed} from "vue"
import {imageBaseUrl} from "../global/index.js"

const {proxy} = getCurrentInstance()

const queryForm = reactive({})
const totalData = ref(0)

// 详情
const detailDialog = ref(false)
const detailForm = reactive({})

const formLabelWidth = ref(80);

let tableData = ref([]);

// 计算属性，处理时间格式
const formattedTableData = computed(() => {
  return tableData.value.map(item => ({
    ...item,
    // 在这里添加处理发布时间的逻辑，去除 'T' 字符
    createTime: item.createTime.replace('T', ' ')
  }));
});

const init = async () => {
  let params = Object.assign({}, queryForm);
  let result = await proxy.axios.post('forum/list', params)
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

const handleDelete = async (idx, rowData) => {
  let result = await proxy.axios.get('forum/delete?id=' + rowData.id)
  if (!result) {
    return
  }
  proxy.msg.success('删除成功')
  init()
}

let commentData = ref([])
let commentDialog = ref(false)
const handleComment = async (idx, rowData) => {
  let forumId = rowData.id
  let result = await proxy.axios.get('forumComment/list?forumId=' + forumId)
  commentData.value = result
  commentDialog.value = true
}

</script>

<style>
</style>
