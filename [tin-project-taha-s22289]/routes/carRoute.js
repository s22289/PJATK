const express = require('express');
const router = express.Router();

const carController = require('../controllers/carControllers');


router.get('/', carController.showCarList);
router.get('/add', carController.showCarFormNew);
router.get('/edit/:CarID', carController.showCarFormEdit);
router.get('/details/:CarID', carController.showCarDetails);


module.exports = router;