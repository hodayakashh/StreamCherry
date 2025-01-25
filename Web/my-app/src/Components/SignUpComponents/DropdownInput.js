import React from "react";

const DropdownInput = ({ label, name, value, options, onChange }) => {
  return (
    <div className="input-group">
      <label htmlFor={name}>{label}:</label>
      <select id={name} name={name} value={value} onChange={onChange} required>
        {options.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
    </div>
  );
};

export default DropdownInput;
