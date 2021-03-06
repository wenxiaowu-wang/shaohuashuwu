new Vue({
    el: '#selectresult_id',
    data() {
        return {

            /**
             * 界面数据*/
            //主类型标签
            labelList: [
                "全部", "玄幻", "奇幻", "武侠", "仙侠", "都市", "历史", "军事", "悬疑", "科幻", "游戏", "体育", "现实", "轻小说"
            ],
            //状态类型
            serialstateList: [
                "全部","连载","完结"
            ],
            //已选分类
            selectedtips:{
                selected_main_label:' ',
                selected_serial_state:' ',
            },
            //为空时显示
            worknull:0,


            /**
             * 上传数据
             * */
            //上传类型和状态
            worksInfoneed:{
                work_main_label:' ',
                work_serial_state:' ',
            },
            /*排行榜信息*/
            rankingInputInfoVo: {
                work_main_label: ' ',
                getneednum: 10,
                transaction_type:' ',
                transaction_time:3,
                time_type:3,
            },


            /**
             * 相应数据*/
            //获取的作品数据
            worksWholeInfoVoList:[],
            workTotal:0,
            //榜单数据
            recommendInfoVoList:[],

            //用户信息
            userInfo:[],



        }
    },
    methods: {

        /**
         * 初始化界面
         */
        //初始化标签类型
        startHtmlContent(){
            this.worksInfoneed.work_main_label = null;
            this.worksInfoneed.work_serial_state = null;
            this.selectedtips.selected_main_label = "全部";
            this.selectedtips.selected_serial_state = "全部";
        },
        //初始化界面数据
        startSearchWorkInfo() {
            var _this = this;
            console.log("上传信息"+JSON.stringify(_this.worksInfoneed));
            axios.post('/shaohuashuwu/workWholeInfoVoController/getWorkWholeInfoBySelectinput',_this.worksInfoneed)
                .then(function (response) {

                        _this.worksWholeInfoVoList = response.data;
                        _this.workTotal=_this.worksWholeInfoVoList.length;
                        console.log("开====="+_this.worksWholeInfoVoList)

                        for( var i = 0 ;i<_this.worksWholeInfoVoList.length;i++){
                            console.log("循环");
                            _this.worknull = 1;
                            /*将1或2转为连载或完结*/
                            if( _this.worksWholeInfoVoList[i].work_serial_state == 1){
                                _this.worksWholeInfoVoList[i].work_serial_state = "连载";
                            }
                            else if(_this.worksWholeInfoVoList[i].work_serial_state == 2){
                                _this.worksWholeInfoVoList[i].work_serial_state = "完结";
                            }

                            /*推荐票大于10万就变为小数*/
                            if(_this.worksWholeInfoVoList[i].work_vote_num>100000){
                                var divisornum = _this.worksWholeInfoVoList[i].work_vote_num/10000;
                                _this.worksWholeInfoVoList[i].work_vote_num = divisornum.toFixed(2);
                                _this.worksWholeInfoVoList[i].work_vote_num = _this.worksWholeInfoVoList[i].work_vote_num + "万";
                            }
                            else {

                            }

                            /*时间戳转换*/
                            _this.worksWholeInfoVoList[i].chapter_time = _this.timestampToTime(_this.worksWholeInfoVoList[i].chapter_time);
                            console.log("----"+_this.worksWholeInfoVoList[i].chapter_time)
                        }


                })
                .catch(function (error){
                    alert("相应失败");
                })
        },


        /**
         * 点击事件
         */
        //改变主类型信息
        changWorkmian_label(val){
            if(val =="全部"){
                this.worksInfoneed.work_main_label = null;
                this.selectedtips.selected_main_label= "全部";
            }else {
                this.worksInfoneed.work_main_label = val;
                this.selectedtips.selected_main_label= val;
            }
        },
        //点击主类型按钮
        clickWorkmain_label(val){
            //改变做类型，和上传数据类型
            this.changWorkmian_label(val);
            //获取作品信息
            this.startSearchWorkInfo();
        },
        //改变作品状态信息
        changWork_serial_state(val){
            if(val == "全部"){
                this.worksInfoneed.work_serial_state = null;
                this.selectedtips.selected_serial_state= "全部";
            }if(val == "连载") {
                this.worksInfoneed.work_serial_state = 1;
                this.selectedtips.selected_serial_state= "连载";
            }
            if(val == "完结" ){
                this.worksInfoneed.work_serial_state = 2;
                this.selectedtips.selected_serial_state= "完结";
            }
        },
        //点击作品状态按钮
        clickWork_sate(val){
            //改变作品状态，和上传数据类型
            this.changWork_serial_state(val);
            //获取作品信息
            this.startSearchWorkInfo();
        },
        //点击主类型取消按钮
        clickCancelWorkmain_label(){

            this.clickWorkmain_label("全部");
        },
        //点击状态取消按钮
        clickCancelWork_sate(){
            this.clickWork_sate("全部");
        },
        //点击作品时按钮
        gotoDetail(work_id){
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








        /*
        * 工具类
        * */
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



    },
    created:function (){ //页面加载时查询所有
        this.startHtmlContent();
        this.startSearchWorkInfo();
        this.recommendListInfo();
        this.startUserInfo();
    }
})