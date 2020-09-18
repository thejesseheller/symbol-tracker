import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080/"
});

export default {
  addSymbol(symbol) {
    return http.post(`add/${symbol}`);
  },
  addMostRecentTimeSeries(symbol, timeSeries) {
    return http.put(`stocks/${symbol}/update`, {      
      open: timeSeries['1. open'],
      high: timeSeries['2. high'],
      low: timeSeries['3. low'],
      close: timeSeries['4. close'],
      volume: timeSeries['5. volume']
    })
  },
  getAllStocks() {
    return http.get('stocks');
  }
}