<template>
    <div>
        <div class="crumbs">
            <h1>添加新闻</h1>
        </div>
        <div class="container">
                    <div class="plugins-tips">
                        <el-input v-model="newsTitle" placeholder="请输入新闻标题" class="newsTitle" size="small"></el-input>
                        <div class="block">
                            <span class="demonstration">新闻的有效期至：</span>
                            <el-date-picker
                                v-model="newsEndTime"
                                align="right"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择日期"
                                :picker-options="pickerOptions1">
                            </el-date-picker>
                        </div>
                    </div>
                    <quill-editor ref="myTextEditor" v-model="newsContent" :options="editorOption"
                                  class="editor"></quill-editor>
                    <el-button class="editor-btn" type="primary" @click="submit">提交</el-button>
        </div>

    </div>
</template>

<script>
    import 'quill/dist/quill.core.css';
    import 'quill/dist/quill.snow.css';
    import 'quill/dist/quill.bubble.css';
    import {quillEditor} from 'vue-quill-editor';

    export default {
        name: "AddNews",
        data: function () {
            return {
                newsContent: '',
                newsEndTime: '',
                newsTitle: '',
                editorOption: {
                    placeholder: '新闻的主体内容'
                },
                pickerOptions1: {
                    disabledDate(time) {
                        return time.getTime() < Date.now();
                    },
                    shortcuts: [{
                        text: '明天',
                        onClick(picker) {
                            picker.$emit('pick', new Date());
                        }
                    }, {
                        text: '三天后',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() + 3600 * 1000 * 24);
                            picker.$emit('pick', date);
                        }
                    }, {
                        text: '一周后',
                        onClick(picker) {
                            const date = new Date();
                            date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', date);
                        }
                    }]
                }
            }
        },
        components: {
            quillEditor
        },
        methods: {
            onEditorChange({editor, html, text}) {
                this.newsContent = html;
            },
            submit() {

                if(this.newsContent.length > 800000){
                    this.$message.error('内容太长，小程序不支持');
                    return;
                }
                let params = new URLSearchParams();
                params.append('newsContent', this.newsContent);
                params.append('newsEndTime', this.newsEndTime);
                params.append('newsTitle', this.newsTitle);
                if(!this.newsContent||!this.newsContent||!this.newsContent){
                    this.$message.error('提交失败,请检查内容！');
                    return;
                }
                console.log(this.newsEndTime);
                console.log(params);
                this.$axios.post("/admin/news/add", params).then((res) =>{
                    this.$message.success('提交成功！');
                    this.$router.push({path:"setnews",query:{
                        newsId:res.data
                        }});
                }).catch((err)=>{
                    this.$message.error('提交失败');
                })

            }
        }
    }
</script>

<style scoped>
    .editor-btn {
        margin-top: 20px;
    }


</style>
