const express = require('express');
const router = express.Router();

const customerController = require('../controllers/customerController');

router.get('/', customerController.showCustomerList);
router.get('/add', customerController.showAddCustomersForm);
router.get('/edit/customerid', customerController.showEditCustomers);
router.get('/details/:customerid', customerController.showCustomersDetails);

module.exports = router;