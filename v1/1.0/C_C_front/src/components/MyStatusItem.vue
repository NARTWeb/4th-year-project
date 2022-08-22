<template>
    <div>
        <el-row>
            <div id="left">
                <span>{{date}}</span>
            </div>
            <div id="mid">
                <span>{{message}}</span>
                <img :src="pictures">
            </div>
            <div id="right">
                <el-icon v-if="isMine" @click="delStatus"><Delete /></el-icon>
                <el-icon v-else style="hidde"><Delete /></el-icon>
            </div>
        </el-row>
    </div>
</template>
<script setup>

    const props = defineProps({
        isMine:Boolean,
        message: String,
        pictures: String,
        date: Date,
        key: String,
    })

    function isMine(){
    }
    function delStatus(){
    }

</script>
<style>
    
</style>