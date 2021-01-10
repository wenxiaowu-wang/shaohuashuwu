new Vue({
    el: "#bookShelfInterface",
    data: {

        book_num: '1',
        user_id: '0',
        user_name: '123',//用户昵称
        activeIndex: '2',
        tableData: [{
            work_name: '斗罗大陆',
            work_main_label: '玄幻',
            user_name: '文小武',
            chapter_title: '第三百五十二章 打死小文',
            work_serial_state: '连载中',
            chapter_time: 'test',
            work_id: 0,
        }],
        checkedChapter: [],//绑定默认选中的数组
        checkAll: false,
        canClick: false,
        selectChapterCount:0,

    },
    methods: {
        //跳转到首页
        backHomePage(){
            window.location.assign("../pages/userMainInterface.html");
        },
        handleSelect(key, keyPath) {
            console.log("当前导航在:(key,keyPath)"+key, keyPath);
            switch (key){
                case "1":window.location.assign("../pages/myHomePage.html");break;
                case "2":{
                    this.$message({
                        type:'info',
                        message:'您已经在【我的书架】界面，不必跳转。'
                    });
                    break;
                }
                case "3":window.location.assign("../pages/messageCenterInterface.html");break;
                case "4":window.location.assign("../pages/personalAccountInterface.html");break;
                default:break;
            }
        },
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

        handleCheckAllChange(val) {//val的值是一个布尔值，点中全选为false，取消全选为true
            this.checkedChapter = [];//如果点击全选，先将原来选中的章节清除，然后再遍历，不然会出现重复结果

            this.tableData.forEach(item => {//当全选被选中的时候，循环遍历源数据，把数据的每一项加入到默认选中的数组去
                this.checkedChapter.push(item.work_id)
            })

            this.checkedChapter = val ? this.checkedChapter : [];

            if (val === true) {

                this.selectChapterCount = this.tableData.length;
            } else {
                this.selectChapterCount = 0;
            }
        },

        handleCheckedChapterChange() {

            if (this.checkedChapter.length === this.tableData.length) {//如果选中值的长度和源数据的长度一样，返回true，就表示你已经选中了全部checkbox，那么就把true赋值给this.checkAll
                this.checkAll = true
                this.selectChapterCount = this.tableData.length;
            } else {
                this.checkAll = false
                this.selectChapterCount = this.checkedChapter.length;
            }
        },

        //单个删除书架书
        deleteBookshelfWork() {


            if(this.selectChapterCount===0){
                this.$message({
                    type: 'warning',
                    message: '请勾选您要移除的小说'
                });
            }else{

                this.$confirm('你确定要从书架中移除这' + this.selectChapterCount + '本小说吗', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {

                    axios.post("/shaohuashuwu/bookshelfInfoController/deleteBookshelfWorkByWorkId/" +
                        this.checkedChapter + "/" +this.user_id).then(response => {
                        let result = response.data;
                        console.log(response);
                        if (result === true) {
                            window.location.assign("../pages/bookShelfInterface.html");
                        }
                    }).catch(error => {
                        console.log(error);
                    });

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });

            }

        },


        //阅读历史
        gotoreadhis(){
            console.log("阅读历史")
            window.location.assign("../pages/readingHistoryInterface.html");
        },



    //点击作品时按钮
        goToInterface(work_id){
        var _this = this;
        axios.post('/shaohuashuwu/worksInfoController/addWork_idSession?work_id='+work_id)
            .then(function (response) {
                window.location.assign("../pages/novelDetailsInterface.html");
            })
            .catch(function (error){
                console.log(error);
                alert("相应失败");
            })
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

            axios.get("/shaohuashuwu/bookshelfInfoController/selectBookshelfInfoByUserId/"
                + this.user_id).then(response => {
                let result = response.data;

                if (result < 0) {
                    this.$message({
                        type: 'error',
                        message: '获取书架信息失败！'
                    });
                } else {

                    this.book_num = result;

                    axios.get("/shaohuashuwu/workWholeInfoVoController/getWorkWholeInfoByuser_id/"
                        + this.user_id ).then(response => {

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
                            if(this.tableData[i].work_serial_state === 1){
                                this.tableData[i].work_serial_state = '连载中';
                            }else if(this.tableData[i].work_serial_state === 2){
                                this.tableData[i].work_serial_state = '已完结';
                            }else{
                                this.tableData[i].work_serial_state = '已下架';
                            }
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

    },
})