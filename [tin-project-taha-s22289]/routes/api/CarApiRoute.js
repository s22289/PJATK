const express = require('express');
const router = express.Router();

const carApiController = require('../../api/CarAPI');

router.get('/', carApiController.getCars);
router.get('/:carid', carApiController.getCarById);
router.post('/', carApiController.createCar);
router.put('/:carid', carApiController.updateCar);
router.delete('/:carid', carApiController.deleteCar);

module.exports = router;
