new Vue({
    el: "#zhangjie",
    data: {


        user_id: '',


        chapter_id: '36',

        textarea: '',//评论
        textarea_reply: '',//回复评论
        textarea_reply_child:'',//回复评论2
        imageURL_header: "../images/avatar/",
        imageURL_suffix: ".jpg",

        dialogFormVisible: false,//书籍评论控制的dialog


        commentData: [{
            user_id: '1',
            user_name: 'fu测试1',
            head_portrait: '001',//用户头像
            comment_time: '2020-12-12',
            comment_content: '测试评论内容',
            comment_id: '1',
            displayReply: 'none',
            reply_comment_btn: '回复',//回复按钮
            reply_comment_btn2:'展开',

        },{
            user_id: '3',
            user_name: 'fu测试3',
            head_portrait: '003',//用户头像
            comment_time: '2020-12-13',
            comment_content: '测试评论内容',
            comment_id: '3',
            displayReply: 'none',
            reply_comment_btn: '回复',//回复按钮
            reply_comment_btn2:'展开',

        },],



        commentChildData: [{
            user_id: '4',
            user_name: 'zi测试4',
            head_portrait: '001',//用户头像
            comment_time: '2020-12-12',
            comment_content: '测试评论内容',
            comment_id: '4',
            comment_pid:'1',
            displayReply: 'none',
            reply_comment_btn: '回复',//回复按钮

        },{
            user_id: '5',
            user_name: 'zi测试5',
            head_portrait: '002',//用户头像
            comment_time: '2020-12-13',
            comment_content: '测试评论内容',
            comment_id: '5',
            comment_pid:'3',
            displayReply: 'none',
            reply_comment_btn: '回复',//回复按钮

        },],

    },
    methods: {

        //控制回复评论按钮显示与隐藏
        changeDisplayReply(id) {

            this.commentData.forEach(function (value, index, array) {
                if (id === value.comment_id) {

                    value.displayReply = value.displayReply === "none" ? "block" : "none";
                    value.reply_comment_btn = value.displayReply === "none" ? "回复" : "收起";


                }
            });

        },

        changeDisplayReplyChild(id){
            this.commentChildData.forEach(function (value, index, array) {
                if (id === value.comment_id) {

                    value.displayReply = value.displayReply === "none" ? "block" : "none";
                    value.reply_comment_btn = value.displayReply === "none" ? "回复" : "收起";


                }
            });
        },
        changeDisplayReply2(id){

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
                axios.post("/shaohuashuwu_war_exploded/commentInfoController/addChapterCommentInfo/"
                    + this.user_id + "/" + this.textarea + "/" + this.chapter_id + "/" + 2).then(response => {
                    let result = response.data;
                    console.log(response);
                    if (result === true) {
                        this.$message({
                            type: 'success',
                            message: '评论成功！'
                        });
                    }
                }).catch(error => {
                    console.log(error);
                });

                this.textarea = '';
            }
        },
        //回复父级评论  传进来的id  是父级评论的id
        submit_commentReply(id) {
            if (this.textarea_reply === "") {
                this.$message({
                    type: 'error',
                    message: '请输入回复内容！'
                });
            } else {

                let this_ = this;
                this.commentData.forEach(function (value, index, array) {
                    if (id === value.comment_id) {

                        axios.post("/shaohuashuwu_war_exploded/commentInfoController/addChapterCommentInfo/"
                            + this_.user_id + "/" + this_.textarea_reply + "/" + this_.chapter_id + "/" + value.comment_id).then(response => {
                            let result = response.data;
                            if(result ===true){
                                alert("回复成功！");
                                this.$message({
                                    type: 'success',
                                    message: '回复评论成功！'
                                });
                            }
                        }).catch(error => {
                            console.log(error);
                        });

                    }
                });
                this.textarea_reply = '';
            }
        },

        //回复次级评论  传进来的id  是次级评论的id
        submit_commentReplyChild(id){

            if (this.textarea_reply_child === "") {
                this.$message({
                    type: 'error',
                    message: '请输入回复内容！'
                });
            }else {

                let this_ = this;

                this.commentChildData.forEach(function (value, index, array) {


                    if (id === value.comment_pid) {

                        axios.post("/shaohuashuwu_war_exploded/commentInfoController/addChapterCommentInfo/"
                            + this_.user_id + "/" + this_.textarea_reply_child + "/" + this_.chapter_id + "/" + value.comment_id).then(response => {
                            let result = response.data;

                            if(result ===true){
                                alert("回复成功！");
                                this.$message({
                                    type: 'success',
                                    message: '回复评论成功！'
                                });
                            }


                        }).catch(error => {
                            console.log(error);
                        });

                    }
                });

                this.textarea_reply_child = '';

            }
        },
    },
    mounted() {
        //钩子函数，在加载页面后，渲染数据前执行

        //获取登录用户的ID和name
        axios.get("/shaohuashuwu_war_exploded/userSession/getPersonalData").then(response => {

            let info = response.data;
            let user_id = info["user_id"];
            let user_name = info["user_name"];

            this.user_id = user_id;
            this.user_name = user_name;

        }).catch(error => {
            console.log("获取信息失败！" + error);
            this.$message({
                type: 'error',
                message: '获取信息失败！'
            });
        });

        //获取章节父评论 根据章节id
        axios.get("/shaohuashuwu_war_exploded/commentInfoController/" +
            "getCommentParentInfoByChapterId/" + this.chapter_id).then(resp3 => {

            let objectData = eval(JSON.stringify(resp3.data));//将字符串转化为数组对象
            console.log("1" + resp3.data);
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
                    reply_comment_btn2:'展开',
                };
                commentData.push(list);
            });

            this.commentData = commentData;

        }).catch(error => {
            console.log("获取父级评论信息失败:" + error);
        });

        //获取次级评论
        axios.get("/shaohuashuwu_war_exploded/commentInfoController/" +
            "getCommentChildInfoByChapterId/" + this.chapter_id).then(resp3 => {

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
})