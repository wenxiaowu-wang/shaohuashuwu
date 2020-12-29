new Vue({
    // router,
    el: '#workSet_id',
    data() {
        return {
            /*
            * 界面相应数据
            * */
            //界面文字变化
            work_Tips: {
                custom_label:'请选择4个以内自定义标签',
                work_introduct_tip:'20~500个字',
                work_otherword_tip:'0~50个字，给读者展示你的版权页',
            },

            //类型选择系列
            labelList:[
                "玄幻","奇幻","武侠","仙侠","都市","历史","军事","悬疑","科幻","游戏","体育","轻小说","现实"
            ],
            //副类型
            vicelabelList:[],
            xuanhuanlist:[
                "东方玄幻","异世大陆","高武世界","王朝争霸"
            ],
            qihuanlist:[
                "剑与魔法","史诗奇幻","黑暗幻想","现代魔法","历史神话","另类幻想"
            ],
            wuxialist:[
                "传统武侠","武侠幻想","国术无双","古武未来","武侠同人"
            ],
            xianxialist:[
                "修真文明","幻想修仙","现代修真","神话修真","古典仙侠"
            ],
            dushilist:[
                "都市生活","娱乐明星","商战职场","异术超能","都市异能","青春校园"
            ],
            lishilist:[
                "架空历史","两宋元明","外国历史","上古先秦","秦汉三国","两晋隋唐","五代十国","清史民国","历史传记","民间传说"
            ],
            jushilist:[
                "战争幻想","谍战特工","军旅生涯","抗战烽火","军事战争"
            ],
            xuanyilist:[
                "悬疑侦探","诡秘悬疑","探险生存","奇妙世界","古今传奇"
            ],
            kehuanlist:[
                "星际文明","时空穿梭","未来世界","古武机甲","超级科技","进化变异","末世危机"
            ],
            youxilist:[
                "电子竞技","虚拟网游","游戏世界","游戏系统","游戏主播"
            ],
            tiyulist:[
                "体育赛事","篮球运动","足球运动"
            ],
            qingxiaoshuolist:[
                "原生幻想","衍生同人","搞笑吐槽","青春日常"],
            xianshilist:[
                "爱情婚姻","现实百态","社会乡土","生活时尚","文学艺术","成功励志","青春文学"
            ],

            //状态选择
            workStateList:["连载","完结"],

            //可选标签
            optionalList:[
                "豪门","盗贼","黑客","特种兵","法师",
                "盗墓","丧尸","位面","穿越","重生",
                "魔兽","宠物","学生","吸血鬼","龙",
                "鉴宝","丹药","魂魄","神器","剑",
                "嚣张","淡定","阳光","爆笑","冷酷",
                "召唤流","异兽流","洪荒流","学院流","练功流",
            ],

            //界面相应作品信息
            worksInfoVo: {
                work_id:'',
                work_name: '',
                work_cover:'',
                work_main_label: '',
                work_vice_label: '',
                work_introduct: '',
                work_other_word: '',
                work_serial_state:'',
            },

            //封面
            imageUrl: '',
            //作者自定义标签界面
            workslabelInfoVo:[],
            //按钮状态
            work_introductResult:0,
            work_other_wordResult:1,
            customLabelResult:1,


            /*
            * 上传数据
            * */
            //上传照片
            doUpload:'',




            /**
             * 相应数据
             */
            //作品信息
            worksInfo: {
                work_id:'',
                work_name: '',
                work_cover:'',
                work_main_label: '',
                work_vice_label: '',
                work_introduct: '',
                work_other_word: '',
                work_serial_state:'',
            },

            //自定义标签
            customLabelList:[],

            //作者自定义
            workslabelInfo:[
                {
                    work_id: '',
                    labelname:'',
                },
                {
                    work_id: '',
                    labelname:'',
                },
                {
                    work_id: '',
                    labelname:'',
                },
                {
                    work_id: '',
                    labelname:'',
                }

            ],

        }
    },
    methods:{
        /*
       * 界面数据变化
       * */
        //主类型转换后自动转换子类型
        mainlabelchange(val){
            if (val=="玄幻"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.xuanhuanlist;
            }
            if (val=="奇幻"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.qihuanlist;
            }
            if (val=="武侠"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.wuxialist;
            }
            if (val=="仙侠"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.xianxialist;
            }
            if (val=="都市"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.dushilist;
            }
            if (val=="历史"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.lishilist;
            }
            if (val=="军事"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.jushilist;
            }
            if (val=="悬疑"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.xuanyilist;
            }
            if (val=="科幻"){

                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.kehuanlist;
            }
            if (val=="游戏"){

                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.youxilist;
            }
            if (val=="体育"){

                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.tiyulist;
            }
            if (val=="轻小说"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.qingxiaoshuolist;
            }
            if (val=="现实"){
                this.worksInfo.work_vice_label=null;
                this.vicelabelList = this.xianshilist;
            }
        },

        //表单作品介绍校验
        isworkintroduct(){
            var _this=this;
            //正则表达式
            var isTel =/^[\u4e00-\u9fa5a-zA-Z\d,\.，。<>《》`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、\t\s\n]{20,500}$/;
            _this.iswork_introduct = 1;

            if(!_this.worksInfo.work_introduct){
                _this.work_Tips.work_introduct_tip="请输入您的作品介绍";
                document.getElementById("work_introduct_tipid").style.color = "red";
                _this.work_introductResult = 0;
            }
            else if(!isTel.test(_this.worksInfo.work_introduct)){
                _this.work_Tips.work_introduct_tip="请您输入的作品介绍在20-500个字之间";
                document.getElementById("work_introduct_tipid").style.color = "red";
                _this.work_introductResult = 0;
            }
            else{
                _this.work_Tips.work_introduct_tip="√";
                document.getElementById("work_introduct_tipid").style.color = "green";
                //按钮改为可提交状态
                _this.work_introductResult = 1;
            }
        },


        //表单给读者的话校验
        isworkotherwork(){
            var _this=this;
            var isTel =/^[\u4e00-\u9fa5a-zA-Z\d,\.，。<>《》`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、\t\s\n]{0,50}$/;

            if(!isTel.test(_this.worksInfo.work_other_word)){
                _this.work_Tips.work_otherword_tip="请您输入的给读者的话在0-50个字之间";
                document.getElementById("work_otherwork_tipid").style.color = "red";
                _this.work_other_wordResult = 0;
            }
            else{
                _this.work_Tips.work_otherword_tip="0~50个字，给读者展示你的版权页";
                document.getElementById("work_otherwork_tipid").style.color = "#868686";
                _this.work_other_wordResult = 1;

            }

        },


        /*
        * 获取数据
        * */
        /*获取作品信息*/
        selectworkByid() {
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/getworkInfoByWork_id')
                .then(function (respone) {
                    _this.worksInfo = respone.data;
                    _this.worksInfoVo = _this.worksInfo;
                    //设置作品状态
                    if(_this.worksInfoVo.work_serial_state == 1){
                        _this.worksInfoVo.work_serial_state = "连载";
                    }
                    else {
                        _this.worksInfoVo.work_serial_state = "完结";
                    }
                    _this.imageUrl =  _this.worksInfoVo.work_cover;

                })
                .catch(function (error){
                    alert("相应作品信息失败");
                })

        },

        //获取作者自定义标签
        getworkslabelInfo() {

            var _this = this;
            axios.post('http://localhost:8080/workslabelInfoController/getWorkslabelInfoByWork_id')
                .then(function (respone) {
                    _this.workslabelInfoVo = respone.data;
                    for(var j=0;j<_this.workslabelInfoVo.length;j++){
                        _this.customLabelList.push(_this.workslabelInfoVo[j].labelname);
                    }
                })
                .catch(function (error){
                    alert("相应自定义标签失败----");
                })

        },


        /*
        * 点击事件
        * */
        //上传成功后
        handleAvatarSuccess(res, file) {
            /*上传地址*/
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        //上传前
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 5;
            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 5MB!');
            }
            let fd = new FormData();
            fd.append('file',file);//传文件
            var _this = this;
            axios.post('http://localhost:8080/uploadCotroller/oss',fd)
                .then(function (response) {
                    _this.imageUrl= response.data;
                    _this.worksInfoVo.work_cover = response.data;

                })
                .catch(function (error){
                    alert(error);
                    console.log(error);
                })
        },
        //点击修改按钮
        updatebutton(){
            this.updatework_info();
            this.updatecustomLabelList();
            alert("修改成功");
        },

        //修改作品信息
        updatework_info(){
            //将显示信息转化为上传信息
            this.worksInfo = this.worksInfoVo;
            console.log("提交信息"+this.worksInfoVo);
            if(this.worksInfo.work_serial_state == "连载"){
                this.worksInfo.work_serial_state = 1;
            }
            else {
                this.worksInfo.work_serial_state = 2;
            }
            var _this = this;
            //修改信息
            axios.post('http://localhost:8080/worksInfoController/updateworkInfoByWork_id',_this.worksInfo)
                .then(function (response) {
                    _this.selectworkByid();
                })
                .catch(function (error){
                    console.log(error);
                })
        },
        //修改自定义信息
        updatecustomLabelList(){
            var _this = this;
            for (var i = 0;i<_this.customLabelList.length;i++){
                console.log("zidingyi======"+JSON.stringify(_this.customLabelList[i]));
                _this.workslabelInfo[i].labelname=_this.customLabelList[i];
                _this.workslabelInfo[i].work_id =_this.worksInfo.work_id;
            }

            //修改自定义标签信息
            axios.post('http://localhost:8080/workslabelInfoController/updateWorkslabelInfoByWork_id',_this.workslabelInfo)
                .then(function (response) {
                    _this.getworkslabelInfo();
                })
                .catch(function (error){
                    console.log(error);
                })
        },
        //作者自定义标签
        checkedCustomLabel(val){

            if(this.customLabelList.length>4){
                this.work_Tips.custom_label="自定义标签最大数量为4个";
                document.getElementById("work_checkbox_tipid").style.color = "red";
                this.customLabelResult = 0;
            }else{
                this.work_Tips.custom_label="请选择4个以内自定义标签";
                document.getElementById("work_checkbox_tipid").style.color = "#868686";
                this.customLabelResult = 1;
            }

        },



    },
    created:function (){ //页面加载时查询所有
         this.selectworkByid();
         this.getworkslabelInfo();

    }
})