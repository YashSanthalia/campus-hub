import React, { useEffect } from "react";
import { Field, reduxForm } from "redux-form";
import { connect } from "react-redux";

import { Button } from "../_utility_components/Button";
import InputField from "../_utility_components/InputField";


const StudentForm = (props) => {

  const allbranch = Object.values(props.allbranch);

  const renderBranchOptions = () => {  
    return allbranch.map((branch) => {
        return <option value = {branch.name} key = {branch.id}>{branch.name}</option>
    });
  }

  return (

    <div>
      <form className="row" onSubmit = { props.handleSubmit(props.onSubmit ) }>
      <div className="col-md-6 ">
        <Field name="registrationNumber" component={InputField} label="REGISTRATION NO" />
        </div>
        <div className="col-md-6 ">
        <Field name="password" component={InputField}  type = "password" label="PASSWORD" />
        </div>
        <div className="col-md-6 ">
        <Field name="name" component={InputField} label="NAME" />
        </div>
        <div className="col-md-6 ">
        <Field name="semester" component={InputField} label="SEMESTER" />
        </div>
        <div className="col-md-12 ">
        <Field name="address" component={InputField} label="ADDRESS" />
        </div>
        <div className="col-md-6 ">
        <Field name="phoneNumber" component={InputField} label="PHONE NUMBER" />
        </div>
        <div className="col-md-6 ">
        <Field name="parentPhoneNumber" component={InputField} label="PARENT'S PHONE NUMBER" />
        </div>
        <div className="col-md-6 ">
          <label>BRANCH</label>
            <Field name="branch" component="select">
              {renderBranchOptions()}
            </Field>
        </div>
        <div className="col-md-6 ">
        <Field name="roomNo" component={InputField} label="ROOM NUMBER" />
        </div>
        <div className="col-md-6 ">
        <Field name="email" component={InputField} label="EMAIL ID" />
        </div>
        <div className="col-md-6 ">
            <label>GENDER</label>
            <label> <Field name = "gender" component = "input" type="radio" value= "Male" checked="checked" /> MALE </label>
            <label> <Field name = "gender" component = "input" type = "radio" value = "Female" /> FEMALE </label>
        </div>
        <div className="col-md-6 ">
        <Field name = "dob" component = {InputField} label = "DOB" placeholder = "yyyy-mm-dd" />
        </div>
        <div className="col-md-6 ">
        <Field name="aadharCardNo" component={InputField} label="AADHAR CARD NO" />
        </div>
        <div className="col-md-6 ">
        <Field name="blackdots" component={InputField} label="BLACK DOTS" />
        </div>
        <Button text="ADD" />
      </form>
    </div>
  );

};

const validate = (formValues) => {
  const errors = {};
  if (!formValues.registrationNumber) errors.registrationNumber = "Enter a valid registration number";
  if (!formValues.name) errors.name = "Enter a valid warden name";
  if (!formValues.email) errors.email = "Enter a valid email id";
  if (!formValues.password) errors.password = "Enter a valid password";
  if (!formValues.phoneNumber) errors.phoneNumber = "Enter a valid phoneNumber";
  if (!formValues.parentPhoneNumber) errors.parentPhoneNumber = "Enter a valid Parent's phoneNumber";
  if (!formValues.hostelName) errors.hostelName = "Select a valid hostel Id";
  if (!formValues.branch) errors.branch = "Enter a valid branch name";
  if (!formValues.semester) errors.semester = "Enter a valid semester";
  if (!formValues.address) errors.address = "Enter a valid address";
  if (!formValues.roomNo) errors.roomNo = "Enter a valid Room No";
  if (!formValues.dob) errors.dob = "Enter a valid Date of Birth";
  if (!formValues.gender) errors.gender = "Enter a valid gender";
  if (!formValues.aadharCardNo) errors.aadharCardNo = "Enter a valid Aadhar No.";
  if (!formValues.blackdots) errors.blackdots = "Enter a valid blackdots";
  return errors;
};

const mapStateToProps = (state) => {
    return { allbranch : state.branch }
}

const formWrapped = reduxForm({
  form: "STUDENT_FORM",
  validate,
})(StudentForm);

export default connect(mapStateToProps)(formWrapped);