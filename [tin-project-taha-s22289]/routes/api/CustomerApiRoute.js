const express = require('express');
const router = express.Router();

const customerApiController = require('../../api/CustomerAPI');

router.get('/', customerApiController.getCustomers);
router.get('/:customerid', customerApiController.getCustomerById);
router.post('/', customerApiController.createCustomer);
router.put('/:customerid', customerApiController.updateCustomer);
router.delete('/:customerid', customerApiController.deleteCustomer);

module.exports = router;
