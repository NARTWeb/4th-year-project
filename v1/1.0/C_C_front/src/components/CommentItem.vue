<template>
    <div>
        <el-row>
            <span>{{username}}</span>
            <span>{{message}}</span>
            <span>{{Date}}</span>
        </el-row>
    </div>
</template>
<script setup>

    const props = defineProps({
        username: String,
        message: String,
        date: Date,
    })

</script>
<style>
    
</style>