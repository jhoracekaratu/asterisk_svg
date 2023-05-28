const path=require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports={
entry:{
    index: path.resolve(__dirname,'./src/index.js')
},
output:{
    path:path.resolve(__dirname,'./build'),
    filename:'[name].bundle.js'
},
plugins: [new HtmlWebpackPlugin({
   
    template: './src/index.html',
    inject: 'body'
  })]
,

module:{
    rules:[
        //   {
        //     test: /\.css$/,
        //     use: ["style-loader", "css-loader"]
        // },
        // 
        // ,
        {
            test: /\.(scss)$/,
        use:[
            {
                loader: 'style-loader'
            },
            {
                loader: 'css-loader'
            },
            
            {
                loader: 'sass-loader'
            }
        ]

    }

    ]
}

,

performance:
        {
            hints:false,
            maxEntrypointSize: 512000,
            maxAssetSize: 512000
        }

}


        
        
    
