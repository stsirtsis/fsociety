// itemRoutes.js

var express = require('express');
var app = express();
var itemRouter = express.Router();

itemRouter.route('/').get(function(req, res){
  res.render('items');  //root of Router -> items.ejs
})
itemRouter.route('/single').get(function(req, res){
  res.render('singleItem'); // /single of Router -> singleItem.ejs
})
itemRouter.route('/add').get(function(req, res){
  res.render('addItem');  // /add of Router -> addItem.ejs
})

module.exports = itemRouter;
