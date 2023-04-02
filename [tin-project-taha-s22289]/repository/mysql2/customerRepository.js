const db = require('../../config/mysql2/db');

exports.getCustomers = () => {
    return db.promise().query('SELECT * FROM customer')
    .then((results, fields) => {
        return results[0];
    })
    .catch((err) => {
        console.log(err);
        
    });
};


exports.getCustomerById = (customerid) => {
    const query = `SELECT c._id as _id, c.firstname,c.surname,c.address,c.age,c.email,c.number, rent._id as rent_id,rent.location, rent.rentdate , rent.returndate, rent.price, car._id as car_id, car.model, car.year, car.colar, car.status
    FROM customer c
    left join  rent on rent.cusomer_id = c._id
    left join  car on rent.car_id = car._id
    
    
    where c._id = ?`;

    /*
    left join tin_rent  rent on rent._id = c._id
    left join car  car on rent._id = car._id
    
    
    */
    return db.promise().query(query, [customerid])
    .then((results, fields) => {
        const firstRow = results[0][0];
        if (!firstRow) {
            return {};
        }

        const customer = {
            _id: parseInt(customerid),
            firstname: firstRow.firstname,
            surname: firstRow.surname,
            address: firstRow.address,
            age: firstRow.age,
            email: firstRow.email,
            number: firstRow.number,
            tin_rents: []
        }
        for( let i=0; i<results[0].length; i++){
            const row = results[0][i];
            if(row.rent_id) {
                    const tin_rent = {
                        _id: row.rent_id,
                        location: row.location,
                        rentdate: row.rentdate,
                        returndate: row.returndate,
                        price: row.price,
                        car: {
                            _id: row.car_id,
                            model: row.model,
                            year: row.year,
                            colar: row.colar,
                            status: row.status
                        }
                    };
                    customer.tin_rents.push(tin_rent);
            }
        }
        return customer;
})
.catch(err => {
    console.log(err);
    throw err;
});
};

exports.createCustomer = (newCustomerData) => {
    const firstname = newCustomerData.firstname;
    const surname = newCustomerData.surname;
    const address = newCustomerData.address;
    const age = newCustomerData.age;
    const email = newCustomerData.email;
    const number= newCustomerData.number;
    const sql = 'INSERT into customer (firstname, surname, address, age, email, number) VALUES (?,?,?,?,?,?)'
    return db.promise().execute(sql, [firstname,surname, address, age, email, number]);
};

exports.updateCustomer = (customerid, customerData) => {
const firstname = customerData.firstname;
    const surname = customerData.surname;
    const address = customerData.address;
    const age = customerData.age;
    const email = customerData.email;
    const number= customerData.number;
    const sql = `UPDATE customer set firstname = ?, surname = ?, address = ?, age = ?, email = ?, number = ? where _id = ?`
    return db.promise().execute(sql, [firstname,surname, address, age, email, number, customerid]);
};

exports.deleteCustomer = (customerid) => {
    const sql1 = 'DELETE FROM RENT where customer_id = ?'
    const sql2 = 'DELETE FROM customer where _id = ?'

    return db.promise().execute(sql1, [customerid])
    .then( () => {
        return db.promise().execute(sql2, [customerid])
    });
};
