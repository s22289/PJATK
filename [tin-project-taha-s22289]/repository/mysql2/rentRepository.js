const db = require('../../config/mysql2/db');

exports.getRents = () => {
    const query = `SELECT rent._id as rent_id, rent.location, rent.rentdate, rent.returndate, rent.price, car._id as car_id, car.model, car.year, car.colar, car.status,c._id as customer_id, c.firstname,c.surname,c.address,c.age,c.email,c.number
    FROM rent rent
    left join customer c on rent.customer_id = c._id
    left join car car on rent.car_id = car._id`
return db.promise().query(query)
.then( (results, fields) => {
    const rents = [];
    for(let i =0; i<results[0].length; i++) {
        const row = results[0][i];
        const rent = {
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
            },
            customer:{
                _id: row.customer_id,
                firstname: row.firstname,
                surname: row.surname,
                address: row.address,
                age: row.age,
                email: row.email,
                number: row.number,
            }
        };
    rents.push(rent);
    }
    console.log(rents);
    return rents;
})
.catch(err => {
    console.log(err);
});
};

exports.getRentById = (rentId) => {
    const query = `SELECT rent._id as rent_id, rent.location, rent.rentdate, rent.returndate, rent.price, car._id as car_id, car.model, car.year, car.colar, car.status,c._id as customer_id, c.firstname,c.surname,c.address,c.age,c.email,c.number
    FROM rent rent
    left join customer c on rent.customer_id = c._id
    left join car car on rent.car_id = car._id
    where rent._id = ?`
    return db.promise().query(query, [rentId])
    .then( (results,fields) => {
        const row = results[0][0];
        if(!row) {
            return {};
        }
        const rent = {
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
            },
            customer:{
                _id: row.customer_id,
                firstname: row.firstname,
                surname: row.surname,
                address: row.address,
                age: row.age,
                email: row.email,
                number: row.number,
            }
        };
        console.log(rent);
        return rent;
    })
    .catch(err => {
        console.log(err);
        throw err;
    });
};

exports.createRent = (data) => {
    console.log('createRent');
    console.log(data);
    const sql = 'INSERT into rent (customer_id, car_id, location,rentdate,returndate,price) VALUES (?,?,?,?,?,?)';
    return db.promise().execute(sql, [data.customerid, data.carid, data.location, data.rentdate, data.returndate, data.price]);
};

exports.updateRent = (rentId, data) => {
   const sql = `UPDATE rent set customer_id = ?, car_id = ?, location = ?, rentdate = ?, returndate = ?, price = ? where _id = ?`;
   return db.promise().execute(sql, [data.customerid, data.carid, data.location, data.rentdate, data.returndate, data.price,rentId]);
};

exports.deleteRent = (rentId) => {
const sql = 'DELETE FROM rent where _id = ?'
return db.promise().execute(sql, [rentId]);
};

exports.deleteManyRents = (rentIds) => {
    const sql = 'DELETE FROM rent where _id IN (?)'
    return db.promise().execute(sql, [rentIds]);
};