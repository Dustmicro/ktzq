<%--
  Created by IntelliJ IDEA.
  User: hyf-htp
  Date: 2023/2/27
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%--  <head>--%>
<%--    <title>测试部署是否成功</title>--%>
<%--  </head>--%>
<%--  <body>--%>
<%--  <h1>成功22</h1>--%>
<%--  </body>--%>
<div class="box">
  <div class="main">
    <div class="titlebox">
      <h1>用户登录</h1>
    </div>
    <div class="formbox">
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item prop="userName">
          <div>账号</div><el-input v-model="form.userName" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <div>密码</div><el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <div class="register">
            <a @click="dialogVisible = true" href="#">注册</a>
          </div>
          <div class="forget">
            <a @click="goForget" href="#">忘记密码？</a>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button class="buttonClass" @click="onSubmit('form')" round type="primary" size="medium"
                     center>登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
  <!-- 注册弹出框 -->
  <div>
    <el-dialog title="注册" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
      <el-form ref="registerForm" :model="registerForm" label-width="80px">
        <el-form-item label="姓名：">
          <el-input v-model="registerForm.name"></el-input>
        </el-form-item>
        <el-form-item label="性别：">
          <el-input v-model="registerForm.name">
          </el-input>
        </el-form-item>
        <el-form-item label="年龄：">
          <el-input v-model="registerForm.name">
          </el-input>
        </el-form-item>
        <el-form-item label="手机号：">
          <el-input v-model="registerForm.name">
          </el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="registerSubmit">注册</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</div>
<script>

  import { service } from '@/plugins/axios'

  export default {
    data() {
      return {
        logining: false,
        // 记住密码
        rememberpwd: false,
        form: {
          userName: '',
          password: '',
          code: '',
          randomStr: '',
          codeimg: ''
        },
        rules: {
          userName: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
          ],
        },
        dialogVisible: false,
        registerForm: {
          name: ''
        }
      }
    },
    create() {

    },
    methods: {
      registerSubmit() {
        console.log('registerSubmit!');
      },
      onSubmit(form) {
        this.$refs[form].validate((valid) => {
          if (valid) {
            this.$axios.post('/login', { userName: this.form.userName, password: this.form.password }).then(res => {
              if (res.status == 200) {
                const token = res.headers.token
                window.localStorage.setItem("token", token)
                this.$router.push('/')
              }
            });
            this.$nuxt.$loading.start()
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        // this.getdata();
      },
      goForget() {
        this.$alert('管理员电话：18111629666', '请联系管理员重置密码', {
          confirmButtonText: '确定',
          // callback: action => {
          //   this.$message({
          //     type: 'info',
          //     message: `action: ${ action }`
          //   });
          // }
        });
      },
      async getdata() {
        console.log(11, '为什么不发请求');
        // let that = this;
        // this.$axios.post('/login', { userName: "18781166142", password: "000000" }).then(res => {
        //   console.log('res', res);
        // });
        await service.post(
                '/api/login',
                {
                  userName: '18781166142',
                  password: '000000'
                }
        ).then(res => {
          console.log(res);
        })
      }
    }
  }

</script>
<style scoped>
  .box {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    position: absolute;
    /*background-image: url( );*/
    background-size: cover;
  }

  .main {
    width: 300px;
    height: 422px;
    /*border: 1px solid (7, 47, 80);*/
    border-radius: 3%;
    background-color: rgba(243, 246, 248, 0.9);
    padding: 0 10px;
  }

  .titlebox {
    margin: 20px 0;
    text-align: center;
    font-weight: 600;
  }

  .h3_title {
    text-align: center;
  }

  .remember {
    margin: 0px 0px 35px 0px;
  }

  .forget {
    float: right;
  }

  .register {
    float: left;
  }

  a {
    text-decoration: none;
    color: blue;
  }

  .buttonClass {
    width: 280px;
    margin-top: 20px;
  }
</style>
</html>

