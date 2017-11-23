// app.js

var express = require('express'); //Require Express package
var mongoose = require('mongoose'); //Require Mongoose package to use MongoDB
var app = express();    //Initialize server app
var port = 3000;
var itemRouter = require('./src/routes/itemRoutes');  //Require router in src/

app.set('view engine', 'ejs');
app.use(express.static('public'));  //Access freely every file in public/
app.use('/items', itemRouter);    //Set as root for router /items example:http://localhost:3000/items/blabla

app.listen(port, function(){
  console.log('Server is running on port:', port);  //Listen on port 3000
})
app.get('/', function(req, res){
  res.send('Hello Express');    //Response to GET http://localhost:3000/
})
mongoose.Promise = require('bluebird');
mongoose.connect('mongodb://testuser:test@ds119436.mlab.com:19436/testing')
.then(() => { // if all is ok we will be here
      console.log('Connected');
    })
    .catch(err => { // if error we will be here
        console.error('App starting error:', err.stack);
        process.exit(1);
    });
