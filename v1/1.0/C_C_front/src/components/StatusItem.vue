<template>
    <div>
        <el-row>
            <div id="left">
                <img :src="avatar" >
                <span>{{uname}}</span>
            </div>
            <div id="mid">
                <span>{{message}}</span>
                <img :src="pictures">
            </div>
            <div id="right">
                <el-icon v-if="likeStatus"><StarFilled /></el-icon>
                <el-icon v-else><Star /></el-icon>
                <el-button round type="primary" @click="makeComment">...</el-button>
            </div>
        </el-row>
    </div>
</template>
<script setup>

    const props = defineProps({
        statusId: String,
        avatar: String,
        uname: String,
        message: String,
        pictures: String,
        heart: Boolean,
        heartNum: int,
        date: Date,
        key: String,
    })

    function likeStatus(){
    }
    function makeComment(){
    }

</script>
<style>
    
</style>