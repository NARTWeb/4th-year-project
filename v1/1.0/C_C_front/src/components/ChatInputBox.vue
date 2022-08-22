<template>
    <div>
        <el-row>
            <div id="left">
                <input placeholder="Say something here...">
            </div>
            
            <div id="right">
                <el-icon @click="addPhoto"><Picture /></el-icon>
                <el-button round type="primary" @click="sendMsg">Send</el-button>
            </div>
        </el-row>
    </div>
</template>
<script setup>

    const props = defineProps({
        
    })

    function addPhoto(){
    }
    function clearText(){
    }
    function saveToSession(msg){
    }
    function sendMsg(){
    }

</script>
<style>
    
</style>