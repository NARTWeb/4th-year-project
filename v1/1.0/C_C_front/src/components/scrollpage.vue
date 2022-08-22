<template>
  <div v-loading="loading" ref="scroll" id="scroll-page" style="overflow: hidden">
    <slot></slot>
  </div>
</template>
<script setup>
    defineProps({
        isUp: Boolean,
        loading: Boolean,
        nodata: Boolean
    })
    function handleScroll(e) {
        if (!this.nodata) {
          let curHeight = document.documentElement.scrollTop || document.body.scrollTop
          var scrollPage = document.getElementById('scroll-page');

          if ((curHeight + window.innerHeight >= 
                this.$refs.scroll.offsetHeight + this.offset) 
            && this.isDownDirection()) {
            if (!this.loading) {
              this.$emit('loadFun')
            }
          }
        }
    }
    function isDownDirection() {
        if (typeof this.scrollAction.x == 'undefined') {
          this.scrollAction.x = window.pageXOffset;
          this.scrollAction.y = window.pageYOffset;
        }
        var diffX = this.scrollAction.x - window.pageXOffset;
        var diffY = this.scrollAction.y - window.pageYOffset;

        this.scrollAction.x = window.pageXOffset;
        this.scrollAction.y = window.pageYOffset;

        if (diffX < 0) {
          // Scroll right
        } else if (diffX > 0) {
          // Scroll left
        } else if (diffY < 0) {
          // Scroll down
          if (!isUp) {
            return true
          }
        } else if (diffY > 0) {
          // Scroll up
          if (isUp) {
            return true
          }
        } else {
          // First scroll event
        }
        return false
    }
</script>
<style></style>
