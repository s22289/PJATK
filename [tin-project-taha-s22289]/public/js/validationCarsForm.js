function validateForm()
{
    // input constants
    const Car_ModelInput = document.getElementById('model');
    const Car_YearInput = document.getElementById('year');
    const Car_ColarInput = document.getElementById('colar');
    const Car_StatusInput = document.getElementById('status');

  
    // error constants
    const errorCar_Model = document.getElementById('errormodel');
    const errorCar_Year = document.getElementById('erroryear');
    const errorCar_Colar = document.getElementById('errorcolar');
    const errorCar_Status = document.getElementById('errorstatus');
 
    // summary constant
    const errorsSummary = document.getElementById('errorsSummary')
    
    // reset errors
    resetErrors([Car_ModelInput, Car_YearInput, Car_ColarInput, Car_StatusInput], [errorCar_Model, errorCar_Year, errorCar_Colar, errorCar_Status], errorsSummary);

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

    // validate Car Model
    if (!checkRequired(Car_ModelInput.value))
    {
        valid = false;
        Car_ModelInput.classList.add("error-input");
        errorCar_Model.innerText = "The field is required";
    }
    else if (!checkTextLengthRange(Car_ModelInput.value, 2, 50))
    {
        valid = false;
        Car_ModelInput.classList.add("error-input");
        errorCar_Model.innerText = "The field should contain from 2 to 50 characters";
    }


    
    // validate Car Year
    if (!checkRequired(Car_YearInput.value))
    {
        valid = false;
        Car_YearInput.classList.add("error-input");
        errorCar_Year.innerText = "The field is required";
    }
    else if (!checkDate(Car_YearInput.value))
    {
        valid = false;
        Car_YearInput.classList.add("error-input");
        errorCar_Year.innerText = "The field should contain a date in the format mm-dd-yyyy (e.g. 01-01-2000)";
    }


   
    // validate Car Colar
    if (!checkRequired(Car_ColarInput.value))
    {
        valid = false;
        Car_ColarInput.classList.add("error-input");
        errorCar_Colar.innerText = "The field is required";
    }
    else if (!checkTextLengthRange(Car_ColarInput.value, 2, 50))
    {
        valid = false;
        Car_ColarInput.classList.add("error-input");
        errorCar_Colar.innerText = "The field should contain from 2 to 50 characters";
    }

    // validate Car Status
    if (!checkRequired(Car_StatusInput.value))
    {
        valid = false;
        Car_StatusInput.classList.add("error-input");
        errorCar_Status.innerText = "The field is required";
    }
    else if (!checkTextLengthRange(Car_StatusInput.value, 2, 50))
    {
        valid = false;
        Car_StatusInput.classList.add("error-input");
        errorCar_Status.innerText = "The field should contain from 2 to 50 characters";
    }



    // validate summary
    if (!valid){
        errorsSummary.innerText = "The form contains errors";
    
    return valid;
    }

}
