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
  getAllStocks() {
    return http.get('stocks');
  },
  getMostRecentDailyData(symbol) {
    return http.get(`stocks/${symbol}/most-recent`);
  },
  stopTrackingStock(symbol) {
    return http.put(`remove/${symbol}`, symbol);
  },
  restartTracking(symbol) {
    return http.put(`/stocks/${symbol}`);
  },
  getSingleStock(symbol) {
    return http.get(`stocks/${symbol}`);
  },
  addName(symbol, stock) {
    return http.put(`stocks/${symbol}/add-name`, {      
      companyName: stock.companyName
    });
  }
}