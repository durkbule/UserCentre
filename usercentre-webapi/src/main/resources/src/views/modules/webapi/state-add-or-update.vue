<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="手机号码" prop="tel">
      <el-input v-model="dataForm.tel" placeholder="手机号码"></el-input>
    </el-form-item>
    <el-form-item label="应用信息id" prop="appId">
      <el-input v-model="dataForm.appId" placeholder="应用信息id"></el-input>
    </el-form-item>
    <el-form-item label="token" prop="token">
      <el-input v-model="dataForm.token" placeholder="token"></el-input>
    </el-form-item>
    <el-form-item label="是否可用[0-不可用，1-可用]" prop="isDeleted">
      <el-input v-model="dataForm.isDeleted" placeholder="是否可用[0-不可用，1-可用]"></el-input>
    </el-form-item>
    <el-form-item label="" prop="expireTime">
      <el-input v-model="dataForm.expireTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userId: '',
          tel: '',
          appId: '',
          token: '',
          isDeleted: '',
          expireTime: '',
          updateTime: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],
          tel: [
            { required: true, message: '手机号码不能为空', trigger: 'blur' }
          ],
          appId: [
            { required: true, message: '应用信息id不能为空', trigger: 'blur' }
          ],
          token: [
            { required: true, message: 'token不能为空', trigger: 'blur' }
          ],
          isDeleted: [
            { required: true, message: '是否可用[0-不可用，1-可用]不能为空', trigger: 'blur' }
          ],
          expireTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/webapi/state/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.state.userId
                this.dataForm.tel = data.state.tel
                this.dataForm.appId = data.state.appId
                this.dataForm.token = data.state.token
                this.dataForm.isDeleted = data.state.isDeleted
                this.dataForm.expireTime = data.state.expireTime
                this.dataForm.updateTime = data.state.updateTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/webapi/state/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'tel': this.dataForm.tel,
                'appId': this.dataForm.appId,
                'token': this.dataForm.token,
                'isDeleted': this.dataForm.isDeleted,
                'expireTime': this.dataForm.expireTime,
                'updateTime': this.dataForm.updateTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
