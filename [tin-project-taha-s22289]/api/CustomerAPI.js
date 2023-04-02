const CustomerRepository = require('../repository/mysql2/customerRepository');

exports.getCustomers = (req, res, next) => {
    CustomerRepository.getCustomers()
    .then(customers => {
        res.status(200).json(customers);
    })
    .catch(err => {
        console.log(err);
    });
};

exports.getCustomerById = (req, res, next) => {
    const customerid = req.params.customerid;
    CustomerRepository.getCustomerById(customerid)
    .then(customer => {
        if(!customer) {
            res.status(404).json({
                message: 'Customer with id: '+customerid+' not found'
            })
        }else {
            res.status(200).json(customer);
        }
    });
};


exports.createCustomer = (req, res, next) => {
    CustomerRepository.createCustomer(req.body)
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

exports.updateCustomer = (req, res, next) => {
    const customerid = req.params.customerid;
    CustomerRepository.updateCustomer(customerid, req.body)
    .then(result => {
        res.status(200).json({message: 'Customer updated!', customer: result });
    })
    .catch(err =>{
        if (!err.statusCode) {
            err.statusCode = 500;
        }
        next(err);
    });
};

exports.deleteCustomer = (req, res, next) => {
    const customerid = req.params.customerid;
    CustomerRepository.deleteCustomer(customerid)
    .then(result =>{
        res.status(200).json({message: 'Removed Customer', customer: result});
    })
    .catch(err => {
        if(!err.statusCode) {
            err.statusCode = 500;
        }
        next(err);
    });
};