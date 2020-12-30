let messageCenterInterface_vm = new Vue({
    el:"#messageCenter",
    data:{
        topTips:"返回首页",
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
            total1:2,   //系统消息的数量
            total2:2,   //更新消息的数量
            total3:2    //私信消息的数量
        },
        tipTotals:{
            //总提示数目（提示未读信息）
            tipTotal1:2,    //系统消息的未读数量
            tipTotal2:1,    //更新消息的未读数量
            tipTotal3:1,    //私信消息的未读数量
        },
        button_disabled:false,      //清空、全部已读按钮禁用状态

        //清空、全部已读按钮禁用状态
        readAll_disabled:{
            button1:false,
            button2:false,
            button3:false,
        },
        deleteAll_disabled:{
            button1:false,
            button2:false,
            button3:false,
        },

        imageURL_header:"../images/message/",    //图片文件头
        imageURL_suffix:".jpg",             //图片文件尾
        systemMessageData:[
            {
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"韶华书屋",    //发送者名字
                notice_content:"您的某某作品涉及违规《韶华书屋作品规定》，现已下架，如有疑问，请联系人工客服",  //通知内容
                notice_title:"有关某某作品的下架通知",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示（是否已读0未读1已读）
                notice_type:1
            },{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"韶华书屋",    //发送者名字
                notice_content:"您的某某作品涉及违规《韶华书屋作品规定》，现已下架，如有疑问，请联系人工客服",  //通知内容
                notice_title:"有关某某作品的下架通知",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示（是否已读0未读1已读）
                notice_type:1
            }
        ],
        updateMessageData:[
            {
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"《玄幻之神级帝皇系统》",    //发送者名字
                notice_content:"更新第一千二百五十一章 蛮妖 王将亲口承认，苏寒为其结拜兄弟。目前两人一起在八臂佛陀门暴揍门主。",  //通知内容
                notice_title:"青州行走结拜兄弟为八阶蛮妖！！",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示
                notice_type:2
            },{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"《云端的王国》",    //发送者名字
                notice_content:"更新第一千二百五十一章 压迫？ 苏寒重归苏国，一人占群敌，竟带来一位与法相金身相当的八阶蛮妖。",  //通知内容
                notice_title:"法相金身莅临苏国！！",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:1,        //通知提示
                notice_type:2
            }
        ],
        chatMessageData:[
            {
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"王勃",    //发送者名字
                notice_content:"只要有知心朋友，四海之内不觉遥远。即便在天涯海角，感觉就像近邻一样。",  //通知内容
                notice_title:"海内存知己，天涯若比邻。",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示
                notice_type:3
            },{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"蒲松龄",    //发送者名字
                notice_content:"天下最愉快的事莫过于能交许多朋友，和朋友在一起很愉快莫过于来自和朋友间的交流、交谈。",  //通知内容
                notice_title:"天下快意之事莫若友,快友之事莫若谈。",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:1,        //通知提示
                notice_type:3
            }
        ],
        displayData:{
            systemMessageData:[{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"韶华书屋",    //发送者名字
                notice_content:"您的某某作品涉及违规《韶华书屋作品规定》，现已下架，如有疑问，请联系人工客服",  //通知内容
                notice_title:"有关某某作品的下架通知",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示（是否已读0未读1已读）
                notice_type:1
            },{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"韶华书屋",    //发送者名字
                notice_content:"您的某某作品涉及违规《韶华书屋作品规定》，现已下架，如有疑问，请联系人工客服",  //通知内容
                notice_title:"有关某某作品的下架通知",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示（是否已读0未读1已读）
                notice_type:1
            }],
            updateMessageData:[{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"《玄幻之神级帝皇系统》",    //发送者名字
                notice_content:"更新第一千二百五十一章 蛮妖 王将亲口承认，苏寒为其结拜兄弟。目前两人一起在八臂佛陀门暴揍门主。",  //通知内容
                notice_title:"青州行走结拜兄弟为八阶蛮妖！！",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示
                notice_type:2
            },{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"《云端的王国》",    //发送者名字
                notice_content:"更新第一千二百五十一章 压迫？ 苏寒重归苏国，一人占群敌，竟带来一位与法相金身相当的八阶蛮妖。",  //通知内容
                notice_title:"法相金身莅临苏国！！",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:1,        //通知提示
                notice_type:2
            }],
            chatMessageData:[
                {
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"王勃",    //发送者名字
                notice_content:"只要有知心朋友，四海之内不觉遥远。即便在天涯海角，感觉就像近邻一样。",  //通知内容
                notice_title:"海内存知己，天涯若比邻。",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:0,        //通知提示
                notice_type:3
            },{
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                send_by_name:"蒲松龄",    //发送者名字
                notice_content:"天下最愉快的事莫过于能交许多朋友，和朋友在一起很愉快莫过于来自和朋友间的交流、交谈。",  //通知内容
                notice_title:"天下快意之事莫若友,快友之事莫若谈。",    //通知标题
                send_time:"2020-10-31 10:45:38",       //通知时间
                notice_tip:1,        //通知提示
                notice_type:3
            }
            ]
        },


    },
    methods:{
        //跳转到首页
        backHomePage(){
            window.location.assign("../pages/userMainInterface.html");
        },
        handleSelect(key, keyPath) {
            console.log("当前导航在:(key,keyPath)"+key, keyPath);
            switch (key){
                // case "1":console.log("当前所在URL："+window.location.href);break;
                case "1":window.location.assign("../pages/myHomePage.html");break;
                case "2":window.location.assign("../pages/bookShelfInterface.html");break;
                case "3":{
                    this.$message({
                        type:'info',
                        message:'您已经在【消息中心】界面，不必跳转。'
                    });
                    break;
                }
                case "4":window.location.assign("../pages/personalAccountInterface.html");break;
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
        //更新各类型消息的 所有已读，所有删除 的按钮的disabled
        updateReadAllAndDeleteAllButton(){
            this.readAll_disabled.button1 = this.tipTotals.tipTotal1 === 0;
            this.readAll_disabled.button2 = this.tipTotals.tipTotal2 === 0;
            this.readAll_disabled.button3 = this.tipTotals.tipTotal3 === 0;
            this.deleteAll_disabled.button1 = this.totals.total1 === 0;
            this.deleteAll_disabled.button2 = this.totals.total2 === 0;
            this.deleteAll_disabled.button3 = this.totals.total3 === 0;
        },
        checkIsHaveNoticeTipOfType(type){
            //检测某消息类型是否含有未阅读的消息
            let isHave = false;
            switch (type) {
                case 1:{
                    isHave = this.tipTotals.tipTotal1 > 0;
                    break;
                }case 2:{
                    isHave = this.tipTotals.tipTotal2 > 0;
                    break;
                }case 3:{
                    isHave = this.tipTotals.tipTotal3 > 0;
                    break;
                }
            }
            return isHave;
        },
        deleteAllMessage(type){
            //先检测该类型中是否含有未读消息
            //限制只能删除已读消息，符合逻辑，而且避免出现删除的消息是未读消息、还要更新未读消息数量
            if (this.checkIsHaveNoticeTipOfType(type)){
                this.$confirm('该类型包含未读消息，消息阅读后方可删除', '提示', {
                    confirmButtonText: '知道了',
                    cancelButtonText: 'I know',
                    type: 'warning'
                }).then(() =>{
                    console.log("知道了");
                }).catch(error =>{
                    console.log("I know ");
                });
                return ;
            }
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
                axios.post("/shaohuashuwu/noticeStateInfoController/updateAllDeleteStateOfNoticeType/" +
                    type).then(response =>{
                    if (response.data){
                        this.$message({
                            type: 'success',
                            message: '删除该类型所有消息成功。'
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
                        this.updateReadAllAndDeleteAllButton();     //更新所有类型的 已读所有、删除所有 按钮的可用状态
                    }else{
                        this.$message({
                            type:'error',
                            message:'删除所有该类型消息失败！'
                        });
                    }
                }).catch(error =>{
                    this.$message({
                        type: 'info',
                        message: '删除所有该类型消息请求失败！'
                    });
                    console.log("删除说有该类型消息请求失败！"+error);
                });
            });
        },
        readOneMessage(message,index){
            //将一条对应消息置为已读
            axios.post("/shaohuashuwu/noticeStateInfoController/updateOneReadState/" +
                message.notice_id).then(resp =>{
                if (resp.data){
                    this.$message({
                        type:'success',
                        message:'标记消息已读成功！'
                    });
                    switch (message.notice_type) {
                        case 1:{
                            this.tipTotals.tipTotal1 += (-1);
                            let opIndex = ((this.dynamicCurrentPage1-1)*this.pageSize)+index;
                            this.systemMessageData.forEach((value, index1) => {
                                if (index1 === opIndex){
                                    value.notice_tip = 1;
                                }
                            });
                            this.handleCurrentChange_each(this.dynamicCurrentPage1);
                            break;
                        }case 2:{
                            this.tipTotals.tipTotal2 += (-1);
                            let opIndex = ((this.dynamicCurrentPage2-1)*this.pageSize)+index;
                            this.updateMessageData.forEach((value, index1) => {
                                if (index1 === opIndex){
                                    value.notice_tip = 1;
                                }
                            });
                            this.handleCurrentChange_attention(this.dynamicCurrentPage2);
                            break;
                        }case 3:{
                            this.tipTotals.tipTotal3 += (-1);
                            let opIndex = ((this.dynamicCurrentPage3-1)*this.pageSize)+index;
                            this.chatMessageData.forEach((value, index1) => {
                                if (index1 === opIndex){
                                    value.notice_tip = 1;
                                }
                            });
                            this.handleCurrentChange_fans(this.dynamicCurrentPage3);
                            break;
                        }default :break;
                    }

                    //更新各类型消息的 所有已读，所有删除 的按钮的disabled
                    this.updateReadAllAndDeleteAllButton();
                    console.log("标记消息已读成功！");
                }else{
                    this.$message({
                        type:'error',
                        message:'服务器出错，标记消息已读失败！'
                    });
                    console.log("服务器出错，标记消息已读失败！");
                }
            }).catch(error =>{
                this.$message({
                    type:'error',
                    message:'标记消息已读请求失败！'
                });
                console.log("标记消息已读请求失败！"+error);
            });
        },
        readAllMessage(type){
            //将对应类型通知消息置为已读
            axios.post("/shaohuashuwu/noticeStateInfoController/updateAllReadStateOfNoticeType/" +
                type).then(response =>{
                if(response.data){
                    this.$message({
                        type: 'success',
                        message: '该类型消息全部已读。'
                    });
                    this.readAllMessageSuccessAfter(type);  //进行已读该类型消息所有之后的操作
                    console.log("该类型消息全部已读");
                }else{
                    this.$message({
                        type:'error',
                        message:'标记该类型所有数据为已读状态失败！'
                    });
                    console.log("标记该类型所有数据为已读状态失败！");
                }
            }).catch(error =>{
                this.$message({
                    type: 'error',
                    message: '标记所有已读请求失败！'
                });
                console.log("标记所有已读请求失败！"+error);
            });

        },
        readAllMessageSuccessAfter(type){
            //进行标记该类型所有消息已读后的操作
            switch (type) {
                case 1:{
                    this.systemMessageData.forEach(function (value, index, array) {
                        value.notice_tip = 1;//value 代表该数组对应元素的值，代表其本身
                    });
                    this.handleCurrentChange_each(this.dynamicCurrentPage1);
                    this.tipTotals.tipTotal1 = 0;
                    //更新所有类型的 已读所有、删除所有 按钮的可用状态
                    this.updateReadAllAndDeleteAllButton();
                    break;
                }case 2:{

                    this.updateMessageData.forEach(function (value, index, array) {
                        value.notice_tip = 1;//value 代表该数组对应元素的值，代表其本身
                    });
                    // for(let i = 0;i< this.updateMessageData.length;i++){
                    //     this.updateMessageData[i].notice_tip = 1;//下标访问的方法
                    // }
                    this.handleCurrentChange_attention(this.dynamicCurrentPage2);
                    this.tipTotals.tipTotal2 = 0;
                    //更新所有类型的 已读所有、删除所有 按钮的可用状态
                    this.updateReadAllAndDeleteAllButton();
                    break;
                }case 3:{
                    this.chatMessageData.forEach(function (value, index, array) {
                        value.notice_tip = 1;//value 代表该数组对应元素的值，代表其本身
                    });
                    this.handleCurrentChange_fans(this.dynamicCurrentPage3);
                    this.tipTotals.tipTotal3 = 0;
                    //更新所有类型的 已读所有、删除所有 按钮的可用状态
                    this.updateReadAllAndDeleteAllButton();
                    break;
                }default:break;
            }
            // return isReadAll;   //不能放在外面，因为这是异步消息，不会等待 同理，也不能放在一个单独函数里（其它等待结果的情况下）
        },
        deleteOneMessage(message,index){
            if (message.notice_tip === 0){
                this.$confirm('消息阅读后方可删除', '提示', {
                    confirmButtonText: '知道了',
                    cancelButtonText: 'I know',
                    type: 'warning'
                }).then(() =>{
                    console.log("知道了");
                }).catch(error =>{
                    console.log("I know ");
                });
                return ;
            }
            console.log("测试是否执行");
          //删除一条对应消息ID（notice_id）的信息
            this.$confirm('此操作将删除该消息, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //删除该条对应消息
                axios.post("/shaohuashuwu/noticeStateInfoController/updateOneDeleteState/" +
                    message.notice_id).then(response =>{
                        if (response.data){
                            this.$message({
                                type: 'success',
                                message: '删除该消息成功！'
                            });
                            //更新页面的数据(很重要)
                            this.updateViewData(message.notice_type,message.notice_tip,index);
                            console.log("删除该消息以及更新视图成功！")
                        }else{
                            this.$message({
                                type:'error',
                                message:'删除该消息失败！'
                            });
                            console.log("删除该消息失败！");
                        }
                }).catch(error =>{
                    this.$message({
                        type: 'error',
                        message: '删除该消息请求失败！'
                    });
                    console.log("删除该消息请求失败！"+error);
                });
            });
        },
        updateViewData(type,tip,index){
            //更新驶入数据（主要是tabs面板里面的数据）【parma:type】:消息类型，也表示是在哪一个面板
            switch(type){
                case 1:{
                    let opIndex = ((this.dynamicCurrentPage1-1)*this.pageSize)+index; //将要操作数据下标
                    let page = this.dynamicCurrentPage1; //显示的对应页，更新视图的作用
                    if (opIndex === this.systemMessageData.length -1 && page -1>0 && index === 0){
                        page -= 1;  //若操作的数组元素为末尾元素，并且为>1的页面而且该元素是显示数组的下标为0的元素，将会显示当前页面的前一个页面
                    }
                    this.systemMessageData.splice(opIndex,1);  //清除缓存的对应数据(从第几个元素开始，开始的几个，要插入的元素)
                    this.totals.total1 -= 1;    //更新整体数据计数
                    this.displayDivsSetting(); //判断是否要显示空内容提示
                    this.handleCurrentChange_each(page);  //跳转页面，相当于更新视图
                    break;
                }
                case 2:{
                    let opIndex = ((this.dynamicCurrentPage2-1)*this.pageSize)+index; //将要操作数据下标
                    let page = this.dynamicCurrentPage2; //显示的对应页，更新视图的作用
                    if (opIndex === this.updateMessageData.length -1 && page -1>0 && index === 0){
                        page -= 1;  //若操作的数组元素为末尾元素，并且为>1的页面而且该元素是显示数组的下标为0的元素，将会显示当前页面的前一个页面
                    }
                    this.updateMessageData.splice(opIndex,1);  //清除缓存的对应数据(从第几个元素开始，开始的几个，要插入的元素)
                    this.totals.total2 -= 1;    //更新整体数据计数
                    this.displayDivsSetting(); //判断是否要显示空内容提示
                    this.handleCurrentChange_attention(page);  //跳转页面，相当于更新视图
                    break;
                }
                case 3:{
                    let opIndex = ((this.dynamicCurrentPage3-1)*this.pageSize)+index; //将要操作数据下标
                    let page = this.dynamicCurrentPage3; //显示的对应页，更新视图的作用
                    if (opIndex === this.chatMessageData.length -1 && page -1>0 && index === 0){
                        page -= 1;  //若操作的数组元素为末尾元素，并且为>1的页面而且该元素是显示数组的下标为0的元素，将会显示当前页面的前一个页面
                    }
                    this.chatMessageData.splice(opIndex,1);  //清除缓存的对应数据(从第几个元素开始，开始的几个，要插入的元素)
                    this.totals.total3 -= 1;    //更新整体数据计数
                    this.displayDivsSetting(); //判断是否要显示空内容提示
                    this.handleCurrentChange_fans(page);  //跳转页面，相当于更新视图
                    break;
                }
                default:break;
            }
            this.updateReadAllAndDeleteAllButton();     //更新所有类型的 已读所有、删除所有 按钮的可用状态

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
    },
    mounted(){
        //钩子函数，在加载页面后，渲染数据前执行
        //获取本用户的ID和name以及关注用户的信息
        axios.get("/shaohuashuwu/userSession/getUser").then(resp =>{
            let info = resp.data;
            let userId = JSON.stringify(info["user_id"]);
            let userName = info["user_name"];
            let userAvatar = info["head_portrait"];
            userId = parseInt(userId);
            this.user_id = userId;
            this.user_name = userName;
            this.user_avatar = userAvatar;
        }).catch(error =>{
            this.$confirm('获取用户数据信息失败!', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //执行操作

            }).catch(() => {
                //执行操作

            });
            console.log("获取用户数据信息失败！"+error);
        });
        //获取所有该用户的通知消息
        axios.get("/shaohuashuwu/noticeInfoController/getAllNoticeInfo").then(resp =>{
            let objectData = eval(JSON.stringify(resp.data));//将字符串转化为数组对象
            // value是当前元素，index当前元素索引，array为当前数组
            //将所有通知信息按照类别分类放置
            let length1 = 0;
            let length2 = 0;
            let length3 = 0;
            this.totals.total1 = 0;
            this.totals.total2 = 0;
            this.totals.total3 = 0;
            this.tipTotals.tipTotal1 = 0;
            this.tipTotals.tipTotal2 = 0;
            this.tipTotals.tipTotal3 = 0;
            this.systemMessageData = [];
            this.updateMessageData = [];
            this.chatMessageData = [];
            let _this = this;
            console.log("数据装配中······");
            objectData.forEach(function (value,index) {
                switch (value.notice_type) {
                    case 1:{
                        // length1 = this.systemMessageData.push(value); //注意在这里使用this，指的是function
                        length1 = _this.systemMessageData.push(value); //注意在这里使用this，指的是function
                        if (value.notice_tip === 0){
                            _this.tipTotals.tipTotal1 += 1;
                        }
                        break;
                    }
                    case 2:{
                        length2 = _this.updateMessageData.push(value);
                        if (value.notice_tip === 0){
                            _this.tipTotals.tipTotal2 += 1;
                        }
                        break;
                    }
                    case 3:{
                        length3 = _this.chatMessageData.push(value);
                        if (value.notice_tip === 0){
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

            console.log("数据装配成功");
            //判断是否显示数据为空的提示
            this.displayDivsSetting();
            this.updateReadAllAndDeleteAllButton();     //更新所有类型的 已读所有、删除所有 按钮的可用状态
        }).catch(error =>{
            console.log("获取失败："+error);
            //判断是否显示数据为空的提示
            this.displayDivsSetting();
            this.updateReadAllAndDeleteAllButton();     //更新所有类型的 已读所有、删除所有 按钮的可用状态
        });



    },
})