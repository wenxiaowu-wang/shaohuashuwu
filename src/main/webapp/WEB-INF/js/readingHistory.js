new Vue({
    el: "#readingHistory",
    data: {

        book_num: '0',
        user_id: '',
        user_name: '123',//用户昵称


        dialogFormVisible: false,//书籍评论控制的dialog

        activeIndex: '2',

        tableData: [{

            work_name: '斗罗大陆',
            work_main_label: '玄幻',
            user_name: '文小武',
            chapter_title: '第三百五十二章 打死小文',
            work_serial_state: '连载中',
            chapter_time: 'test',
            work_id: '',

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

        //清空历史记录
        deleteHistoryWorkByUserId() {
            axios.post("/shaohuashuwu_war_exploded/readingHistoryInfoController/deleteHistoryWorkByUserId/" +
                this.user_id).then(response => {

                let result = response.data;

                console.log(response);
                if (result == true) {
                    this.$message({
                        type: 'success',
                        message: '清空成功'
                    });
                }
            }).catch(error => {
                console.log(error);
            });

        },

        //历史记录单个删除
        deleteHistoryWorkByWorkId(id) {

            this.tableData.forEach(function (value, index, array) {
                if (id === value.work_id) {
                    axios.post("/shaohuashuwu_war_exploded/readingHistoryInfoController/deleteHistoryWorkByWorkId/" +
                        id ).then(response => {
                        let result = response.data;
                        console.log(response);
                        if (result == true) {
                            alert("删除记录成功");
                        }
                    }).catch(error => {
                        console.log(error);
                    });
                }
            });

        },
    },
    mounted() {

        axios.get("/shaohuashuwu_war_exploded/userSession/getPersonalData").then(response => {

            let info = response.data;
            let user_id = info["user_id"];
            let user_name = info["user_name"];
            this.user_id = user_id;
            this.user_name = user_name;

            axios.get("/shaohuashuwu_war_exploded/readingHistoryInfoController/getReadingHistoryCountByUserId/"
                + user_id).then(response => {

                let result = response.data;
                if (result < 0) {
                    this.$message({
                        type: 'error',
                        message: '获取信息失败！'
                    });
                } else {

                    this.book_num = result;

                    axios.get("/shaohuashuwu_war_exploded/workWholeInfoVoController/getWorkWholeInfoToHistoryByUser_id/"
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


