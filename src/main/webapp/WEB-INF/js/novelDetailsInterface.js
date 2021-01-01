let novelDetailsInterface_vm = new Vue({
    el: '#novelDetailsInterface_id',
    data() {
        return {
            /**
             * 界面数据变化
             * */
            //背景图片变化
            showbackgroundInfo:'',
            //类型描述信息变化
            typedescribeInfo:'',
            //评论显示
            dialogFormVisible: false,
            dialogVisible: false,



            /*
            * 上传数据
            * */
            textarea_reply:'',
            textarea:'',

            /*
            * 获取数据
            * */
            //顶部用户信息
            userInfo:[],
            //作品信息
            worksInfo: [],
            //作者信息
            authorInfo:[],
            //作者的其他作品信息
            authorOtherWorksInfoList:[],
            //章节目录信息
            catalogInfoVoList: [],
            //最新章节信息
            newChapterInfoVo:[],
            //累计作品数量
            otherWorkNum:'',
            //累计作品总字数
            otherWorkwork_word_num:'',
            //作者自定义
            workslabelInfoVoList:[],
            //作者自定义数量
            workslabelInfoVoNum:0,

            /*
            *上传数据
             */

            userinterestInfo:{

                user_id:'',
                label_name:'',
                select_num:1,
            },



            /*
            *郝振威
            *
            *
            * */


            commentChildData:[],
            commentData:[],




            activeIndex: '1',
            drawer: false,
        }
    },
    methods: {

        /*
        * 初始化界面
        * */

        /*获取用户信息*/
        getUserInfo(){
            var _this = this;
            axios.get('/shaohuashuwu/userInfoController/getUserLoginInfo')
                .then(function (response){
                    _this.userInfo = response.data;
                    console.log("用户信息--："+JSON.stringify(_this.userInfo));
                })
                .catch(function (error){
                    alert("相应失败");
                })
        },
        /*获取作品信息*/
        getworksInfoList(){
            var _this = this;
            //获取作品信息
            axios.post('/shaohuashuwu/worksInfoController/getworkInfoByWork_id')
                .then(function (response) {
                    _this.worksInfo = response.data;
                    _this.userinterestInfo.label_name = _this.worksInfo.work_main_label;
                    _this.adduserInterestInfo();

                    /*当字数和订阅数大于10000时，*/
                    if(_this.worksInfo.work_word_num >100000 ){
                        _this.worksInfo.work_word_num = (_this.worksInfo.work_word_num/10000).toFixed(2);
                        _this.worksInfo.work_word_num = _this.worksInfo.work_word_num + '万';
                    };
                    if(_this.worksInfo.work_vote_num >100000){
                        _this.worksInfo.work_vote_num = (_this.worksInfo.work_vote_num/10000).toFixed(2);
                        _this.worksInfo.work_vote_num = _this.worksInfo.work_vote_num + '万';
                    };
                    /*依据作品类型改变背景*/
                    _this.changebackgroudInfo(_this.worksInfo.work_main_label);
                    /*依据作品状态改变作品*/
                    if(_this.worksInfo.work_serial_state == 1){
                        _this.worksInfo.work_serial_state ="连载";
                    }
                    else {
                        _this.worksInfo.work_serial_state ="完结";
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        /*获取作者信息*/
        getAuthorInfo(){
            var _this = this;
            //获取作者信息
            axios.post('/shaohuashuwu/userInfoController/getUserInfoByWork_id')
                .then(function (response) {
                    _this.authorInfo = response.data;
                    console.log("作者信息--："+JSON.stringify(_this.authorInfo));
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        /*获取作者其他作品信息*/
        getOtherWorkInfo(){
            var _this = this;
            //获取作者其他作品信息
            axios.post('/shaohuashuwu/worksInfoController/getOtherWorkInfoByWork_id')
                .then(function (response) {
                    _this.authorOtherWorksInfoList = response.data;
                    /*获取作品数量*/
                    _this.otherWorkNum=_this.authorOtherWorksInfoList.length;
                    /*累计每一个作品字数增加字数*/
                    var allwork_word_num = 0;
                    for( var i = 0 ;i<_this.authorOtherWorksInfoList.length;i++){
                        allwork_word_num   = allwork_word_num +  _this.authorOtherWorksInfoList[i].work_word_num;
                    }
                    /*字数大于10万字就变为小数*/
                    if(allwork_word_num>100000){
                        var divisornum = allwork_word_num/10000;
                        _this.otherWorkwork_word_num = divisornum.toFixed(2);
                        _this.otherWorkwork_word_num = _this.otherWorkwork_word_num + "万";
                    }
                    else {
                        _this.otherWorkwork_word_num = allwork_word_num;
                    }

                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        /*获取目录信息*/
        getchaptercatalogInfo(){
            var _this = this;
            //传入作品id，获取目录
            axios.post('/shaohuashuwu/catalogInfoVoController/getchaptercatalogBywork_id')
                .then(function (response) {
                    _this.catalogInfoVoList = response.data;
                    console.log("作品目录信息--："+JSON.stringify(_this.catalogInfoVoList));

                })
                .catch(function (error) {
                    console.log(error);
                    alert("相应失败");
                })
        },
        /*获取最新章节信息*/
        getnewChapterByword_id(){
            var _this = this;
            //获取最新章节信息
            axios.post('/shaohuashuwu/chapterInfoController/getnewChapterInfoByword_id')
                .then(function (response) {
                    _this.newChapterInfoVo = response.data;
                    console.log("最新章节信息--："+JSON.stringify(_this.newChapterInfoVo));
                })
                .catch(function (error) {
                    console.log(error);
                })
        },
        /*获取作品兴趣界面*/
        //获取作者自定义标签
        getworkslabelInfo() {
            var _this = this;
            axios.post('/shaohuashuwu/workslabelInfoController/getWorkslabelInfoByWork_id')
                .then(function (respone) {
                    _this.workslabelInfoVoList = respone.data;
                    console.log("作品兴趣标签信息--"+JSON.stringify(_this.workslabelInfoVoList));

                    _this.workslabelInfoVoNum = _this.workslabelInfoVoList.length;
                })
                .catch(function (error){
                    alert("相应自定义标签失败----");
                })
        },

        /*添加用户兴趣*/
        adduserInterestInfo(){
            var _this = this;
            axios.post('/shaohuashuwu/userInteresInfoController/updateUserInterestInfo',_this.userinterestInfo)
                .then(function (respone) {

                })
                .catch(function (error){
                    alert("添加用户兴趣失败----");
                })

        },






        /*
        * 初始化评论---------郝振威
        *
        *
        *
        *
        * */
        getComent(){

            var _this = this;
            axios.get("/shaohuashuwu/commentInfoController/getCommentParentInfoByWorkId/" + this.worksInfo.work_id).then(resp3 => {

                console.log("1-----------------" + JSON.stringify(resp3.data));
                _this.commentData = resp3.data;
                console.log("1-1111111111111----------------" );
                console.log("1-----------------" + JSON.stringify(_this.commentData));



            }).catch(error => {
                console.log("获取父级评论信息失败:" + error);
            });

            axios.get("/shaohuashuwu/commentInfoController/getCommentChildInfoByWorkId/" + this.worksInfo.work_id).then(resp3 => {

                let objectData = eval(JSON.stringify(resp3.data));//将字符串转化为数组对象

                console.log("2=================" + JSON.stringify(resp3.data));

                _this.commentChildData = resp3.data;

                console.log("2=================" + JSON.stringify(_this.commentChildData ));

            }).catch(error => {
                console.log("获取父级评论信息失败:" + error);
            });
        },



        /*
        * 点击事件
        * */

        /*点击开始阅读，进入*/
        gotoreadNovelInterface() {
            var fristchapter = this.catalogInfoVoList[0].chapter_id;
            console.log("章节id-------------------------------------"+fristchapter);
            //传入章节id，进入阅读该章节页面
             this.gotoreadChapterInfo(fristchapter);
        },

        /*进入需要的章节*/
        gotoneedchapter(chapter_id){
            this.gotoreadChapterInfo(chapter_id);
        },

        //加入书架
        addToBookshelf(){
            console.log("上传信息======")
            var user_id= this.userInfo.user_id;
            var work_id = this.worksInfo.work_id;
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

        //点击评论作品
        commentbook(){

            console.log("评论按钮1====")
            this.dialogVisible = true;
            console.log("评论按钮2----")

        },






        /**
         * 公用类
         * */

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
          * 界面相应方法
          * */

        /*背景相应变化*/
        changebackgroudInfo(val){
            console.log("背景类型--------------："+val);
            console.log("背景类型--------------："+JSON.stringify(val));
            if (val=="玄幻"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/xuanhuanbackground.jpg';
                this.typedescribeInfo = '心潮澎湃，无限幻想，迎风挥击千层浪，少年不败热血!';
            }
            if (val=="奇幻"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/qihuanbackground.png';
                this.typedescribeInfo = '魔兽践踏，巨龙咆哮，巫师诅咒，魔法璀璨之光照耀知识灯塔！';
            }
            if (val=="武侠"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/wuxiabackground.jpg';
                this.typedescribeInfo = '借三尺明月，衔两袖青龙，轻剑快马恣意，携侣江湖同游！';
            }
            if (val=="仙侠"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/xianxiabackground.jpg';
                this.typedescribeInfo = '修仙觅长生，热血任逍遥，踏莲曳波涤剑骨，凭虚御风塑圣魂！';
            }
            if (val=="都市"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/dushibackground.jpg';
                this.typedescribeInfo = '重生过去、畅想未来、梦幻现实，再塑传奇人生！';
            }
            if (val=="历史"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/lishibackground.jpg';
                this.typedescribeInfo = '醒掌天下权，醉卧美人膝，五千年风华烟雨，是非成败转头空！';
            }
            if (val=="军事"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/junshibackground.jpg';
                this.typedescribeInfo = '热血战斗，保家卫国，誓死守护，傲骨无双铸军魂！';
            }
            if (val=="悬疑"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/xuanyibackground.jpg';
                this.typedescribeInfo = '考古探险、鉴宝收藏，侦探推理、诡秘分析，戏说传承古今中外的民间悬疑文化！';
            }
            if (val=="科幻"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/kehuanbackground.jpg';
                this.typedescribeInfo = '星海漫游，时空穿梭，机械科技，目标是未知的星辰大海！';
            }
            if (val=="游戏"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/youxibackground.jpg';
                this.typedescribeInfo = '肆意挥洒激情的游戏人生，打破现实框架的无尽幻想！';
            }
            if (val == "体育"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/tiyubackground.jpg';
                this.typedescribeInfo = '体坛多英雄，赛场我为王，热血澎湃的竞技体育';
            }
            if (val=="现实"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/xianshibackground.jpg';
                this.typedescribeInfo = '青春寄语，生活时尚，乡土文化，展现现实百态！';
            }
            if (val=="轻小说"){
                this.showbackgroundInfo = 'https://shaohuashuwu.oss-cn-beijing.aliyuncs.com/detailsBackgroud/qingxiaoshuobackground.png';
                this.typedescribeInfo = '热血的少年，为打破次元壁一往无前！';
            }

        },



        /**
         * 无用
        * */
        handleSelect(key, keyPath) {
            console.log("选择" + key, keyPath);
        },



    },
    created: function () { //页面加载时查询所有
        // this.satrtnovelDetailsInterface();
        this.getUserInfo();
        this.getworksInfoList();
        this.getAuthorInfo();
        this.getOtherWorkInfo();
        this.getnewChapterByword_id();
        this.getchaptercatalogInfo();
        this.getworkslabelInfo();

        this.getComent();




    }
});