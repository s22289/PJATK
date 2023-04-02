const CustomerRepository = require('../repository/mysql2/customerRepository');
const CarRepository = require('../repository/mysql2/carRepository');
const RentRepository = require('../repository/mysql2/rentRepository');


exports.showRentList = (req, res, next) => {
       RentRepository.getRents()
       .then(rents => {
              res.render('pages/rent/rent-list', {
              rents: rents,
              pageTitle: 'List of customer',
              navLocation: 'rent'
       });
  });       
       
}

exports.showRentFormNew = (req, res, next) => {
       let allCustomers, allCars;

       RentRepository.getRents()
           .then(rent => {
               allRents = rent;
               return CustomerRepository.getCustomers();
           })
           .then(customer => {
              allCustomers = customer;
               return CarRepository.getCars();
           })
           .then(car => {
               allCars = car;
               res.render('pages/rent/rent-form-new', {
                   rent: {},
                   allCustomers: allCustomers,
                   allCars: allCars,
                   formMode: 'createNew',
                   pageTitle: 'New rent',
                   btnLabel: 'Add rent',
                   formAction: '/rent/add',
                   navLocation: 'rent',
                   validationErrors: []
               });
           });

}

exports.showRentFormEdit = (req, res, next) => {
       const rentId = req.params.rentId;
       let allCustomers, allCars, allRents;
   
       RentRepository.getRents()
           .then(rent => {
               allRents = rent;
               return CustomerRepository.getCustomers();
           })
           .then(customer => {
               allCustomers = customer;
               return CarRepository.getCars();
           })
           .then(car => {
               allCars = car;
               return RentRepository.getRentById(rentId);
           })
           .then(rent => {
                  res.render('pages/rent/rent-form-new', {
                   rent: rent,
                   allCustomers: allCustomers,
                   allCars: allCars,
                   allRents: allRents,
                   formMode: 'edit',
                   pageTitle: 'Rent edit',
                   btnLabel: 'Edit rent',
                   formAction: '/rent/edit',
                   navLocation: 'rent',
                   validationErrors: []
               });
           });
     
}

exports.showRentDetails = (req, res, next) => {
       const rentId = req.params.rentId;
       let allCustomers, allCars;
   
       CustomerRepository.getCustomers()
           .then(customer => {
               allCustomers = customer;
               return CarRepository.getCars();
           })
           .then(car => {
               allCars = car;
               return RentRepository.getRentById(rentId)
           })
           .then(rent => {
                  res.render('pages/rent/rent-form-new', {
                   rent: rent,
                   allCustomers: allCustomers,
                   allCars: allCars,
                   formMode: 'showDetails',
                   pageTitle: 'Rent details',
                   formAction: '',
                   navLocation: 'rent',
                   validationErrors: []
               });
           });
}


exports.addRent = (req, res, next) => {
       let allCustomers, allCars, error;
       const data = { ...req.body };
       RentRepository.createRent(data)
       .then(result => {
           res.redirect('/rent');
       })
       .catch(err => {
           CustomerRepository.getCustomers()
               .then(customer => {
                   allCustomers = customer;
                   return CarRepository.getCars()
               })
               .then(cars => {
                   allCars = cars
                   res.render('pages/rent/rent-form-new', {
                       rent: {},
                       allCustomers: allCustomers,
                       allCars: allCars,
                       formMode: 'createNew',
                       pageTitle: 'New rent',
                       btnLabel: 'Add rent',
                       formAction: '/rent/add',
                       navLocation: 'rent',
                       validationErrors: err.errors
                   });
               })

       });

   };
   exports.updateRent = (req, res, next) => {
       let allCustomers, allCars, error;
       const rentId = req.body._id;
       const data = { ...req.body };
   
       RentRepository.updateRent(rentId, data)
           .then(result => {
               res.redirect('/rent');
           })
   
           .then(customer => {
               allCustomers = customer;
               return CarRepository.getCars();
           })
           .then(car => {
               allCars = car;
               return RentRepository.getRentById(rentId)
           })
   
           .catch(err => {
              res.render('pages/rent/rent-form-new', {
                   rent: data,
                   allCustomers: allCustomers,
                   allCars: allCars,
                   formMode: 'edit',
                   pageTitle: 'car edit',
                   btnLabel: 'Edit car',
                   formAction: '/car/edit',
                   navLocation: 'car',
                   validationErrors: err.errors
               });
           });
             
};

exports.deleteRent = (req, res, next) => {
       const rentId = req.params.rentId;

       RentRepository.deleteRent(rentId)
           .then(() => {
               res.redirect('/rent');
           })
           .catch(err => {
              res.render('pages/rent/rent-form-new', {
                   rent: rentId,
                   pageTitle: 'rent delete',
                   formMode: 'delete',
                   btnLabel: 'Delete rent',
                   formAction: '/rent/delete',
                   navLocation: 'rent',
                   validationErrors: []
               })
           });
              
};