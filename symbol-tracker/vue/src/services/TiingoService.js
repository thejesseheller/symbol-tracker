import axios from 'axios';

const apiKey = 'd9a5e62fe038e315d244009ee814f8edebdc8c8c';

const http = axios.create({
  baseURL: "https://api.tiingo.com/tiingo/daily",
  headers: {    
    'content-type': "application/json"
  }
});

export default {
  getMeta(symbol) {
    return http.get(`/${symbol}?token=${apiKey}`)
  }
}