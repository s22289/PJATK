const db = require('../../config/mysql2/db');


exports.getCars = () => {
    return db.promise().query('SELECT * FROM car')
    .then((results, fields) => {
        return results[0];
    })
    .catch((err) => {
        console.log(err);
    });
};


exports.getCarById = (carid) => {
    const query = `SELECT car._id as car_id, car.model, car.year, car.colar, car.status, rent._id as rent_id,rent.location, rent.rentdate , rent.returndate, rent.price, customer._id as customer_id, customer.firstname,customer.surname,customer.address,customer.age,customer.email,customer.number
    FROM car 
    left join rent on rent.car_id = car._id
    left join customer on rent.customer_id = customer._id
    where car._id = ?`;
    return db.promise().query(query, [carid])
    .then((results, fields) => {
        const firstRow = results[0][0];
        if (!firstRow) {
            return {};
        }

        const car = {              
            _id: parseInt(carid),
            model: firstRow.model,
            year: firstRow.year,
            colar: firstRow.colar,
            status: firstRow.status,
            rents: []
        }
        for( let i=0; i<results[0].length; i++){
            const row = results[0][i];
            if(row.rent_id) {
                    const rent = {
                        _id: row.rent_id,
                        location: row.location,
                        rentdate: row.rentdate,
                        returndate: row.returndate,
                        price: row.price,
                        customer: {
                            _id: row.customer_id,
                            firstname: row.firstname,
                            surname: row.surname,
                            address: row.address,
                            age: row.age,
                            email: row.email,
                            number: row.number
                        } 
                    };
                    car.rents.push(rent);
            }
        }
        return customer;
})
.catch(err => {
    console.log(err);
    throw err;
});
};

exports.createCar = (newCarData) => {
    const model = newCarData.model;
    const year = newCarData.year;
    const colar = newCarData.colar;
    const status = newCarData.status;
    const sql = 'INSERT into car (model, year, colar, status) VALUES (?,?,?,?)'
    return db.promise().execute(sql, [model,year, colar, status]);
};    

exports.updateCar = (carid, carData) => {
    const model = carData.model;
    const year = carData.year;
    const colar = carData.colar;
    const status = carData.status;

    const sql = `UPDATE car set model = ?, year = ?, colar = ?, status = ? where _id = ?`
    return db.promise().execute(sql, [model,year, colar, status, carid]);
};

exports.deleteCar = (carid) => {
    const sql1 = 'DELETE FROM RENT where car_id = ?'
    const sql2 = 'DELETE FROM car where _id = ?'

    return db.promise().execute(sql1, [carid])
    .then( () => {
        return db.promise().execute(sql2, [carid])
    });
};
