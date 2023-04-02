function validateForm()
{
    // input constants
    const firstNameInput = document.getElementById('firstname');
    const SurnameInput = document.getElementById('surname');
    const AddressInput = document.getElementById('address');
    const AgeInput = document.getElementById('age');
    const emailInput = document.getElementById('email');
    const NumberInput = document.getElementById('number');

    // error constants
    const errorFirstName = document.getElementById('errorFirstname');
    const errorSurname = document.getElementById('errorsurname');
    const errorAddress = document.getElementById('erroraddress');
    const errorAge = document.getElementById('errorage');
    const erroremail = document.getElementById('erroremail');
    const errorNumber = document.getElementById('errornumber');

    // summary constant
    const errorsSummary = document.getElementById('errorsSummary');

    // reset errors
    resetErrors([firstNameInput, SurnameInput, AddressInput, AgeInput, emailInput, NumberInput],[errorFirstName, errorSurname, errorAddress, errorAge, erroremail, errorNumber], errorsSummary);
    
    let valid = true;

    // validate firstName
    if (!checkRequired(firstNameInput.value))
    {
        valid = false;
        firstNameInput.classList.add("error-input");
        errorFirstName.innerText = "The field is required.";
    }
    else if (!checkTextLengthRange(firstNameInput.value, 2, 50))
    {
        valid = false;
        firstNameInput.classList.add("error-input");
        errorFirstName.innerText = "The field should contain from 2 to 50 characters.";
    }
    
    // validate Surname
    if (!checkRequired(SurnameInput.value))
    {
        valid = false;
        SurnameInput.classList.add("error-input");
        errorSurname.innerText = "The field is required.";
    }
    else if (!checkTextLengthRange(SurnameInput.value, 2, 50))
    {
        valid = false;
        SurnameInput.classList.add("error-input");
        errorSurname.innerText = "The field should contain from 2 to 50 characters.";
    }

    // validate Address
    if (!checkRequired(AddressInput.value))
    {
        valid = false;
        AddressInput.classList.add("error-input");
        errorAddress.innerText = "The field is required.";
    }
    else if (!checkTextLengthRange(AddressInput.value, 2, 50))
    {
        valid = false;
        AddressInput.classList.add("error-input");
        errorAddress.innerText = "The field should contain from 2 to 50 characters.";
    }

    // validate Age

    if (!checkRequired(AgeInput.value))
    {
        valid = false;
        AgeInput.classList.add("error-input");
        errorAge.innerText = "The field is required.";
    }
    else if (!checkTextLengthRange(AgeInput.value > 18))
    {
        valid = false;
        AgeInput.classList.add("error-input");
        errorAge.innerText = "The field should contain Must be over 18 years old.";
    }
    
    // gmail
    if (!checkRequired(emailInput.value)) {
        valid = false;
        emailInput.classList.add('error-input');
        erroremail.innerText = "The field is required.";
    }else if (!checkEmail(emailInput.value)) {
        valid = false;
        emailInput.classList.add('error-input');
        erroremail.innerText = "The field should contain a valid gmail address."
    }


      // validate phoneNumber
      if (!checkRequired(NumberInput.value))
      {
          valid = false;
          NumberInput.classList.add("error-input");
          errorNumber.innerText = "The field is required.";
      }
      else if (!checkNumber(NumberInput.value))
      {
          valid = false;
          NumberInput.classList.add("error-input");
          errorNumber.innerText = "The field should contain a valid Phone Number.";
      }

    // validate summary
    if (!valid){
        errorsSummary.innerText = "The form contains errors";
        errorsSummary.style.color = 'red';
    return valid;
    }

    const conf = confirm('Do you want to add Customer?');
    if(conf){
        return valid;
    }else{
        return false;
    }
}