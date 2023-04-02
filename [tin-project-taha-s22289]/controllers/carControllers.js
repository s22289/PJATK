const CarRepository = require('../repository/mysql2/carRepository');

exports.showCarList = (req, res, next) => {
    CarRepository.getCars()
    .then(cars => {
        console.log(cars)
        res.render('pages/car/car-list', {
        cars: cars,
        navLocation: 'car'
    });
});   

}



 
exports.showCarFormNew = (req, res, next) => {
        res.render('pages/car/car-form-new', {
        car: {},
        pageTitle: 'New car',
        formMode: 'createNew',
        btnLabel: 'Add a car',
        formAction: '/car/add',
        navLocation: 'car',
        validationErrors: []
    });
}



exports.showCarFormEdit = (req, res, next) => {

    const carid = req.params.carid;
    
    CarRepository.getCarById(carid)
    .then(car => {
        res.render('pages/car/car-form-new', {
            car: car,
            formMode: 'edit',
            pageTitle: 'car edit',
            btnLabel: 'Edit Car',
            formAction: '/car/edit',
            navLocation: 'car',
            validationErrors: []
        });
    });
}



exports.showCarDetails = (req, res, next) => {
    const carid = req.params.carid;
    
    CarRepository.getCarById(carid)
    .then(car => {
        console.log(car.rents); 
        res.render('pages/car/car-form-new', {
            car: car,
            formMode: 'showDetails',
            pageTitle: 'car details',
            formAction: '',
            navLocation: 'car',
                validationErrors: []
            });
        });
}




exports.addCar = (req, res, next) => {
    const carData = { ...req.body };
    console.log(carData)
    CarRepository.createCar(carData)
        .then( result => {
            res.redirect('/car');
        })
        .catch(err => {
            console.log(err.errors)
            res.render('pages/car/car-form-new', {
                car: carData,
                pageTitle: 'New car',
                formMode: 'createNew',
                btnLabel: 'Add car',
                formAction: '/car/add',
                navLocation: 'car',
                validationErrors: err.errors
            })
        });
       
};

exports.updateCar = (carid, carData) => {

    return car.update(carData, {where: {_id: carid }});
         
};

exports.deleteCar = (carid) => {

    return car.destroy({
        where: { _id: carid }
    });

        
};