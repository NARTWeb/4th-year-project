<template>
    <div>
        <div>
            <el-row>
                <el-button round type="primary" @click="loadMore">Load More</el-button>
                <el-icon @click="loadMore"><CaretBottom /></el-icon>
            </el-row>  
        </div>
    </div>
</template>
<script setup>

    const props = defineProps({
        isUp: Boolean,
        loading: Boolean,
        nodata: Boolean,
    })

    function isDirectionUp(isUP){
        this.$emit('isUP')
    }
    function propsLoad(){
        this.$emit('propsLoad');
    }
    function noNewData(noNew){
        this.$emit('noData')
    }
    function loadMore(){
        this.$emit('loadMore');
    }

</script>
<style>
    
</style>