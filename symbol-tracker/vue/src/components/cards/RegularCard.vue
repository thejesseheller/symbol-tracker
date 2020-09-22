<template>  
    <md-card md-with-hover>
      <md-card-header>
        <slot name="title"></slot>
      </md-card-header>

      <md-card-content>
        <slot name="info"></slot>  
      </md-card-content>

      <md-card-actions>
        <slot name="button1"></slot>
        <slot name="button2"></slot>
      </md-card-actions>
    </md-card>  
</template>

<style lang="scss" scoped>

</style>

<script>
export default {
  name: 'regular-card'
}
</script>