import React from 'react';
import ReactDOM from 'react-dom';
import FormComponent from './FormComponent';

it('Should mount', () => {
  const div = document.createElement('div');
  ReactDOM.render(<FormComponent />, div);
  ReactDOM.unmountComponentAtNode(div);
});