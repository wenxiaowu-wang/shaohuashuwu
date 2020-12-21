new Vue({
    el: "#app",
    data: {
        phone_number:'',
        user_id:'',
        user_name:'',
        head_portrait:'',
    },
    methods: {
        disPlay() {
            axios.get("/shaohuashuwu_war_exploded/userSession/getPhoneNumber").then(response =>{
                let info = response.data;
                let phone_number_ = info["phone_number"];
                this.phone_number = phone_number_;
                this.$message({
                    type: 'success',
                    message: '成功！'
                });
            }).catch(error =>{
                console.log("获取信息失败！"+error);
            });

        }
    },
    mounted(){

        //钩子函数是在HTML渲染前执行
        console.log("created() loading");
        axios.get("/shaohuashuwu_war_exploded/userSession/getPhoneNumber").then(response =>{
            let info = response.data;
            let phone_number_ = info["phone_number"];
            this.phone_number = phone_number_;
            this.$message({
                type: 'success',
                message: '成功！'
            });
        }).catch(error =>{
            console.log("获取信息失败！"+error);
        });
    }
})