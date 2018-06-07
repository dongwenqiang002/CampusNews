<template>
    <div class="login-wrap">
        <div class="ms-title">西财新闻小叮当后台管理</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="password" v-model="ruleForm.password"
                              @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </div>
                <p style="font-size:12px;line-height:30px;color:#999;color: red">{{messagee}}</p>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {
                messagee: '',
                ruleForm: {
                    username: 'admin',
                    password: 'admin'
                },
                rules: {
                    username: [
                        {required: true, message: '请输入用户名', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                console.log(this.$refs[formName]);
                console.log(this.$refs[formName]);
                let that = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //在这里提交用户名和密码
                        //---------------------------
                        let params = new URLSearchParams();
                        params.append('username', that.ruleForm.username);
                        params.append('password', that.ruleForm.password);
                        this.$axios.post("/admin/login", params).then((res) => {
                            //this.tableData = res.data.list;
                            console.log(res)
                            if (res.data.user) {
                                localStorage.setItem('ms_username', res.data.user.name);
                                localStorage.setItem('sessionId', res.data.sessionId);
                                that.$router.push('/');
                            } else {
                                console.log(res.data);
                                that.messagee = res.data.message;
                            }
                        }).catch((e) => {
                            that.messagee = "请检查用户名或密码"
                            console.log("登录异常")
                            console.log(e);
                        })

                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .login-wrap {
        position: relative;
        width: 100%;
        height: 100%;
    }

    .ms-title {
        position: absolute;
        top: 50%;
        width: 100%;
        margin-top: -230px;
        text-align: center;
        font-size: 30px;
        color: #fff;

    }

    .ms-login {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 300px;
        height: 160px;
        margin: -150px 0 0 -190px;
        padding: 40px;
        border-radius: 5px;
        background: #fff;
    }

    .login-btn {
        text-align: center;
    }

    .login-btn button {
        width: 100%;
        height: 36px;
    }
</style>
