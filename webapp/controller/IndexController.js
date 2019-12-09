
var express = require('express');
var router = express.Router();
var url = require('url');
var path = require('path');

router.get('/login', function (req, res) {
    //res.send('hello this is xiaoyunhui app server api');
    res.sendFile(path.join(__dirname, '../pages/common/login/login.html'));
});


router.get('/register', function (req, res) {
    //res.send('hello this is xiaoyunhui app server api');
    res.sendFile(path.join(__dirname, '../pages/common/register/register.html'));
});
module.exports = router;
