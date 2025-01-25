import React from "react";

const FormInput = ({ label, type, name, value, onChange,required}) => {
  return (
    <div className="input-group">
      <label htmlFor={name}>{label}:</label>
      <input
        type={type}
        id={name}
        name={name}
        value={value}
        onChange={onChange}
        required = {required}
      />
    </div>
  );
};

export default FormInput;
