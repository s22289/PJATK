const CarRepository = require('../repository/mysql2/carRepository');

exports.getCars = (req, res, next) => {
    CarRepository.getCars()
    .then(cars => {
        res.status(200).json(cars);
    })
    .catch(err => {
        console.log(err);
    });
};

exports.getCarById = (req, res, next) => {
    const carid = req.params.carid;
    CarRepository.getCarById(carid)
    .then(car => {
        if(!car) {
            res.status(404).json({
                message: 'Car with id: '+carid+' not found'
            })
        }else {
            res.status(200).json(car);
        }
    });
};


exports.createCar = (req, res, next) => {
    CarRepository.createCar(req.body)
    .then(newObj => {
        res.status(201).json(newObj);

    })
    .catch(err => {
        if (!err.statusCode){
            err.statusCode = 500;
        }
        next(err);
    });
};

exports.updateCar = (req, res, next) => {
    const carid = req.params.carid;
    CarRepository.updateCar(carid, req.body)
    .then(result => {
        res.status(200).json({message: 'Car updated!', car: result });
    })
    .catch(err =>{
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        next(err);
    });
};

exports.deleteCar = (req, res, next) => {
    const carid = req.params.carid;
    CarRepository.deleteCar(carid)
    .then(result =>{
        res.status(200).json({message: 'Removed Car', car: result});
    })
    .catch(err => {
        if(!err.statusCode) {
            err.statusCode = 500;
        }
        next(err);
    });
};