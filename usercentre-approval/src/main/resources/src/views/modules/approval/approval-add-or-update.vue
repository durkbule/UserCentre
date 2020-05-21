<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="申请提交人id" prop="applicantId">
      <el-input v-model="dataForm.applicantId" placeholder="申请提交人id"></el-input>
    </el-form-item>
    <el-form-item label="申请人" prop="applicantName">
      <el-input v-model="dataForm.applicantName" placeholder="申请人"></el-input>
    </el-form-item>
    <el-form-item label="应用名" prop="appName">
      <el-input v-model="dataForm.appName" placeholder="应用名"></el-input>
    </el-form-item>
    <el-form-item label="审核状态[0-未通过，1-通过]" prop="state">
      <el-input v-model="dataForm.state" placeholder="审核状态[0-未通过，1-通过]"></el-input>
    </el-form-item>
    <el-form-item label="审查人id" prop="reviewerId">
      <el-input v-model="dataForm.reviewerId" placeholder="审查人id"></el-input>
    </el-form-item>
    <el-form-item label="审查人" prop="reviewerName">
      <el-input v-model="dataForm.reviewerName" placeholder="审查人"></el-input>
    </el-form-item>
    <el-form-item label="修改时间" prop="modTime">
      <el-input v-model="dataForm.modTime" placeholder="修改时间"></el-input>
    </el-form-item>
    <el-form-item label="添加时间" prop="addTime">
      <el-input v-model="dataForm.addTime" placeholder="添加时间"></el-input>
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
          applicantId: '',
          applicantName: '',
          appName: '',
          state: '',
          reviewerId: '',
          reviewerName: '',
          modTime: '',
          addTime: ''
        },
        dataRule: {
          applicantId: [
            { required: true, message: '申请提交人id不能为空', trigger: 'blur' }
          ],
          applicantName: [
            { required: true, message: '申请人不能为空', trigger: 'blur' }
          ],
          appName: [
            { required: true, message: '应用名不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '审核状态[0-未通过，1-通过]不能为空', trigger: 'blur' }
          ],
          reviewerId: [
            { required: true, message: '审查人id不能为空', trigger: 'blur' }
          ],
          reviewerName: [
            { required: true, message: '审查人不能为空', trigger: 'blur' }
          ],
          modTime: [
            { required: true, message: '修改时间不能为空', trigger: 'blur' }
          ],
          addTime: [
            { required: true, message: '添加时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/approval/approval/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.applicantId = data.approval.applicantId
                this.dataForm.applicantName = data.approval.applicantName
                this.dataForm.appName = data.approval.appName
                this.dataForm.state = data.approval.state
                this.dataForm.reviewerId = data.approval.reviewerId
                this.dataForm.reviewerName = data.approval.reviewerName
                this.dataForm.modTime = data.approval.modTime
                this.dataForm.addTime = data.approval.addTime
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
              url: this.$http.adornUrl(`/approval/approval/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'applicantId': this.dataForm.applicantId,
                'applicantName': this.dataForm.applicantName,
                'appName': this.dataForm.appName,
                'state': this.dataForm.state,
                'reviewerId': this.dataForm.reviewerId,
                'reviewerName': this.dataForm.reviewerName,
                'modTime': this.dataForm.modTime,
                'addTime': this.dataForm.addTime
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
