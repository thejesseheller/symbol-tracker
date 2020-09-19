import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080/"
});

export default {
  addSymbolAndDailyData(symbol, date, data) {
    return http.post(`add/${symbol}`, {
      date: date,      
      open: data['1. open'],
      high: data['2. high'],
      low: data['3. low'],
      close: data['4. close'],
      volume: data['5. volume']
    });
  },
  // addMostRecentTimeSeries(symbol, timeSeries) {
  //   return http.put(`stocks/${symbol}/update`, {      
  //     open: timeSeries['1. open'],
  //     high: timeSeries['2. high'],
  //     low: timeSeries['3. low'],
  //     close: timeSeries['4. close'],
  //     volume: timeSeries['5. volume']
  //   })
  // },
  getAllStocks() {
    return http.get('stocks');
  }
}