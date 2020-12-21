new Vue({
    el: '#app',
    data() {

        return {

            user_id: '',
            passID: '',//comment_id
            passWorkID: '',
            textarea: '',//评论
            passDeleteindex:'',
            dialogFormVisible: false,//书籍评论控制的dialog

            Works: [{
                work_id: '1',
                work_name: '作品1'
            },{
                work_id: '3232',
                work_name: '作品23'
            },],


            commentData: [{

                user_id: '测试1',
                user_name: '测试1',
                comment_time: '测试1',
                comment_content: '测试评论内容1',
                comment_id: '1',

            }, {

                user_id: '测试2',
                user_name: '测试2',
                comment_time: '测试2',
                comment_content: '测试评论内容2',
                comment_id: '2',

            }, {

                user_id: '测试3',
                user_name: '测试3',
                comment_time: '测试3',
                comment_content: '测试评论内容3',
                comment_id: '3',

            },],

        }
    },
    methods: {
        handleClick(row) {
            console.log(row);
        },

        passCommentId(id) {
            this.passID = id;
        },
        passWorkId(id){
            this.passWorkID=id;
        },
        passDeleteIndex(id){
            this.passDeleteindex=id;
        },
        deleteRow(index, rows) {
            rows.splice(index, 1);
        },
        selectWork(id) {

            axios.get("/shaohuashuwu_war_exploded/commentInfoController/" +
                "getCommentParentInfoByWorkId/" + id).then(resp3 => {

                let objectData = eval(JSON.stringify(resp3.data));//将字符串转化为数组对象

                console.log("1" + resp3.data);
                let commentData = [];
                objectData.forEach(function (value) {

                    let list = {
                        user_id: value["user_id"],
                        user_name: value["user_name"],
                        comment_time: value["comment_time"],
                        comment_content: value["comment_content"],
                        comment_id: value["comment_id"],
                    };
                    commentData.push(list);
                });

                this.commentData = commentData;


            }).catch(error => {
                console.log("获取父级评论信息失败:" + error);
            });

        },
        deleteComment(id) {
            this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {

                this.deleteRow(this.passDeleteindex,this.commentData);

                axios.get("/shaohuashuwu_war_exploded/commentInfoController/deleteWorkComment/"
                    + id).then(response => {
                    if (response.data === true) {
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                    } else {
                        this.$message({
                            type: 'false',
                            message: '删除失败!'
                        });
                    }
                }).catch(error => {
                    console.log(error);
                });

            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        replyComment() {

            if (this.textarea === '') {
                this.$message({
                    type: 'error',
                    message: '评论内容不可为空!'
                });
            } else {

                axios.post("/shaohuashuwu_war_exploded/commentInfoController/addCommentInfo/"
                    + this.user_id + "/" + this.textarea + "/" + this.passWorkID + "/" + this.passID).then(response => {
                    let result = response.data;
                    if (result === true) {
                        this.$message({
                            type: 'success',
                            message: '回复评论成功！'
                        });
                    }
                }).catch(error => {
                    console.log(error);
                });
            }
        },
    },

    mounted() { //页面加载时查询所有

        //获取session信息
        axios.get("/shaohuashuwu_war_exploded/userSession/getPersonalData").then(response => {

            let info = response.data;
            let user_id = info["user_id"];

            this.user_id = user_id;
            axios.get("/shaohuashuwu_war_exploded/worksInfoController/getWorkIdNameByUserId/"
                + user_id).then(response => {

                let objectData = eval(JSON.stringify(response.data));//将字符串转化为数组对象
                console.log(objectData);
                let Data = [];
                objectData.forEach(function (value) {
                    let list = {
                        work_id: value["work_id"],
                        work_name: value["work_name"],
                    };
                    Data.push(list);
                });

                this.Works = Data;

            }).catch(error => {
                console.log(error);
            });


        }).catch(error => {
            console.log("获取信息失败！" + error);
            this.$message({
                type: 'error',
                message: '获取信息失败！'
            });
        });


    }
})