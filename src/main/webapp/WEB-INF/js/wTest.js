new Vue({
    el: '#app',
    data: {
        value: [],




        form: {
            city: '',
            area: '',
            minarea: '',
            selectedOptions: [],//地区筛选数组
        },
    },
    created() {
    },
    methods: {

        handleChange1(value) {
            console.log(value);
        },

        handleChange(value) {
            this.form.city = this.form.selectedOptions[0];
            this.form.area = this.form.selectedOptions[1]
            this.form.minarea = this.form.selectedOptions[2]
        },
        myAddressCity: function (value) {
            for (y in this.CityInfo) {
                if (this.CityInfo[y].value == value) {
                    return value = this.CityInfo[y].label
                }
            }
        },
        myAddressArea: function (value) {
            for (y in this.CityInfo) {
                for (z in this.CityInfo[y].children) {
                    if (this.CityInfo[y].children[z].value == value && value != undefined) {
                        return value = this.CityInfo[y].children[z].label;
                    }
                }
            }
        },
        myAddressMinarea: function (value) {
            for (y in this.CityInfo) {
                for (z in this.CityInfo[y].children) {
                    for (i in this.CityInfo[y].children[z].children) {
                        if (this.CityInfo[y].children[z].children[i].value == value && value != undefined) {
                            return value = this.CityInfo[y].children[z].children[i].label
                        }
                    }
                }
            }
        },
    },

    filters: {


    },
})