let bookComment_vm = new Vue({
    el: "#bookShelf",
    data: {

        user_id: '',
        work_id: '',
        user_name: '',
        textarea: '',//评论
        textarea_reply: '',//回复评论
        textarea_reply_child: '',//回复评论2
        dialogFormVisible: false,//书籍评论控制的dialog

        //头像前后缀
        imageURL_header: "../images/avatar/",
        imageURL_suffix: ".jpg",

        commentData: [{
            user_id: '1',
            user_name: 'fu测试1',
            head_portrait: '001',//用户头像
            comment_time: '2020-12-12',
            comment_content: '测试评论内容',
            comment_id: '1',
            displayReply: 'none',
            reply_comment_btn: '回复',//回复按钮
            reply_comment_btn2: '展开',

        }],


        commentChildData: [{
            user_id: '4',
            user_name: 'zi测试4',
            head_portrait: '001',//用户头像
            comment_time: '2020-12-12',
            comment_content: '测试评论内容',
            comment_id: '4',
            comment_pid: '1',
            comment_aid: '1',
            parent_name: '',
            displayReply: 'none',
            reply_comment_btn: '回复',//回复按钮

        }],

    },
    methods: {

        /*时间戳类型转换*/
        timestampToTime(timestamp) {
            var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            Y = date.getFullYear() + '-';
            M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
            D = date.getDate() + ' ';
            h = date.getHours() + ':';
            m = date.getMinutes() + ':';
            s = date.getSeconds();
            return Y + M + D + h + m + s;
        },

        //控制回复评论按钮显示与隐藏
        changeDisplayReply(id) {

            this.commentData.forEach(function (value, index, array) {
                if (id === value.comment_id) {

                    value.displayReply = value.displayReply === "none" ? "block" : "none";
                    value.reply_comment_btn = value.displayReply === "none" ? "回复" : "收起";


                }
            });

        },
        changeDisplayReplyChild(id) {
            this.commentChildData.forEach(function (value, index, array) {
                if (id === value.comment_id) {

                    value.displayReply = value.displayReply === "none" ? "block" : "none";
                    value.reply_comment_btn = value.displayReply === "none" ? "回复" : "收起";


                }
            });
        },
        changeDisplayReply2(id) {

            this.commentData.forEach(function (value, index, array) {
                if (id === value.comment_id) {
                    value.reply_comment_btn2 = value.reply_comment_btn2 === "收起" ? "展开" : "收起";
                }
            });
        },
        //父级评论
        submit_comment() {
            if (this.textarea === "") {
                this.$message({
                    type: 'error',
                    message: '请输入评论信息！'
                });
            } else {
                axios.post("/shaohuashuwu/commentInfoController/addCommentInfo/"
                    + this.user_id + "/" + this.textarea + "/" + this.work_id + "/" + 2 + "/" + 0).then(response => {
                    let result = response.data;
                    console.log(response);
                    if (result === true) {
                        this.$message({
                            type: 'success',
                            message: '回复评论成功！'
                        });
                    }
                }).catch(error => {
                    this.$message({
                        type: 'error',
                        message: '网络异常！'
                    });
                    console.log(error);
                });

                this.textarea = '';
            }
        },

        //回复父级评论  传进来的id  是第一级父级评论的id
        submit_commentReply(id) {
            if (this.textarea_reply === '') {
                this.$message({
                    type: 'error',
                    message: '请输入回复内容！'
                });
            } else {
                axios.post("/shaohuashuwu/commentInfoController/addCommentInfo/"
                    + this.user_id + "/" + this.textarea_reply + "/" + this.work_id + "/" + id + "/" + id).then(response => {
                    let result = response.data;
                    if (result === true) {
                        this.$message({
                            type: 'success',
                            message: '回复评论成功！'
                        });
                    }
                }).catch(error => {
                    this.$message({
                        type: 'error',
                        message: '网络异常！'
                    });
                    console.log(error);
                });

                this.textarea_reply = '';
            }
        },

        //回复次级评论  传进来的id  是次级评论的id  aid 是总父亲的id
        submit_commentReplyChild(id, aid) {

            if (this.textarea_reply_child === '') {
                this.$message({
                    type: 'error',
                    message: '请输入回复内容！'
                });
            } else {
                axios.post("/shaohuashuwu/commentInfoController/addCommentInfo/"
                    + this.user_id + "/" + this.textarea_reply_child + "/" + this.work_id + "/" + id + "/" + aid).then(response => {
                    let result = response.data;
                    if (result === true) {
                        this.$message({
                            type: 'success',
                            message: '回复评论成功！'
                        });
                    }

                }).catch(error => {
                    this.$message({
                        type: 'error',
                        message: '网络异常！'
                    });
                    console.log(error);
                });

                this.textarea_reply_child = '';

            }
        },

        getInterfaceData(work_id) {
            this.work_id = work_id;
            //获取书籍父评论
            axios.get("/shaohuashuwu/commentInfoController/" +
                "getCommentParentInfoByWorkId/" + work_id).then(resp3 => {


                let objectData = eval(JSON.stringify(resp3.data));//将字符串转化为数组对象

                let commentData = [];
                objectData.forEach(function (value) {

                    let list = {
                        user_id: value["user_id"],
                        user_name: value["user_name"],
                        head_portrait: value["head_portrait"],//用户头像
                        comment_time: value["comment_time"],
                        comment_content: value["comment_content"],
                        comment_id: value["comment_id"],
                        displayReply: 'none',
                        reply_comment_btn: '回复',//回复按钮
                        reply_comment_btn2: '展开',
                    };
                    commentData.push(list);
                });
                this.commentData = commentData;

            }).catch(error => {
                console.log("获取父级评论信息失败:" + error);
            });

            //获取书籍子评论
            axios.get("/shaohuashuwu/commentInfoController/" +
                "getCommentChildInfoByWorkId/" + work_id).then(resp3 => {

                let objectData = eval(JSON.stringify(resp3.data));//将字符串转化为数组对象

                let commentChildData = [];

                objectData.forEach(function (value) {

                    let list = {
                        user_id: value["user_id"],
                        user_name: value["user_name"],
                        head_portrait: value["head_portrait"],//用户头像
                        comment_time: value["comment_time"],
                        comment_content: value["comment_content"],
                        comment_id: value["comment_id"],
                        comment_pid: value["comment_pid"],
                        parent_name: value["parent_name"],
                        comment_aid: value["comment_aid"],
                        displayReply: 'none',
                        reply_comment_btn: '回复',//回复按钮
                    };
                    commentChildData.push(list);
                });

                this.commentChildData = commentChildData;


            }).catch(error => {
                console.log("获取父级评论信息失败:" + error);
            });
        },
    },

    mounted() {
        //钩子函数，在加载页面后，渲染数据前执行

        //获取本用户的ID和name
        axios.get("/shaohuashuwu/userSession/getPersonalData").then(response => {
            let info = response.data;
            let user_id = info["user_id"];
            let user_name = info["user_name"];
            this.user_id = user_id;
            this.user_name = user_name;
        }).catch(error => {
            console.log("获取信息失败！" + error);
        });


    },
})