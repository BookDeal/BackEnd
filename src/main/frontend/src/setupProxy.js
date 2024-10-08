
import { createProxyMiddleware } from 'http-proxy-middleware';

const { REACT_APP_API_URL } = process.env;
module.exports = function (app: any) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: REACT_APP_API_URL || 'http://localhost:8080',
            changeOrigin: true,
        })
    );
};