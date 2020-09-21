<template>
  <div class="top">
    <h2 id="lookup-heading">{{ msg }}</h2>
    <label class="field a-field a-field_a3">
    <input class="field__input a-field__input" v-model="symbol" placeholder="e.g. AAPL, NFLX" required>
    <span class="a-field__label-wrap">
      <span class="a-field__label">Symbol goes here</span>
    </span>
    </label>    
    <button class="button" @click="getMostRecentlyDailyTimeSeriesData" v-on:click="isHidden = false">Go!</button>
      <div class="most-recent-daily-data" v-if="!isHidden">
        <h2 class="dd-symbol">{{ symbol }}</h2>
        <h3 class="dd-date">{{ lastRefreshed }}</h3>
        <p>Open: {{mostRecentDailyData['1. open']}}</p>
        <p>High: {{mostRecentDailyData['2. high']}}</p>
        <p>Low: {{mostRecentDailyData['3. low']}}</p>
        <p>Close: {{mostRecentDailyData['4. close']}}</p>
        <p>Volume: {{mostRecentDailyData['5. volume']}}</p>
        <button class="button" @click="trackStock">Track this Stock</button>        
      </div>     
  </div>
</template>

<script>
import DatabaseService from '@/services/DatabaseService';
import AlphaService from '@/services/AlphaService';

export default {
  name: 'symbol-input',  
  props: {
    msg: String
  },
  data() {
    return {
      symbol: '',
      lastRefreshed: '',
      mostRecentDailyData: {},
      isHidden: true
    }
  },
  methods: {
    trackStock() {
      DatabaseService.addSymbolAndDailyData(this.symbol, this.lastRefreshed, this.mostRecentDailyData);           
    }, 
    getMostRecentlyDailyTimeSeriesData() {                  
      AlphaService.getMostRecentTimeSeries(this.symbol).then(response => {        
        this.lastRefreshed = response.data['Meta Data']['3. Last Refreshed'];        
        this.mostRecentDailyData = response.data['Time Series (Daily)'][this.lastRefreshed];        
      })
    }
  }
}
</script>

<style>

.top {
  color: white;    
}

#lookup-heading {
  font-size: 3rem;  
}

.field{
  
  --uiFieldPlaceholderColor: var(--fieldPlaceholderColor, #767676);
}

.field__input{ 
  background-color: transparent;
  border-radius: 0;
  border: none;

  -webkit-appearance: none;
  -moz-appearance: none;

  font-family: inherit;
  font-size: 1em;
  color: white
}

.field__input:focus::-webkit-input-placeholder{
  color: var(--uiFieldPlaceholderColor);
}

.field__input:focus::-moz-placeholder{
  color: var(--uiFieldPlaceholderColor);
  opacity: 1;
}

.a-field{
  display: inline-block;
}

.a-field__input{ 
  display: block;
  box-sizing: border-box;
  width: 100%;
}

.a-field__input:focus{
  outline: none;
}

.a-field{
  --uiFieldHeight: var(--fieldHeight, 2.5rem);  
  --uiFieldBorderWidth: var(--fieldBorderWidth, 2px);
  --uiFieldBorderColor: var(--fieldBorderColor);

  --uiFieldFontSize: var(--fieldFontSize, 1rem);
  --uiFieldHintFontSize: var(--fieldHintFontSize, 1rem);

  --uiFieldPaddingRight: var(--fieldPaddingRight, 1rem);
  --uiFieldPaddingBottom: var(--fieldPaddingBottom, 1rem);
  --uiFieldPaddingLeft: var(--fieldPaddingLeft, 1rem);   

  position: relative;
  box-sizing: border-box;
  font-size: var(--uiFieldFontSize);
  padding-top: 1rem;  
}

.a-field__input{
  height: var(--uiFieldHeight);
  padding: 0 var(--uiFieldPaddingRight) 0 var(--uiFieldPaddingLeft);
  border-bottom: var(--uiFieldBorderWidth) solid var(--uiFieldBorderColor);  
}

.a-field__input::-webkit-input-placeholder{
  opacity: 0;
  transition: opacity .2s ease-out;
}

.a-field__input::-moz-placeholder{
  opacity: 0;
  transition: opacity .2s ease-out;
}

.a-field__input:not(:placeholder-shown) ~ .a-field__label-wrap .a-field__label{
  opacity: 0;
  bottom: var(--uiFieldPaddingBottom);
}

.a-field__input:focus::-webkit-input-placeholder{
  opacity: 1;
  transition-delay: .2s;
}

.a-field__input:focus::-moz-placeholder{
  opacity: 1;
  transition-delay: .2s;
}

.a-field__label-wrap{
  box-sizing: border-box;
  width: 100%;
  height: var(--uiFieldHeight);	

  pointer-events: none;
  cursor: text;

  position: absolute;
  bottom: 0;
  left: 0;
}

.a-field__label{
  position: absolute;
  left: var(--uiFieldPaddingLeft);
  bottom: calc(50% - .5em);

  line-height: 1;
  font-size: var(--uiFieldHintFontSize);

  pointer-events: none;
  transition: bottom .2s cubic-bezier(0.9, -0.15, 0.1, 1.15), opacity .2s ease-out;
  will-change: bottom, opacity;
}

.a-field__input:focus ~ .a-field__label-wrap .a-field__label{
  opacity: 1;
  bottom: var(--uiFieldHeight);
}

.a-field_a3{
  padding-top: 1.5rem;
}

.a-field_a3 .a-field__label-wrap::after{
  content: "";
  box-sizing: border-box;
  width: 100%;
  height: 0;

  opacity: 0;
  border: var(--uiFieldBorderWidth) solid var(--fieldBorderColorActive);

  position: absolute;
  bottom: 0;
  left: 0;

  will-change: opacity, height;
  transition: height .2s ease-out, opacity .2s ease-out;
}

.a-field_a3 .a-field__input:focus ~ .a-field__label-wrap::after{
  height: 100%;
  opacity: 1;
}

.a-field_a3 .a-field__input:focus ~ .a-field__label-wrap .a-field__label{
  bottom: calc(var(--uiFieldHeight) + .5em);
}

.field{
  --fieldBorderColor: #D1C4E9;
  --fieldBorderColorActive: #673AB7;
}

button,
.button{
  --button: hsl(0, 0%, 12%);
  --button-hover: hsl(0, 0%, 20%);
  --button-active:  hsl(0, 0%, 30%);
  --button-visited: hsl(0, 0%, 20%);
  --button-colour: var(--colour-white);
  --button-border: var(--colour-black);
}

button {
  padding: .9rem 1.7rem;
  color: var(--button-colour);
  font-weight: 500;
  font-size: 16px;
  transition: all 0.3s ease-in-out;
  background: var(--button);
  border: solid 1px var(--button-border);
  box-shadow: inset 0 0 0 2px var(--colour-white);
  margin-right: 1em;
}

button:hover {
  text-decoration: underline;
  background: var(--button-hover);
  box-shadow: inset 0 0 0 4px var(--colour-white);
}

button:active {
  background: var(--button-active);
}

button:visited {
  background: var(--button-visited);
}

</style>