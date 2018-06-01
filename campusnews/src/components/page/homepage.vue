<template>
    <div>
        <el-row>
            <template>
                <el-carousel :interval="4000" type="card" height="200px">
                    <el-carousel-item v-for="item in 6" :key="item">
                        <h3>{{ item }}</h3>
                    </el-carousel-item>
                </el-carousel>
            </template>
        </el-row>
        <el-row :gutter="45">
            <el-col :span="15">
                <el-card shadow="hover" :body-style="{ height: '304px'}">
                    <div slot="header" class="clearfix">
                        <span>通告通知</span>
                        <el-button style="float: right; padding: 3px 0" type="text">添加</el-button>
                    </div>
                    <el-table :data="todoList" :show-header="false" height="304" style="width: 100%;font-size:14px;">
                        <el-table-column>
                            <template slot-scope="scope">
                                <div class="todo-item" :class="{'todo-item-del': scope.row.status}">
                                    {{scope.row.title}}
                                </div>
                            </template>
                        </el-table-column>
                        <el-table-column width="60">
                            <template slot-scope="scope">
                                <i class="el-icon-edit"></i>
                                <i class="el-icon-delete"></i>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>

            </el-col>
            <el-col :span="9">
                <el-card class="box-card" :body-style="{padding: '0px',height: '344px'}">
                    <div slot="header" class="clearfix" >
                        <span>找同事</span>
                        <el-button style="float: right; padding: 3px 0;height: 24px;width: 24px " type="text"><i class="el-icon-search"></i></el-button>
                    </div>
                    <template>
                        <el-table  :data="mailList" height="250" style="width: 100% ;padding: 0">
                            <el-table-column prop="name" label="姓名" width="180"></el-table-column>
                            <el-table-column prop="iphone" label="电话"></el-table-column>
                        </el-table>
                    </template>
                </el-card>


            </el-col>
        </el-row>
        <el-row :gutter="20">

            <el-col :span="12">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-edit-outline grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">4</div>
                                    <div>待我审批</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-circle-close grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">321</div>
                                    <div>处理BUG</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-info grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div class="grid-num">5</div>
                                    <div>消息</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>

            </el-col>
            <el-col :span="12">
                <template>
                    <vue-event-calendar :events="demoEvents" @monthChanged="" @dayChanged="">
                        <template slot-scope="props">
                            <div v-for="(event, index) in props.showEvents" class="event-item">
                                <!-- 这里拿到的是传入的单个event所有数据 -->
                                <h1>我的天呀，他</h1>
                                {{event}}
                            </div>
                        </template>
                    </vue-event-calendar>
                </template>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        data() {
            return {

                mailList: [{
                    date: '2016-05-03',
                    name: '王小虎',
                    iphone:'1201101991122'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    iphone:'1201101991122'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    iphone:'1201101991122'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    iphone:'1201101991122'
                }, {
                    date: '2016-05-08',
                    name: '王小虎',
                    iphone:'1201101991122'
                }, {
                    date: '2016-05-06',
                    name: '王小虎',
                    iphone:'1201101991122'
                }, {
                    date: '2016-05-07',
                    name: '王小虎',
                    iphone:'1201101991122'
                }],

                demoEvents: [{
                    date: '2018/5/29',
                    title: '这一天信息二',
                    desc: '穷的揭不开锅'
                }, {
                    date: '2018/5/30',
                    title: '请假啦'
                }],

                name: localStorage.getItem('ms_username'),
                todoList: [
                    {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '明天要放假',
                        status: false,
                    },
                    {
                        title: '今天要写100行代码加几个bug吧',
                        status: false,
                    }, {
                        title: '今天要修复100个bug',
                        status: false,
                    },
                    {
                        title: '今天要修复100个bug',
                        status: true,
                    },
                    {
                        title: '今天要写100行代码加几个bug吧',
                        status: true,
                    }
                ]
            }
        },
        computed: {


            role() {
                return this.name === 'admin' ? '超级管理员' : '普通用户';
            }
        }
    }

</script>


<style scoped>
    .el-row {
        margin-bottom: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 12px;
        color: #999;
    }

    .grid-num {
        font-size: 30px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    .todo-item {
        font-size: 14px;
    }

    .todo-item-del {
        text-decoration: line-through;
        color: #999;
    }

    /*轮播图*/
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
</style>
