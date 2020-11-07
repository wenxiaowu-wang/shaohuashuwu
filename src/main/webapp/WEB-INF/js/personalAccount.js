let personalAccountInterface_vm = new Vue({
    el:"#personalAccount",
    data:{
        topTips:"个人中心",
        user_id:0,
        user_name:"我吃唐家土豆",
        activeIndex: "4",
        user_avatar:"avatar",

        goldBeanNum:0,
        ticketNum:0,
        search_reward: '',
        search_subscription: '',
        search_voteTicket: '',

        totals:{
            total1:0,
            total2:0,
            total3:0,
            total4:0
        },

        imageURL_header:"../images/message/",    //图片文件头
        imageURL_suffix:".jpg",             //图片文件尾
        topUpData:[
            {
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"韶华书屋平台",      //接受者名字
                transaction_type:"充值",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"支付宝",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-3 22:21:12",    //交易时间
                transaction_quantity:50, //交易数量
                transaction_unit:"元人民币"     //交易单位
            },{   //第二个测试展示的数据
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"韶华书屋平台",      //接受者名字
                transaction_type:"充值",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"微信",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-4 09:55:00",    //交易时间
                transaction_quantity:20, //交易数量
                transaction_unit:"元人民币"     //交易单位
            },{   //第三个测试展示的数据
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"韶华书屋平台",      //接受者名字
                transaction_type:"充值",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"微信",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-4 09:55:16",    //交易时间
                transaction_quantity:30, //交易数量
                transaction_unit:"元人民币"     //交易单位
            }
        ],
        rewardData:[
            {
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"《斗破苍穹》第一章：陨落的天才",      //接受者名字
                transaction_type:"打赏",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"其它",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-3 22:21:12",    //交易时间
                transaction_quantity:50, //交易数量
                transaction_unit:"个金豆"     //交易单位
            },{   //第二个测试展示的数据
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"《斗破苍穹》第二章：斗之气三段",      //接受者名字
                transaction_type:"打赏",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"其它",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-4 09:55:00",    //交易时间
                transaction_quantity:20, //交易数量
                transaction_unit:"个金豆"     //交易单位
            },{   //第三个测试展示的数据
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"《极品家丁》第五百二十一章：开在断肠时",      //接受者名字
                transaction_type:"打赏",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"其它",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-4 09:55:16",    //交易时间
                transaction_quantity:30, //交易数量
                transaction_unit:"个金豆"     //交易单位
            }
        ],
        subscriptionData:[
        ],
        voteTicketData:[
            {
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"《斗破苍穹》第一章：陨落的天才",      //接受者名字
                transaction_type:"投票",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"其它",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-3 22:21:12",    //交易时间
                transaction_quantity:5, //交易数量
                transaction_unit:"张推荐票"     //交易单位
            },{   //第二个测试展示的数据
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"《斗破苍穹》第二章：斗之气三段",      //接受者名字
                transaction_type:"投票",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"其它",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-4 09:55:00",    //交易时间
                transaction_quantity:1, //交易数量
                transaction_unit:"张推荐票"     //交易单位
            },{   //第三个测试展示的数据
                transaction_id:0,       //交易ID
                consumer_id:0,          //消费者Id
                recipient_id:0,         //接受者(当交易类型为2订阅时，接受者为作品ID)
                recipient_name:"《极品家丁》第五百二十一章：开在断肠时",      //接受者名字
                transaction_type:"打赏",    //交易类型(0充值、1打赏、2订阅、3投票)
                transaction_mode:"其它",    //交易方式(0支付宝、1微信)
                transaction_time:"2020-11-4 09:55:16",    //交易时间
                transaction_quantity:2, //交易数量
                transaction_unit:"张推荐票"     //交易单位
            }
        ],
        //the data end
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
            axios.get("/shaohuashuwu_war_exploded/transactionInfoController/getAllConsumptionTransactionInfo/"+userId).then(resp =>{
                console.log("获取成功");
                let objectData = eval(JSON.stringify(resp.data));//将字符串转化为数组对象
                // value是当前元素，index当前元素索引，array为当前数组
                //将所有通知信息按照类别分类放置
                let length1 = 0;
                let length2 = 0;
                let length3 = 0;
                let length4 = 0;
                this.topUpData = [];
                this.rewardData = [];
                this.subscriptionData = [];
                this.voteTicketData = [];
                let _this = this;
                console.log("数据装配中······");
                objectData.forEach(function (value,index,array) {
                    switch (value.transaction_type) {
                        case "充值":{
                            // length1 = this.systemMessageData.push(value); //注意在这里使用this，指的是function
                            length1 = _this.topUpData.push(value); //注意在这里使用this，指的是function
                            break;
                        }
                        case "打赏":{
                            length2 = _this.rewardData.push(value);
                            break;
                        }
                        case "订阅":{
                            length3 = _this.subscriptionData.push(value);
                            break;
                        }
                        case "投票":{
                            length4 = _this.voteTicketData.push(value);
                            break;
                        }
                        default:break;
                    }
                });
                this.totals.total1 = length1;
                this.totals.total2 = length2;
                this.totals.total3 = length3;
                this.totals.total4 = length4;
                console.log("数据装配成功");
                console.log("订阅记录信息条数："+this.subscriptionData.length+"条");

            }).catch(error =>{
                console.log("获取交易记录失败："+error);
            });
            //获取用户金豆数量
            axios.get("/shaohuashuwu_war_exploded/userController/getGoldBeanNum/"+userId).then(response_getGoldBeanNum =>{
                let str_goldBean = JSON.stringify(response_getGoldBeanNum.data);
                this.goldBeanNum = parseInt(str_goldBean);
            }).catch(error =>{
                console.log("获取金豆数量："+error);
            });
            //获取用户推荐票数量
            axios.get("/shaohuashuwu_war_exploded/userController/getTicketNum/"+userId).then(response_getTicketNum =>{
                let str_ticketNum = JSON.stringify(response_getTicketNum.data);
                this.ticketNum = parseInt(str_ticketNum);
            }).catch(error =>{
               console.log("获取推荐票数量："+error);
            });
        }).catch(error =>{
            alert("获取本用户ID和name错误。"+error);
        });
    },
})