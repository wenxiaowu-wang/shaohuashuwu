let messageCenterInterface_vm = new Vue({
    el:"#messageCenter",
    data:{
        topTips:"个人中心",
        user_id:0,
        user_name:"我系成龙",
        activeIndex: "3",
        user_avatar:"avatar",
        tip_img_url:"tan",
        bell_img_url:"bell",
        displayDivs:{
            displayDiv1:"block", //以块状元素展示
            displayDiv2:"block", //以块状元素展示
            displayDiv3:"block", //以块状元素展示
        },
        buttonContext:"删除",
        pageSize: 2, //每页显示4条数据
        currentPage: 1, //初始定位页数
        dynamicCurrentPage1:1, //动态变化的互相关注当前页数
        dynamicCurrentPage2:1, //动态变化的关注当前页数
        dynamicCurrentPage3:1, //动态变化的粉丝当前页数
        totals:{
            //总条目数（总共数据条数）
            total1:0,   //系统消息的数量
            total2:0,   //更新消息的数量
            total3:0    //私信消息的数量
        },
        tipTotals:{
            //总提示数目（提示未读信息）
            tipTotal1:0,    //系统消息的未读数量
            tipTotal2:0,    //更新消息的未读数量
            tipTotal3:0,    //私信消息的未读数量
        },
        button_disabled:false,      //清空、全部已读按钮禁用状态
        imageURL_header:"../images/message/",    //图片文件头
        imageURL_suffix:".jpg",             //图片文件尾
        systemMessageData:[
            {
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"信息门",    //发送者名字
                notice_content:"王将亲口承认，苏寒为其结拜兄弟。目前两人一起在八臂佛陀门暴揍门主。",  //通知内容
                notice_title:"震惊！！青州行走结拜兄弟为八阶蛮妖！！",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:1,        //通知提示
                notice_type:1
            }
        ],
        updateMessageData:[],
        chatMessageData:[],
        displayData:{
            systemMessageData:[{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"信息门",    //发送者名字
                notice_content:"王将亲口承认，苏寒为其结拜兄弟。目前两人一起在八臂佛陀门暴揍门主。",  //通知内容
                notice_title:"震惊！！青州行走结拜兄弟为八阶蛮妖！！",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:1,        //通知提示
                notice_type:1
            },{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"信息门",    //发送者名字
                notice_content:"苏寒重归苏国，一人占群敌，竟带来一位与法相金身相当的八阶蛮妖。",  //通知内容
                notice_title:"震惊！！法相金身莅临苏国！！",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示
                notice_type:1
            }],
            updateMessageData:[],
            chatMessageData:[]
        },


    },
    methods:{
        handleSelect(key, keyPath) {
            console.log("当前导航在:(key,keyPath)"+key, keyPath);
            switch (key){
                case "1":console.log("当前所在URL："+window.location.href);break;
                case "2":window.location.assign("http://www.baidu.com/");break;
                case "3":window.location.assign("http://www.baidu.com/");break;
                case "4":window.location.assign("http://www.baidu.com/");break;
                default:break;
            }
        },
        handleClick(row){
            console.log(row);

        },
        handleSizeChange(val) {
            //每页多少条数据
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange_each(page) {
            //当前页((x-1)*y,x*y)	page:x
            console.log(`当前页: ${page}`);
            this.dynamicCurrentPage1 = page;
            //刷新页
            let beginNums = (page-1) * this.pageSize;
            let endNums = beginNums + this.pageSize;
            if (endNums > this.totals.total1){
                endNums = this.totals.total1;
            }
            this.displayData.systemMessageData = this.systemMessageData.slice(beginNums,endNums);
        },
        handleCurrentChange_attention(page) {
            //当前页((x-1)*y,x*y)	page:x
            console.log(`当前页: ${page}`);
            this.dynamicCurrentPage2 = page;
            //刷新页
            let beginNums = (page-1) * this.pageSize;
            let endNums = beginNums + this.pageSize;
            if (endNums > this.totals.total2){
                endNums = this.totals.total2;
            }
            this.displayData.updateMessageData = this.updateMessageData.slice(beginNums,endNums);
        },
        handleCurrentChange_fans(page) {
            //当前页((x-1)*y,x*y)	page:x
            console.log(`当前页: ${page}`);
            this.dynamicCurrentPage3 = page;
            //刷新页
            let beginNums = (page-1) * this.pageSize;
            let endNums = beginNums + this.pageSize;
            if (endNums > this.totals.total3){
                endNums = this.totals.total3;
            }
            this.displayData.chatMessageData = this.chatMessageData.slice(beginNums,endNums);
        },
        deleteAllMessage(type){
            //删除对应类型的所有的通知
            let tip = "";
            switch (type) {
                case 1: tip = "系统消息"; break;
                case 2: tip = "更新提醒"; break;
                case 3: tip = "私信"; break;
                default:break;
            }
            this.$confirm('此操作将删除所有【'+tip+'】通知消息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //删除关注信息
                axios.post("/shaohuashuwu_war_exploded/noticeInfoController/deleteAllNoticeInfoByIdAndType/" +
                    this.user_id+"/"+type).then(response =>{
                    this.$message({
                        type: 'success',
                        message: '删除成功。'
                    });
                    //更新页面的数据(很重要)，删除所有对应类型信息后，进行视图数据更新。
                    switch (type) {
                        case 1:{
                            this.displayData.systemMessageData = [];
                            this.systemMessageData = [];
                            this.totals.total1 = 0;
                            this.tipTotals.tipTotal1 = 0;
                            this.dynamicCurrentPage1 = 1;
                            this.handleCurrentChange_each(1);
                            this.displayDivs.displayDiv1 = "block";
                            break;
                        }
                        case 2:{
                            this.displayData.updateMessageData = [];
                            this.updateMessageData = [];
                            this.totals.total2 = 0;
                            this.tipTotals.tipTotal2 = 0;
                            this.dynamicCurrentPage2 = 1;
                            this.handleCurrentChange_attention(1);
                            this.displayDivs.displayDiv2 = "block";
                            break;
                        }
                        case 3:{
                            this.displayData.chatMessageData = [];
                            this.chatMessageData = [];
                            this.totals.total3 = 0;
                            this.tipTotals.tipTotal3 = 0;
                            this.dynamicCurrentPage3 = 1;
                            this.handleCurrentChange_fans(1);
                            this.displayDivs.displayDiv3 = "block";
                            break;
                        }
                        default:break;
                    }
                }).catch(error =>{
                    this.$message({
                        type: 'info',
                        message: '删除消息失败！'
                    });
                });
            });
        },
        readAllMessage(type){
            //将对应类型通知消息置为已读
            switch (type) {
                case 1:{
                    if (this.tipTotals.tipTotal1 == 0){
                        this.$message({
                            type: 'info',
                            message: '系统消息全部已读，请勿重复操作。'
                        });
                    }else{
                        this.readAllMessageAxios(type);
                        this.handleCurrentChange_each(this.dynamicCurrentPage1);
                        this.systemMessageData.forEach(function (value, index, array) {
                            value.notice_tip = 0;
                        });
                        this.displayData.systemMessageData.forEach(function (value, index, array) {
                            value.notice_tip = 0;
                        });
                    }
                    break;
                }case 2:{
                    if (this.tipTotals.tipTotal2 == 0){
                        this.$message({
                            type: 'info',
                            message: '更新提醒全部已读，请勿重复操作。'
                        });
                    }else{
                        this.readAllMessageAxios(type);
                        this.handleCurrentChange_attention(this.dynamicCurrentPage2);
                        this.updateMessageData.forEach(function (value, index, array) {
                            value.notice_tip = 0;
                        });
                        this.displayData.updateMessageData.forEach(function (value, index, array) {
                            value.notice_tip = 0;
                        });
                    }
                    break;
                }case 3:{
                    if (this.tipTotals.tipTotal3 == 0){
                        this.$message({
                            type: 'info',
                            message: '私信全部已读，请勿重复操作。'
                        });
                    }else{
                        this.readAllMessageAxios(type);
                        this.handleCurrentChange_fans(this.dynamicCurrentPage3);
                        this.chatMessageData.forEach(function (value, index, array) {
                            value.notice_tip = 0;
                        });
                        this.displayData.chatMessageData.forEach(function (value, index, array) {
                            value.notice_tip = 0;
                        });
                    }
                    break;
                }default:break;
            }
        },
        readAllMessageAxios(type){
            //readAllMessage()的axios模块
            axios.post("/shaohuashuwu_war_exploded/noticeInfoController/updateAllNoticeInfoByIdAndType/" +
                this.user_id+"/"+type).then(response =>{
                this.$message({
                    type: 'success',
                    message: '该类型全部已读。'
                });
                //更新页面的数据(很重要)
                switch (type) {
                    case 1:{
                        this.tipTotals.tipTotal1 = 0;
                        break;
                    }case 2:{
                        this.tipTotals.tipTotal2 = 0;
                        break;
                    }case 3:{
                        this.tipTotals.tipTotal3 = 0;
                        break;
                    }default:break;
                }
            }).catch(error =>{
                this.$message({
                    type: 'info',
                    message: '标记已读失败！'
                });
            });
        },
        deleteMessage(id,type,tip,index){
          //取消关注选中ID的用户
            this.$confirm('此操作将删除该消息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //删除关注信息
                axios.post("/shaohuashuwu_war_exploded/noticeInfoController/deleteOneNoticeInfo/" +
                    id).then(response =>{
                    this.$message({
                        type: 'success',
                        message: '删除成功。'
                    });
                    //更新页面的数据(很重要)
                    this.updateViewData(type,tip,index);
                }).catch(error =>{
                    this.$message({
                        type: 'info',
                        message: '删除消息失败！'
                    });
                });
            });
        },
        updateViewData(type,tip,index){
            //更新驶入数据（主要是tabs面板里面的数据）【parma:type】:消息类型，也表示是在哪一个面板
            switch(type){
                case 1:{
                    if (tip == 1){
                        this.tipTotals.tipTotal1 -= 1;
                    }
                    let opIndex = ((this.dynamicCurrentPage1-1)*this.pageSize)+index; //将要操作数据下标
                    let page = this.dynamicCurrentPage1; //显示的对应页，更新视图的作用
                    if (opIndex == this.systemMessageData.length -1 && page -1>0 && index == 0){
                        page -= 1;  //若操作的数组元素为末尾元素，并且为>1的页面而且该元素是显示数组的下标为0的元素，将会显示当前页面的前一个页面
                    }
                    //this.displayData.attentionData.splice(index,index); //清除对应显示数据
                    this.systemMessageData.splice(opIndex,1);  //清除缓存的对应数据(从第几个元素开始，开始的几个，要插入的元素)
                    this.totals.total1 -= 1;    //更新整体数据计数
                    this.handleCurrentChange_each(page);  //跳转页面，相当于更新视图
                    this.displayDivsSetting(); //判断是否要显示空内容提示
                    // this.updateEachAttentionData(id); //更新被影响的相互关注的数据
                    break;
                }
                case 2:{
                    if (tip == 1){
                        this.tipTotals.tipTotal2 -= 1;
                    }
                    let opIndex = ((this.dynamicCurrentPage2-1)*this.pageSize)+index; //将要操作数据下标
                    let page = this.dynamicCurrentPage2; //显示的对应页，更新视图的作用
                    if (opIndex == this.updateMessageData.length -1 && page -1>0 && index == 0){
                        page -= 1;  //若操作的数组元素为末尾元素，并且为>1的页面而且该元素是显示数组的下标为0的元素，将会显示当前页面的前一个页面
                    }
                    //this.displayData.attentionData.splice(index,index); //清除对应显示数据
                    this.updateMessageData.splice(opIndex,1);  //清除缓存的对应数据(从第几个元素开始，开始的几个，要插入的元素)
                    this.totals.total2 -= 1;    //更新整体数据计数
                    this.handleCurrentChange_attention(page);  //跳转页面，相当于更新视图
                    this.displayDivsSetting(); //判断是否要显示空内容提示
                    // this.updateEachAttentionData(id); //更新被影响的相互关注的数据
                    break;
                }
                case 3:{
                    if (tip == 1){
                        this.tipTotals.tipTotal3 -= 1;
                    }
                    let opIndex = ((this.dynamicCurrentPage3-1)*this.pageSize)+index; //将要操作数据下标
                    let page = this.dynamicCurrentPage3; //显示的对应页，更新视图的作用
                    if (opIndex == this.chatMessageData.length -1 && page -1>0 && index == 0){
                        page -= 1;  //若操作的数组元素为末尾元素，并且为>1的页面而且该元素是显示数组的下标为0的元素，将会显示当前页面的前一个页面
                    }
                    //this.displayData.attentionData.splice(index,index); //清除对应显示数据
                    this.chatMessageData.splice(opIndex,1);  //清除缓存的对应数据(从第几个元素开始，开始的几个，要插入的元素)
                    this.totals.total3 -= 1;    //更新整体数据计数
                    this.handleCurrentChange_each(page);  //跳转页面，相当于更新视图
                    this.displayDivsSetting(); //判断是否要显示空内容提示
                    // this.updateEachAttentionData(id); //更新被影响的相互关注的数据
                    break;
                }
                default:break;
            }
        },
        displayDivsSetting(){
            //判断是否要显示空内容提示
            let display1 = this.totals.total1 > 0 ? "none":"block";
            let display2 = this.totals.total2 > 0 ? "none":"block";
            let display3 = this.totals.total3 > 0 ? "none":"block";
            this.displayDivs.displayDiv1 = display1;
            this.displayDivs.displayDiv2 = display2;
            this.displayDivs.displayDiv3 = display3;
        },
        updateEachAttentionData(id){
            //更新互相关注的数据
            // val是当前元素，index当前元素索引，array为当前数组
            let up_index = -1;
            this.eachAttentionData.forEach(function (value, index, array) {
                if(id == value.user_id){
                    up_index = index;
                }
            });
            //如果有影响的值，使用array.splice()删除对应元素
            if (up_index >= 0){
                this.eachAttentionData.splice(up_index,1);
                this.totals.total1 -= 1;
                let page = this.dynamicCurrentPage1; //显示页，更新视图的作用
                if (up_index == this.attentionData.length -1 && page -1>0 && up_index%this.pageSize == 0){
                    page -= 1;  //若操作的数组元素为末尾元素，并且为>1的页面而且该元素是显示数组的下标为0的元素，将会显示当前页面的前一个页面
                }
                this.handleCurrentChange_each(page);  //跳转页面，相当于更新视图
            }
        },
    },
    mounted(){
        //钩子函数，在加载页面后，渲染数据前执行
        //获取本用户的ID和name以及关注用户的信息
        axios.get("/shaohuashuwu_war_exploded/userSession/getUser").then(resp =>{
            let info = resp.data;
            let userId = JSON.stringify(info["user_id"]);
            let userName = info["user_name"];
            let userAvatar = info["head_portrait"];
            userId = parseInt(userId);
            this.user_id = userId;
            this.user_name = userName;
            this.user_avatar = userAvatar;
            //获取所有该用户的通知消息
            axios.get("/shaohuashuwu_war_exploded/noticeInfoController/getAllNoticeInfo/"+userId).then(resp =>{
                console.log("获取成功");
                let objectData = eval(JSON.stringify(resp.data));//将字符串转化为数组对象
                // value是当前元素，index当前元素索引，array为当前数组
                //将所有通知信息按照类别分类放置
                let length1 = 0;
                let length2 = 0;
                let length3 = 0;
                this.systemMessageData = [];
                this.updateMessageData = [];
                this.chatMessageData = [];
                let _this = this;
                console.log("数据装配中······");
                objectData.forEach(function (value,index,array) {
                    switch (value.notice_type) {
                        case 1:{
                            console.log("系统提醒数据装配中······【"+index+"】");
                            // length1 = this.systemMessageData.push(value); //注意在这里使用this，指的是function
                            length1 = _this.systemMessageData.push(value); //注意在这里使用this，指的是function
                            if (value.notice_tip == 1){
                                _this.tipTotals.tipTotal1 += 1;
                            }
                            break;
                        }
                        case 2:{
                            console.log("更新提醒数据装配中······【"+index+"】");
                            length2 = _this.updateMessageData.push(value);
                            if (value.notice_tip == 1){
                                _this.tipTotals.tipTotal2 += 1;
                            }
                            break;
                        }
                        case 3:{
                            console.log("私信数据装配中······【"+index+"】");
                            length3 = _this.chatMessageData.push(value);
                            if (value.notice_tip == 1){
                                _this.tipTotals.tipTotal3 += 1;
                            }
                            break;
                        }
                        default:break;
                    }
                });
                this.totals.total1 = this.systemMessageData.length;
                this.totals.total2 = this.updateMessageData.length;
                this.totals.total3 = this.chatMessageData.length;
                //配置初始显示数据
                let endNums1 = this.pageSize > this.systemMessageData.length ? this.systemMessageData.length : this.pageSize;
                let endNums2 = this.pageSize > this.updateMessageData.length ? this.updateMessageData.length : this.pageSize;
                let endNums3 = this.pageSize > this.chatMessageData.length ? this.chatMessageData.length : this.pageSize;

                this.displayData.systemMessageData = this.systemMessageData.slice(0,endNums1);
                this.displayData.updateMessageData = this.updateMessageData.slice(0,endNums2);
                this.displayData.chatMessageData = this.chatMessageData.slice(0,endNums3);
                //更新无数据提示的显示情况
                let display1 = this.totals.total1 > 0 ? "none":"block";
                let display2 = this.totals.total2 > 0 ? "none":"block";
                let display3 = this.totals.total3 > 0 ? "none":"block";
                this.displayDivs.displayDiv1 = display1;
                this.displayDivs.displayDiv2 = display2;
                this.displayDivs.displayDiv3 = display3;

                console.log("数据装配成功");

            }).catch(error =>{
                console.log("获取失败："+error);
            })
        }).catch(error =>{
            alert("获取本用户ID和name错误。");
        });
    },
})