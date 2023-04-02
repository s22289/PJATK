const CustomerRepository = require('../repository/mysql2/customerRepository');

exports.showCustomerList = (req, res, next) => {
    CustomerRepository.getCustomers()
     .then(customers => {
        console.log(customers)
         res.render('pages/customer/customer-list', {
            customers: customers,
            navLocation: 'customer'
     });
});       
        
};

exports.showAddCustomersForm = (req, res, next)=> {
    res.render('pages/customer/customer-form-new', {
        customer: {},
        pageTitle: 'New Customer',
        formMode: 'createNew',
        btnLabel: 'add customer',
        formAction:'/customer/add',
        navLocation: 'customer' ,
        validationErrors: []
    });
    
};

exports.showEditCustomers = (req, res, next)=> {
    const customerid = req.params.customerid;
    CustomerRepository.getCustomerById(customerid)
    .then(customer => {
        res.render('pages/customer/customer-form-new', {
          
            customer: customer,
            formMode: 'edit',
            pageTitle: 'Edit customer',
            btnLabel: 'Edit customer',
            formAction: '/customer/edit',
            navLocation: 'customer' ,
            validationErrors: []
    });
});
};

exports.showCustomersDetails = (req, res, next)=> {
    const customerid = req.params.customerid;
    CustomerRepository.getCustomerById(customerid)
    .then(customer => {
        res.render('pages/customer/customer-form-new', {
            customer: customer,
            fortMode: 'showDetails',
            pageTitle: 'Customer details',
            formAction: '',
            navLocation: 'customer'
        });

    });
};

exports.addCustomer = (req, res, next) => {
    const customerData = { ...req.body };
    console.log(customerData)
    CustomerRepository.createCustomer(customerData)
        .then( result => {
            res.redirect('/customer');
        })
        .catch(err => {
            res.render('pages/customer/customer-form-new', {
                customer: customerData,
                pageTitle: 'New customer',
                formMode: 'createNew',
                btnLabel: 'Add customer',
                formAction: '/customer/add',
                navLocation: 'customer',
                validationErrors: err.errors
            })
        });
};


exports.updateCustomer = (req, res, next) => {
    const customerid = req.body._id;
    const customerData = { ...req.body };
    let error;
    
    CustomerRepository.updateCustomer(customerid, customerData)
        .then(result => {
            res.redirect('/customer');
        })
        .catch(err => {
            res.render('pages/customer/customer-form-new', {
                customer: customerData,
                formMode: 'edit',
                pageTitle: 'customer edit',
                btnLabel: 'Edit customer',
                formAction: '/customer/edit',
                navLocation: 'customer',
                validationErrors: err.errors
            })
        });
};





exports.deleteCustomer = (req,res,next) => {

    const customerid = req.params.customerid;
    const customerData = { ...req.body}

    CustomerRepository.deleteCustomer(customerid)
    .then(() => {
        res.redirect('/customer');
    })

    .catch(err => {
        res.render('pages/customer/customer-form-new',{
            customer:customerData,
            formMode:'delete',
            pageTitle:'delete customer',
            btnLabel:'delete customer',
            formAction:'/customer/delete',
            navLocation:'customer',
            validationErrors: []
        })
    });
};