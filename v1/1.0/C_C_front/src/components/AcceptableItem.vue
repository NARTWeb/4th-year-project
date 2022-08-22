<template>
    <div>
        <el-row>
            <div id="left">
                <img :src="avatar" >
                <span>{{name}}</span>
                <span>{{senderName}}</span>
                <span>{{message}}</span>
            </div>
            <div id="right">
                <el-button round type="primary" @click="acceptBtn">{{buttonLabel}}</el-button>
                <el-button round type="primary" @click="rejectBtn">{{buttonLabel}}</el-button>
            </div>
        </el-row>
    </div>
</template>
<script setup>

    const props = defineProps({
        avatar: String,
        name: String,
        senderName: String,
        message: String,
        buttonLabel: String,
        key: String,
    })

    function acceptBtn(){
        this.$emit('accept')
    }
    function rejectBtn(){
        this.$emit('reject');
    }

</script>
<style>
    
</style>