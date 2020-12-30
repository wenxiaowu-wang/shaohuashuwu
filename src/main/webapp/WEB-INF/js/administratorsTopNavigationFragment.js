new Vue({
    el: '#administrators_topNavigation_div_id',
    data() {
        return {
            /*
            * 获取数据
            * */
            //管理员信息
            administrator:'',

        }
    },
    methods: {
        getadminisrtratorInfo(){
            var _this = this;
            axios.get('/shaohuashuwu/adminInfoController/getadmin_id')
                .then(function (response){
                    _this.administrator = response.data;
                })
                .catch(function (error){
                    alert("相应失败");
                })
        }

    },
    created:function (){ //页面加载时查询所有
        this.getadminisrtratorInfo();
    }
})