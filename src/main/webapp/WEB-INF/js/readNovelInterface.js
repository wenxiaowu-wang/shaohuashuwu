new Vue({
    el: '#app',
    data() {
        return {
            dialogVisible: false,

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
            //相应数据
            readSettingInfo:[],
            chapterInfo:[],
            worksInfo:[],
            userInfo:[],
            chapterInfo:[],





        }
    },
    methods: {

        /**
         * 初始化界面，依据章节id获取章节信息、作品信息、作者信息。
         */
        startChapterInfo(){
            //获取作品信息
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/selectworkInfoByChapter_id')
                .then(function (response) {
                    _this.worksInfo = response.data;
                    console.log("相应结果aa："+JSON.stringify(_this.worksInfo));


                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
            //获取作者信息
            axios.post('http://localhost:8080/userInfoController/selectuserInfoByuserid')
                .then(function (response) {
                    _this.userInfo = response.data;
                    console.log("相应结果aa："+JSON.stringify(_this.userInfo));


                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
            //获取章节信息
            axios.post('http://localhost:8080/chapterInfoController/selectUserInfoByChapter_id')
                .then(function (response) {
                    _this.chapterInfo = response.data;
                    console.log("相应章节："+JSON.stringify(_this.chapterInfo));


                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },


        /**
         * 设置中进行的操作
         * data：    settingInfo：作用显示数据
         *          readSettingInfo：作用传输数据，与后端进行数据交换
         *
         * 操作1
         * 1.先获取设置，初始化设置，将获取信息保存进入data-- readSettingInfo中，
         * 同时调用1.2,1.3
         * 2.显示获取的数据，settingInfo=readSettingInfo中
         * 3.依据设置改变背景，settringChangeInfo，显示界面
         *
         * 操作2
         * 1. 点击设置按钮后dialogVisible = true，显示设置
         * 2.点击一个主题后，改变settingInfo，同时调用1.3
         * 3.点击一个字体后，改变settingInfo，同时调用1.3
         * 4.点击一个字号后，改变settingInfo，同时调用1.3
         *
         *操作3
         * 1.点击确定按钮后调用saveSettingInfo，将数据readSettingInfo=settingInfo，将数据传递给后端
         * 同时调用1.3
         *
         * 操作4
         * 1.点击取消按钮，让settingInfo=readSettingInfo，同时调用1.3
         *
         */
        /*
        *设置中的方法
        */
        //初始化设置，初始化界面
        startSettingInfo(){
            var _this = this;
            axios.post('http://localhost:8080/readSettingInfoController/selectReadSettinginfo')
                .then(function (response) {
                    _this.readSettingInfo = response.data;
                    console.log("相应结果aa："+JSON.stringify(_this.readSettingInfo));

                    _this.settingTransformation();
                    _this.settringChangeInfo();

                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },
        //将获取的设置信息转化到settingInfo中
        settingTransformation(){
            this.settingInfo = this.readSettingInfo;
            console.log("输出----------："+JSON.stringify(this.settingInfo));
        },
        //依据设置改变背景
        settringChangeInfo(){
            //主题背景
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

            console.log("setting_font_type输出---:"+this.settingInfo.setting_font_type);
            //字体
            if (this.settingInfo.setting_font_type == 1){
                console.log("字体宋体");
                document.getElementById("chapter_content_p_id").style.fontFamily = 'Arial';
            }
            if (this.settingInfo.setting_font_type == 2){
                console.log("字体宋体");
                document.getElementById("chapter_content_p_id").style.fontFamily = 'SimSun';
            }

            if (this.settingInfo.setting_font_type == 3){
                console.log("字体宋体");
                document.getElementById("chapter_content_p_id").style.fontFamily = 'KaiTi';
            }



            //字号
            var setting_font_size1 = this.settingInfo.setting_font_size;
                document.getElementById("chapter_content_p_id").style.fontSize = this.settingInfo.setting_font_size+'px';


        },
        //设置按钮
        readSettingbutton(){

            this.dialogVisible = true;
            // this.settingNeed();
            console.log("设置------------");
            console.log("设置");
            // document.getElementById("setting_font_type_button_1").checked = true;
            //初始化设置按钮

        },
        //设置-阅读主题
        settingThemeclick(val){

            // this.settingThemeInfoTemporaryporary(val);
            // document.getElementById("htmlbody_id").style.background = "#181a1b";
            console.log("--"+val);
            console.log("1++++++++"+this.settingInfo.setting_theme);
            console.log("2++++++++++"+this.readSettingInfo.setting_theme);
            this.settingInfo.setting_theme = val;
            console.log("1"+this.settingInfo.setting_theme);
            console.log("2"+this.readSettingInfo.setting_theme);
            this.settingNeed();
            this.settringChangeInfo();

        },
        //设置-正文字体
        settingFont(val){
            console.log("--"+val);
            this.settingInfo.setting_font_type = val;
            console.log("+++"+this.settingInfo.setting_font_type);
            this.settingNeed();
            this.settringChangeInfo();
        },
        //设置-字体大小
        settingFontSize(val){
            console.log("字体大小："+val);
            if (val == 2) {
                if (this.settingInfo.setting_font_size + 2 <= 38) {
                    console.log("应该加：+2");
                    this.settingInfo.setting_font_size = this.settingInfo.setting_font_size + 2;
                }
                else {
                    console.log("bu应该加：+2");
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

            // this.settingInfo.setting_font_size = val;
            this.settingNeed();
            this.settringChangeInfo();
        },
        //设置-选中
        settingNeed(){

            console.log("设置-选中");
            //主题
            var ip;
            for (ip = 1;ip <= 7 ; ip++){
                if(this.settingInfo.setting_theme == ip){
                    console.log("-+-"+ip);
                    document.getElementById("setting_theme_p_"+ip).style.visibility = "visible";
                }else {
                    document.getElementById("setting_theme_p_"+ip).style.visibility = "hidden";
                }
            }

            //正文
            var i;
            for(i = 1;i <= 3 ; i++){
                if(this.settingInfo.setting_font_type == i){
                    console.log("-+-"+i);
                    document.getElementById("setting_font_type_button_"+i).style.background = "#ef6d6d";
                }else {
                    document.getElementById("setting_font_type_button_"+i).style.background = "#e5e5e5";
                }
            }
            //字体大小
            // var j;

        },
        //点击确定设置按钮
        saveSettingInfo(){
            var _this = this;
            this.readSettingInfo = this.settingInfo;
            console.log("确认设置按钮---:");
            console.log(_this.readSettingInfo);
            console.log("确认设置按钮"+JSON.stringify(_this.readSettingInfo));


            axios.post('http://localhost:8080/readSettingInfoController/updateReadSettingInfoByid',_this.readSettingInfo)
                .then(function (response) {
                    // _this.readSettingInfo = response.data;
                    // console.log("相应结果aa："+JSON.stringify(_this.readSettingInfo));
                    //
                    //
                    console.log(response.data);

                    _this.dialogVisible = false;

                    // this.startSettingInfo();


                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })

        },
        //点击取消设置按钮
        notsaveSettingInfo(){
            console.log("点击取消按钮");
            console.log("1"+JSON.stringify(this.settingInfo));
            console.log("2"+JSON.stringify(this.readSettingInfo));
            this.dialogVisible = false;
            this.settingInfo = this.readSettingInfo;
            console.log("3"+JSON.stringify(this.settingInfo));
            // this.settingTransformation();
            this.settringChangeInfo();
        },


    },
    created:function (){ //页面加载时查询所有
        this.startSettingInfo();
        this.startChapterInfo();
    }
})