const express = require('express');
const router = express.Router();

const rentController = require('../controllers/rentControllers');

router.get('/', rentController.showRentList);
router.get('/add', rentController.showRentFormNew);
router.get('/edit/:id', rentController.showRentFormEdit);
router.get('/details/:Rent_ID', rentController.showRentDetails);

module.exports = router;