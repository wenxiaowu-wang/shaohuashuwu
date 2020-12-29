new Vue( {
    el: '#allWorksInterface_id',
    data() {
        return {
            /**
             * 界面响应数据*/
            //当前页
            currentPage:'',
            //主类型标签
            labelList: [
                "全部", "玄幻", "奇幻", "武侠", "仙侠", "都市", "历史", "军事", "悬疑", "科幻", "游戏", "体育", "现实", "轻小说"
            ],
            //副类型标签
            vicelabelList: [],
            xuanhuanlist: [
                "东方玄幻", "异世大陆", "高武世界", "王朝争霸"
            ],
            qihuanlist: [
                "剑与魔法", "史诗奇幻", "黑暗幻想", "现代魔法", "历史神话", "另类幻想"
            ],
            wuxialist: [
                "传统武侠", "武侠幻想", "国术无双", "古武未来", "武侠同人"
            ],
            xianxialist: [
                "修真文明", "幻想修仙", "现代修真", "神话修真", "古典仙侠"
            ],
            dushilist: [
                "都市生活", "娱乐明星", "商战职场", "异术超能", "都市异能", "青春校园"
            ],
            lishilist: [
                "架空历史", "两宋元明", "外国历史", "上古先秦", "秦汉三国", "两晋隋唐", "五代十国", "清史民国", "历史传记", "民间传说"
            ],
            jushilist: [
                "战争幻想", "谍战特工", "军旅生涯", "抗战烽火", "军事战争"
            ],
            xuanyilist: [
                "悬疑侦探", "诡秘悬疑", "探险生存", "奇妙世界", "古今传奇"
            ],
            kehuanlist: [
                "星际文明", "时空穿梭", "未来世界", "古武机甲", "超级科技", "进化变异", "末世危机"
            ],
            youxilist: [
                "电子竞技", "虚拟网游", "游戏世界", "游戏系统", "游戏主播"
            ],
            tiyulist: [
                "体育赛事", "篮球运动", "足球运动"
            ],
            qingxiaoshuolist: [
                "原生幻想", "衍生同人", "搞笑吐槽", "青春日常"
            ],
            xianshilist: [
                "爱情婚姻", "现实百态", "社会乡土", "生活时尚", "文学艺术", "成功励志", "青春文学"
            ],
            //连载状态标签
            serialstateList: [
                "全部","连载","完结"
            ],
            //已选分类
            selectedtips:{
                selected_main_label:'',
                selected_vice_label:'',
                selected_serial_state:''
            },

            /**
             * 提交数据
             * */
            //上传用户所选分类数据
            worksInfoneed:{
                work_main_label:'',
                work_vice_label:'',
                work_serial_state:'',
                works_page: '',
                works_page_num:8,
            },


            /**
             * 接收数据
             */
            //作品信息
            worksInfoList:[],
            //作品数量
            total:0,

        }

    },
    methods: {

        /*
        * 初始化界面
        * */
        startallWorksInterfaceHtml(){
            this.mainSelected();
            this.stateSelected();
        },




        /*****************8*/

        /*
        * 点击事件
        * */
        /**
         *点击主类型标签
         * */
        mianwork(val){

            //设置标签提交数据
            this.worksInfoneed.work_main_label = val;
            this.worksInfoneed.work_vice_label = null;
            //设置已选标签数据
            this.selectedtips.selected_main_label = val;

            //设置当前处于第一页
            this.currentPage = 1;
            //设置提交数据
            this.worksInfoneed.works_page = 1;

            if (val=="全部"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_main_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签
                this.vicelabelList = null;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="玄幻"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.xuanhuanlist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="奇幻"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.qihuanlist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="武侠"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.wuxialist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="仙侠"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.xianxialist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="都市"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.dushilist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="历史"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.lishilist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="军事"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.jushilist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="悬疑"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.xuanyilist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="科幻"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.kehuanlist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="游戏"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.youxilist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="体育"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.tiyulist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="轻小说"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.qingxiaoshuolist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }
            if (val=="现实"){
                //设置表单提交主类型为空
                this.worksInfoneed.work_vice_label = null;
                //设置界面已选副标签为空
                this.selectedtips.selected_vice_label = null;
                //设置界面隐藏可选择副标签变为显示状态
                this.vicelabelList = this.xianshilist;
                //控制组件隐藏
                this.hideviceSelect_button();
            }

            /**
             * 提交表单，获取作品信息
            * */
            this.getworksInfoList();

            /**
             * 获取作品数量
             * */
            this.gettotalNum();


        },
        /**
         *点击已选副标签
         * */
        vicework(val){
            //设置提交表单的副标签
            this.worksInfoneed.work_vice_label = val;
            //设置显示的副标签
            this.selectedtips.selected_vice_label = val;

            //设置当前处于第一页
            this.currentPage = 1;
            //设置提交保单的当前页
            this.worksInfoneed.works_page = 1;

            //控制组件显示
            this.showviceSelect_button();

            /**
             * 提交表单，获取作品信息
             * */
            this.getworksInfoList();

            /**
             * 获取作品数量
             * */
            this.gettotalNum();
        },
        /**
         * 点击想要选择的作品状态按钮
         * */
        serialstatework(val){

            //设置界面已选择显示
            this.selectedtips.selected_serial_state = val;

            //设置当前处于第一页
            this.currentPage = 1;
            //设置提交数据的当前页
            this.worksInfoneed.works_page = 1;

            /*
            *设置提交数据的作品状态
            * */
            if(val == "全部"){
                this.worksInfoneed.work_serial_state = null;
            }
            if(val == "连载"){
                this.worksInfoneed.work_serial_state = 1;
            }
            if(val == "完结"){
                this.worksInfoneed.work_serial_state = 2;
            }

            /**
             * 提交表单，获取作品信息
             * */
            this.getworksInfoList();

            /**
             * 获取作品数量
             * */
            this.gettotalNum();
        },
        /*
        * 点击已选的主类型，返回全部信息
        * */
        mainSelected(){
            //设置主类型为全部
            this.mianwork("全部");
        },
        /*
        * 点击已选的副类型，返回取消副类型选项
        * */
        viceSelected(){

            //进入之前所选的父类型
            this.mianwork(this.worksInfoneed.work_main_label);

        },
        /*
        * 点击已选的状态类型，返回取消副类型选项
        * */
        stateSelected(){
            this.serialstatework("全部");
        },
        /*
        * 获取输入当前的页数
        * */
        handleCurrentChange(val) {
            //设置当前页数
            this.worksInfoneed.works_page = val;

            /**
             * 提交表单，获取作品信息
             * */
            this.getworksInfoList();
        },
        //点击作品时按钮
        gotoDetail(work_id){
            var _this = this;
            axios.post('http://localhost:8080/worksInfoController/addWork_idSession?work_id='+work_id)
                .then(function (response) {
                    window.location.assign("../pages/novelDetailsInterface.html");
                })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },


        /*
        * 公共类
        * */
        /**
         * 设置组件已选副标签隐藏
         * */
        hideviceSelect_button(){
            //控制组件隐藏
            var ui =document.getElementById("viceSelected-button-id");
            ui.style.display="none";
        },
        /**
         * 设置组件已选副标签显示*/
        showviceSelect_button(){
            //控制组件显示
            var ui =document.getElementById("viceSelected-button-id");
            ui.style.display="";
        },

        /**
         * 提交表单，获取作品信息
         * */
        getworksInfoList(){
            var _this=this;
            axios.post('http://localhost:8080/worksInfoController/getworksneed',_this.worksInfoneed)
                .then(function (respone){
                    _this.worksInfoList = respone.data;//相应数据给worksInfoList

                    for( var i = 0 ;i<_this.worksInfoList.length;i++){
                        /*将1或2转为连载或完结*/
                        if( _this.worksInfoList[i].work_serial_state == 1){
                            _this.worksInfoList[i].work_serial_state = "连载";
                        }
                        else if(_this.worksInfoList[i].work_serial_state == 2){
                            _this.worksInfoList[i].work_serial_state = "完结";
                        }
                        /*字数大于10万字就变为小数*/
                        if(_this.worksInfoList[i].work_word_num>100000){
                            var divisornum = _this.worksInfoList[i].work_word_num/10000;
                            _this.worksInfoList[i].work_word_num = divisornum.toFixed(2);
                            _this.worksInfoList[i].work_word_num = _this.worksInfoList[i].work_word_num + "万";
                        }
                        else {
                            _this.worksInfoList[i].work_word_num = _this.worksInfoList[i].work_word_num;
                        }


                    }
                })
                .catch(function (error){
                    alert("相应失败");
                })
        },
        /**
         * 获取作品数量
         * */
        gettotalNum(){
            var _this=this;
            axios.post('http://localhost:8080/worksInfoController/getworkstotal',_this.worksInfoneed)
                .then(function (respone){
                    _this.total = respone.data;
                })
                .catch(function (error){
                    alert("相应失败");
                })

        },


    },
    created:function (){ //页面加载时查询所有
        this.startallWorksInterfaceHtml();
    }
})