import axios from 'axios';

const apiKey = '465e25cc7823482e822283944afca046';

const http = axios.create({
  baseURL: "https://newsapi.org/v2",
  headers: {    
    'content-type': "application/json"
  }
});

export default {
  getNews(symbol) {
    return http.get(`/everything?q=${symbol}&pageSize=5&apiKey=${apiKey}`);
  }
  
}