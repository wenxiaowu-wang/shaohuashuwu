new Vue( {
    el: '#disanhang-id',
    data() {
        return {
            difvolenumInfo:{
                xuanhuannum:'',
                qihuannum:'',
                wuxianum:'',
                xianxianum:'',
                dushinum:'',
                xianshinum:'',
                lishinum:'',
                junshinum:'',
                youxinum:'',
                xuanyinum:'',
                kehuannum:'',
                tiyunum:'',
                qingxiaoshuonum:'',
            },
        };
    },
    methods: {
        finddifvolenum(){
            var _this = this;
            axios.get('http://localhost:8080/worksInfoController/difvolenum')
                .then(function (response){
                    _this.difvolenumInfo = response.data;//相应数据给userlist
                    // alert(JSON.stringify(response.data));

                    console.log(response.data);
                    console.log("--"+_this.difvolenumInfo.xuanhuannum);
            })
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
        },

    },
    created:function (){ //页面加载时查询所有
        this.finddifvolenum();
    }
})