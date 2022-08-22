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
                <button @click="changeBtn">change</button>
                <button @click="ifMatch">change</button>
            </div>
        </el-row>
    </div>
</template>
<script setup>
    const props = defineProps({
        label: String,
        value: String,
        hideValue: Boolean,
        hideBtn: Boolean,
        matchFormat: String,
    })

    function changeBtn(){
    }
    function ifMatch(){
    }

</script>
<style>
    
</style>