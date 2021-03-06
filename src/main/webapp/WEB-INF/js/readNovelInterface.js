let readNovelInterface_vm = new Vue({
    el: '#readNovel_id',
    data() {
        return {

            /*
            * 界面数据
            * */
            //控制弹出框
            drawer: false,
            dialogVisible: false,
            //设置数据相应
            readSettingInfo:[],

            //打赏弹框
            rewardWorksDialog: false,


            /**
             * 获取数据
             * */
            //用户信息
            userInfo:[],
            //作者信息
            authorInfo:[],
            //作品信息
            worksInfo:[],
            //目录信息
            catalogInfoVoList:[],
            //章节信息
            chapterInfo:[],

            /*
            *设置中的数据
            */
            settingInfo:{
                setting_id:'',
                user_id:'',
                setting_theme:'1',
                setting_font_type:'1',
                setting_font_size:'18',
            },


            arr:[],

        }
    },
    methods: {

        /*
        * 初始化界面信息
        * */

        /*初始化界面*/
        startreadNovelhtml(){
            //获取用户信息
            this.getUserInfo();
            //获取作品信息
            this.getworksInfoList();
            //获取作者信息
            this.getAuthorInfo();
            //获取目录信息
            this.getchaptercatalogInfo();
            /*获取章节信息*/
            this.getChapterInfo();
            /*获取设置信息*/
            this.startSettingInfo()

        },

        /*获取用户信息*/
        getUserInfo(){
            var _this = this;
            axios.get('/shaohuashuwu/userInfoController/getUserLoginInfo')
                .then(function (response){
                    _this.userInfo = response.data;
                })
                .catch(function (error){
                    alert("相应失败");
                })
        },
        /*获取作品信息*/
        getworksInfoList(){
            var _this = this;
            //获取作品信息
            axios.post('/shaohuashuwu/worksInfoController/getworkInfoByChapter_id')
                .then(function (response) {
                    _this.worksInfo = response.data;


                    _this.addToReadingHistory();
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        /*获取作者信息*/
        getAuthorInfo(){
            var _this = this;
            //获取作者信息
            axios.post('/shaohuashuwu/userInfoController/getauthorInfoBychapterid')
                .then(function (response) {
                    _this.authorInfo = response.data;
                    console.log("作者信息--："+JSON.stringify(_this.authorInfo));
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        /*获取目录信息*/
        getchaptercatalogInfo(){
            var _this = this;
            //传入作品id，获取目录
            axios.post('/shaohuashuwu/catalogInfoVoController/getchaptercatalogBychapter_id')
                .then(function (response) {
                    _this.catalogInfoVoList = response.data;
                    console.log("作品目录信息--："+JSON.stringify(_this.catalogInfoVoList));

                })
                .catch(function (error) {
                    console.log(error);
                    alert("相应失败");
                })
        },
        /*获取章节信息*/
        getChapterInfo(){
            var _this = this;
            //获取章节信息
            axios.post('/shaohuashuwu/chapterInfoController/getChapterInfoByChapter_id')
                .then(function (response) {
                    _this.chapterInfo = response.data;


                    if(_this.chapterInfo.chapter_content != null){
                        _this.chapterInfo.chapter_content = _this.chapterInfo.chapter_content.replace(/。/g, "。<br/>"+"&nbsp;"+"&nbsp;")
                        _this.chapterInfo.chapter_content = _this.chapterInfo.chapter_content.replace(/”/g, "“<br/>"+"&nbsp;"+"&nbsp;")

                    }

                })
                .catch(function (error){
                    console.log(error);
                    alert("章节信息相应失败");
                })
        },
        /*获取设置信息*/
        startSettingInfo(){
            var _this = this;
            axios.post('/shaohuashuwu/readSettingInfoController/getReadSettinginfo')
                .then(function (response) {
                    _this.readSettingInfo = response.data;

                    _this.settingTransformation();
                    _this.settringChangeInfo();
                    _this.settingNeed();
                })
                .catch(function (error){
                    console.log(error);
                })
        },


        //存入阅读历史
        addToReadingHistory(){
            console.log("存储阅读历史-------------")
            axios.post("/shaohuashuwu/readingHistoryInfoController/addToReadingHistory/" +
                 this.worksInfo.work_id ).then(resp => {
                let Result = resp.data;
                console.log("数据同步存到数据库。"+Result);

            }).catch(error => {
                console.log("数据同步存到数据库失败。"+error);
            });
        },




        /*
        * 点击事件
        * */
        //点击打开章节信息
        openchapterCatalogbutton(){
            this.drawer=true;
        },
        //打开设置按钮
        openreadSettingbutton(){
            this.dialogVisible = true;
        },
        //点击某一章节按钮
        clickchatpter_name(chapter_id){
            var _this = this;
            axios.post('/shaohuashuwu/chapterInfoController/saveChapter_idSession?chapter_id='+ chapter_id)
                .then(function (response) {
                })
                .catch(function (error){
                    console.log(error);
                })
            _this.getChapterInfo();
        },
        //设置里点击-阅读主题
        settingThemeclick(val){
            this.settingInfo.setting_theme = val;

            this.settingNeed();
            this.settringChangeInfo();
        },
        //设置里点击-正文字体
        settingFont(val){
            this.settingInfo.setting_font_type = val;

            this.settingNeed();
            this.settringChangeInfo();
        },
        //设置里点击-字体大小
        settingFontSize(val){
            if (val == 2) {
                if (this.settingInfo.setting_font_size + 2 <= 38) {
                    this.settingInfo.setting_font_size = this.settingInfo.setting_font_size + 2;
                }
                else {
                    this.settingInfo.setting_font_size = this.settingInfo.setting_font_size;
                }

            }
            if (val == 1) {
                if (this.settingInfo.setting_font_size - 2 >= 12) {
                    this.settingInfo.setting_font_size = this.settingInfo.setting_font_size - 2;
                }
                else {
                    this.settingInfo.setting_font_size = this.settingInfo.setting_font_size;
                }

            }

            this.settingNeed();
            this.settringChangeInfo();
        },
        //设置里点击-确定按钮
        saveSettingInfo(){
            var _this = this;
            this.readSettingInfo = this.settingInfo;
            axios.post('/shaohuashuwu/readSettingInfoController/updateReadSettingInfoByuser_id',_this.readSettingInfo)
                .then(function (response) {
                    _this.dialogVisible = false;
                })
                .catch(function (error){
                    console.log(error);
                })

        },
        //设置里点击-取消按钮
        notsaveSettingInfo(){
            this.dialogVisible = false;
            this.settingInfo = this.readSettingInfo;

            this.settingTransformation();
            this.settringChangeInfo();
            this.settingNeed();
        },
        //点击下一章按钮
        nextchapter(){

            //获取下一章章节
            var chapter_id_before=this.chapterInfo.chapter_id;
            var chaptertatol=Object.keys(this.catalogInfoVoList).length;
            var chapter_id_num;
            var chapter_id;

            for(var i = 0;i< chaptertatol ;i++){
                if(this.catalogInfoVoList[i].chapter_id === chapter_id_before){
                    chapter_id_num = 1+i;
                    break;
                }else {
                    continue;
                }
            }
            chapter_id = this.catalogInfoVoList[chapter_id_num].chapter_id;

            var _this = this;
            axios.post('/shaohuashuwu/chapterInfoController/saveChapter_idSession?chapter_id='+ chapter_id)
                .then(function (response) {

                })
                .catch(function (error){
                    console.log(error);
                })
            /*加载下一章节*/
            _this.getChapterInfo();
        },

        //点击订阅按钮
        clicksubscribeChapter(){
            console.log("订阅点击")

            //先获取用户的金豆书
            axios.get("/shaohuashuwu/userInfoController/getGoldBeanNum").then(resp => {
                var object = JSON.stringify(resp.data);
                let data = parseInt(object);

                if (data >= 10) {
                    let data2 = -10;
                    let data3 = 10;
                    this.$confirm('此操作将扣除您10金豆数您的金豆剩余：'+data+',是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        //订阅章节事务回滚处理！
                        axios.get("/shaohuashuwu/transactionInfoController/subscribeAChapterGUN/" +
                            this.userInfo.user_id + "/" + data2 + "/" + this.chapterInfo.chapter_id + "/" + data3+"/"+this.worksInfo.work_id).then(resp => {
                            if (resp.data === true) {
                                this.$message({
                                    type: 'success',
                                    message: '订阅成功!'
                                });
                                //重定向本界面
                                 window.location.assign("../pages/readNovelInterface.html");
                            }
                        }).catch(error => {
                            console.log(error);
                            alert("网络异常！");
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消订阅'
                        });
                    });
                } else {
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

                }
            }).catch(error => {
                console.log(error);
            });

        },

        //点击更多订阅
        clicksubscribeMoreChapter(){
            window.location.assign("../pages/subscribeToNovelsInterface.html");
        },


        /*
        * 界面相应数据信息
        * */
        //将获取的设置信息转化到settingInfo中
        settingTransformation(){
            this.settingInfo = this.readSettingInfo;
            console.log("输出----------："+JSON.stringify(this.settingInfo));
        },
        //依据设置改变背景
        settringChangeInfo(){
            //主题背景设置
            if(this.settingInfo.setting_theme == 1){
                document.getElementById("htmlbody_id").style.background = "#f6f1e7";
            }
            if(this.settingInfo.setting_theme == 2){
                document.getElementById("htmlbody_id").style.background = "#f8f1da";
            }
            if(this.settingInfo.setting_theme == 3){
                document.getElementById("htmlbody_id").style.background = "#e1ede1";

            }
            if(this.settingInfo.setting_theme == 4){
                document.getElementById("htmlbody_id").style.background = "#e1eef2";
            }
            if(this.settingInfo.setting_theme == 5){
                document.getElementById("htmlbody_id").style.background = "#f5e4e4";

            }
            if(this.settingInfo.setting_theme == 6){
                document.getElementById("htmlbody_id").style.background = "#e9e9e9";

            }
            if(this.settingInfo.setting_theme == 7){
                document.getElementById("htmlbody_id").style.background = "#181a1b";

            }

            //字体设置
            if (this.settingInfo.setting_font_type == 1){
                /*雅黑*/
                document.getElementById("chapter_content_p_id").style.fontFamily = 'Arial';
            }
            if (this.settingInfo.setting_font_type == 2){
                /*宋体*/
                document.getElementById("chapter_content_p_id").style.fontFamily = 'SimSun';
            }

            if (this.settingInfo.setting_font_type == 3){
                /*楷书*/
                document.getElementById("chapter_content_p_id").style.fontFamily = 'KaiTi';
            }

            //字号设置
            var setting_font_size1 = this.settingInfo.setting_font_size;
            document.getElementById("chapter_content_p_id").style.fontSize = this.settingInfo.setting_font_size+'px';


        },
        //设置中-显示选中的状态
        settingNeed(){
            console.log("设置-选中");
            //主题
            var ip;
            for (ip = 1;ip <= 7 ; ip++){
                if(this.settingInfo.setting_theme == ip){
                    document.getElementById("setting_theme_p_"+ip).style.visibility = "visible";
                }else {
                    document.getElementById("setting_theme_p_"+ip).style.visibility = "hidden";
                }
            }
            //正文
            var i;
            for(i = 1;i <= 3 ; i++){
                if(this.settingInfo.setting_font_type == i){
                    document.getElementById("setting_font_type_button_"+i).style.background = "#ef6d6d";
                }else {
                    document.getElementById("setting_font_type_button_"+i).style.background = "#e5e5e5";
                }
            }
        },



        gotoUserMainIterface(){
            window.location.assign("../pages/userMainInterface.html");
        },
        /*全部作品*/
        gotoAllWorksInterface(){
            window.location.assign("../pages/allWorksInterface.html");
        },
        //个人中心
        gotoMysqelfHtml(){
            window.location.assign("../pages/myHomePage.html");
        },
        /*加入书架*/
        gotoBookshelf(){
            window.location.assign("../pages/bookShelfInterface.html");
        },
        /*加入书架*/
        gotoReadingHistory(){
            window.location.assign("../pages/bookShelfInterface.html");
        },

    },
    created:function (){ //页面加载时查询所有
        this.startreadNovelhtml();
    }
})