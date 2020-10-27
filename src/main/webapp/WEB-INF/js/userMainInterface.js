
new Vue( {
    el: '#app',
    data() {
        return {
            select_input:'',
            work_info:{
                work_name:'',

            },

        };
    },
    methods: {
        selectbyinfo(){
            // window.location.assign("../pages/keywordSearchResultInterface.html");
            if(this.select_input == null){
                console.log("搜索为空");
            }
            if(this.select_input != null){

                this.work_info.work_name = this.select_input;
                var _this = this;
                console.log(_this.select_input);

                axios.post('http://localhost:8080/worksInfoController/selectworkbyinfo',_this.work_info)
                    .then(function (response){
                        window.location.assign("../pages/keywordSearchResultInterface.html");
                }.bind(this))
                .catch(function (error){
                    console.log(error);
                    alert("相应失败");
                })
            }
        },
    }
})