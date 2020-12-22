
new Vue({

    el: '#app',
    data() {
        return {

            /*
            * 表单提交内容
            * */
            //表单内容提交系列
            worksInfo: {
                work_name: '',
                work_main_label: '',
                work_vice_label:'',
                work_introduct: '',
                work_other_word:'',
            },

            /*
            * 界面相应内容
            * */
            //文本提示系列
            work_Tips: {
                work_name_tip: '15字以内书名，请勿添加书名号等特殊字符',
                work_label_tip:'请选择您创作的作品类型',
                work_introduct_tip:'20~500个字',
                work_otherword_tip:'0~50个字，给读者展示你的版权页',
            },
            //类型选择系列
            labelList:[
                "玄幻","奇幻","武侠","仙侠","都市","历史","军事","悬疑","科幻","游戏","体育","轻小说","现实"
            ],
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

            //提交按钮控制系列，作用用于控制提交按钮是否可以被选中
            iswork_name:[1],
            iswork_introduct:[1],
            iswork_otherword:[1],
            issubmitFrom_button:[1],







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




        /*
        * 失去焦点事件
        * */
        //异步请求判断作品是否存在,和作品输入是否符合规范
        isworkname(){
            var _this=this;
            //正则表达式，作品名称在1-10字
            var isTel =/^[\u4e00-\u9fa5]{1,10}$/;
            //初始化判断作品名称是否符合规范，用于控制提交按钮
            _this.iswork_name= 1;
            //判断是否为空，是否为规定长度汉字，判断作品是否存在
            if(!_this.worksInfo.work_name){

                _this.work_Tips.work_name_tip="请输入您要创建的作品名称";
                document.getElementById("work_name_tipid").style.color = "red";
            }
            else if(!isTel.test(_this.worksInfo.work_name)){
                _this.work_Tips.work_name_tip="请输入1-10个汉字";
                document.getElementById("work_name_tipid").style.color = "red";
            }
            else{
                /*异步判断作品是否存在*/
                axios.post('http://localhost:8080/worksInfoController/isworkname',_this.worksInfo)
                    .then(function (response){
                        _this.iswork_name=response.data;
                        //作品存在和不存在时的处理
                        if(_this.iswork_name > 0){
                            _this.work_Tips.work_name_tip="这本大作已经有人写过了，再想个名字吧！";
                            document.getElementById("work_name_tipid").style.color = "red";
                        }else{
                            _this.work_Tips.work_name_tip="√";
                            document.getElementById("work_name_tipid").style.color = "green";
                        }
                    })
                    .catch(function (error){
                        console.log(error);
                    })

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
            }
            else if(!isTel.test(_this.worksInfo.work_introduct)){
                _this.work_Tips.work_introduct_tip="请您输入的作品介绍在20-500个字之间";
                document.getElementById("work_introduct_tipid").style.color = "red";
            }
            else{
                _this.work_Tips.work_introduct_tip="√";
                document.getElementById("work_introduct_tipid").style.color = "green";
                //按钮改为可提交状态
                _this.iswork_introduct = 0;
            }
        },


        //表单给读者的话校验
        isworkotherwork(){
            var _this=this;
            var isTel =/^[\u4e00-\u9fa5a-zA-Z\d,\.，。<>《》`~!@#$%^&*()_\-+=<>?:"{}|,.\/;'\\[\]·~！@#￥%……&*（）——\-+={}|《》？：“”【】、；‘'，。、\t\s\n]{0,50}$/;
            _this.iswork_otherword = 1;


            if(!isTel.test(_this.worksInfo.work_other_word)){
                _this.work_Tips.work_otherword_tip="请您输入的给读者的话在0-50个字之间";
                document.getElementById("work_otherwork_tipid").style.color = "red";
            }
            else{
                _this.work_Tips.work_otherword_tip="0~50个字，给读者展示你的版权页";
                document.getElementById("work_otherwork_tipid").style.color = "#868686";
                _this.iswork_otherword = 0;
            }

        },




        /*
        * 点击事件
        * */
        //点击提交按钮
        submitForm(formName) {
            var _this= this;
            axios.post('http://localhost:8080/worksInfoController/addworkInfo',this.worksInfo)
                .then(function (response) {
                    if(response.data == 0){
                        alert("对不起，创建作品："+_this.worksInfo.work_name+" -失败")
                    }
                    else {
                        alert("恭喜您，创建作品："+_this.worksInfo.work_name+" -成功")
                        window.location.assign("../pages/worksMangagementInterface.html")
                    }
                })
                .catch(function (error){
                    console.log(error);

                })

        },


        //重置表单
        resetForm(worksInfo) {
            this.worksInfo.work_name=null;
            this.worksInfo.work_main_label=null;
            this.worksInfo.work_vice_label=null;
            this.worksInfo.work_introduct=null;
            this.worksInfo.work_other_word=null;
            this.work_Tips.work_otherword_tip="0~32个字，给读者展示你的版权页";
            document.getElementById("work_otherwork_tipid").style.color = "#868686";
            this.work_Tips.work_introduct_tip="20~500个字";
            document.getElementById("work_introduct_tipid").style.color = "#868686";
            this.work_Tips.work_name_tip="15字以内书名，请勿添加书名号等特殊字符";
            document.getElementById("work_name_tipid").style.color = "#868686";

        },








        //提交按钮状态判定

        



    }
})