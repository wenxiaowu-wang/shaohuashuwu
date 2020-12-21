new Vue({
    el: "#bookShelf",
    data: {

        book_num: '1',
        user_id: '',

        work_id: '32',
        chapter_id: '11',
        user_name: '123',//用户昵称
        textarea: '',//评论

        textarea_reply: '',//回复评论
        textarea_reply_child:'',//回复评论2
        imageURL_header: "../images/avatar/",
        imageURL_suffix: ".jpg",

        dialogFormVisible: false,//书籍评论控制的dialog

        activeIndex: '2',

        tableData: [{
            work_name: '斗罗大陆',
            work_main_label: '玄幻',
            user_name: '文小武',
            chapter_title: '第三百五十二章 打死小文',
            work_serial_state: '连载中',
            chapter_time: 'test',
        }],


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

        /*时间戳类型转换*/
        timestampToTime(timestamp) {
            var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            Y = date.getFullYear() + '-';
            M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            D = date.getDate() + ' ';
            h = date.getHours() + ':';
            m = date.getMinutes() + ':';
            s = date.getSeconds();
            return Y+M+D+h+m+s;
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

        deleteBookshelfWork() {
            axios.post("/shaohuashuwu_war_exploded/bookshelfInfoController/deleteBookshelfWorkByWorkId/" +
                this.work_id).then(response => {

                let result = response.data;
                console.log(response);
                if (result == true) {
                    this.$message({
                        type: 'success',
                        message: '删除成功'
                    });
                }
            }).catch(error => {
                console.log(error);
            });
        },
        //历史记录单个删除
        deleteHistoryWorkByWorkId() {
            axios.post("/shaohuashuwu_war_exploded/readingHistoryInfoController/deleteHistoryWorkByWorkId/" +
                this.work_id).then(response => {
                let result = response.data;
                console.log(response);
                if (result == true) {
                    this.$message({
                        type: 'success',
                        message: '删除成功'
                    });
                }
            }).catch(error => {
                console.log(error);
            });

        },
        //清空历史记录
        deleteHistoryWorkByUserId() {
            axios.post("/shaohuashuwu_war_exploded/readingHistoryInfoController/deleteHistoryWorkByUserId/" +
                this.user_id).then(response => {

                let result = response.data;

                console.log(response);
                if (result == true) {
                    this.$message({
                        type: 'success',
                        message: '删除成功'
                    });
                }
            }).catch(error => {
                console.log(error);
            });

        },
        //订阅本章
        subscriptionChapter() {
            //先获取用户的金豆书
            axios.get("/shaohuashuwu_war_exploded/userInfoController/getGoldBeanNum/" +
                this.user_id).then(resp => {

                var object = JSON.stringify(resp.data);
                let data = parseInt(object);

                if (data >= 10) {

                    let data2 = -10;
                    let data3 = 10;

                    //订阅一章减少用户的金豆数
                    axios.get("/shaohuashuwu_war_exploded/userInfoController/updateUserGoldBean/" +
                        this.user_id + "/" + data2).then(resp => {

                        if (resp.data == true) {
                            // this.$message({
                            //     type: 'success',
                            //     message: '更新用户金豆成功！'
                            // });
                        }

                    }).catch(error => {
                        console.log(error);
                    });

                    //将订阅记录存到交易记录表
                    axios.get("/shaohuashuwu_war_exploded/transactionInfoController/subscribeChapter/" +
                        this.user_id + "/" + this.chapter_id + "/" + data3).then(resp => {
                        let result = resp.data;
                        if (result == true) {
                            // this.$message({
                            //     type: 'success',
                            //     message: '更新交易记录表 成功！'
                            // });
                        }
                    }).catch(error => {
                        console.log(error);
                    });

                    //订阅后增加作者金豆数
                    axios.get("/shaohuashuwu_war_exploded/userInfoController/selectAuthorIDByChapterID/" +
                        this.chapter_id).then(resp => {
                        let author_id = resp.data;
                        //更新作者的金豆数
                        axios.get("/shaohuashuwu_war_exploded/userInfoController/updateUserGoldBean/" +
                            author_id + "/" + data3).then(resp => {
                            if (resp.data == true) {
                                // this.$message({
                                //     type: 'success',
                                //     message: '更新作者金豆成功！'
                                // });
                            }
                        }).catch(error => {
                            console.log(error);
                        });
                    }).catch(error => {
                        console.log(error);
                    });

                    //重定向界面


                } else {
                    alert("金豆不足，将前往充值界面!")
                    window.location.assign("../pages/topUpsInterface.html");
                }

            }).catch(error => {
                console.log(error);
            });
        },

        //订阅目前更新到的所有章节（不包含未更新的）
        subscriptionAllChapter() {

        },

        //父级评论
        submit_comment() {
            if (this.textarea === "") {
                this.$message({
                    type: 'error',
                    message: '请输入评论信息！'
                });
            } else {
                axios.post("/shaohuashuwu_war_exploded/commentInfoController/addCommentInfo/"
                    + this.user_id + "/" + this.textarea + "/" + this.work_id + "/" + 2).then(response => {
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

                        axios.post("/shaohuashuwu_war_exploded/commentInfoController/addCommentInfo/"
                            + this_.user_id + "/" + this_.textarea_reply + "/" + this_.work_id + "/" + value.comment_id).then(response => {
                             let result = response.data;
                            //
                            // console.log(response);

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

                        axios.post("/shaohuashuwu_war_exploded/commentInfoController/addCommentInfo/"
                            + this_.user_id + "/" + this_.textarea_reply_child + "/" + this_.work_id + "/" + value.comment_id).then(response => {
                            let result = response.data;
                            //
                            // console.log(response);

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
        //获取本用户的ID和name
        axios.get("/shaohuashuwu_war_exploded/userSession/getPersonalData").then(response => {

            let info = response.data;
            let user_id = info["user_id"];
            let user_name = info["user_name"];
            this.user_id = user_id;
            this.user_name = user_name;

            axios.get("/shaohuashuwu_war_exploded/bookshelfInfoController/selectBookshelfInfoByUserId/"
                + this.user_id).then(response => {
                let result = response.data;

                if (result < 0) {
                    this.$message({
                        type: 'error',
                        message: '获取书架信息失败！'
                    });
                } else {

                    this.book_num = result;
                    axios.get("/shaohuashuwu_war_exploded/workWholeInfoVoController/getWorkWholeInfoByuser_id/"
                        + user_id ).then(response => {

                        let objectData = eval(JSON.stringify(response.data));//将字符串转化为数组对象
                        let Data = [];
                        objectData.forEach(function (value) {
                            let list = {
                                work_name: value["work_name"],
                                work_main_label: value["work_main_label"],
                                user_name: value["user_name"],
                                chapter_title: value["chapter_title"],
                                work_serial_state: value["work_serial_state"],
                                chapter_time: value["chapter_time"],
                                work_id:value["work_id"],
                            };
                            Data.push(list);
                        });

                        this.tableData = Data;

                        for (let i =0 ; i<this.tableData.length;i++){
                            this.tableData[i].chapter_time = this.timestampToTime(this.tableData[i].chapter_time);
                        }


                    }).catch(error => {
                        console.log(error);
                    });
                }

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
        axios.get("/shaohuashuwu_war_exploded/commentInfoController/" +
            "getCommentParentInfoByWorkId/" + this.work_id).then(resp3 => {

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

        axios.get("/shaohuashuwu_war_exploded/commentInfoController/" +
            "getCommentChildInfoByWorkId/" + this.work_id).then(resp3 => {

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