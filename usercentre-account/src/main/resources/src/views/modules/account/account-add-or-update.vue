<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户名" prop="userName">
      <el-input v-model="dataForm.userName" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="addTime">
      <el-input v-model="dataForm.addTime" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="电话号码" prop="tel">
      <el-input v-model="dataForm.tel" placeholder="电话号码"></el-input>
    </el-form-item>
    <el-form-item label="是否被可用[0-不可用，1-可用]" prop="isDeleted">
      <el-input v-model="dataForm.isDeleted" placeholder="是否被可用[0-不可用，1-可用]"></el-input>
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
          userName: '',
          password: '',
          addTime: '',
          tel: '',
          isDeleted: ''
        },
        dataRule: {
          userName: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          addTime: [
            { required: true, message: '注册时间不能为空', trigger: 'blur' }
          ],
          tel: [
            { required: true, message: '电话号码不能为空', trigger: 'blur' }
          ],
          isDeleted: [
            { required: true, message: '是否被可用[0-不可用，1-可用]不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/account/account/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userName = data.account.userName
                this.dataForm.password = data.account.password
                this.dataForm.addTime = data.account.addTime
                this.dataForm.tel = data.account.tel
                this.dataForm.isDeleted = data.account.isDeleted
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
              url: this.$http.adornUrl(`/account/account/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userName': this.dataForm.userName,
                'password': this.dataForm.password,
                'addTime': this.dataForm.addTime,
                'tel': this.dataForm.tel,
                'isDeleted': this.dataForm.isDeleted
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
