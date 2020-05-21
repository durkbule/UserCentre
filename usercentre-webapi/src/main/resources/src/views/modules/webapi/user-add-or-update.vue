<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户id，标识唯一用户" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户id，标识唯一用户"></el-input>
    </el-form-item>
    <el-form-item label="用户名" prop="userName">
      <el-input v-model="dataForm.userName" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item label="电话号码,标识唯一用户" prop="tel">
      <el-input v-model="dataForm.tel" placeholder="电话号码,标识唯一用户"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="dataForm.password" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="dataForm.email" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="应用信息id" prop="appId">
      <el-input v-model="dataForm.appId" placeholder="应用信息id"></el-input>
    </el-form-item>
    <el-form-item label="应用名" prop="appName">
      <el-input v-model="dataForm.appName" placeholder="应用名"></el-input>
    </el-form-item>
    <el-form-item label="注册时间" prop="addTime">
      <el-input v-model="dataForm.addTime" placeholder="注册时间"></el-input>
    </el-form-item>
    <el-form-item label="盐" prop="salt">
      <el-input v-model="dataForm.salt" placeholder="盐"></el-input>
    </el-form-item>
    <el-form-item label="是否可用[0-不可用，1-可用]" prop="isDeleted">
      <el-input v-model="dataForm.isDeleted" placeholder="是否可用[0-不可用，1-可用]"></el-input>
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
          userName: '',
          tel: '',
          password: '',
          email: '',
          appId: '',
          appName: '',
          addTime: '',
          salt: '',
          isDeleted: ''
        },
        dataRule: {
          userId: [
            { required: true, message: '用户id，标识唯一用户不能为空', trigger: 'blur' }
          ],
          userName: [
            { required: true, message: '用户名不能为空', trigger: 'blur' }
          ],
          tel: [
            { required: true, message: '电话号码,标识唯一用户不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '密码不能为空', trigger: 'blur' }
          ],
          email: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          appId: [
            { required: true, message: '应用信息id不能为空', trigger: 'blur' }
          ],
          appName: [
            { required: true, message: '应用名不能为空', trigger: 'blur' }
          ],
          addTime: [
            { required: true, message: '注册时间不能为空', trigger: 'blur' }
          ],
          salt: [
            { required: true, message: '盐不能为空', trigger: 'blur' }
          ],
          isDeleted: [
            { required: true, message: '是否可用[0-不可用，1-可用]不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/webapi/user/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userId = data.user.userId
                this.dataForm.userName = data.user.userName
                this.dataForm.tel = data.user.tel
                this.dataForm.password = data.user.password
                this.dataForm.email = data.user.email
                this.dataForm.appId = data.user.appId
                this.dataForm.appName = data.user.appName
                this.dataForm.addTime = data.user.addTime
                this.dataForm.salt = data.user.salt
                this.dataForm.isDeleted = data.user.isDeleted
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
              url: this.$http.adornUrl(`/webapi/user/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userId': this.dataForm.userId,
                'userName': this.dataForm.userName,
                'tel': this.dataForm.tel,
                'password': this.dataForm.password,
                'email': this.dataForm.email,
                'appId': this.dataForm.appId,
                'appName': this.dataForm.appName,
                'addTime': this.dataForm.addTime,
                'salt': this.dataForm.salt,
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
