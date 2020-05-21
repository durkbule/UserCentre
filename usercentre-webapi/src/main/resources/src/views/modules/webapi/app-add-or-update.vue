<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="应用名称" prop="appName">
      <el-input v-model="dataForm.appName" placeholder="应用名称"></el-input>
    </el-form-item>
    <el-form-item label="审查id" prop="approvalId">
      <el-input v-model="dataForm.approvalId" placeholder="审查id"></el-input>
    </el-form-item>
    <el-form-item label="应用申请人id" prop="applicantId">
      <el-input v-model="dataForm.applicantId" placeholder="应用申请人id"></el-input>
    </el-form-item>
    <el-form-item label="应用申请人用户名" prop="applicantName">
      <el-input v-model="dataForm.applicantName" placeholder="应用申请人用户名"></el-input>
    </el-form-item>
    <el-form-item label="备注" prop="remark">
      <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
    </el-form-item>
    <el-form-item label="回掉地址" prop="redirectRul">
      <el-input v-model="dataForm.redirectRul" placeholder="回掉地址"></el-input>
    </el-form-item>
    <el-form-item label="是否可用" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="是否可用"></el-input>
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
          appName: '',
          approvalId: '',
          applicantId: '',
          applicantName: '',
          remark: '',
          redirectRul: '',
          isDelete: ''
        },
        dataRule: {
          appName: [
            { required: true, message: '应用名称不能为空', trigger: 'blur' }
          ],
          approvalId: [
            { required: true, message: '审查id不能为空', trigger: 'blur' }
          ],
          applicantId: [
            { required: true, message: '应用申请人id不能为空', trigger: 'blur' }
          ],
          applicantName: [
            { required: true, message: '应用申请人用户名不能为空', trigger: 'blur' }
          ],
          remark: [
            { required: true, message: '备注不能为空', trigger: 'blur' }
          ],
          redirectRul: [
            { required: true, message: '回掉地址不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '是否可用不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/webapi/app/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.appName = data.app.appName
                this.dataForm.approvalId = data.app.approvalId
                this.dataForm.applicantId = data.app.applicantId
                this.dataForm.applicantName = data.app.applicantName
                this.dataForm.remark = data.app.remark
                this.dataForm.redirectRul = data.app.redirectRul
                this.dataForm.isDelete = data.app.isDelete
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
              url: this.$http.adornUrl(`/webapi/app/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'appName': this.dataForm.appName,
                'approvalId': this.dataForm.approvalId,
                'applicantId': this.dataForm.applicantId,
                'applicantName': this.dataForm.applicantName,
                'remark': this.dataForm.remark,
                'redirectRul': this.dataForm.redirectRul,
                'isDelete': this.dataForm.isDelete
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
