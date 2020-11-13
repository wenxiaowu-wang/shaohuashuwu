new Vue({
    // router,
    el: '#app',
    data() {
        return {
            authorInfo:[],
        }
    },
    methods:{
        getAuthorInfo(){
            var _this = this;
            axios.post('http://localhost:8080/authorInfoController/selectAuthorInfoVo')
                .then(function (response){
                    _this.authorInfo = response.data;
                    console.log(response.data)
                    console.log(_this.authorInfo);
                })
                .catch(function (error){
                        console.log(error);
                        alert("相应失败");
                    })
        },



    },
    created:function (){ //页面加载时查询所有
        this.getAuthorInfo();
    }
})