import axios from 'axios';

const apiKey = '0D2KY60RDJTR9TEZ';

const http = axios.create({
  baseURL: "https://www.alphavantage.co",
  headers: {    
    'content-type': "application/json"
  }
});

export default {
  getMostRecentTimeSeries(symbol) {
    return http.get(`/query?function=TIME_SERIES_DAILY&symbol=${symbol}&apikey=${apiKey}`)
  },
  getIntradayTimeSeries(symbol) {
    return http.get(`/query?function=TIME_SERIES_INTRADAY&symbol=${symbol}&interval=60min&outputsize=compact&apikey=${apiKey}`)
  }
}