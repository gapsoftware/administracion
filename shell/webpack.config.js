/**
 * Created by gperezv on 28-03-18.
 */

var path = require('path');

var config = {
    devtool: 'sourcemaps',
    cache: true,
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
};


var appHome = Object.assign({},config,{
    name: "app.home",
    entry: './src/main/js/app.home.js',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/home.bundle.js'
    }
});

var appAdmin = Object.assign({},config,{
    name: "app.admin",
    entry: './src/main/js/app.admin.js',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/admin.bundle.js'
    }
});

var appAccount = Object.assign({},config,{
    name: "app.account",
    entry: './src/main/js/app.account.js',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/account.bundle.js'
    }
});


module.exports = [appHome, appAdmin, appAccount];
