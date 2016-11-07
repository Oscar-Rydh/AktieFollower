var express = require("express")
var app = express();

app.get('/', function(req,res){
res.send('Tjena Calle');
})

app.listen(8080);
