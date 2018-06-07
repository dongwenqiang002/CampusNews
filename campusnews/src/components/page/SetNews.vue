<template>
    <section class="main">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-rank"></i> 选择接收新闻的学校</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="plugins-tips">
                <el-row :gutter="20">
                    <el-col :span="15">
                        <el-select v-model="thisNewsId" placeholder="请选择" style="width: 400px">
                            <el-option center=true
                                       v-for="item in newsList"
                                       :key="item.id"
                                       :label="item.title"
                                       :value="item.id">
                                <span style="float: left">( {{   timesFun(item.pubdate) }} ) {{ item.title }}</span>
                                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.id }}</span>
                            </el-option>
                        </el-select>
                        <el-button type="primary" round @click="submit">确定提交</el-button>
                    </el-col>

                </el-row>
            </div>
            <div class="drag-box">
                <div class="drag-box-item-h">
                    <div class="item-title">接收新闻群体</div>
                    <draggable v-model="receive" @remove="removeHandle" :options="dragOptions">
                        <transition-group tag="div" id="receive" class="item-ul">
                            <div v-for="(item,index) in receive" class="drag-list" :key="index">
                                {{item.name}}
                            </div>
                        </transition-group>
                    </draggable>
                </div>
                <div class="drag-box-item" v-for="(schoolOS,index) in schoolOSs" >
                    <div class="item-title"></div>
                    <draggable v-model="schoolOSs[index]" @remove="removeHandle" :options="dragOptions">
                        <transition-group tag="div" :id="index" class="item-ul" >
                            <div v-for="(item,i) in schoolOSs[index]" class="drag-list" :key="i"  @click="showSchool(item,index)">
                                {{item.name}}
                            </div>
                        </transition-group>
                    </draggable>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
    import draggable from 'vuedraggable'
    import ElCol from "element-ui/packages/col/src/col";

    export default {
        data() {
            return {
                thisNewsId: 1,
                newsList: [],
                dragOptions: {
                    animation: 120,
                    scroll: true,
                    group: 'sortlist',
                    ghostClass: 'ghost-style'
                },

                receive: [],
                 schoolOSs: [[],[],[],[]],
               /* done: [
                    {
                        content: '软件1401'
                    },
                    {
                        content: '软件1402'
                    }, {
                        content: '计算机1401'
                    }, {
                        content: '电子信息1401'
                    }, {
                        content: '软件1402'
                    },
                ]*/
            }
        },
        components: {
            ElCol,
            draggable
        },
        methods: {
            removeHandle(event) {
                console.log(event);
                console.log(this.receive[event.newIndex]);
                let cc = this.receive[event.newIndex];
                if(!cc.remark){
                    cc.isUser = true;
                }
                //this.$message.success(`从 ${event.from.id} 移动到 ${event.to.id} `);
                //if()
            },
            timesFun(timesData) {
                //如果时间格式是正确的，那下面这一步转化时间格式就可以不用了
                var dateBegin = new Date(timesData);//将-转化为/，使用new Date
                var dateEnd = new Date();//获取当前时间
                var dateDiff = dateEnd.getTime() - dateBegin.getTime();//时间差的毫秒数
                var dayDiff = Math.floor(dateDiff / (24 * 3600 * 1000));//计算出相差天数
                var leave1 = dateDiff % (24 * 3600 * 1000)    //计算天数后剩余的毫秒数
                var hours = Math.floor(leave1 / (3600 * 1000))//计算出小时数
                //计算相差分钟数
                var leave2 = leave1 % (3600 * 1000)    //计算小时数后剩余的毫秒数
                var minutes = Math.floor(leave2 / (60 * 1000))//计算相差分钟数


                var timesString = '';
                if (dayDiff != 0) {
                    timesString = dayDiff + '天之前';
                } else if (dayDiff == 0 && hours != 0) {
                    timesString = hours + '小时之前';
                } else if (dayDiff == 0 && hours == 0) {
                    timesString = minutes + '分钟之前';
                }
                console.log(timesString);
                return timesString;
            },
            //显示接下来的学校
            showSchool(sc,i){
                let that = this;
                console.log(sc);
                console.log(i);
                if(!sc.remark)return;
                that.$axios.get("/admin/news/getSchoolOS",{params:{id:sc.id}}).then((res) => {
                    if(res.data||i<=2) {
                        console.log(res.data);
                        that.$set(that.schoolOSs, i+1, res.data ? res.data : null);
                        for(i+=1;i<3;i++) {
                            that.$set(that.schoolOSs, i+1, null);
                        }
                    }
                })
            },
            submit(){
                let that = this;
                let params = new URLSearchParams();
                params.append('list', JSON.stringify(that.receive));

                params.append('newsId', this.thisNewsId);
                console.log(JSON.stringify(that.receive));
                if(!this.thisNewsId){
                    this.$message.error('失败');
                    return
                }
                that.$axios.post("/admin/news/setNewsPub",params).then((res) => {
                    console.log(res);
                    if(res){
                        this.$message.success('设置成功');
                        this.$router.push("addnews");
                    }
                });
            }

        },
        //在显示之前
        beforeRouteEnter(to, from, next) {
            console.log("---------hahahahahah")
            //vm是组件的实例
            next(vm => {
                vm.thisNewsId = to.query.newsId;
                vm.$axios.get("/admin/news/getNewsList").then((res) => {
                    console.log(res.data);
                    vm.newsList = res.data;
                });
                vm.$axios.get("/admin/news/getSchoolOS").then((res) => {
                    console.log(res.data);
                    vm.schoolOSs[0] =  res.data;
                })

            })

        }
    }

</script>

<style scoped>
    .drag-box {
        display: flex;
        user-select: none;
    }
    .drag-box-item-h{
        flex: 1;
        max-width: 500px;
        min-width: 340px;
        background-color: #eff1f5;
        margin-right: 16px;
        border-radius: 6px;
        border: 1px #e1e4e8 solid;
        margin-right: 60px;
    }
    .drag-box-item {
        flex: 1;
        max-width: 300px;
        min-width: 230px;
        background-color: #eff1f5;
        margin-right: 16px;
        border-radius: 6px;
        border: 1px #e1e4e8 solid;
    }

    .item-title {
        padding: 8px 8px 8px 12px;
        font-size: 14px;
        line-height: 1.5;
        color: #24292e;
        font-weight: 600;
    }

    .item-ul {
        padding: 0 8px 8px;
        height: 500px;
        overflow-y: scroll;
    }

    .item-ul::-webkit-scrollbar {
        width: 0;
    }

    .drag-list {
        border: 1px #e1e4e8 solid;
        padding: 10px;
        margin: 5px 0 10px;
        list-style: none;
        background-color: #fff;
        border-radius: 6px;
        cursor: pointer;
        -webkit-transition: border .3s ease-in;
        transition: border .3s ease-in;
    }

    .drag-list:hover {
        border: 1px solid #20a0ff;
    }

    .drag-title {
        font-weight: 400;
        line-height: 25px;
        margin: 10px 0;
        font-size: 22px;
        color: #1f2f3d;
    }

    .ghost-style {
        display: block;
        color: transparent;
        border-style: dashed
    }
</style>
