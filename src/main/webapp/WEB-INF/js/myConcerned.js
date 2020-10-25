let attentionInterface_vm = new Vue({
    el:"#myConcerned",
    data:{
        topTips:"个人中心",
        user_id:0,
        user_name:"我系成龙",
        activeIndex: '1',
        circleUrl:"../images/avatar.jpg",
        buttonContext:"取关",
        attentionNum:0,
        fans:0,
        eachAttention:0,
        tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
        }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
        }],
        userData:[
            {
                avatar:"头像0",
                name:"name0",
                id:0
            },{
                avatar:"头像1",
                name:"name1",
                id:1
            }
        ],
    },
    methods:{
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClick(row){
            console.log(row);
        },
        selectUser(){
            axios.post("/shaohuashuwu_war_exploded/userSession/saveSelectedUser/" +
                4+"/"+encodeURI("王宏斌")).then(response =>{
                alert(JSON.stringify(response.data));
            }).catch(error =>{
                alert(JSON.stringify("选中的用户信息装填失败"));
            });
        },
        addAttention(){
            //先判断是否已经关注该作者用户
            axios.post("/shaohuashuwu_war_exploded/attentionInfoController/isAlreadyAttention/" +
                this.user_id+"/"+this.selected_user_id).then(response =>{
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
        cancelAttention(id){
          //取消关注选中ID的用户
           alert("取消关注ID为["+id+"]的用户");
        },
    },
    // mounted(){
    //     //钩子函数，在加载页面后，渲染数据前执行
    //     //获取本用户的ID和name
    //     axios.get("/shaohuashuwu_war_exploded/userSession/getUser").then(resp =>{
    //         let info = resp.data;
    //         let userId = JSON.stringify(info["user_id"]);
    //         let userName = info["user_name"];
    //         userId = parseInt(userId);
    //         this.user_id = userId;
    //         this.user_name = userName;
    //         alert("user_id & user_name的值 = ["+userId+"] ["+userName+"]");
    //     }).catch(error =>{
    //         alert("获取本用户ID和name错误。")
    //     });
    //     axios.get("/shaohuashuwu_war_exploded/userSession/getSelectedUser").then(resp =>{
    //         let info = resp.data;
    //         let selected_userId = JSON.stringify(info["selected_user_id"]);
    //         let selected_userName = info["selected_user_name"];
    //         selected_userId = parseInt(selected_userId);
    //         this.selected_user_id = selected_userId;
    //         this.selected_user_name = selected_userName;
    //         alert("selected_user_id & selected_user_name的值 = ["+selected_userId+"] ["+selected_userName+"]");
    //     }).catch(error =>{
    //         alert("获取选中用户ID和name错误。");
    //     });
    // },
    // created(){
    //     //created()钩子函数，在加载页面时调用，在mounted()钩子函数之前执行
    //     axios.post("/shaohuashuwu_war_exploded/userSession/saveSelectedUser/" +
    //         4+"/"+encodeURI("王宏斌")).then(response =>{
    //         console.log(JSON.stringify(response.data));
    //     }).catch(error =>{
    //         console.log(JSON.stringify("选中的用户信息装填失败"));
    //     });
    // }
})