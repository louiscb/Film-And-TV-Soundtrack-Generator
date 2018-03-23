var express = require('express');
var router = express.Router();

router.get('/:name', function(req, res, next) {
    var db = req.db;
    var name = req.params.name;

    var collection = db.get(name);

    collection.find({}, {}, function(e, docs){
        res.json(docs);
    });
});

module.exports = router;
