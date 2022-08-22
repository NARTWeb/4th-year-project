<template>
    <div>
        <el-row>
            <div id="left">
                <img :src="avatar" >
                <span>{{username}}</span>
            </div>
            <div id="right">
                <el-button round type="primary" @click="pressBtn">{{buttonLabel}}</el-button>
                <el-icon @click="deleteItem"><Close /></el-icon>
            </div>
        </el-row>
    </div>
</template>
<script setup>
    const props = defineProps({
        avatar: String,
        username: String,
        buttonLabel: String,
        key: String,
    })

    function deleteItem(){
        this.$emit('delItem')
    }
    function pressBtn(){
        this.$emit('btnFunc');
    }

</script>
<style>
    
</style>