<template>
    <div class="all">
            <div class="top">
                <span>{{props.label}}</span>
                <hr>
            </div>
            <div class="bottom">
                <div v-if="isPwd" class="pwde">
                    <div>
                        <input placeholder="Please type your old password here" >
                    </div>
                    <div>
                        <el-button round type="primary" v-show="ifMatch" @click="changeBtn">change</el-button>
                    </div>
                </div>
                <div v-else-if="isAva" class="avae">
                    <div>
                        <img :src="value">
                    </div>
                    <div>
                        <el-button round type="primary" @click="changeBtn">change</el-button>
                    </div>
                </div>
                <div v-else class="elements">
                    <div>
                        <span>{{propsvalue}}</span>
                    </div>
                    <div>
                        <el-button round type="primary" @click="changeBtn">change</el-button>
                    </div>
                </div>   
            </div>
    </div>
</template>
<script setup>

    const props = defineProps({
        label: String,
        value: String,
        // hideValue: Boolean,
        // hideBtn: Boolean,
        // matchFormat: String,
    })

    function isPwd(){
        if(props.label=="password"){
            return true;
        }else{
            return false;
        }
    }
    function isAva(){
        if(props.label=="avatar"){
            return true;
        }else{
            return false;
        }
    }
    function changeBtn(){
    }
    function ifMatch(){
        if(input.value==props.value){
            return true;
        }else{
            return false;
        }
    }

</script>
<style scoped>
    .all {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: space-between;
        flex-flow: column nowrap;
        align-items: center;
        width: 100vw;
    }
    .top {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: space-between;
        flex-flow: column nowrap;
        width: 100vw;
    }
    .pwde {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: space-between;
        flex-flow: row nowrap;
        align-items: center;
        width: 100vw;
    }
    .avae {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: space-between;
        flex-flow: row nowrap;
        align-items: center;
        width: 100vw;
    }
    .elements {
        display: -webkit-flex; /* Safari */
        display: flex;
        justify-content: space-between;
        flex-flow: row nowrap;
        align-items: center;
        width: 100vw;
    }
</style>