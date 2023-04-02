function validateForm()
{
    // input constants
    const CustomerInput = document.getElementById('customer');
    const CarInput = document.getElementById('car');
    const LocationInput = document.getElementById('location');
    const Rent_dateInput = document.getElementById('rentdate');
    const Return_dateInput = document.getElementById('returndate');
    const Car_PriceInput = document.getElementById('price');

    // error constants
    const errorCustomer = document.getElementById('errorcustomer');
    const errorCar = document.getElementById('errorcar');
    const errorLocation = document.getElementById('errorlocation');
    const errorRent_date = document.getElementById('errorrentdate');
    const errorReturn_date = document.getElementById('errorreturndate');
    const errorCar_Price = document.getElementById('errorprice');

    // summary constant
    const errorsSummary = document.getElementById('errorsSummary');

    // reset errors
    resetErrors([CustomerInput, CarInput, LocationInput, Rent_dateInput, Return_dateInput, Car_PriceInput],[errorCustomer, errorCar, errorLocation, errorRent_date, errorReturn_date, errorCar_Price], errorsSummary);

    let valid = true;
    
        // current date
        let nowDate = new Date(),
            month = '' + (nowDate.getMonth() + 1),
            day = '' + nowDate.getDate(),
            year = nowDate.getFullYear();
        
        if (month.length < 2)
            month = '0' + month;
        
        if (day.length < 2)
            day = '0' + day;
        const nowString = [day, month, year].join('-');
    
        // validate customer
    if (!checkRequired(CustomerInput.value) || CustomerInput.value == "---Choose a customer---")
    {
        valid = false;
        CustomerInput.classList.add("error-input");
        errorCustomer.innerText = "The field is required";
    }
    
        // validate car
    if (!checkRequired(CarInput.value) || CarInput.value == "---Choose a Car---")
    {
        valid = false;
        CarInput.classList.add("error-input");
        errorCar.innerText = "The field is required";
    }

            // validate Car Location
        if (!checkRequired(LocationInput.value))
        {
            valid = false;
            LocationInput.classList.add("error-input");
            errorLocation.innerText = "The field is required";
        }
        else if (!checkTextLengthRange(LocationInput.value, 2, 50))
        {
            valid = false;
            LocationInput.classList.add("error-input");
            errorLocation.innerText = "The field should contain from 2 to 50 characters";
        }
    

        // validate rent date
    if (!checkRequired(Rent_dateInput.value))
    {
        valid = false;
        Rent_dateInput.classList.add("error-input");
        errorRent_date.innerText = "The field is required";
    }
    else if (!checkDate(Rent_dateInput.value))
    {
        valid = false;
        Rent_dateInput.classList.add("error-input");
        errorRent_date.innerText = "The field should contain a date in the format mm-dd-yyyy (e.g. 01-01-2000)";
    }
    else if (checkDateIfAfter(Rent_dateInput.value, nowString))
    {
        valid = false;
        Rent_dateInput.classList.add("error-input");
        errorRent_date.innerText = "The date cannot be from the future";
    }
    
    // validate return date
    if (Return_dateInput.value != null && !checkDate(Return_dateInput.value))
    {
        valid = false;
        Return_dateInput.classList.add("error-input");
        errorReturn_date.innerText = "The field should contain a date in the format mm-dd-yyyy (e.g. 01-01-2000)";
    }
     //if the rental date and the return date are correct, we check the order of the dates
    else if (checkRequired(Return_dateInput.value) && checkDate(Return_dateInput.value)
        && !checkDateIfAfter(Return_dateInput.value, Rent_dateInput.value))
    {
        valid = false;
        Return_dateInput.classList.add("error-input");
        errorReturn_date.innerText = "The return date should be later than the rental date";
    }


        // validate Car Price
        if (!checkRequired(Car_PriceInput.value))
        {
            valid = false;
            Car_PriceInput.classList.add("error-input");
            errorCar_Price.innerText = "The field is required";
        }
        else if (!checkTextLengthRange(AgeInput.value > 500))
        {
            valid = false;
            Car_PriceInput.classList.add("error-input");
            errorCar_Price.innerText = "The field should contain Must be over 500";
        }
    
    
    
        // validate summary
        if (!valid){
            errorsSummary.innerText = "The form contains errors";
        
        return valid;
        }
    
}
    