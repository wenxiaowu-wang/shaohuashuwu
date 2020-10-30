let messageCenterInterface_vm = new Vue({
    el:"#messageCenter",
    data:{
        topTips:"个人中心",
        user_id:0,
        user_name:"我系成龙",
        activeIndex: "3",
        user_avatar:"avatar",
        tip_img_url:"",
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
            tipTotal1:0,    //系统消息提示的数量
            tipTotal2:0,    //更新消息提示的数量
            tipTotal3:0,    //私信消息提示的数量
        },
        button_disabled:false,      //清空、全部已读按钮禁用状态
        imageURL_header:"../images/avatar/",    //图片文件头
        imageURL_suffix:".jpg",             //图片文件尾
        systemMessageData:[
            {
                notice_id:0,        //通知ID
                send_by:0,          //发送者ID
                notice_content:"",  //通知内容
                notice_title:"",    //通知标题
                send_time:"",       //通知时间
                notice_tip:1        //通知提示
            }
        ],
        updateMessageData:[],
        chatMessageData:[],
        displayData:{
            systemMessageData:[],
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
            this.displayData.eachAttentionData = this.eachAttentionData.slice(beginNums,endNums);
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
            this.displayData.attentionData = this.attentionData.slice(beginNums,endNums);
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
            this.displayData.fansData = this.fansData.slice(beginNums,endNums);
        },
        addAttention(id){
            //先判断是否已经关注该作者用户
            axios.post("/shaohuashuwu_war_exploded/attentionInfoController/isAlreadyAttention/" +
                this.user_id+"/"+id).then(response =>{
                    alert("hello "+response.data);
                    //当判断结果为false时，进行添加关注信息操作
                    if (!response.data){
                        axios.post("/shaohuashuwu_war_exploded/attentionInfoController/addAttentionInfo",{
                            "reader_id":this.user_id,
                            "author_id":this.selected_user_id
                        }).then(response =>{
                            alert("关注成功");
                        }).catch(error =>{
                            alert("因为其它原因，关注失败");
                        });
                    }else{
                        alert("您已经关注了该用户，请勿重复关注");
                    }
            }).catch(error =>{
                alert("未判断出是否已经关注该用户");
            });
        },
        cancelAttention(id,name,index){
          //取消关注选中ID的用户
          //   let this_ = this;
            this.$confirm('此操作将取消关注[ '+name+' ], 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                // let this__ = this;
                //删除关注信息
                axios.post("/shaohuashuwu_war_exploded/attentionInfoController/deleteAttentionInfo/" +
                    this.user_id+"/"+id).then(response =>{
                    this.$message({
                        type: 'success',
                        message: '取消关注成功，将不会给您发送该作者动态。'
                    });
                    //更新页面的数据(很重要)
                    let opIndex = ((this.dynamicCurrentPage2-1)*this.pageSize)+index;
                    let page = this.dynamicCurrentPage2; //显示页，更新视图的作用
                    if (opIndex == this.attentionData.length -1 && page -1>0 && index == 0){
                        page -= 1;  //若操作的数组元素为末尾元素，并且为>1的页面而且该元素是显示数组的下标为0的元素，将会显示当前页面的前一个页面
                    }
                    //this.displayData.attentionData.splice(index,index); //清除对应显示数据
                    this.attentionData.splice(opIndex,1);  //清除缓存的对应数据(从第几个元素开始，开始的几个，要插入的元素)
                    this.totals.total2 -= 1;    //更新整体数据计数
                    this.handleCurrentChange_attention(page);  //跳转页面，相当于更新视图
                    this.displayDivsSetting(); //判断是否要显示空内容提示
                    this.updateEachAttentionData(id); //更新被影响的相互关注的数据
                }).catch(error =>{
                    this.$message({
                        type: 'info',
                        message: '取消关注失败'
                    });
                });

            });
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
        updateFansButton(id){
            //更新粉丝页面的按钮显示
            //等待更新
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
            //获取与该用户相互关注的用户信息
            axios.get("/shaohuashuwu_war_exploded/attentionInfoController/" +
                 "getEachAttentionUserInfo/"+userId).then(resp =>{
                 let objectData = eval(JSON.stringify(resp.data));//将字符串转化为数组对象
                 console.log("获取的相互关注信息为："+typeof(objectData));
                 this.eachAttentionData = objectData;
                 console.log("与该用户互相关注信息总条数为："+objectData.length);
                 this.totals.total1 = objectData.length;
                 //配置显示数据
                 let endNums = this.pageSize;
                 if (endNums > objectData.length){
                     endNums = objectData.length;
                 }
                 this.displayData.eachAttentionData = objectData.slice(0,endNums);
                 //更新无数据提示的显示情况
                 if(objectData.length > 0){
                     this.displayDivs.displayDiv1 = "none";
                 }
            }).catch(error =>{
                 console.log("获取与该用户互相关注信息失败:"+error);
            });
            //获取该用户关注的用户信息
            axios.get("/shaohuashuwu_war_exploded/attentionInfoController/" +
                "getAttentionAuthorInfo/"+userId).then(resp2 =>{
                let objectData = eval(JSON.stringify(resp2.data));//将字符串转化为数组对象
                console.log("获取的信息为："+typeof(objectData));
                this.attentionData = objectData;
                console.log("关注信息总条数为："+objectData.length);
                this.totals.total2 = objectData.length;
                //配置当前显示数据
                let endNums = this.pageSize;
                if (endNums > objectData.length){
                    endNums = objectData.length;
                }
                this.displayData.attentionData = objectData.slice(0,endNums);
                //更新无数据提示的显示情况
                if (objectData.length > 0){  this.displayDivs.displayDiv2 = "none";  }
            }).catch(error =>{
                console.log("获取关注信息失败:"+error);
            });
            //获取关注该用户的用户信息，即粉丝信息
            axios.get("/shaohuashuwu_war_exploded/attentionInfoController/" +
                "getFansInfo/"+userId).then(resp3 =>{
                let objectData = eval(JSON.stringify(resp3.data));//将字符串转化为数组对象
                console.log("获取粉丝的信息为："+typeof(objectData));
                this.fansData = objectData;
                console.log("粉丝的关注信息总条数为："+objectData.length);
                this.totals.total3 = objectData.length;
                //配置当前显示数据
                let endNums = this.pageSize;
                if (endNums > objectData.length){
                    endNums = objectData.length;
                }
                this.displayData.fansData = objectData.slice(0,endNums);
                //更新无数据提示的显示情况
                if (objectData.length > 0){  this.displayDivs.displayDiv3 = "none";  }
            }).catch(error =>{
                console.log("获取粉丝关注信息失败:"+error);
            });

        }).catch(error =>{
            alert("获取本用户ID和name错误。");
        });
    },
})