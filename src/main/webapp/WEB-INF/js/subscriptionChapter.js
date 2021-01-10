new Vue({
    el: "#subscriptionChapter",
    data: {

        work_name: '',
        work_id: '',
        user_id: '',
        user_name: '',
        gold_bean_num: '',//账户剩余金豆数
        gold_bean_num_pay: '',//需要付的金豆数
        selectChapterCount: '',
        chapterTotalCount: '',
        chapterList2: [
            {
                chapter_id: 0,
                chapter_title: "",
                chapter_word_num: '',
            },
        ],

        chapterList: [
            {
                chapter_id: 0,
                chapter_title: "",
                chapter_word_num: '',
            }
        ],
        checkAll: false,
        checkedChapter:[],//绑定默认选中的数组
        canClick: false,


    },
    methods: {

        handleCheckAllChange(val) {//val的值是一个布尔值，点中全选为false，取消全选为true
            this.checkedChapter = [];//如果点击全选，先将原来选中的章节清除，然后再遍历，不然会出现重复结果

            this.chapterList.forEach(item => {//当全选被选中的时候，循环遍历源数据，把数据的每一项加入到默认选中的数组去
                this.checkedChapter.push(item.chapter_id)
            })

            this.checkedChapter = val ? this.checkedChapter : [];

            if(val === true){

                this.selectChapterCount = this.chapterList.length;
                this.gold_bean_num_pay = this.chapterList.length * 10;
            }else{
                this.selectChapterCount = 0;
                this.gold_bean_num_pay = 0;
            }
        },

        handleCheckedChapterChange() {

            if (this.checkedChapter.length === this.chapterList.length) {//如果选中值的长度和源数据的长度一样，返回true，就表示你已经选中了全部checkbox，那么就把true赋值给this.checkAll
                this.checkAll = true

                this.selectChapterCount = this.chapterList.length;
                this.gold_bean_num_pay = this.chapterList.length * 10;

            } else {

                this.checkAll = false
                this.selectChapterCount = this.checkedChapter.length;
                this.gold_bean_num_pay = this.checkedChapter.length * 10;
            }
        },

        subscribeAll() {
            if (this.gold_bean_num < this.gold_bean_num_pay) {
                this.$confirm('您的金豆不足是否去充值?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    alert("充值");
                    //重定向到充值界面
                    // window.location.assign("../pages/topUpsInterface.html");
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消充值'
                    });
                });
            } else {
                //将章节id数组传入后端
                if(this.checkedChapter.length===0){
                    this.$message({
                        type: 'warning',
                        message: '请选择要订阅的章节！'
                    });
                }else{

                    this.$confirm('此操作将扣除您'+this.gold_bean_num_pay+'金豆数您的金豆剩余：'+this.gold_bean_num+',是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        axios.post("/shaohuashuwu/transactionInfoController/subscribeChapterGUN/"+this.user_id+"/"+this.checkedChapter+"/"+this.work_id).then(resp => {
                            if (resp.data){
                                alert("订阅成功！");
                                window.location.assign("../pages/subscribeToNovelsInterface.html");
                            }else{
                                this.$message({
                                    type: 'error',
                                    message: '网络异常，请重试！'
                                });
                                console.log("false");
                            }
                        }).catch(error => {
                            console.log(error);
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消订阅'
                        });
                    });


                }
            }
        }
    },

    created() {

        //获取用户的userid username
        axios.get("/shaohuashuwu/userSession/getPersonalData").then(response => {
            let info = response.data;
            let user_id = info["user_id"];
            let user_name = info["user_name"];
            this.user_id = user_id;
            this.user_name = user_name;
            //获取用户金豆数量
            axios.get("/shaohuashuwu/userInfoController/getGoldBeanNum").then(resp => {
                let object = JSON.stringify(resp.data);
                this.gold_bean_num = parseInt(object);

            }).catch(error => {
                console.log(error);
            });

            //获取章节id
            axios.get("/shaohuashuwu/chapterSession/getChapter").then(response => {
                let info = response.data;
                let chapter_id = info["chapter_id"];

                axios.get("/shaohuashuwu/worksInfoController/getWorkNameByChapterId/" +
                    chapter_id).then(response => {
                    this.work_name = response.data;
                }).catch(error => {
                    console.log("获取信息失败！" + error);
                });

                //获取作品ID
                axios.get("/shaohuashuwu/worksInfoController/getWorkIdByChapterId/" +
                    chapter_id).then(response => {
                    let work_id = response.data;
                    this.work_id = response.data;
                    //获取作品未订阅的作品
                    axios.get("/shaohuashuwu/chapterInfoController/getChapterInfoByUserIdWorkId/" +
                        work_id + "/" + user_id).then(resp3 => {
                        let objectData = eval(JSON.stringify(resp3.data));//将字符串转化为数组对象
                        let commentData = [];
                        objectData.forEach(function (value) {
                            let list = {
                                chapter_id: value["chapter_id"],
                                chapter_title: value["chapter_title"],
                                chapter_word_num: value["chapter_word_num"],
                            };
                            commentData.push(list);
                        });
                        this.chapterList = commentData;
                    }).catch(error => {
                        console.log("获取章节e信息失败:" + error);
                    });
                    //获取付费的作品数量
                    axios.get("/shaohuashuwu/chapterInfoController/getChapterCountByUserIdWorkId/" +
                        work_id ).then(resp3 => {
                        let object = JSON.stringify(resp3.data);
                        this.chapterTotalCount = parseInt(object);
                    }).catch(error => {
                    });

                    //获取作品已订阅的作品
                    axios.get("/shaohuashuwu/chapterInfoController/getChapterInfoByUserIdWorkId2/" +
                        work_id + "/" + user_id).then(resp3 => {
                        let objectData = eval(JSON.stringify(resp3.data));//将字符串转化为数组对象
                        let commentData = [];
                        objectData.forEach(function (value) {
                            let list = {
                                chapter_id: value["chapter_id"],
                                chapter_title: value["chapter_title"],
                                chapter_word_num: value["chapter_word_num"],
                            };
                            commentData.push(list);
                        });
                        this.chapterList2 = commentData;
                    }).catch(error => {
                        console.log("获取章节e信息失败:" + error);
                    });
                }).catch(error => {
                    console.log("获取信息失败！" + error);
                });
            }).catch(error => {
                console.log("获取信息失败！" + error);
            });
        }).catch(error => {
            console.log("获取信息失败！" + error);
        });

    }
})