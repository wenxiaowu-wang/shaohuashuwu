new Vue({
    el: '#addChapter_id',
    data() {
        return {

            /*
            * 获取数据
            * */
            //作品信息
            worksInfo:[],
            //作品目录
            catalogInfoVoList:[],


            /*
            * 界面数据
            * */
            //控制弹出框
            drawer: false,
            //题目字数
            chapter_titleNum:'0',
            //章节内容字数
            chapter_contentNum:'0',
            //给读者话字数
            chapter_other_wordNum:'0',

            //下拉框内容
            labelList:[
                // "免费章节","付费章节",
                '免费章节','付费章节'
            ],
            model_label:'',

            //提交按钮样式控制
            ischapter_title:0,
            ischapter_content:0,
            ischapter_other_word:1,


            input:'',

        //    上传数据
            chapterInfo:{
                chapter_title:'',
                chapter_word_num:0,
                chapter_content:'',
                chapter_other_word:'',
                chapter_charge:'3',
            },
        }
    },
    methods: {

        /*
        * 初始化数据
        * */
        getWorksInfoList(){
            var _this = this;
            console.log("作品信息");
            axios.post('http://localhost:8080/worksInfoController/getworkInfoByWork_id')
                .then(function (response){
                    _this.worksInfo = response.data;
                    console.log("作品信息"+JSON.stringify(_this.worksInfo));
                })
                .catch(function (error){
                    console.log(error);
                })
        },
        //获取作品目录
        getcatalogInfo(){
            var _this = this;
            //传入作品id，获取目录
            axios.post('http://localhost:8080/catalogInfoVoController/getchaptercatalogBywork_id2')
                .then(function (response) {
                    _this.catalogInfoVoList = response.data;
                    console.log("作品目录信息--："+JSON.stringify(_this.catalogInfoVoList));

                })
                .catch(function (error) {
                    console.log(error);
                    alert("相应失败");
                })
        },








        /*
        * 界面数据变化
        * */

        /*
        * 统计爱书
        * */
        //统计题目字数
        getchapter_titleNum(){
            this.chapter_titleNum = document.getElementById("chapter_title_id").value.length;
            if(this.chapter_titleNum > 20 ||  this.chapter_titleNum < 1){
                document.getElementById("chapter_title_Numid").style.color = "red";
                this.ischapter_title = 0;
            }
            if (this.chapter_titleNum <= 20 && this.chapter_titleNum >= 1)
            {
             document.getElementById("chapter_title_Numid").style.color = "#38c438";
             this.ischapter_title = 1;
            }
        },
        //统计章节内容字数
        getchapter_contentNum(){
            this.chapter_contentNum = document.getElementById("chapter_content_id").value.length;
            if(this.chapter_contentNum > 12000 || this.chapter_contentNum <= 1000){
                document.getElementById("chapter_content_Numid").style.color = "red";
                this.ischapter_content = 0;
            }
            if (this.chapter_contentNum <= 12000 && this.chapter_contentNum > 1000)
            {
                this.chapterInfo.chapter_word_num = this.chapter_contentNum;
                document.getElementById("chapter_content_Numid").style.color = "#38c438";
                this.ischapter_content = 1;
            }

        },
        //统计给读者话字数
        getchapter_other_wordNum(){
            this.chapter_other_wordNum = document.getElementById("chapter_other_word_id").value.length;
            if(this.chapter_other_wordNum > 300){
                document.getElementById("chapter_other_word_Numid").style.color = "red";
                this.ischapter_other_word = 0;
            }
            if (this.chapter_other_wordNum <= 300)
            {
                 document.getElementById("chapter_other_word_Numid").style.color = "#38c438";
                this.ischapter_other_word = 1;
            }
        },


        //章节收费状态改变
        chapter_chargeChange(val){
            if(val == '免费章节'){
                this.chapterInfo.chapter_charge = '0';
                this.model_label='免费章节';
            }
            if(val == '付费章节'){
                this.chapterInfo.chapter_charge = '1';
                this.model_label='付费章节';
            }

        },




        /*
        * 点击事件
        * */
        //点击保存按钮
        savechapter(){
            var _this = this;
            axios.post('http://localhost:8080/chapterInfoController/addchapter_info',_this.chapterInfo)
                .then(function (respone) {
                    console.log(respone.data);
                    if(respone.data != 0){
                        alert("添加成功")
                    }
                    else {
                        alert("添加失败")
                    }
                    _this.getcatalogInfo();
                })
                .catch(function (error){
                    console.log(error);

                })


        },




        showcatalogInfo(){
            this.drawer = true;
        },







    },
    created:function (){ //页面加载时查询所有
        this.getWorksInfoList();
        this.getcatalogInfo();
    }
})