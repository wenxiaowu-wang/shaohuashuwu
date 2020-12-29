new Vue({
    el: "#readingHistory",
    data: {

        book_num: '0',
        user_id: '',
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
        //加入书架
        addBookshelf(work_id) {

            axios.post("http://localhost:8080/bookshelfInfoController/addToBookshelf/" +
                this.user_id + "/" + work_id ).then(resp => {
                let Result = resp.data;
                console.log("数据同步存到数据库。"+Result);
                if(Result===true){
                    this.$message({
                        type: 'success',
                        message: '加入书架成功！'
                    });
                }else{
                    this.$message({
                        type: 'error',
                        message: '小说已加入书架，无需重复添加!'
                    });
                }
            }).catch(error => {
                console.log("数据同步存到数据库失败。"+error);
            });

        },

        //清空历史记录
        deleteHistoryWorkByUserId() {

            this.$confirm('你确定要清空阅读历史记录吗', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.post("http://localhost:8080/readingHistoryInfoController/deleteHistoryWorkByUserId/" +
                    this.user_id).then(response => {
                    let result = response.data;
                    console.log(response);
                    if (result == true) {
                        this.$message({
                            type: 'success',
                            message: '清空成功'
                        });
                        window.location.assign("../pages/readingHistoryInterface.html");
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

        },

        GoToBookshelf(){
            window.location.assign("../pages/bookShelfInterface.html");
        },
    },
    mounted() {

        axios.get("http://localhost:8080/userSession/getPersonalData").then(response => {

            let info = response.data;
            let user_id = info["user_id"];
            let user_name = info["user_name"];
            this.user_id = user_id;
            this.user_name = user_name;

            axios.get("http://localhost:8080/readingHistoryInfoController/getReadingHistoryCountByUserId/"
                + user_id).then(response => {

                let result = response.data;
                if (result < 0) {
                    this.$message({
                        type: 'error',
                        message: '获取信息失败！'
                    });
                } else {

                    this.book_num = result;

                    axios.get("http://localhost:8080/workWholeInfoVoController/getWorkWholeInfoToHistoryByUser_id/"
                        + user_id).then(response => {

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
                            if (this.tableData[i].work_serial_state === 1) {
                                this.tableData[i].work_serial_state = '连载中';
                            } else if (this.tableData[i].work_serial_state === 2) {
                                this.tableData[i].work_serial_state = '已完结';
                            } else {
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

            this.$message({
                type: 'error',
                message: '获取信息失败！'
            });
        });
    }
})


