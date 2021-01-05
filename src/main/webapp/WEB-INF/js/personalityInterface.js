new Vue({
    // router,
    el: '#personalityRecommend_id',
    data() {
        return {
            drawer: false,
            //作品信息
            workWholeInfoVoList:[],

            /*排行榜信息*/
            rankingInputInfoVo: {
                work_main_label: ' ',
                getneednum: 10,
                transaction_type:' ',
                transaction_time:3,
                time_type:3,
            },
            //榜单数据
            recommendInfoVoList:[],
            //用户
            userInfo:[],



        }
    },
    methods:{
        /*
        * 初始化界面
        * */
        //获取信息
        getRecommmend(){
            console.log("推荐信息")
            var _this = this;
            axios.post('/shaohuashuwu/workWholeInfoVoController/getworkWholeInfoVoByuser_id')
                .then(function (response) {
                    _this.workWholeInfoVoList = response.data;

                    for (var i=0;i<_this.workWholeInfoVoList.length;i++){
                        _this.workWholeInfoVoList[i].chapter_time = _this.timestampToTime(_this.workWholeInfoVoList[i].chapter_time);
                        if(_this.workWholeInfoVoList[i].work_serial_state == 2){
                            _this.workWholeInfoVoList[i].work_serial_state = "完结";
                        }
                        else if(_this.workWholeInfoVoList[i].work_serial_state == 1){
                            _this.workWholeInfoVoList[i].work_serial_state = "连载";
                        }
                        /*字数大于10万字就变为小数*/
                        if(_this.workWholeInfoVoList[i].work_vote_num>100000){
                            var divisornum = _this.workWholeInfoVoList[i].work_vote_num/10000;
                            _this.workWholeInfoVoList[i].work_vote_num = divisornum.toFixed(2);
                            _this.workWholeInfoVoList[i].work_vote_num = _this.workWholeInfoVoList[i].work_vote_num + "万";
                        }
                    }
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                })
        },
        //推荐榜
        recommendListInfo(val){
            console.log("推荐榜");
            this.rankingInputInfoVo.transaction_type = 3;
            var _this = this;
            axios.post('/shaohuashuwu/rankingInfoController/getRankingInfo', this.rankingInputInfoVo)
                .then(function (response3) {
                    _this.recommendInfoVoList = response3.data;
                    console.log("排行信息")
                })
                .catch(function (error) {
                    console.log(error);
                    alert("相应失败");
                })
        },


        //点击书籍名称
        clickWork_name(work_id){
            console.log("作品id："+work_id);
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



        //点击加入书架
        //加入书架
        addToBookshelf(selectwork_id){
            console.log("上传信息======")
            var user_id= this.userInfo.user_id;
            var work_id = selectwork_id;
            console.log("上传信息"+user_id+"---"+work_id)
            axios.post("/shaohuashuwu/bookshelfInfoController/addToBookshelf/" +
                user_id + "/" + work_id ).then(resp => {
                let Result = resp.data;
                console.log("数据同步存到数据库。"+Result);
                if(Result==true){
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


        /*跳转进入选定章节*/
        gotoreadChapterInfo(chapter_id){

            /*跳转进入章节*/
            axios.post('/shaohuashuwu/chapterInfoController/saveChapter_idSession?chapter_id=' + chapter_id)
                .then(function (response) {
                    window.location.assign("../pages/readNovelInterface.html");
                })
                .catch(function (error) {
                    console.log(error);
                    alert("相应失败");
                })
        },


        //点击用户名字
        clickUser_name(author_id){
            console.log("作者id："+author_id);
            var _this = this;
            axios.post('/shaohuashuwu/userInfoController/addAuthor_idSession?author_id='+author_id)
                .then(function (response) {
                    window.location.assign("../pages/authorInfoInterface.html");
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },


        startUserInfo(){
            var _this = this;
            axios.get('/shaohuashuwu/userInfoController/getUserLoginInfo')
                .then(function (response){
                    _this.userInfo = response.data;
                })
                .catch(function (error){
                    alert("相应失败");
                })
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
        }
    },
    created:function (){ //页面加载时查询所有
        this.getRecommmend();
        this.recommendListInfo();
        this.startUserInfo();
    }
})